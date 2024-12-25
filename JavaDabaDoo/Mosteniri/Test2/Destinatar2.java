import java.util.*;

abstract class Destinatar{
	protected String nume;
	
	public Destinatar(String nume){
		this.nume = nume;
	}
	
	abstract public void receptioneaza(Utilizator user, String mesaj);
	
	public boolean equals(Object o)
	{
		if(o==null)
			return  false;
		if(o instanceof Destinatar)
			{
				Destinatar other = (Destinatar)o;
				if((this.nume).equals(other.nume))
					return true;
			}
		return false;
	}
	
}

class Utilizator extends Destinatar{
	private String jurnal;
	
	public Utilizator(String nume){
		super(nume);
		jurnal = "";
	}
	
	public void receptioneaza(Utilizator user, String mesaj){
		jurnal += "Primit de la " + user.nume + " mesajul: " + mesaj + "\n";
	}
	
	public void trimite(Destinatar user, String mesaj){
		this.jurnal += "Trimis catre " + user.nume + " mesajul: " + mesaj + "\n";
		user.receptioneaza(this, mesaj);
	}
	
	public String toString(){
		String s = this.nume + ": \n" + jurnal;
		return s;
	}
}

class DestinatarDuplicat extends Exception{
	public DestinatarDuplicat(){
		super("Avem dubura!!");
	}
}

class Grup extends Destinatar{
	private List<Destinatar> lista;
	
	
	public Grup(String nume){
		super(nume);
		lista = new ArrayList<Destinatar>();
	}
	
	public void inscrie(Destinatar user) throws DestinatarDuplicat{
		
		for(Destinatar d:lista)
			if(d.equals(user))
			{
				throw new DestinatarDuplicat();
				return false;
			}
		list.add(user);
		return true;
	}
}

class Destinatar2{
	public static void main(String[] args){
		
	}
}
