class Piramida {
	private int n;
	
	public Piramida(int val){
		this.n = val;
	}
	
	public void tiparirePiramida(){
		for(int i=1; i<=n; i++){
		    for(int j=1; j<=n-i+1; j++ ){
			System.out.print(i + " ");
			}
		    System.out.println();
			
		}
	} 
	
	public static void main(String[] arg){
		
		Piramida p = new Piramida(4);
		p.tiparirePiramida();
	}
	
}
