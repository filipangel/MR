package hr.fer.zemris.fuzzy;

public class KormiloFuzzySystemMin implements FuzzySystem {
	private IDefuzzifier def;	
	
	public KormiloFuzzySystemMin(IDefuzzifier def) {
		this.def = def;
	}

	@Override
	public int zakljuci() {
		// TODO Auto-generated method stub
		return 0;
	}

}
