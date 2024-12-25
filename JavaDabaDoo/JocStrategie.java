abstract class UnitateLupta{
	abstract public void ranire(int damage);
	abstract public void loveste(UnitateLupta unitate);
	abstract public boolean esteVie();
}

abstract class UnitateSimpla extends UnitateLupta{
	private int viata;
	private int forta_lovitura;
	
	public UnitateSimpla(int viata, int forta_lovitura){
		this.viata = viata;
		this.forta_lovitura = forta_lovitura;
	}	
	
	public void ranire(int damage){
		if(this.esteVie())
			viata-=damage;
	}
	
	public void loveste(UnitateLupta unitate){
		if(this.esteVie())
			unitate.ranire(forta_lovitura);
	}
	
	public boolean esteVie(){
		if(viata > 0)
			return true;
		else
			return false;
	}
}

class Arcas extends UnitateSimpla{
	private static final int VIATA_ARCAS = 100;
	private static final int FORTA_ARCAS = 10;
	
	public Arcas(){
		super(VIATA_ARCAS, FORTA_ARCAS);
	}
}

class Calaret extends UnitateSimpla{
	private static final int VIATA_CALARET = 200;
	private static final int FORTA_CALARET = 15;
	private static int cai_morti = 0;
	
	public Calaret(){
		super(VIATA_CALARET, FORTA_CALARET);
	}	
	
	public static int getNrCaiMorti(){
		return cai_morti;
	}
	
	public void ranire(int damage){
		boolean inainte_de_ranire = this.esteVie();
		super.ranire(damage);
	boolean dupa_ranire = this.esteVie();
	if((inainte_de_ranire == true) && (dupa_ranire == false)) {
	cai_morti++;
	}
	}
}

class Pluton extends UnitateLupta{
	private UnitateLupta[] pluton;
	private int count;
	
	public Pluton(){
		pluton = new UnitateLupta[10];
		count = 0;
	}
	
	public void ranire(int damage){
		for(int i=0; i<count; i++)
			if(pluton[i].esteVie())
				{
					pluton[i].ranire(damage);
					break;
				}
	}
	
	public void loveste(UnitateLupta unitate){
		for(int i=0; i<count; i++)
			pluton[i].loveste(unitate);
	}
	
	public boolean esteVie(){
		if(count == 0)
			return true;
		else
		{
			for(int i=0; i<count; i++)
				if(pluton[i].esteVie()==false)
					return false;
			return true;
		}
	}
	
	public boolean adauga(UnitateLupta u_new){
		if(!(u_new.esteVie()) || !(this.esteVie()))
			return false;
		if(count == pluton.length){
			UnitateLupta[] temp = new UnitateLupta[pluton.length + 10];
			for(int i=0; i<count; i++)
				temp[i] = pluton[i];
			pluton = temp;
		}
		pluton[count++] = u_new;
		return true;
	}
	
}

class JocStrategie{
	public static void main(String[] args){
	
	Pluton pluton1, pluton2, pluton3;
	pluton1 = new Pluton();
	pluton1.adauga(new Calaret());
	pluton1.adauga(new Calaret());
	pluton1.adauga(new Arcas());
	pluton1.adauga(new Calaret());
			
	pluton3 = new Pluton();
	pluton3.adauga(new Arcas());
	pluton3.adauga(new Arcas());
	pluton2 = new Pluton();
	pluton2.adauga(new Calaret());
	pluton2.adauga(pluton3);
	//Pentru a determina aleator care pluton loveste primul
	//folosim metoda statica random din clasa Math ce intoarce
	//un numar aleator intre 0 si 1.
	boolean loveste_primul = (Math.random() > 0.5);
	while(pluton1.esteVie() && pluton2.esteVie()) {
	if(loveste_primul) {
	System.out.println("Loveste Pluton1");
	pluton1.loveste(pluton2);
	} else {
	System.out.println("Loveste Pluton2");
	pluton2.loveste(pluton1);
	}
	loveste_primul = !loveste_primul;
	}
	System.out.println("Pluton1 este viu:" + pluton1.esteVie());
	System.out.println("Pluton2 este viu:" + pluton2.esteVie());
	System.out.println("A castigat:" + (pluton1.esteVie() ? "pluton1" :
	pluton2.esteVie() ? "pluton2" : "nimeni"));
	System.out.println("Numar cai decedati:"
	+ Calaret.getNrCaiMorti());
	}
}
