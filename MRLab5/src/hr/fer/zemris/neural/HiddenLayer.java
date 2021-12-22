package hr.fer.zemris.neural;

public class HiddenLayer {
	
	private Matrica weights;
	private int neuronNumber;
	private int inputNumber;
	
	public HiddenLayer(int neuronNumber, int inputNumber) {
		weights = new Matrica(neuronNumber, inputNumber + 1);
		
		for(int i = 0; i < neuronNumber; i++) {
			for(int j = 0; j < inputNumber + 1; j++) {
				weights.set(i,j, (2 * Math.random()) - 1);
			}
		}
	}
	
	public void adjustWeights(Matrica adjustments) {
		weights = weights.add(adjustments);
	}
	
	public Matrica output(Matrica input) {
		input = input.insertColumnOfOnes();		
		return weights.multiply(input.transpose()).applySigmoid();
	}
	
	public Matrica output2(Matrica input) {
		input = input.insertRowOfOnes();
		return weights.multiply(input).applySigmoid();
	}
	
	public int getRows() {
		return neuronNumber;
	}
	
	public int getColumns() {
		return inputNumber;
	}
	
}
