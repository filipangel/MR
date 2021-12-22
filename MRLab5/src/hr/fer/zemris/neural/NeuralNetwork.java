package hr.fer.zemris.neural;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
	private List<HiddenLayer> layers;
	
	public NeuralNetwork(String architecture) {
		layers = new ArrayList<HiddenLayer>();
		
		String[] parts = architecture.split("x");
		
		int prevNeurons = Integer.parseInt(parts[0]);
		int inputSize = prevNeurons;
		
		// pravimo se kao da input layer ne postoji, samo pamtimo velicinu inputa
		
		for(int i = 1; i < parts.length; i++) {
			int currentNeurons = Integer.parseInt(parts[i]);
			HiddenLayer hiddenLayer = new HiddenLayer(currentNeurons, prevNeurons);
			layers.add(hiddenLayer);
			prevNeurons = currentNeurons;
		}
	}
	
	public void train(Matrica X, Matrica t, int type, double eta) {
		switch(type) {
			case 1:	// backpropagation
				Matrica[] y = forwardProp(X);
				y[0].print();
				System.out.println();
				y[1].print();
				System.out.println();
				y[2].print();
				System.out.println();
				
				
				
				break;
			case 2: // stochastic backprop
				
				break;
			case 3: // mini-batch backprop
				
				break;
		}
	}
	
	public Matrica[] forwardProp(Matrica X) {
		Matrica[] y = new Matrica[layers.size() + 1];
		y[0] = X; // y0 = ulaz
		
		Matrica input = layers.get(0).output(X);
		y[1] = input; // y1 = izlaz prvog sloja
		
		for(int i = 1; i < layers.size(); i++) {
			input = layers.get(i).output2(input);
			y[i + 1] = input; // y[i+1] = izlaz drugog sloja pa nadalje
		}
		
		return y;
	}
}
