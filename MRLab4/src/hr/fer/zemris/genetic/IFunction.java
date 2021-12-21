package hr.fer.zemris.genetic;

public interface IFunction {
	public double at(DoubleVector sol, double x, double y);
	public double MSE(DoubleVector sol);	
}
