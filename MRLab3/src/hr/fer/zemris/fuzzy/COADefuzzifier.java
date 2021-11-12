package hr.fer.zemris.fuzzy;

public class COADefuzzifier implements IDefuzzifier {

	@Override
	public double defuzzify(IFuzzySet set) {
		IDomain domain = set.getDomain();
		double upper = 0;
		double lower = 0;
		for(DomainElement el : domain) {
			upper += el.getComponentValue(0) * set.getValueAt(el); 
			lower += set.getValueAt(el);
		}
		return (double) (upper/lower);
	}
}
