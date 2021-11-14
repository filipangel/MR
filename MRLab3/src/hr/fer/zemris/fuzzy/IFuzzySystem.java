package hr.fer.zemris.fuzzy;

public interface IFuzzySystem {
	public int zakljuci(int L, int D, int LK, int DK, int V, int S);
	public IfThenRule getRule(int ruleIndex);
	public IFuzzySet conclusionSet(int L, int D, int LK, int DK, int V, int S);
}
