package hr.fer.zemris.fuzzy;

import java.util.ArrayList;

public class AkcelFuzzySystemMin implements IFuzzySystem {
	private IDefuzzifier def;
	private ArrayList<IfThenRule> akcelRules = new ArrayList<IfThenRule>();
	
	public AkcelFuzzySystemMin(IDefuzzifier def) {
		this.def = def;
		akcelRules.add(new IfThenRule(new IFuzzySet[]{null, null, null, null, BoatSets.SPORO_KRETANJE, null},
				BoatSets.UBRZANJE));
		akcelRules.add(new IfThenRule(new IFuzzySet[] {null, null, null, null, BoatSets.BRZO_KRETANJE, null},
				BoatSets.USPORAVANJE));
	}

	@Override
	public int zakljuci(int L, int D, int LK, int DK, int V, int S) {				
		return (int) def.defuzzify(conclusionSet(L,D,LK,DK,V,S));
	}
	
	public IFuzzySet conclusionSet(int L, int D, int LK, int DK, int V, int S) {
		int[] values = {L,D,LK,DK,V,S};
		ArrayList<IFuzzySet> consequents = new ArrayList<IFuzzySet>();
		for(IfThenRule rule : akcelRules) {
			consequents.add(rule.apply(values, Operations.zadehAnd()));
		}
		IFuzzySet conclusion = consequents.get(0);
		for(IFuzzySet set : consequents) {
			conclusion = Operations.binaryOperation(conclusion, set, Operations.zadehOr());
		}
		return conclusion;
	}
	
	public IfThenRule getRule(int index) {
		return akcelRules.get(index);
	}
}
