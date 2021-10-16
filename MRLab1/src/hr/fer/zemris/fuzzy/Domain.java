package hr.fer.zemris.fuzzy;

public abstract class Domain implements IDomain {

	public static IDomain intRange(int first, int last){
		return new SimpleDomain(first, last);
	}
	
	public static Domain combine(IDomain first, IDomain second){
		SimpleDomain[] domains = new SimpleDomain[first.getNumberOfComponents() + second.getNumberOfComponents()];
		
		int index = 0;
		for (int i = 0; i < first.getNumberOfComponents(); i++){
			domains[index++] = (SimpleDomain) first.getComponent(i);
		}
		
		for (int i = 0; i < second.getNumberOfComponents(); i++){
			domains[index++] = (SimpleDomain) second.getComponent(i);
		}
		
		return new CompositeDomain(domains);
	}

	public int indexOfElement(DomainElement element){
		int index = 0;
		for(DomainElement element2 : this){
			if(element2.equals(element)){
				return index;
			}
			index++;
		}
		return -1;
	}
	
	public DomainElement elementForIndex(int index) {
		if(index >= getCardinality()) {
			throw new IndexOutOfBoundsException();
		}
		
		int current = 0;
		for (DomainElement element : this) {
            if (current == index) {
                return element;
            }
            current++;
        }
		
		return null;
	}
}
