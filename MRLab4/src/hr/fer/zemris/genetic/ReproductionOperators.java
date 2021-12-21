package hr.fer.zemris.genetic;

import java.util.Random;

public class ReproductionOperators {
	public static DoubleVector[] DiscreteRecombination(DoubleVector first, DoubleVector second) {
		int length = first.getParams().length;
		
		double[] params1 = first.getParams();
		double[] params2 = second.getParams();
		double[] combinedParams1 = new double[length];
		double[] combinedParams2 = new double[length];
		
		Random rand = new Random();
		
		for(int i = 0; i < length; i++) {
			if(rand.nextDouble() < 0.5) {
				combinedParams1[i] = params1[i];
				combinedParams2[i] = params2[i];
			} else {
				combinedParams1[i] = params2[i];
				combinedParams2[i] = params1[i];
			}
		}
		
		DoubleVector child1 = new DoubleVector(combinedParams1);
		DoubleVector child2 = new DoubleVector(combinedParams2);		
		return new DoubleVector[] {child1, child2};		
	}
	
	public static DoubleVector[] SimpleArithmeticRecombination(DoubleVector first, DoubleVector second) {
		Random rand = new Random();
		
		int length = first.getSize();
		int cutoff = rand.nextInt(1, length);
		
		double[] params1 = first.getParams();
		double[] params2 = second.getParams();
		double[] combinedParams1 = new double[length];
		double[] combinedParams2 = new double[length];
		
		for(int i = 0; i < length; i++) {
			if(i < cutoff) {
				combinedParams1[i] = params1[i];
				combinedParams2[i] = params2[i];
			} else {
				combinedParams1[i] = (params1[i] + params2[i]) / 2;
				combinedParams2[i] = (params1[i] + params2[i]) / 2;
			}
		}
		
		DoubleVector child1 = new DoubleVector(combinedParams1);
		DoubleVector child2 = new DoubleVector(combinedParams2);		
		return new DoubleVector[] {child1, child2};	
	}
	
	public static DoubleVector[] SingleArithmeticRecombination(DoubleVector first, DoubleVector second) {
		Random rand = new Random();
		
		int length = first.getSize();
		int swapPoint = rand.nextInt(0, length);
		
		double[] params1 = first.getParams();
		double[] params2 = second.getParams();
		double[] combinedParams1 = new double[length];
		double[] combinedParams2 = new double[length];
		
		for(int i = 0; i < length; i++) {
			if(i == swapPoint) {
				combinedParams1[i] = (params1[i] + params2[i]) / 2;
				combinedParams2[i] = (params1[i] + params2[i]) / 2;
			} else {
				combinedParams1[i] = params1[i];
				combinedParams2[i] = params2[i];
			}
		}
		
		DoubleVector child1 = new DoubleVector(combinedParams1);
		DoubleVector child2 = new DoubleVector(combinedParams2);		
		return new DoubleVector[] {child1, child2};	
	}
	
	public static DoubleVector[] WholeArithmeticRecombination(DoubleVector first, DoubleVector second) {		
		int length = first.getSize();
		
		double[] params1 = first.getParams();
		double[] params2 = second.getParams();
		double[] combinedParams1 = new double[length];
		double[] combinedParams2 = new double[length];
		
		for(int i = 0; i < length; i++) {
			combinedParams1[i] = (params1[i] + params2[i]) / 2;
			combinedParams2[i] = (params1[i] + params2[i]) / 2;
		}
		
		DoubleVector child1 = new DoubleVector(combinedParams1);
		DoubleVector child2 = new DoubleVector(combinedParams2);		
		return new DoubleVector[] {child1, child2};	
	}
	
}
