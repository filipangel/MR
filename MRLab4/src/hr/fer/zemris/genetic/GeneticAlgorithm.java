package hr.fer.zemris.genetic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	private String dataset;
	private String alg;
	private int genLimit;
	private boolean elitism;
	private int velPop;
	private double mutProb;
	private String recombType;
	private int mortality;
	private IFunction f;
	
	public GeneticAlgorithm(int setNum, String alg, int genLimit, boolean elitism, int velPop, double mutProb, String recombType, int mortality) throws IOException {
		this.dataset = "zad4-dataset" + setNum + ".txt";
		this.alg = alg;
		this.genLimit = genLimit;
		this.elitism = elitism;
		this.velPop = velPop;
		this.mutProb = mutProb;
		this.recombType = recombType;
		this.mortality = mortality;
		
		f = new ZadanaFunkcija(dataset);
		
		System.out.print("Pokrenut genetski algoritam sa sljedećim postavkama:\nDataset: " + this.dataset);
		System.out.print("\nAlgoritam: ");
		if(this.alg.equals("gen")) {
			System.out.print("Kanonski generacijski genetski algoritam");
			System.out.print("\nElitizam: " + elitism);
			System.out.print("\nBroj generacija: " + genLimit);
		}
		else if(this.alg.equals("elim")) {
			System.out.print("Kanonski eliminacijski genetski algoritam");
			System.out.print("\nMortalitet: " + (double) mortality/velPop * 100 + "%");
		}
		System.out.print("\nVeličina populacije: " + velPop);
		System.out.print("\nVjerojatnost mutacije: " + mutProb);
		System.out.print("\nVrsta križanja: ");
		switch(recombType) {
			case "DR":
				System.out.print("Diskretna rekombinacija");
				break;
			case "SAR":
				System.out.print("Jednostavna aritmetička rekombinacija");
				break;
			case "SingleAR":
				System.out.print("Jednostruka aritmetička rekombinacija");
				break;
			case "WAR":
				System.out.print("Kompletna aritmetička rekombinacija");
				break;
		}
		System.out.println();
		System.out.println();
	}
	
	public void solve() throws InterruptedException {
		if(alg.equals("gen")) generational();
		else if(alg.equals("elim")) eliminational();
	}
		
	public void generational() throws InterruptedException {		
		DoubleVector[] population = initPop();
		DoubleVector bestSolution = findBest(population);
		
		for(int gen = 1; gen < genLimit; gen++) {
			DoubleVector[] newPop = new DoubleVector[velPop];		
			
			if(elitism) {
				newPop[0] = findBest(population); 
				
				for(int i = 1; i < velPop; i += 2) {
					DoubleVector[] parents = proportionalSimpleChoose(population, 2);
					DoubleVector[] children = combine(parents[0], parents[1]);
					
					if(velPop % 2 == 0 && i == velPop - 1) {
						newPop[i] = children[0].mutate(mutProb, -4, 4);
					} else {
						newPop[i] = children[0].mutate(mutProb, -4, 4);
						newPop[i+1] = children[1].mutate(mutProb, -4, 4);
					}
				}
			} else {
				for(int i = 0; i < velPop; i += 2) {
					DoubleVector[] parents = proportionalSimpleChoose(population, 2);
					DoubleVector[] children = combine(parents[0], parents[1]);
					
					if(velPop % 2 == 1 && i == velPop - 1) {
						newPop[i] = children[0].mutate(mutProb, -4, 4);
					} else {
						newPop[i] = children[0].mutate(mutProb, -4, 4);
						newPop[i+1] = children[1].mutate(mutProb, -4, 4);
					}
				}
			}
			population = Arrays.copyOf(newPop, newPop.length);
			DoubleVector currentBest = findBest(population);
			if(f.MSE(currentBest) < f.MSE(bestSolution)) {
				bestSolution = currentBest;
				System.out.println("Generacija #" + gen + ". Pronađeno bolje rješenje: " + bestSolution + " s MSE = " + f.MSE(bestSolution));
			}
		}
		
		System.out.println("Najbolje pronađeno rješenje: " + bestSolution + " s MSE = " + f.MSE(bestSolution));
	}

	public void eliminational() {
		DoubleVector[] startPop = initPop();
		List<DoubleVector> population = new ArrayList<DoubleVector>();
		population.addAll(Arrays.asList(startPop));
		DoubleVector bestSolution = findBest(population);
		
		for(int gen = 1; gen < genLimit; gen++) {					
			for(int i = 0; i < mortality; i++) {
				List<DoubleVector> random = findThreeRandom(population);
				DoubleVector worst = findWorst(random);
				population.remove(worst);
				random.remove(worst);
				population.add(combine(random.get(0), random.get(1))[0].mutate(mutProb, -4, 4));
			}			
			DoubleVector currentBest = findBest(population);			
			if(f.MSE(currentBest) < f.MSE(bestSolution)) {
				bestSolution = currentBest;
				System.out.println("Generacija #" + gen + ". Pronađeno bolje rješenje: " + bestSolution + " s MSE = " + f.MSE(bestSolution));
			}
		}
		System.out.println("Najbolje pronađeno rješenje: " + bestSolution + " s MSE = " + f.MSE(bestSolution));
	}

	private List<DoubleVector> findThreeRandom(List<DoubleVector> population) {
		List<DoubleVector> threeRandom = new ArrayList<DoubleVector>();
		List<DoubleVector> popCopy = new ArrayList<DoubleVector>();
		for(DoubleVector x : population) {
			popCopy.add(x);
		}		
		Random rand = new Random();
		for(int i = 0; i < 3; i++) {
			int selected = rand.nextInt(0, popCopy.size());
			threeRandom.add(popCopy.get(selected));
			popCopy.remove(selected);
		}		
		return threeRandom;
	}

	private DoubleVector[] combine(DoubleVector doubleVector, DoubleVector doubleVector2) {
		DoubleVector[] children = new DoubleVector[2];
		switch(recombType) {
			case "DR":
				children = ReproductionOperators.DiscreteRecombination(doubleVector, doubleVector2);
				break;
			case "SAR":
				children = ReproductionOperators.SimpleArithmeticRecombination(doubleVector, doubleVector2);
				break;
			case "SingleAR":
				children = ReproductionOperators.SingleArithmeticRecombination(doubleVector, doubleVector2);
				break;
			case "WAR":
				children = ReproductionOperators.WholeArithmeticRecombination(doubleVector, doubleVector2);
				break;
		}
		return children;
	}
	
	public void printPop(DoubleVector[] pop) {
		System.out.print("Populacija:\n");
		for(DoubleVector x : pop) {
			if(x != null) {
				System.out.println(x + " MSE: " + f.MSE(x));
			}
		}
		System.out.println();
	}
	
	public DoubleVector[] initPop() {
		DoubleVector[] pop = new DoubleVector[velPop];
		Random rand = new Random();
		for(int i = 0; i < velPop; i++) {
			pop[i] = new DoubleVector(rand, 5, -4, 4);
		}		
		return pop;
	}
	
	private DoubleVector findBest(DoubleVector[] pop) {
		double min = f.MSE(pop[0]);
		DoubleVector bestSol = pop[0];
		
		for(int i = 1; i < velPop; i++) {
			double current = f.MSE(pop[i]);
			if(current < min) {
				min = current;
				bestSol = pop[i];
			}
		}
		return bestSol;
	}
	
	private DoubleVector findBest(List<DoubleVector> pop) {
		double min = f.MSE(pop.get(0));
		DoubleVector bestSol = pop.get(0);
		
		for(int i = 1; i < velPop; i++) {
			double current = f.MSE(pop.get(i));
			if(current < min) {
				min = current;
				bestSol = pop.get(i);
			}
		}
		return bestSol;
	}

	private int findWorst(DoubleVector[] population) {
		int worstIndex = 0;
		double worstValue = f.MSE(population[0]);
		for(int i = 1; i < population.length; i++) {
			double currentValue = f.MSE(population[i]);
			if(currentValue > worstValue) {
				worstValue = currentValue;
				worstIndex = i;
			}
		}		
		return worstIndex;
	}
	
	private DoubleVector findWorst(List<DoubleVector> population) {
		DoubleVector worst = population.get(0);
		for(DoubleVector vec : population) {
			if(f.MSE(vec) > f.MSE(worst)) {
				worst = vec;
			}
		}
		return worst;
	}
	
	private DoubleVector[] proportionalSimpleChoose(DoubleVector[] population, int num) {
		Random rand = new Random();
		DoubleVector[] parents = new DoubleVector[num];
		
		double[] values = new double[population.length];
		int worst = findWorst(population);
		double worstValue = f.MSE(population[worst]);
		
		for(int i = 0; i < population.length; i++) {
			values[i] = worstValue - values[i];
		}
		
		double sum = 0;
		for(int i = 0; i < population.length; i++) {
			sum += values[i];
		}
		
		for(int i = 0; i < num; i++) {
			double r = rand.nextDouble();
			double limit = r * sum;
			
			int chosen = 0;
			double upperLimit = values[chosen];
			
			while(limit > upperLimit && chosen < population.length - 1) {
				chosen++;
				upperLimit += values[chosen];
			}
			parents[i] = population[chosen];
		}
		return parents;
	}
}
