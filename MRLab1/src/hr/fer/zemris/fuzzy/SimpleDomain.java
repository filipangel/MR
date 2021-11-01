package hr.fer.zemris.fuzzy;

import java.util.Iterator;

public class SimpleDomain extends Domain {
	
	private int first;
	private int last;
	
	public SimpleDomain(int first, int last) {		
		this.first = first;
		this.last = last;
	}

	public int getCardinality(){
		return last - first;
	}
	
	public IDomain getComponent(int index){
		return this;
	}
	
	public int getNumberOfComponents(){
		return 1;
	}
	
	public Iterator<DomainElement> iterator(){
		return new Iterator<DomainElement>(){
			private int value = first;
			
			public boolean hasNext(){
				if(value >= last) return false;
				return true;
			}
			
			public DomainElement next(){
				if(hasNext()){
					int[] nextValue = {value};
					DomainElement nextElement = new DomainElement(nextValue);
					value++;
					return nextElement;
				}
				else {
					return null;
				}
			}
		};		
	}
	
	public int getFirst(){
		return first;
	}
	
	public int getLast(){
		return last;
	}
}
