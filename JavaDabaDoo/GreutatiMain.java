abstract class Greutate{
	abstract public int capacitate();
	//abstract public String toString();  //TEORETIC O ARE DEJA, TREBUIA DOAR SA DAU OVERRIDE 
}

class GreutateSimpla extends Greutate{
	private int cap;
	public GreutateSimpla(int cap){
		this.cap = cap;
	}
	
	public int capacitate(){
		return cap;
	}
	
	public String toString(){
		String s = "Capacitate: " + this.capacitate()+"\n";
		return s;
	}
}

class GreutateDubla extends Greutate{
	private Greutate g1;
	private Greutate g2;
	
	public GreutateDubla(Greutate g1, Greutate g2){
		this.g1 = g1;
		this.g2 = g2;
	}
	
	public void setGreutate1(Greutate g){
		g1 = g;
	}
	
	public void setGreutate2(Greutate g){
		g2 = g;
	}
	
	public int capacitate(){
		return g1.capacitate() + g2.capacitate();
	}
	
	public String toString(){
		String s = "Greutate 1: " + g1.toString();
		       s += "Greutate 2: " + g1.toString();
		return s;
	}
}

class GreutateMultipla extends Greutate{
	private Greutate[] vector_greutati;
	
	public GreutateMultipla(Greutate[] g){
		vector_greutati = g;
	}
	
	public int capacitate(){
		int cap_totala = 0;
		
		for(int i=0; i<vector_greutati.length; i++){
			if(vector_greutati[i]==null)
				continue;
			cap_totala += vector_greutati[i].capacitate();
		}
		return cap_totala;
	}
	
	public String toString(){
		String s ="Greutate multipla: \n";
		for(int i=0; i<vector_greutati.length; i++)
		{
			if(vector_greutati[i]==null)
				continue;
			s+= "GREUTATE " + (i+1) +" " + vector_greutati[i].toString();
		}
		return s;
	}
	
}

class ColectieGreutati {
	private int count=0;
	private Greutate[] vg;
	
	public ColectieGreutati(int nrmax){
		vg = new Greutate[nrmax];
	}
	
	public void adauga(Greutate g){
		if(count < vg.length){
			vg[count++] = g;
		}
	}
	
	public double medie(){
		double suma = 0;
		
		for(int i=0; i<count; i++)
			suma += vg[i].capacitate();
			
		return suma/count;
	}
}

class GreutatiMain{
	public static void main(String[] args){
		GreutateSimpla g1 = new GreutateSimpla(10);
		GreutateSimpla g2 = new GreutateSimpla(20);
		GreutateDubla g3 = new GreutateDubla(g1, g2);
		Greutate[] g = {g1, g2, g3};
		GreutateMultipla g4 = new GreutateMultipla(g);
		System.out.println(g3);
		System.out.println(g4);
	}
}
