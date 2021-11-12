package hr.fer.zemris.fuzzy;

import java.util.ArrayList;

public class IfThenRule {
	private ArrayList<IFuzzySet> antecedent;
	private IFuzzySet consequent;
	
	public IfThenRule(ArrayList<IFuzzySet> antecedent, IFuzzySet consequent) {
		this.antecedent = antecedent;
		this.consequent = consequent;
	}	
}
