package symulacja;
import java.util.*;

public class AgentZwykly extends Agent{
	
	protected boolean organizacjaSpotkania(){
		double p = Parametry.dajPrawdSpotkania();
		if(status.equals("zarażony")) p /= 2;
		return MaszynaLosujaca.losujZdarzenie(p);
	}
	protected TreeSet<Agent> dajZnajomych(){
		return dajSąsiadów();
	}
	
	public AgentZwykly(int numer){
		super(numer);
	}
}
