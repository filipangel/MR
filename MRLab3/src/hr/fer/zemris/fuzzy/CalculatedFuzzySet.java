package hr.fer.zemris.fuzzy;

public class CalculatedFuzzySet implements IFuzzySet {

	private IDomain domain;
	private IIntUnaryFunction func;	
	
	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction func) {
		this.domain = domain;
		this.func = func;
	}

	public IDomain getDomain() {
		return domain;
	}

	public double getValueAt(DomainElement element) {
		return func.valueAt(domain.indexOfElement(element));
	}

	@Override
	public IFuzzySet cut(double mi) {
		return new IFuzzySet() {

			@Override
			public IDomain getDomain() {
				return domain;
			}

			@Override
			public double getValueAt(DomainElement element) {
				return Math.min(mi, func.valueAt(domain.indexOfElement(element)));
			}

			@Override
			public IFuzzySet cut(double mi) {
				return null;
			}
			
		};
	}
}
