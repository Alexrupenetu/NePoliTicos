class Carte {
	private int pagini;
	
	public Carte(int val){
		this.pagini = val;
	}
	
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		if(o instanceof Carte){
			Carte other = (Carte) o;
			return other.pagini == pagini;
		}
		return false;
	}
	
	public static void main(String[] arg){
	
		Carte c1 = new Carte(15);
		Carte c2 = new Carte(20);
		Carte c3 = new Carte(15);
		
		if(c1.equals(c3)){
		System.out.println("Obiectele sunt egale dpdv al echivalentei");
		}else{
		System.out.println("Obiectele NU sunt egale dpdv al echivalentei");
		}
		
	}
}
