package symulacja;
import java.io.*;

public class Raport {
	
	private PrintWriter raport;
	
	public void dodajWiersz(String s){
		raport.println(s);
	}
	public void nowaLinia(){
		raport.println();
	}
	
	public void zamknij(){
		raport.close();
	}
	
	public void dodajParametry(){
		dodajWiersz("# twoje wyniki powinny zawierać te komentarze");
		dodajWiersz("seed=" + Parametry.dajSeed());
		dodajWiersz("liczbaAgentów=" + Parametry.dajLiczbęAgentów());
		dodajWiersz("prawdTowarzyski=" + Parametry.dajPrawdTowarzyski());
		dodajWiersz("prawdSpotkania=" + Parametry.dajPrawdSpotkania());
		dodajWiersz("prawdZarażenia=" + Parametry.dajPrawdZarażenia());
		dodajWiersz("prawdWyzdrowienia=" + Parametry.dajPrawdWyzdrowienia());
		dodajWiersz("śmiertelność=" + Parametry.dajŚmiertelność());
		dodajWiersz("liczbaDni=" + Parametry.dajLiczbęDni());
		dodajWiersz("śrZnajomych=" + Parametry.dajŚrZnajomych());
	}
	public void dodajAgentów(Graf g){
		dodajWiersz("# agenci jako: id typ lub id* typ dla chorego");
		for(Agent v: g.dajListęWierzchołków()){
			String s = Integer.toString(v.dajNumer());
			if(v.dajStatus().equals("zarażony")) s += "*";
			s += " ";
			if(v.getClass().isInstance(AgentZwykly.class)) s += "zwykły";
			else s += "towarzyski";
			dodajWiersz(s);
		}
	}
	public void dodajLiczność(Graf g){
		int zdrowi = 0, chorzy = 0, uodp = 0;
		for(Agent v: g.dajListęWierzchołków()){
			String status = v.dajStatus();
			if(status.equals("zdrowy")) zdrowi++;
			if(status.equals("zarażony")) chorzy++;
			if(status.equals("odporny")) uodp++;
		}
		dodajWiersz(zdrowi + " " + chorzy + " " + uodp);
	}
	public void dodajGraf(Graf g){
		dodajWiersz("# graf");
		for(Agent v: g.dajListęWierzchołków()){
			String s = v.dajNumer() + " ";
			for(Agent u: v.dajSąsiadów()){
				s += u.dajNumer() + " ";
			}
			dodajWiersz(s);
		}
	}
	
	public Raport(String ścieżka){
		try{
			this.raport = new PrintWriter(ścieżka, "UTF-8");
		}
		catch(IOException e){
			System.out.println("Problem z utworzeniem raportu.");
			System.exit(1);
		}
	}
}
