import java.util.*;
import symulacja.*;

public class Symulacja {
	
	private static void przeprowadźSymulację(){
		new Parametry();
		new MaszynaLosujaca();
		Graf graf = new Graf();
		Raport wynik = new Raport(Parametry.dajPlikZRaportem());
		wynik.dodajParametry();
		wynik.nowaLinia();
		wynik.dodajAgentów(graf);
		wynik.nowaLinia();
		wynik.dodajGraf(graf);
		Spotkania planSpotkań = new Spotkania();
		wynik.dodajWiersz("\n# liczność w kolejnych dniach");
		for(int dzień = 1; dzień <= Parametry.dajLiczbęDni(); dzień++ ){
			ArrayList<Agent> doUsunięcia = new ArrayList<Agent>();
			
			for(Agent v: graf.dajListęWierzchołków()){
				if(v.losujŚmierć()) doUsunięcia.add(v);
				else v.losujWyzdrowienie();
			}
			for(Agent v: doUsunięcia) graf.usuńWierzchołek(v);
				
			if(Integer.valueOf(dzień) != Integer.valueOf(Parametry.dajLiczbęDni())){
				for(Agent v: graf.dajListęWierzchołków()){
					planSpotkań.dodajSpotkania(v.wylosujSpotkania(dzień + 1));
				}
			}
			ArrayList<Spotkanie> lista = planSpotkań.dzisiejszeSpotkania(dzień);
			for(Spotkanie s: lista){
				s.zrealizujSpotkanie(graf);
			}
			wynik.dodajLiczność(graf);
		}
		wynik.zamknij();
	}
	
	public static void main(String[] args) {
		przeprowadźSymulację();
	}

}
