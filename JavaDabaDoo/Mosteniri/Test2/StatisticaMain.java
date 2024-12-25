import java.util.*;

abstract class Statistica{
	protected String jurnal;
	
	public Statistica(){
		jurnal = "";
	}

	abstract public void calculeaza(List<String> secventa_stringuri);
	
	public String toString(){
		return jurnal;
	}
} 

class StatisticaNumarAparitii extends Statistica{
	private List<String> siruri_cautate;
	
	public StatisticaNumarAparitii(List<String> lista){
		siruri_cautate = lista;
	}
	
	public void calculeaza(List<String> secventa_stringuri){
		int contor = 0;
        
        	for(String sirCautat : siruri_cautate){
        		for(String sirSecventa: secventa_stringuri){
        			if(sirCautat.equals(sirSecventa)){
        				contor++;
        			}
        		}
        	}
		jurnal += "In secventa {";
		
		Iterator<String> it3 = siruri_cautate.iterator();
		Iterator<String> it4 = secventa_stringuri.iterator();
		
		while(it3.hasNext()){
			jurnal += it3.next();
			if(it3.hasNext())
				jurnal += ",";
		}
		
		jurnal += "}" + " apar " +  contor + " siruri din secventa {";
		
		while(it4.hasNext()){
			jurnal += it4.next();
			if(it4.hasNext())
				jurnal += ",";
		}
		jurnal += "} \n";
		
	}
}

class StatisticaNumereNonReale extends Statistica{
	
	public void calculeaza(List<String> secventa_stringuri){
		int contor = 0;
		for(String iterator: secventa_stringuri){
			try{
				Double.parseDouble(iterator);
			}catch(NumberFormatException e){
				contor++;
			};
		}
		
		jurnal += "In secventa { ";
		
		Iterator<String> it = secventa_stringuri.iterator();
		
		while(it.hasNext()){
			jurnal += it.next();
			if(it.hasNext())
				jurnal += ",";
		}
		
		jurnal += " } avem " + contor + " siruri ce nu sunt numere reale"; 
		
	}

}


class Executor {
	private List<Statistica> lista;
	
	public Executor(List<Statistica> lista){
		this.lista = lista;
	}
	
	public void executa(List<String> secventa_stringuri){
		for(Statistica sp: lista)
		{
			sp.calculeaza(secventa_stringuri);
			System.out.println(sp);
		}
	}
}

class StatisticaMain{

	public static void main(String[] args){
		List<String> lista = new ArrayList<String>();
		lista.add("Ana");
		lista.add("are");
		lista.add("mere");
		
		List<String> lista2 = new ArrayList<String>();
		lista2.add("Ana");
		lista2.add("detine");
		lista2.add("2.1");
		lista2.add("pere");
		lista2.add("3");
		lista2.add("mere");
		lista2.add("caca");
		lista2.add("4.5");
		
		StatisticaNumarAparitii idk = new StatisticaNumarAparitii(lista);
		StatisticaNumereNonReale idk2 = new StatisticaNumereNonReale();
		
		List<Statistica> l = new ArrayList<Statistica>();
		l.add(idk); l.add(idk2);
		
		Executor e = new Executor(l);
		e.executa(lista2);
		
	}
}
