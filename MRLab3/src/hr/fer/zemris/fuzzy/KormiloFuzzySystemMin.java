package hr.fer.zemris.fuzzy;

import java.util.ArrayList;

public class KormiloFuzzySystemMin implements IFuzzySystem {
	private IDefuzzifier def;
	private ArrayList<IfThenRule> kormiloRules = new ArrayList<IfThenRule>();
	
	public KormiloFuzzySystemMin(IDefuzzifier def) {
		this.def = def;
		kormiloRules.add(new IfThenRule(new IFuzzySet[]{BoatSets.BLIZU_OBALE, null, BoatSets.BLIZU_OBALE, null, null, null},
				BoatSets.BLAGO_DESNO));
		kormiloRules.add(new IfThenRule(new IFuzzySet[] {null, BoatSets.BLIZU_OBALE, null, BoatSets.BLIZU_OBALE, null, null},
				BoatSets.BLAGO_LIJEVO));
	}

	@Override
	public int zakljuci(int L, int D, int LK, int DK, int V, int S) {
		return (int) def.defuzzify(conclusionSet(L,D,LK,DK,V,S));
	}
	
	public IFuzzySet conclusionSet(int L, int D, int LK, int DK, int V, int S) {
		int[] values = {L,D,LK,DK,V,S};
		ArrayList<IFuzzySet> consequents = new ArrayList<IFuzzySet>();
		for(IfThenRule rule : kormiloRules) {
			consequents.add(rule.apply(values, Operations.zadehAnd()));
		}
		IFuzzySet conclusion = consequents.get(0);
		for(IFuzzySet set : consequents) {
			conclusion = Operations.binaryOperation(conclusion, set, Operations.zadehOr());
		}
		return conclusion;
	}

	@Override
	public IfThenRule getRule(int ruleIndex) {
		return kormiloRules.get(ruleIndex);
	}

}
