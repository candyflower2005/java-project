package symulacja;
import java.util.*;

public class AgentTowarzyski extends Agent{
	
	protected boolean organizacjaSpotkania(){
		double p = Parametry.dajPrawdSpotkania();
		return MaszynaLosujaca.losujZdarzenie(p);
	}
	protected TreeSet<Agent> dajZnajomych(){
		TreeSet<Agent> znajomi = new TreeSet<Agent>(sąsiedzi);
		if(!status.equals("zarażony")){
			for(Agent v: sąsiedzi){
				znajomi.addAll(v.dajSąsiadów());
			}
		}
		return znajomi;
	}
	
	public AgentTowarzyski(int numer){
		super(numer);
	}
}
