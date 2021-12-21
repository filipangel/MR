package hr.fer.zemris.genetic;

import java.util.Random;

public class DoubleVector {
	private double[] betas;
	private int size;
	
	public DoubleVector(double[] betas) {
		this.betas = betas;
		this.size = betas.length;
	}
	
	public DoubleVector(Random rand, int size, double lowerBound, double upperBound) {
		this.betas = new double[size];
		this.size = size;
		
		for(int i = 0; i < size; i++) {
			betas[i] = rand.nextDouble(lowerBound, upperBound);
		}
	}
	
	public double[] getParams() {
		return betas;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i = 0; i < size; i++) {
			sb.append(String.format("%.3f", betas[i]) + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(")");
		return sb.toString();
	}
	
	public DoubleVector mutate(double chance, double lowerBound, double upperBound) {
		Random rand = new Random();
		double[] newBetas = new double[5];
		for(int i = 0; i < betas.length; i++) {
			if(rand.nextDouble() <= chance) {
				newBetas[i] = rand.nextDouble(lowerBound, upperBound);
			} else {
				newBetas[i] = betas[i];
			}
		}
		return new DoubleVector(newBetas);
	}
	
	
}
