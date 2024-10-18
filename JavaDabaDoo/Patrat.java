class Patrat {
	private int latura;
	
	public Patrat(){
		this.latura = 10;
	}
	
	public Patrat(int val){
		this.latura = val;
	}
	
	public String toString(){
		return "Latura: " + latura + " Aria: " + latura*latura;  
	}
	
	public static void main(String[] arg){
		Patrat p1 = new Patrat();
		Patrat p2 = new Patrat(5);
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());	
	}
	
}
