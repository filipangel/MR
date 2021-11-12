package hr.fer.zemris.fuzzy;

public class AkcelFuzzySystemMin implements FuzzySystem {
	private IDefuzzifier def;	
	
	public AkcelFuzzySystemMin(IDefuzzifier def) {
		this.def = def;
	}
	
	@Override
	public int zakljuci() {
		// TODO Auto-generated method stub
		return 0;
	}

}
