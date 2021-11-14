package hr.fer.zemris.fuzzy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class checkRule {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		IDefuzzifier def = new COADefuzzifier();
		IFuzzySystem fsAkcel = new AkcelFuzzySystemMin(def);
		IFuzzySystem fsKormilo = new KormiloFuzzySystemMin(def);
		
		System.out.println("Unesite koji sustav zelite ispitati. 0 za kormilo, 1 za akceleraciju.");
		String line = br.readLine();
		int sysToUse = Integer.parseInt(line);
		
		System.out.println("Unesite vrijednosti:");
		System.out.println("L D LK DK V S");
		
		line = br.readLine();
		if(line != null) {
			if(line.equals("KRAJ")) {
				return;
			} else {
				String[] parts = line.split(" ");
					
				int L = Integer.parseInt(parts[0]);
				int D = Integer.parseInt(parts[1]);
				int LK = Integer.parseInt(parts[2]);
				int DK = Integer.parseInt(parts[3]);
				int V = Integer.parseInt(parts[4]);
				int S = Integer.parseInt(parts[5]);
				
				int[] values = {L,D,LK,DK,V,S};
				
				System.out.println("Ispitati koje pravilo?");
				line = br.readLine();
				int ruleIndex = Integer.parseInt(line);
				
				IfThenRule rule = null;
				if(sysToUse == 1) {
					rule = fsAkcel.getRule(ruleIndex);				
				} else if (sysToUse == 0) {
					rule = fsKormilo.getRule(ruleIndex);
				} else {
					throw new IllegalArgumentException();
				}
				
				IFuzzySet conclusion = rule.apply(values, Operations.zadehAnd());
				
				Debug.print(conclusion, "Zakljucak za pravilo br. " + ruleIndex + " i upisane podatke:");
				
				System.out.println("U dekodiranom obliku: " + (int) def.defuzzify(conclusion));
			}
		}			
	}
}

