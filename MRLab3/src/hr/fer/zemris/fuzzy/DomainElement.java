package hr.fer.zemris.fuzzy;

import java.util.Arrays;

public class DomainElement {

	private int[] values;
	
	public DomainElement(int[] values) {
		this.values = Arrays.copyOf(values, values.length);
	}

	public int getNumberOfComponents(){
		return values.length;
	}
	
	public int getComponentValue(int component){
		return values[component];
	}
	
	public static DomainElement of(int ... values){
		return new DomainElement(values);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(values.length == 1){
			sb.append(values[0]);
		} else if(values.length > 1){
			sb.append("(");
			sb.append(values[0]);
			for(int i = 1; i < values.length; i++){
				sb.append("," + values[1]);
			}
			sb.append(")");
		} else {
			sb.append("Error");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainElement other = (DomainElement) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}
	
	
}
