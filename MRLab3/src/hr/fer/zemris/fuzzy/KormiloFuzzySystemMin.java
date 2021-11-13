package hr.fer.zemris.fuzzy;

public class KormiloFuzzySystemMin implements IFuzzySystem {
	private IDefuzzifier def;	
	
	public KormiloFuzzySystemMin(IDefuzzifier def) {
		this.def = def;
	}

	@Override
	public int zakljuci(int L, int D, int LK, int DK, int V, int S) {
		return 0;
	}

}
