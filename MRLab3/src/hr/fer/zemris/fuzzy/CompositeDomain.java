package hr.fer.zemris.fuzzy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CompositeDomain extends Domain {
	
	private SimpleDomain[] domains;
	
	public CompositeDomain(SimpleDomain[] domains){
		this.domains = domains;
	}
	
	public int getCardinality(){
		int cardinality = 1;
		
		for(SimpleDomain domain : domains){
			cardinality *= domain.getCardinality();
		}
		return cardinality;
	}
	
	public IDomain getComponent(int component){
		return domains[component];
	}
	
	public int getNumberOfComponents(){
		return domains.length;
	}

	public Iterator<DomainElement> iterator(){
		return new Iterator<DomainElement>(){
			private List<Iterator<DomainElement>> iters;
			private int[] values;
			private int counter;
			
			{
				iters = new ArrayList<Iterator<DomainElement>>();
				for(int i = 0; i < domains.length; i++){
					iters.add(domains[i].iterator());
				}
				
				values = new int[domains.length];
				for(int i = 0; i < domains.length; i++){
					DomainElement element = iters.get(i).next();
					values[i] = element.getComponentValue(0);
				}
			}
			
			public boolean hasNext(){
				return counter < getCardinality();
			}
			
			public DomainElement next(){
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				
				DomainElement element = new DomainElement(values);
				counter++;
				
				int i = iters.size() - 1;
				
				while(i > -1){
					if(!iters.get(i).hasNext()){
						iters.set(i, domains[i].iterator());
						values[i] = iters.get(i).next().getComponentValue(0);
						i--;
					} else {
						values[i] = iters.get(i).next().getComponentValue(0);
						break;
					}
				}
				
				return element;
			}
		};
	}
}
