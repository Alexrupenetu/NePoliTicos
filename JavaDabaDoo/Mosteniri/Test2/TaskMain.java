import java.util.*;
abstract class Task{
	protected String nume;
	
	public Task(String nume){
		this.nume = nume;
	}
	
	abstract public boolean execute(double secunde);
	
	public String toString(){
		return "Name:  " + nume + " ";	
	}
}

class SimpleTask extends Task{
	private double timp;
	public SimpleTask(String nume, double timp){
		super(nume);
		this.timp = timp;
	}
	
	public boolean execute(double secunde){
		timp -= secunde;
		
		if(timp<=0)
			return true;
		else
			return false;
		
	}
	
	public String toString(){
	return super.toString() + "Time: " + timp + "\n";
	}
}

class ComposedTask extends Task{
	private List<SimpleTask> lista;
	
	public ComposedTask(String nume, List<SimpleTask> lista){
		super(nume);
		this.lista = lista;
	}
	
	public boolean execute(double secunde){
		boolean ok=true;
		for(SimpleTask i:lista)
			if(i.execute(secunde/(lista.size()))==false)
				ok=false;
		return ok;
	}
	
	public String toString(){
		String s = super.toString() + "Content: \n";
		
		Iterator<SimpleTask> it = lista.iterator();
		
		while(it.hasNext()){
			s += (it.next()).toString();
		} 
		
		return s;
	}
}

class Secventa{
	private List<Task> caca;
	
	public Secventa(List<Task> caca){
		this.caca = caca;
	}	
	
	public void finishAllTasks(){
		boolean ok = true;
		do{
		ok = true;
		for(Task it:caca)
			if(it.execute(5)==false)
				ok=false;
		}while(ok==false);
	}
	
	public String toString(){
		String s = "Procesor: \n";
		for(Task it: caca)
			s+=it.toString();
		return s;
	}
}

class TaskMain{
	public static void main(String[] args){
		SimpleTask s1 = new SimpleTask("ST1", 2.5);
		SimpleTask s2s = new SimpleTask("ST2", 4);
		List<SimpleTask> lista = new ArrayList<SimpleTask>();
		lista.add(s1); lista.add(s2s);
		ComposedTask t1 = new ComposedTask("Numaipot",lista);
		System.out.println(t1);
		
	}
}
