package hr.fer.zemris.genetic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		int dataset;
		String alg;
		int genLimit;
		boolean elitism;
		int velPop;
		double mutProb;
		String recombType;
		
		ArrayList<String> parameters = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("config.txt"));
		while(br.ready()) {
			String line = br.readLine();
			String[] parts = line.split(" ");
			parameters.add(parts[1]);
		}
		br.close();
		dataset = Integer.parseInt(parameters.get(0));
		alg = parameters.get(1);
		genLimit = Integer.parseInt(parameters.get(2));
		elitism = Boolean.parseBoolean(parameters.get(3));
		velPop = Integer.parseInt(parameters.get(4));
		mutProb = Double.parseDouble(parameters.get(5));
		recombType = parameters.get(6);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(dataset, alg, genLimit, elitism, velPop, mutProb, recombType);
		ga.solve();
	}

}
