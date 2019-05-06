package symulacja;

public class Spotkanie {
	private Agent v;
	private Agent u;
	
	public void zrealizujSpotkanie(Graf g){
		if(!g.dajListęWierzchołków().contains(v) || !g.dajListęWierzchołków().contains(u)) return;
		v.losujZarażenie(u);
		u.losujZarażenie(v);
	}
	
	public Agent dajPierwszego(){
		return v;
	}
	public Agent dajDrugiego(){
		return u;
	}
	
	Spotkanie(Agent v, Agent u){
		this.v = v;
		this.u = u;
	}
}
