package hr.fer.zemris.fuzzy;

public class AkcelFuzzySystemMin implements FuzzySystem {
	private IDefuzzifier def;	
	
	public AkcelFuzzySystemMin(IDefuzzifier def) {
		this.def = def;
	}

	@Override
	public int zakljuci(int L, int D, int LK, int DK, int V, int S) {
		return 0;
	}
}
