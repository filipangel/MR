package hr.fer.zemris.genetic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ZadanaFunkcija implements IFunction {
	private double[][] readings = new double[250][3];
	private int lineCount;
	
	public ZadanaFunkcija(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		lineCount = 0;
		
		while(br.ready()) {
			String line = br.readLine();
			
			String[] parts = line.split("\t");
			double x = Double.parseDouble(parts[0]);
			double y = Double.parseDouble(parts[1]);
			double f = Double.parseDouble(parts[2]);
			
			readings[lineCount++] = new double[] {x, y, f};
		}
		br.close();
	}
	
	@Override
	public double at(DoubleVector sol, double x, double y) {
		double[] betas = sol.getParams();
		double term1 = Math.sin(betas[0] + betas[1] * x);
		double term2 = betas[2] * Math.cos(x * (betas[3] + y));
		double term3 = (1 + Math.exp(Math.pow(x - betas[4], 2)));
		return term1 + (term2 / term3);
	}
	
	public double MSE(DoubleVector sol) {
		double error = 0;
		
		for(int i = 0; i < lineCount; i++) {
			double x = readings[i][0];
			double y = readings[i][1];
			double f = readings[i][2];
			error += Math.pow((f - at(sol, x, y)), 2);
		}
		double output = error / lineCount;
		return output;		
	}
	
}
