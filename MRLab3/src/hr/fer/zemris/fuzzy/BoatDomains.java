package hr.fer.zemris.fuzzy;

public class BoatDomains {
	public static IDomain distance = Domain.intRange(0, 1301);
	public static IDomain speed = Domain.intRange(0, 101);
	public static IDomain angle = Domain.intRange(-90, 91);
	public static IDomain acceleration = Domain.intRange(-50, 51);
}
