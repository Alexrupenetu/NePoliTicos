class Card{
	private String numar;
	private double sold;
	
	public Card(String str, double val){
		numar = str;
		sold = val;
	}
	
	public String getNumar(){
		return numar;
	}
	
	public double getSold(){
		return sold;
	}
	
	public String toString(){
		return numar + " - " + sold + "\n";
	}
}

class Portofel{
	private String proprietar;
	private Card[] carduri = new Card[6];
	private int contor = 0;
	
	public Portofel(String str){
		proprietar = str;
	}
	
	private boolean gasesteCard(String str){
		for(int i=0; i<contor; i++)
			if((carduri[i].getNumar()).equals(str))
				return true;
		return false;
	}
	
	public boolean adaugaCard(String str, double val){
		if(contor<6){
			if(gasesteCard(str)==false)
			{
				Card newC = new Card(str, val);
				carduri[contor++] = newC;
				return true;
			}
			else
				return false;
		}
		return false;
	}
	
	public String toString(){
		String str = "Portofel " + proprietar + ":\n";
		
		for(int i=0; i<contor; i++)
			str += "Card " + (i+1) + " " + carduri[i].toString();
			
		return str;
	}
	
	public double returneazaSold(){
		double suma = 0;
		for(int i=0; i<contor; i++)
			suma+=carduri[i].getSold();
		return suma;
	}
	
}

class TestPortofel{
	public static void main(String[] args){
	Card c1 = new Card("1234", 678.96);
	Card c2 = new Card("3456", 987.45);
	Card c3 = new Card("1234", 123.45);
	Card c4 = new Card("5678", 876.34);
	Portofel p = new Portofel("Popescu Ion");
	p.adaugaCard("1234", 678.96);
	p.adaugaCard("3456", 987.45);
	p.adaugaCard("1234", 123.45);
	p.adaugaCard("5678", 876.34);
	System.out.print(p);
	System.out.println("Sold total: " + p.returneazaSold());
	}
}
