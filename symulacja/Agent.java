package symulacja;
import java.util.*;

public abstract class Agent implements Comparable<Agent>{
	
	private int numer;
	protected TreeSet<Agent> sąsiedzi;
	protected String status;
	
	protected abstract boolean organizacjaSpotkania();
	protected abstract TreeSet<Agent> dajZnajomych();
	
	private void usuńAgenta(){
		for(Agent v: sąsiedzi){
			v.dajZnajomych().remove(this);
		}
	}
	public HashMap<Integer, ArrayList<Spotkanie>> wylosujSpotkania(int dzień){
		HashMap<Integer,  ArrayList<Spotkanie>> planSpotkań = new HashMap<Integer, ArrayList<Spotkanie>>();
		TreeSet<Agent> znajomi = dajZnajomych();
		while(znajomi.size() > 0 && organizacjaSpotkania()){
			Agent v = MaszynaLosujaca.losujAgenta(znajomi);
			int d = MaszynaLosujaca.losujDzień(dzień, Parametry.dajLiczbęDni());
			if(!planSpotkań.containsKey(d)) planSpotkań.put(d, new ArrayList<Spotkanie>());
			planSpotkań.get(d).add(new Spotkanie(this, v));
		}
		return planSpotkań;
	}
	public boolean losujŚmierć(){
		if(status.equals("zarażony")){
			if(MaszynaLosujaca.losujZdarzenie(Parametry.dajŚmiertelność())){
				usuńAgenta();
				return true;
			}
		}
		return false;
	}
	public void losujWyzdrowienie(){
		if(status.equals("zarażony")){
			if(MaszynaLosujaca.losujZdarzenie(Parametry.dajPrawdWyzdrowienia())){
				status = "odporny";
			}
		}
	}
	public void losujZarażenie(Agent v){
		if(status.equals("zdrowy") && v.dajStatus().equals("zarażony")){
			if(MaszynaLosujaca.losujZdarzenie(Parametry.dajPrawdZarażenia())){
				status = "zarażony";
			}
		}
	}
	
	public TreeSet<Agent> dajSąsiadów(){
		return sąsiedzi;
	}
	public int dajNumer(){
		return numer;
	}
	public String dajStatus(){
		return status;
	}
	
	public int compareTo(Agent v){
		return numer - v.dajNumer();
	}
	
	public Agent(int numer){
		this.numer = numer;
		sąsiedzi = new TreeSet<Agent>();
		status = "zdrowy";
	}
	
}
