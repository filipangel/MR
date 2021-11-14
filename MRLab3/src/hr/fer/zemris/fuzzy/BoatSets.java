package hr.fer.zemris.fuzzy;

public class BoatSets {
	// normalna udaljenost od obale je izmedu 70 i 100 piksela
	public static final IFuzzySet TAMAN = new CalculatedFuzzySet(BoatDomains.distance, 
			StandardFuzzySets.gammaFunction(70, 100));
	
	// blizu obale znaci da je udaljenost izmedu 40 i 70 piksela
	public static final IFuzzySet BLIZU_OBALE = new CalculatedFuzzySet(BoatDomains.distance, 
			StandardFuzzySets.lFunction(40, 70));
	
	// jako blizu je izmedu 25 i 40 piksela
	public static final IFuzzySet JAKO_BLIZU_OBALE = new CalculatedFuzzySet(BoatDomains.distance, 
					StandardFuzzySets.lFunction(15, 40));
	
	// brod se krece presporo ako je brzina izmedu 40 i 70
	public static final IFuzzySet SPORO_KRETANJE = new CalculatedFuzzySet(BoatDomains.speed, 
			StandardFuzzySets.lFunction(0, 70));
	
	// brod se krece brzo ako je brzina izmedu 70 i 100
	public static final IFuzzySet BRZO_KRETANJE = new CalculatedFuzzySet(BoatDomains.speed, 
			StandardFuzzySets.gammaFunction(70, 100));
	
	// skretanje blago lijevo
	public static final IFuzzySet BLAGO_LIJEVO = new CalculatedFuzzySet(BoatDomains.angle, 
			StandardFuzzySets.gammaFunction(120, 150));
	
	// skretanje blago desno
	public static final IFuzzySet BLAGO_DESNO = new CalculatedFuzzySet(BoatDomains.angle, 
			StandardFuzzySets.lFunction(30, 60));
	
	// skretanje blago lijevo
	public static final IFuzzySet OŠTRO_LIJEVO = new CalculatedFuzzySet(BoatDomains.angle, 
			StandardFuzzySets.gammaFunction(150, 180));
		
	// skretanje blago desno
	public static final IFuzzySet OŠTRO_DESNO = new CalculatedFuzzySet(BoatDomains.angle, 
			StandardFuzzySets.lFunction(0, 30));
	
	// ubrzavanje
	public static final IFuzzySet UBRZANJE = new CalculatedFuzzySet(BoatDomains.acceleration,
			StandardFuzzySets.gammaFunction(70, 100));
	
	// usporavanje
	public static final IFuzzySet USPORAVANJE = new CalculatedFuzzySet(BoatDomains.acceleration,
			StandardFuzzySets.lFunction(0, 30));
}
