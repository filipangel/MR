package hr.fer.zemris.fuzzy;

public class MutableFuzzySet implements IFuzzySet {
	
	private double[] memberships;
	private IDomain domain;

	public MutableFuzzySet(IDomain domain) {
		this.domain = domain;
		memberships = new double[domain.getCardinality()];
	}

	public IDomain getDomain() {
		return domain;
	}

	public double getValueAt(DomainElement element) {
		return memberships[domain.indexOfElement(element)];
	}
	
	public MutableFuzzySet set(DomainElement el, double value) {
		memberships[domain.indexOfElement(el)] = value;
		return this;
	}

}
