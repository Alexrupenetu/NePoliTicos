import java.util.*;

abstract class Test{
	abstract public int getNumarTeste();
	abstract public String toString();
}

class WrongQualityIndicatorException extends RuntimeException{
	public WrongQualityIndicatorException(){
		super("Wrong buddy Integration!\n");
	}
}

class WrongComponentComplexityIndicatorException extends Exception{
	public WrongComponentComplexityIndicatorException(){
		super("Wrong buddy Component!\n");
	}
}


abstract class TestTunat extends Test{
	protected String nume;
	protected int quality_ind;
	
	public TestTunat(String nume, int quality_ind) throws WrongQualityIndicatorException {
		this.nume = nume;
		this.quality_ind = quality_ind;
		if(quality_ind<1 || quality_ind>10)
			throw new WrongQualityIndicatorException();
	}
	
	public int getNumarTeste(){
		return 1;
	}
	
	abstract public String toString();

}

class IntegrationTest extends TestTunat{
	public IntegrationTest(String nume, int quality){
		super(nume, quality);
	}
	
	public String toString(){
		return "IntegrationTest( name = " + nume + " , quality indicator = " + quality_ind + " ) \n";
	}
}

class ComponentTest extends TestTunat{
	private int complexity;
	
	public ComponentTest(String name, int quality, int complexity) throws WrongComponentComplexityIndicatorException{
		super(name, quality);
		this.complexity = complexity;
		
		if(!(complexity>0))
			throw new WrongComponentComplexityIndicatorException();
	}
	
	public String toString(){
		return "ComponentTest( name = " + nume + " , quality indicator = " + quality_ind + ", component complexity indicator = " + complexity + ") \n"; 
	}
	
}

class TestSuite extends Test{
	private List<Test> lista = new ArrayList<Test>();
	
	public TestSuite(List<Test> lista){
		this.lista = lista;
	}
	
	public int getNumarTeste(){
		int contor = 0;
		for(Test i:lista)
			contor += i.getNumarTeste();
		return contor;
	}
	
	public boolean addNewIntegration(String name, int indicator){
		IntegrationTest test_nou;
		try{
		test_nou = new IntegrationTest(name, indicator);
		}catch(WrongQualityIndicatorException e){
			return false;
		}
		lista.add(test_nou);
		return true;
	}
	
	public boolean addnewComponentTest(String name, int indicator, int complexity) throws WrongComponentComplexityIndicatorException{
		ComponentTest test_nou;
		try{
		test_nou = new ComponentTest(name, indicator, complexity);
			
		}catch(WrongQualityIndicatorException e){
			return false;
		}catch(WrongComponentComplexityIndicatorException e){
			throw e;//new WrongComponentComplexityIndicatorException();
		}
		lista.add(test_nou);
		return true;
	}
	
	public String toString(){
		String s = "TestSuite ( ";
		for(Test it: lista){
			s+= it.toString() + " ";
		}
		return s;
	}
}

class SoftwareMain{
	public static void main(String[] args){
		IntegrationTest i1 = new IntegrationTest("IntegrareA", 5);
		IntegrationTest i2 = new IntegrationTest("IntegrareB", 1);
		IntegrationTest i3 = new IntegrationTest("IntegrareC", 4);
		ComponentTest c1 = null; ComponentTest c2 = null;
		
		try{
		c1 = new ComponentTest("ComponentA", 8, 6);
		}catch(WrongComponentComplexityIndicatorException e){
			System.out.println(e.getMessage());
		}
		
		try{
		c2 = new ComponentTest("ComponentB", 20, 6);
		}catch(WrongComponentComplexityIndicatorException e){
			System.out.println(e.getMessage());
		}
		
		List<Test> l = new ArrayList<Test>();
		l.add(i1); l.add(i2); 
		if(c1!=null) l.add(c1);
		TestSuite t1 = new TestSuite(l);
		List<Test> l2 = new ArrayList<Test>();
		if(c2!=null) l2.add(c2); l2.add(i3);
		TestSuite t2 = new TestSuite(l2);
		List<Test> f = new ArrayList<Test>();
		f.add(t1); f.add(t2);
		
		TestSuite listaTest = new TestSuite(f);
		
		System.out.println(listaTest);
		System.out.println(listaTest.getNumarTeste());
		listaTest.addNewIntegration("IntegrareD", 6);
		//System.out.println(listaTest.addNewIntegration("IntegrareD", 13));
		System.out.println(listaTest);
		try{
		listaTest.addnewComponentTest("ComponentC", 11, 13);
		}catch(WrongComponentComplexityIndicatorException e){
			System.out.println(e.getMessage());
		}catch(WrongQualityIndicatorException e){
			System.out.println(e.getMessage());
		}
		System.out.println(listaTest);
	}
}




