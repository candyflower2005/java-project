package symulacja;
import java.util.*;

public class Spotkania {
	
	private HashMap<Integer, ArrayList<Spotkanie>> planSpotkań;
	
	public void dodajSpotkania(HashMap<Integer, ArrayList<Spotkanie>> noweSpotkania){
		for(Map.Entry<Integer, ArrayList<Spotkanie>> para: noweSpotkania.entrySet()){
			int dzień = para.getKey();
			if(!planSpotkań.containsKey(dzień)){
				planSpotkań.put(dzień, new ArrayList<Spotkanie>());
			}
			planSpotkań.get(dzień).addAll(para.getValue());
		}
	}
	public ArrayList<Spotkanie> dzisiejszeSpotkania(int dzień){
		if(!planSpotkań.containsKey(dzień)) return new ArrayList<Spotkanie>();
		return planSpotkań.get(dzień);
	}
	
	public Spotkania(){
		planSpotkań = new HashMap<Integer, ArrayList<Spotkanie>>();
	}
}
