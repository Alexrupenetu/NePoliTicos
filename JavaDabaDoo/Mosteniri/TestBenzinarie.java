class Masina{
	private String nr_inmat;
	private int litri;
	
	
	public Masina(String str, int val){
		nr_inmat = str;
		litri = val;
	}
	
	public String getNrInmat(){
		return nr_inmat;
	}
	
	public int getLitri(){
		return litri;
	}
	
	public String toString(){
		String str = nr_inmat + " - " + litri;
		return str;
	}
}

class Benzinarie{
	private int litri_disp;
	private Masina[] coada = new Masina[10];
	private int contor=0;
	
	public Benzinarie(int val){
		litri_disp = val;
	}
	
	public boolean alimenteazaMasina(Masina masina){
		if(litri_disp>=masina.getLitri())
		{
			litri_disp -= masina.getLitri();
			return true;
		}
		else
		{	if(contor<10){
			coada[contor++] = masina;
			return true;
			}
			
			return false;
		}
	}
	
	public String toString(){
		String str = "Cantitate benzina disp: " + litri_disp + "\nCoada benzinarie: \n";
		for(int i=0; i<contor; i++){
			str += "Masina " + (i+1) + " " + coada[i] + "\n";
		}
		return str;
			
	}
	
	public void alimenteazaBenzinarie(int cantitate){
		litri_disp += cantitate;
		for(int i=0; i<contor && (litri_disp>=coada[i].getLitri()) && contor!=0; i++)
		{
			litri_disp -= coada[i].getLitri();
			for(int j=i;j<contor-1; j++)
				coada[j] = coada[j+1];
			contor--;
			i--;
		}
	}
	
}

class TestBenzinarie{
	public static void main(String[] args){
		Masina m1 = new Masina("TM06ELH", 5);
		Masina m2 = new Masina("TM09EXC", 10);
		Masina m3 = new Masina("TM20ABD", 15);
		Masina m4 = new Masina("CS29SEX", 10);
		Masina m5 = new Masina("CS07ESC", 20);
		Benzinarie b = new Benzinarie(20);
		b.alimenteazaMasina(m1);
		b.alimenteazaMasina(m2);
		b.alimenteazaMasina(m3);
		b.alimenteazaMasina(m4);
		b.alimenteazaMasina(m5);
		System.out.println(b);
		b.alimenteazaBenzinarie(10);
		System.out.print(b);
	}
}
