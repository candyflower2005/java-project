package symulacja;
import java.util.*;

public class Graf {
	
	private TreeSet<Agent> listaWierzchołków;
	
	public void usuńWierzchołek(Agent v){
		if(listaWierzchołków.contains(v)){
			listaWierzchołków.remove(v);
		}
	}
	public TreeSet<Agent> dajListęWierzchołków(){
		return listaWierzchołków;
	}
	
	public Graf(){
		listaWierzchołków = new TreeSet<Agent>();
		int n = Parametry.dajLiczbęAgentów();
		for(int i = 1; i <= n; i++){
			if(MaszynaLosujaca.losujZdarzenie(Parametry.dajPrawdTowarzyski())){
				listaWierzchołków.add(new AgentTowarzyski(i));
			}
			else{
				listaWierzchołków.add(new AgentZwykly(i));
			}
		}
		
		int krawędzie = 0;
		double ile = Parametry.dajŚrZnajomych() * n / 2;
		while(krawędzie < n * (n - 1) / 2 && krawędzie < ile){
			Agent a = MaszynaLosujaca.losujAgenta(listaWierzchołków);
			Agent b = MaszynaLosujaca.losujAgenta(listaWierzchołków);
			if(Integer.valueOf(a.dajNumer()) != Integer.valueOf(b.dajNumer()) && !a.sąsiedzi.contains(b)){
				a.sąsiedzi.add(b);
				b.sąsiedzi.add(a);
				krawędzie++;
			}
		}
		
		MaszynaLosujaca.losujAgenta(listaWierzchołków).status = "zarażony";
	}	
}
