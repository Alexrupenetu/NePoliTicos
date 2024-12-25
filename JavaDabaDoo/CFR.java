abstract class Vagon{
	private int colete;
	
	public Vagon(int colete){
		this.colete = colete;
	}
	
	public String toString(){
		String s="";
		s = s + " Colete: " + colete+"\n";
		return s;
	}
	
	public int getColete(){
		return colete;
	}
}

abstract class VagonCalatori extends Vagon{
	private int pasageri;
	
	public VagonCalatori(int pasageri, int colete){
		super(colete);
		this.pasageri = pasageri;
	}
	
	public void deschidereUsi(){
		System.out.println("S au deschis usile");
	}
	
	public void inchidereUsi(){
		System.out.println("S au inchis usile");
	}
	
	public String toString(){
		String s="";
		s = s + "Pasageri: " + pasageri + super.toString();
		return s;
	}
}

class CalatoriA extends VagonCalatori{
	public CalatoriA(){
		super(40, 300);
	}
}

class CalatoriB extends VagonCalatori{
	public CalatoriB(){
		super(50, 400);
	}
	
	public void blocareGeamuri(){
		System.out.println("S au blocat geamurile");
	}
}

class Marfa extends Vagon{
	public Marfa(){
		super(400);
	}
	
}

class Tren {
	private int nrVagoane;
	private Vagon[] vagoane;
	
	public Tren(){
		nrVagoane = 0;
		vagoane = new Vagon[15];
	}
	
	public void addVagon(Vagon v){
		if(nrVagoane<15)
		{
			vagoane[nrVagoane++] = v;
		}
		else
			System.out.println("Trenul e plin");
	}
	
	public String toString(){
		String s="Tren: \n";
		for(int i=0; i<nrVagoane; i++)
		{
			s+="Vagonul " + (i+1) + " ";
			s+=vagoane[i].toString();
		}
		return s;
	}
	
	private int calculNrColete(){
		int colete = 0;
		
		for(int i=0; i<nrVagoane; i++)
			colete += vagoane[i].getColete();
			
		return colete;
			
	}
	
	public boolean equals(Object o){
		if(o==null)
			return false;
		if(o instanceof Tren)
		{
			if(this.calculNrColete() == ((Tren)o).calculNrColete())
				return true;
		}
		return false;
	}
}

class CFR{
	public static void main(String[] args){
		CalatoriA v1 = new CalatoriA();
		CalatoriB v2 = new CalatoriB();
		Marfa v3 = new Marfa();
		CalatoriA v4 = new CalatoriA();
		Tren t = new Tren();
		Tren t2 = new Tren();
		
		t.addVagon(v1);
		t.addVagon(v2);
		t.addVagon(v3);
		
		
		t2.addVagon(v2);
		t2.addVagon(v3);
		t2.addVagon(v4);
		t2.addVagon(v1);
		
		System.out.println(t);
		System.out.println(t2);
		System.out.println(t.equals(t2));
	}
}
