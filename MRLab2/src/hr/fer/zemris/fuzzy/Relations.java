package hr.fer.zemris.fuzzy;

import java.util.ArrayList;
import java.util.Collections;

public class Relations {
	public static boolean isSymmetric(IFuzzySet relation) {
		if(isUtimesURelation(relation)) {
			IDomain domain = relation.getDomain();
			for(DomainElement e : domain) {
				if(relation.getValueAt(DomainElement.of(e.getComponentValue(1), e.getComponentValue(0))) != relation.getValueAt(e)) {
					return false;
				}
			}
		}

		return true;
	}
	
	public static boolean isReflexive(IFuzzySet relation) {		
		if(isUtimesURelation(relation)) {
			IDomain domain = relation.getDomain();
			for(DomainElement e : domain) {
				if(e.getComponentValue(0) == e.getComponentValue(1)) {
					if(relation.getValueAt(e) != 1) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isMaxMinTransitive(IFuzzySet relation) {
		IDomain domain = relation.getDomain();
		if(isUtimesURelation(relation)) {
			for(DomainElement e : domain) {
				double mu = relation.getValueAt(e);
				int lower = relation.getDomain().elementForIndex(0).getComponentValue(0);
				int upper = lower + relation.getDomain().getComponent(0).getCardinality();
				ArrayList<Double> min = new ArrayList<Double>();
				for(int i = lower; i < upper; i++) {
					int[] values = {e.getComponentValue(0), i};
					DomainElement xy = new DomainElement(values);
					int[] values2 = {i, e.getComponentValue(1)};
					DomainElement yz = new DomainElement(values2);
					
					if(relation.getValueAt(xy) > relation.getValueAt(yz)) {
						min.add(relation.getValueAt(yz));
					} else {
						min.add(relation.getValueAt(xy));
					}
				}
				Collections.sort(min);
				if(relation.getValueAt(e) < min.get(min.size() - 1)) return false;
			}
		}
		
		return true;
	}
	
	public static boolean isUtimesURelation(IFuzzySet relation) {
		if(relation.getDomain().getNumberOfComponents() == 2) {
			if(relation.getDomain().getComponent(0).equals(relation.getDomain().getComponent(1))) {
				return true;
			}
		}
		
		return false;
	}
	
	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet r1, IFuzzySet r2) {
		if(r1.getDomain().getNumberOfComponents() != 2 && r2.getDomain().getNumberOfComponents() != 2) {
			System.out.println("Given relations are not binary");
			throw new IllegalArgumentException();
		}
		if(r1.getDomain().getComponent(1).getCardinality() != r2.getDomain().getComponent(0).getCardinality()) {
			System.out.println("Given relations do not have the same domain Y");
			throw new IllegalArgumentException();
		}
		IDomain newDomain = Domain.combine(r1.getDomain().getComponent(0), r2.getDomain().getComponent(1));
		IFuzzySet newSet = new MutableFuzzySet(newDomain);
		
		for(DomainElement e : newDomain) {
			int yLower = r1.getDomain().elementForIndex(0).getComponentValue(1);
			int yUpper = yLower + r1.getDomain().getComponent(1).getCardinality();
			ArrayList<Double> min = new ArrayList<Double>();
			for(int i = yLower; i < yUpper; i++) {
				int[] values = {e.getComponentValue(0), i};
				DomainElement xy = new DomainElement(values);
				int[] values2 = {i, e.getComponentValue(1)};
				DomainElement yz = new DomainElement(values2);
				
				if(r1.getValueAt(xy) < r2.getValueAt(yz)) min.add(r1.getValueAt(xy));
				else min.add(r2.getValueAt(yz));
			}
			Collections.sort(min);
			double newMu = min.get(min.size() - 1);			
			((MutableFuzzySet) newSet).set(e, newMu);
		}
		
		return newSet;
	}

	public static boolean isFuzzyEquivalence(IFuzzySet r2) {
		if(Relations.isMaxMinTransitive(r2) && Relations.isReflexive(r2) && Relations.isSymmetric(r2)) return true;
		return false;
	}
}
