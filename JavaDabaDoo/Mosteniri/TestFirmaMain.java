class Angajat {
	public String nume;
	public int salariu; 
	
	public Angajat(String nume, int salariu){
		this.nume = nume;
		this.salariu = salariu;
	}
	
	public String ToString(){
		return "Angajat " + nume + " - " + salariu + "\n";
	}
}


class Firma {
	private String numeFirma;
	private int buget;
	private static final int MAX_SIZE = 30;
	private static int contor = 0;
	private Angajat[] tablou_angajati = new Angajat[MAX_SIZE];
	
	public Firma(String nume, int value){
		this.numeFirma = nume;
		this.buget = value;
	}
	
	public boolean adaugaAngajat(Angajat angajat_nou)
	{
		if(contor<MAX_SIZE-1)
		{
			int i, ok=1;
			for(i=0; i<contor; i++)
				if(tablou_angajati[i] == angajat_nou)
					ok = 0;
			if(ok==1){
				tablou_angajati[contor] = angajat_nou;
				contor++;
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	public String ToStringFirma(){
		String afisare;
		afisare = numeFirma;
		
		int i;
		for(i=0; i<contor; i++)
			afisare = afisare + tablou_angajati[i].ToString(); 
		
		return afisare;
	}
		
}

class TestFirmaMain {
	public static void main(String[] arg){
		Angajat ang1 = new Angajat("Alex Tunea", 3500);
		Angajat ang2 = new Angajat("Johnny Depp", 2400);
		Angajat ang3 = new Angajat("Tzanca Uraganu", 6500);
		Firma firma = new Firma("AlexTM", 350000);
		boolean var;
		var = firma.adaugaAngajat(ang1);
		if(var == true)
			System.out.println("Angajatul a fost adaugat cu succes ");
		else 
			System.out.println("Angajatul nu a fost adaugat");	
		var = firma.adaugaAngajat(ang2);
		if(var == true)
			System.out.println("Angajatul a fost adaugat cu succes ");
		else 
			System.out.println("Angajatul nu a fost adaugat");			
			
		System.out.println(firma.ToStringFirma());
		
	}
}
