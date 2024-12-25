import java.util.*;

abstract class Statistica{
	protected String jurnal;
	abstract public void calculeaza(List<String> secventa_stringuri);
	
	public Statistica(){
		jurnal = "";
	}
	
	
	public String toString(){
		return jurnal;
	}
}

class StatisticaNrAparitii extends Statistica{
	private List<String> siruriCautate;
	
	public StatisticaNrAparitii(List<String> siruriCautate){
		this.siruriCautate = siruriCautate;
	}
	
	public void calculeaza(List<String>  secventa){
		int contor = 0;
		
		for(String sc : siruriCautate)
			for(String sec: secventa)
				if(sc.equals(sec))
					contor++;
					
		jurnal += "In secventa { ";
		for(String sc: siruriCautate)
			jurnal+=sc + " ";
		jurnal+="} apar " + contor + " siruri din secventa { ";
		for(String sec:secventa)
			jurnal +=sec + " ";
		jurnal+="} \n";
		
	}

}

class StatisticaNrNonReale extends Statistica{
	
	public StatisticaNrNonReale(){
		super();
	}
	
	public  void calculeaza(List<String> secventa_siruri){
		int contor = 0;
		
		for(String s : secventa_siruri){
			try{
				Double.parseDouble(s);
			}catch(NumberFormatException e){
				contor++;
			}
		}
		jurnal += "In secventa { ";
		for(String sc: secventa_siruri)
			jurnal+=sc + " ";
		jurnal += "avem " + contor + " siruri care nu sunt nr reale \n";
	}	
}


class Executor{
	private List<Statistica> lista;
	
	public Executor(List<Statistica> lista){
		this.lista = lista;
	}
	
	public void executa(List<String> stringuri){
		for(Statistica st : lista)
				{
					st.calculeaza(stringuri);
					System.out.println(st);
				}
	}
} 






