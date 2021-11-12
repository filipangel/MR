package hr.fer.zemris.fuzzy;

public class Test {

	public static void main(String[] args) {
		IDomain d = Domain.intRange(-5, 10);
		IFuzzySet set = new CalculatedFuzzySet(d, StandardFuzzySets.lambdaFunction(
					d.indexOfElement(DomainElement.of(0)),
					d.indexOfElement(DomainElement.of(4)),
					d.indexOfElement(DomainElement.of(8))
					));
		IDefuzzifier def = new COADefuzzifier();
		double value = def.defuzzify(set);
		System.out.println((int) value);
	}
}
