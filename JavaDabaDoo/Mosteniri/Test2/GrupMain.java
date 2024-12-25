import java.util.*;

abstract class Destinatar{
	protected String nume;
	
	public Destinatar(String nume){
		this.nume = nume;
	}
	
	public abstract void receptioneaza(Utilizator u, String mesaj);
	
	public boolean equals(Object o){
		if(o==null)
			return false;
		else
		{
			if(o instanceof Destinatar)
			{
				if((this.nume).equals(((Destinatar)o).nume))
					return true;
				else 
					return false;
			}
		}
		return false;
	}
}

class Utilizator extends Destinatar{
	private String jurnal;
	
	public Utilizator(String nume){
		super(nume);
		this.jurnal = "";
	}
	
	public void receptioneaza(Utilizator exp, String mesaj){
		jurnal += "Primit de la " + exp.nume + " mesajul: " + mesaj + "\n";
	
	}
	
	public void trimite(Destinatar dest, String mesaj){
		jurnal += "Trimis catre " + dest.nume + " mesajul: " + mesaj + "\n";
		dest.receptioneaza(this, mesaj);
	}
	
	public String toString(){
		String s = "";
		s = s + this.nume + "\n" + jurnal;
		return s;
	}
}

class ExceptieDestinatarDuplicat extends Exception {
	public ExceptieDestinatarDuplicat(){
		super("Avem un duplicat!");
	}
}

class Grup extends Destinatar{
	private List<Destinatar> lista_dest;
	
	public Grup(String nume){
		super(nume);
		lista_dest = new ArrayList<Destinatar>();
	}
	
	public void inscrie(Destinatar d_new) throws ExceptieDestinatarDuplicat{
		Iterator<Destinatar> it = lista_dest.iterator();
		
		while(it.hasNext())
		{
			if(it.next().equals(d_new))
				throws new ExceptieDestinatarDuplicat();
		}
		list_dest.add(d_new);
	}
}


class GrupMain{
	public static void main(String[] args){
		Utilizator u1 = new Utilizator("Alex");
		Utilizator u2 = new Utilizator("Lica");
		
		u1.trimite(u2, "Ce faci?");
		System.out.println(u1);
		System.out.println(u2);
	}
}
