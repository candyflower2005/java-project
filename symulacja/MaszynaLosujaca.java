package symulacja;
import java.util.*;

public class MaszynaLosujaca {
	
	private static Random random;
	
	public static boolean losujZdarzenie(double p){
		if(random.nextDouble() > p){
			return false;
		}
		return true;
	}
	public static Agent losujAgenta(TreeSet<Agent> agenci){
		int s = agenci.size();
		Iterator<Agent> it = agenci.iterator();
		int ile = random.nextInt(s);
		while(ile != 0){
			it.next();
			ile--;
		}
		return it.next();
	}
	public static int losujDzień(int pocz, int kon){
		return random.nextInt(kon - pocz + 1) + pocz;
	}
	
	public MaszynaLosujaca(){
		random = new Random(Parametry.dajSeed());
	}
	
}
