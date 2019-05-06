package symulacja;
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.channels.*;

public class Parametry {
	
	private Properties p;
	private static long seed = -1;
	private static int liczbaAgentów = -1;
	private static double prawdTowarzyski = -1;
	private static double prawdSpotkania = -1;
	private static double prawdZarażenia = -1;
	private static double prawdWyzdrowienia = -1;
	private static double śmiertelność = -1;
	private static int liczbaDni = -1;
	private static int śrZnajomych = -1;
	private static String plikZRaportem = null;
	
	private double czytajDouble(String klucz){
		String s = p.getProperty(klucz);
		if(s == null) return -1;
		try{
			double d =  Double.parseDouble(s);
			if(0. > d || d >= 1.){
				System.out.println("Niedozwolona wartość " + s + " dla klucza " + klucz);
				System.exit(1);
			}
			return d;
		}
		catch(NumberFormatException e){
			System.out.println("Niedozwolona wartość " + s + " dla klucza " + klucz);
			System.exit(1);
		}
		return -1;
	}
	private long czytajLong(String klucz, long min, long max){
		String s = p.getProperty(klucz);
		if(s == null) return -1;
		try{
			long n =  Integer.parseInt(s);
			if(min > n || n > max){
				System.out.println("Niedozwolona wartość " + s + " dla klucza " + klucz);
				System.exit(1);
			}
			return n;
		}
		catch(NumberFormatException e){
			System.out.println("Niedozwolona wartość " + s + " dla klucza " + klucz);
			System.exit(1);
		}
		return -1;
	}
	private String czytajString(String klucz){
		String s = p.getProperty(klucz);
		return s;
	}
	
	private double przypiszDouble(double par, String klucz){
		double s = czytajDouble(klucz);
		if(s == -1) return par;
		return s;
	}
	private long przypiszLong(long par, String klucz, long min, long max){
		long s = czytajLong(klucz, min, max);
		if(s == -1) return par;
		return s;
	}
	private String przypiszString(String par, String klucz){
		String s = czytajString(klucz);
		if(s == null) return par;
		return s;
	}
	private void zapiszParametry(){
		seed = przypiszLong(seed, "seed", Long.MIN_VALUE, Long.MAX_VALUE);
		liczbaAgentów = (int)przypiszLong(liczbaAgentów, "liczbaAgentów", 1, 1000000);
		prawdTowarzyski = przypiszDouble(prawdTowarzyski, "prawdTowarzyski");
		prawdSpotkania = przypiszDouble(prawdSpotkania, "prawdSpotkania");
		prawdZarażenia = przypiszDouble(prawdZarażenia, "prawdZarażenia");
		prawdWyzdrowienia = przypiszDouble(prawdWyzdrowienia, "prawdWyzdrowienia");
		śmiertelność = przypiszDouble(śmiertelność, "śmiertelność");
		liczbaDni = (int)przypiszLong(liczbaDni, "liczbaDni", 1, 1000);
		śrZnajomych = (int)przypiszLong(śrZnajomych, "śrZnajomych", 0, liczbaAgentów - 1);
		plikZRaportem = przypiszString(plikZRaportem, "plikZRaportem");
	}
	
	private void brakWartości(String klucz){
		System.out.println("Brak wartości dla klucza " + klucz);
		System.exit(1);
	}
	private void sprawdźParametry(){
		if(seed == -1) brakWartości("seed");
		if(liczbaAgentów == -1) brakWartości("liczbaAgentów");
		if(prawdTowarzyski == -1) brakWartości("prawdTowarzyski");
		if(prawdSpotkania == -1) brakWartości("prawdSpotkania");
		if(prawdZarażenia == -1) brakWartości("prawdZarażenia");
		if(prawdWyzdrowienia == -1) brakWartości("prawdWyzdrowienia");
		if(śmiertelność== -1) brakWartości("śmiertelność");
		if(liczbaDni == -1) brakWartości("liczbaDni");
		if(śrZnajomych == -1) brakWartości("śrZnajomych");
		if(plikZRaportem == null) brakWartości("plikZRaportem");
		
	}
	
	public static long dajSeed(){
		return seed;
	}
	public static int dajLiczbęAgentów(){
		return liczbaAgentów;
	}
	public static double dajPrawdTowarzyski(){
		return prawdTowarzyski;
	}
	public static double dajPrawdSpotkania(){
		return prawdSpotkania;
	}
	public static double dajPrawdZarażenia(){
		return prawdZarażenia;
	}
	public static double dajPrawdWyzdrowienia(){
		return prawdWyzdrowienia;
	}
	public static double dajŚmiertelność(){
		return śmiertelność;
	}
	public static int dajLiczbęDni(){
		return liczbaDni;
	}
	public static double dajŚrZnajomych(){
		return śrZnajomych;
	}
	public static String dajPlikZRaportem(){
		return plikZRaportem;
	}
	
	public Parametry(){
		p = new Properties();
		try(FileInputStream stream = new FileInputStream("default.properties");
				Reader reader = Channels.newReader(stream.getChannel(), StandardCharsets.UTF_8.name())){
		    	p.load(reader);
		}
		catch(MalformedInputException e){
				System.out.println("default.properties nie jest tekstowy");
				System.exit(1);
		}
		catch(IOException e ){
		    	System.out.println("Brak pliku default.properties");
		    	System.exit(1);
		}
		zapiszParametry();
		p = new Properties();
		try{
			p.loadFromXML(new FileInputStream("simulation-conf.xml"));
		}
		catch(MalformedInputException e){
			System.out.println("simulation-conf.xml nie jest tekstowy");
			System.exit(1);
		}
		catch(IOException e ){
	    	System.out.println("Brak pliku simulation-conf.xml");
	    	System.exit(1);
		}
		zapiszParametry();
		sprawdźParametry();
	}
	
}
