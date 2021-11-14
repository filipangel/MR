package hr.fer.zemris.fuzzy;

public class IfThenRule {
	private IFuzzySet[] antecedent;
	private IFuzzySet consequent;
	
	public IfThenRule(IFuzzySet[] antecedent, IFuzzySet consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;		
	}
	
	public IFuzzySet apply(int[] values, IBinaryFunction tNorm) {
		double mi = 1;
		for(int i = 0; i < antecedent.length; i++) {
			if(antecedent[i] != null) {
				mi = tNorm.valueAt(mi, antecedent[i].getValueAt(DomainElement.of((int) values[i])));
				// ovo je za produkt
				// mi *= antecedent[i].getValueAt(DomainElement.of((int) values[i]));
			}
		}
		return consequent.cut(mi);
	}
}
