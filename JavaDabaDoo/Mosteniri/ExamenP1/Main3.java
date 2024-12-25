abstract class A{
	public void m(int p){}
	public void r(int p) {}
}

class B extends A{
	public void m(int p) {}
	public void p(int p) {}
	public void q(int p) {}
}

class C extends A{
	public void m(int p) {}
	public void p(int p) {}
	public void r(int p, int t) {}
}

class Main{
	public static void main(String argv[]){
		A a = new B();
		C c = new C();
		// c.equals(a); //Nu pusca
		//c.r(1); //NU pusca
		//a.q(1); //PUSCA pt ca a este un obiect de tip A si nu poate accesa ce e in B
		((B)a).p(1); //PUSCA din acelasi motiv 
		//a.m(1); //NU pusca   acceseaza metoda m din clasa A tho, nu aia din clasa B
	}
}
