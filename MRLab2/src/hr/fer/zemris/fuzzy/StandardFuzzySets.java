package hr.fer.zemris.fuzzy;

public class StandardFuzzySets {
	public static IIntUnaryFunction lFunction(final double a, final double b) {		
		return new IIntUnaryFunction() {

			public double valueAt(int index) {
				double x = index;
				if(x < a) {
					return 1;
				}
				else if(x >= a && x < b) {
					return (b - x)/(b - a);
				}
				else if(x >= b){
					return 0;
				}
				return 0;
			}			
		};		
	}
	
	public static IIntUnaryFunction gammaFunction(final double a, final double b) {
		return new IIntUnaryFunction() {

			public double valueAt(int index) {
				double x = index;
				if(x < a) {
					return 0;
				}
				else if(x >= a && x < b) {
					return (x - a)/(b - a);
				}
				else if(x >= b){
					return 1;
				}
				return 0;
			}			
		};
	}
	
	public static IIntUnaryFunction lambdaFunction(final double a, final double b, final double c) {
		return new IIntUnaryFunction() {

			public double valueAt(int index) {
				double x = index;
				if(x < a) {
					return 0;
				} else if(x >= a && x < b) {
					return (x-a)/(b-a);
				} else if(x >= b && x < c) {
					return (c-x)/(c-b);
				} else if(x >= c) {
					return 0;
				}				
				return 0;
			}			
		};
	}
}
