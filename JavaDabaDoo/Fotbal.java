import java.util.Random;
import java.util.Date;
class CoordinateGenerator {
private Random randomGenerator;
public CoordinateGenerator() {
Date now = new Date();
long sec = now.getTime();
randomGenerator = new Random(sec);
}
public int generateX() {
	int x = randomGenerator.nextInt(101);
	if(x < 5) {
	x = 0;
	} else if(x > 95) {
	x = 100;} else {
	x = randomGenerator.nextInt(99) + 1;
	}
	return x;
}
public int generateY() {
	int y = randomGenerator.nextInt(101);
	if(y < 5) {
	y = 0;
	} else if(y > 95) {
	y = 50;
	} else {
	y = randomGenerator.nextInt(49) + 1;
	}
	return y;
}
}


class ExceptionOut extends Exception{
	public ExceptionOut(){
		super("OUT!!");
	}
}

class ExceptionGol extends Exception{
	public ExceptionGol(){
		super("GOOOL!!");
	}
}

class ExceptionCorner extends Exception{
	public ExceptionCorner(){
		super("CORNER!!");
	}
}

class Minge {
	private static CoordinateGenerator gen = new CoordinateGenerator();
	private int X, Y;
	public Minge(int X, int Y){
		this.X = X;
		this.Y = Y;
	}
	
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
	
	public void suteaza() throws ExceptionOut, ExceptionGol, ExceptionCorner{
		X = gen.generateX();
		Y = gen.generateY();
		
		if(Y==0 || Y==50)
			throw new ExceptionOut();
		else
		if((X==0 || X==100) && (Y>=20 && Y<=30))
			throw new ExceptionGol();
		else
		//if((X==0 || X==100) || ((0<Y && Y<20) || (30<Y && Y<50)))
			throw new ExceptionCorner();
		
	}
	
	public String toString(){
		return "Mingea se afla la coordonatele (" + X + " , " + Y + ")";
	}
}

class Joc{
	private String echipa1, echipa2;
	private int goluri1=0, goluri2=0, goluri=0, cornere=0, outuri=0;
	
	public Joc(String echipa1, String echipa2){
		this.echipa1 = echipa1;
		this.echipa2 = echipa2;
	}
	
	public void comenteaza(Minge m){
		String s = echipa1 + " - " + echipa2 + " : " + m.toString() +"\n" ;
        System.out.print(s);

	}
	
	public void simuleaza(){
		Minge minge = new Minge(50, 25);
		comenteaza(minge);
		
		for(int i=0; i<10000; i++){
			try{
				minge.suteaza();
			}catch(ExceptionOut e){
				Minge minge_new = new Minge(minge.getX(), minge.getY());
				minge = minge_new;
				outuri++;
			}catch(ExceptionGol e){
				if(minge.getX() == 0){
					goluri2++;
				}
				if(minge.getX() == 100){
					goluri1++;
				}
				minge = new Minge(50, 25);
			}catch(ExceptionCorner e){
				cornere++;
				if(minge.getY()<20)
				minge = new Minge(0, 0);
				else
				minge = new Minge(100,0);
			}finally{
				comenteaza(minge);
			}
			System.out.println(this);
		}
		
	}
	
	
	public String toString(){
		String s = echipa1 + "  -  " + echipa2 + "\n";
		s += goluri1 + "   -    " + goluri2 + "\n";
		s += "Cornere: " + cornere + "\n" + "Outuri: " + outuri + "\n";
		return s;
	}
}


class Fotbal{
	public static void main(String[] args){
		Joc joculetz = new Joc("Bozovici", "Timi");
		joculetz.simuleaza();
	}
}





