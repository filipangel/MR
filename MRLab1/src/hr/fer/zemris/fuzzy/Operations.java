package hr.fer.zemris.fuzzy;

public class Operations {
	public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction func) {
		IFuzzySet newSet = new MutableFuzzySet(set.getDomain());
		for(DomainElement el : set.getDomain()) {
			((MutableFuzzySet) newSet).set(el, func.valueAt(set.getValueAt(el)));
		}
		return newSet;
	}
	
	public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction func) {
		if(!set1.getDomain().equals(set2.getDomain())) {
			System.out.println("Sets do not have the same domains!");
			return null;
		}
		
		IFuzzySet newSet = new MutableFuzzySet(set1.getDomain());
		for(DomainElement el : set1.getDomain()) {
			((MutableFuzzySet) newSet).set(el, func.valueAt(set1.getValueAt(el), set2.getValueAt(el)));
		}
		return newSet;
	}
	
	public static IUnaryFunction zadehNot() {
		return new IUnaryFunction() {
			public double valueAt(double x) {				
				return 1 - x;
			}			
		};
	}
	
	public static IBinaryFunction zadehAnd() {
		return new IBinaryFunction() {
			public double valueAt(double x, double y) {
				if(x == y) return x;
				else if(x < y) return x;
				else return y;
			}			
		};
	}
	
	public static IBinaryFunction zadehOr() {
		return new IBinaryFunction() {
			public double valueAt(double x, double y) {
				if(x == y) return x;
				else if (x < y) return y;
				else return x;
			}			
		};
	}
	
	public static IBinaryFunction hamacherTNorm(final double v) {
		return new IBinaryFunction() {
			public double valueAt(double x, double y) {
				return (x*y)/(v + (1 - v)*(x + y - x * y));
			}			
		};
	}
	
	public static IBinaryFunction hamacherSNorm(final double v) {
		return new IBinaryFunction() {
			public double valueAt(double x, double y) {
				return (x + y - (2 - v)*x*y)/(1 - (1 - v) * x * y);
			}			
		};
	}
}
