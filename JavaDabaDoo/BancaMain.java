interface SumaTotala{
	public float getSumaTotala();
}

abstract class ContBancar implements SumaTotala{
	private String numarCont;
	private float suma;
	
	public ContBancar(String numarCont, float suma){
		this.numarCont = numarCont;
		this.suma = suma;
	}
	
	public void setSuma(float suma_noua){
		suma = suma_noua;
	}
	
	public float getSuma(){
		return suma;
	}
	
	public float getSumaTotala(){
		return suma;
	}
	
} 

class ContLei extends ContBancar{
	
	public ContLei(String numarCont, float suma){ 
		super(numarCont, suma);
	}

	public void transfer(ContBancar contDestinatie, float suma){
		if(contDestinatie instanceof ContLei && suma > 0 && suma<=super.getSuma()){
			super.setSuma(super.getSuma() - suma);
			contDestinatie.setSuma(contDestinatie.getSuma() + suma);
		}
		else
		{
			System.out.println("Eroare transfer!");
		}
	}
}

class ContEuro extends ContBancar{
	public ContEuro(String nrCont, float suma){
		super(nrCont, suma);
	}
	
	public float getDobanda(){
		if(super.getSuma() > 500)
			return (float)0.3;
		else
			return 0;
	}
	
	public float getSumaTotala(){
		return super.getSuma() * (float)36.000;
	}
}

class Client {
	private String nume, adresa;
 	private ContBancar[] conturi = new ContBancar[10];
	
	
	public Client(String nume, String adresa, ContBancar[] conturi){
		this.nume = nume;
		this.adresa = adresa;
		this.conturi = conturi;
	}
	
	public void setNume(String nume_nou){
		nume = nume_nou;
	}
	
	public String getNume(){
		return nume;
	}
	
	public void setAdresa(String adresa_noua){
		adresa = adresa_noua;
	}
	
	public void setConturi(ContBancar[] conturi_nou){
		if(conturi_nou.length >=1 && conturi_nou.length<=5){
			conturi = conturi_nou;
		}
		else
		{
			System.out.println("Eroare conturi!");
		}
	}
	
	public String toString(){
		String s = nume + " " + adresa + "\n";
		for(int i=0; i<conturi.length; i++)
			s+=conturi[i].toString();
		return s;
	}
}

class Banca{
	private Client[] clienti;
	private int contor;
	private String codBanca;
	
	public Banca(String codBanca){
		this.codBanca = codBanca;
		clienti = new Client[10];
		contor = 0;
	}
	
	public void add(Client c)
	{
		if(contor<10){
			clienti[contor++] = c;
		}
	}
	
	public void afisareClient(String nume){
		for(int i=0; i<contor; i++)
			if(clienti[i].getNume().equals(nume))
				System.out.println(clienti[i].toString());
	}
}

class BancaMain{
	public static void main(String[] args){
		
	}
}
