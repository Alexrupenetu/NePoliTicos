import java.util.*;

abstract class CircuitElectric{
	abstract public double rezistentaEchivalenta();
	public double curent(double tensiune){
		return tensiune/rezistentaEchivalenta();
	}
	abstract public boolean contineSubcircuit(CircuitElectric circuit);
}

class Rezistenta extends CircuitElectric{
	private double val_ohmi;
	
	public Rezistenta(double val_ohmi){
		this.val_ohmi = val_ohmi;
	}
	
	public double rezistentaEchivalenta(){
		return val_ohmi;
	}
	
	public boolean contineSubcircuit(CircuitElectric circuit){
		if(this == circuit)
			return true; 
		else
			return false;
	}
}


class CircuitSerie extends CircuitElectric{
	private List<CircuitElectric> lista_serie = new ArrayList<CircuitElectric>();
	
	public CircuitSerie(){
		lista_serie = new ArrayList<CircuitElectric>();
	}
	
	public CircuitSerie(List<CircuitElectric> lista){
		lista_serie = lista;
	}
	
	public double rezistentaEchivalenta(){
		double s = 0;
		Iterator<CircuitElectric> it = lista_serie.iterator();
		
		while(it.hasNext())
		{
			s+=(it.next()).rezistentaEchivalenta();
		}
		
		return s;
	}
	
	public boolean contineSubcircuit(CircuitElectric circuit){
		if(this == circuit)
			return true;
		Iterator<CircuitElectric> it = lista_serie.iterator();
		while(it.hasNext()){
			if((it.next()).contineSubcircuit(circuit))
				return true;
		}
		return false;
	}
	
}

class CircuitParalel extends CircuitElectric{
	private List<CircuitElectric> lista_paralel = new ArrayList<CircuitElectric>();;
	
	public CircuitParalel(){
		lista_paralel = new ArrayList<CircuitElectric>();
	}
	
	public CircuitParalel(List<CircuitElectric> lista){
		lista_paralel = lista;
	}
	
	public double rezistentaEchivalenta(){
		double s = 0;
		
		Iterator<CircuitElectric> it = lista_paralel.iterator();
		while(it.hasNext())
		{
			s+=1/(it.next()).rezistentaEchivalenta();
		}
		return (double)1/s;
	}
	
	public boolean contineSubcircuit(CircuitElectric circuit){
		if(this == circuit)
			return true;
		
		Iterator<CircuitElectric> it = lista_paralel.iterator();
		while(it.hasNext()){
			if((it.next()).contineSubcircuit(circuit))
				return true;
		}
		return false;
	}
}


class CircuitMain{
	public static void main(String[] args){
		Rezistenta r1 = new Rezistenta(2);
		Rezistenta r2 = new Rezistenta(1);
		Rezistenta r3 = new Rezistenta(4);
		Rezistenta r4 = new Rezistenta(2);
		
		List<CircuitElectric> l1 = new ArrayList<CircuitElectric>(3);
		l1.add(r1); l1.add(r2);
		
		List<CircuitElectric> l2 = new ArrayList<CircuitElectric>(3);
		l2.add(r3); l2.add(r4);
		
		CircuitSerie serie1 = new CircuitSerie(l1);
		CircuitSerie serie2 = new CircuitSerie(l2);
		
		List<CircuitElectric> l3 = new ArrayList<CircuitElectric>(3);
		l3.add(serie1); l3.add(serie2);
		
		CircuitParalel paralel = new CircuitParalel(l3);
		
		System.out.println(serie1.rezistentaEchivalenta());
		System.out.println(serie2.rezistentaEchivalenta());
		System.out.println(paralel.rezistentaEchivalenta());
		
		System.out.println(serie1.curent(10));
		
		System.out.println(paralel.contineSubcircuit(r1));
		System.out.println(serie2.contineSubcircuit(r3));
		
	}
}
