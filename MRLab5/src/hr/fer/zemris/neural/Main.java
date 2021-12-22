package hr.fer.zemris.neural;

public class Main {

	public static void main(String[] args) {		
		NeuralNetwork testNet = new NeuralNetwork("1x6x1");
		
		Matrica X = new Matrica(new double[][] {
			{-1},
//			{-0.8},
//			{-0.6},
//			{-0.4},
//			{-0.2},
//			{0},
//			{0.2},
//			{0.4},
//			{0.6},
//			{0.8},
//			{1}
		});
		Matrica y = new Matrica(new double[][] {
			{1},
			{0.64},
			{0.36},
			{0.16},
			{0.04},
			{0},
			{0.04},
			{0.16},
			{0.36},
			{0.64},
			{1}
		});
		
		testNet.train(X, y, 1, 0.5);
		
//		testNet.predict(X);
	}

}
