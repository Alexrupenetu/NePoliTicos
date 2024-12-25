abstract class Avion {
	private String planeID;
	private int totalEnginePower;
	
	public Avion(String planeID, int totalEnginePower){
		this.planeID = planeID;
		this.totalEnginePower = totalEnginePower;
	}
	
	public String getPlaneID(){
		return planeID;
	}
	
	public int getTotalEnginePower(){
		return totalEnginePower;
	}
	
	public void takeOff(){
		System.out.println("Plane " + planeID + " is taking off.");
	}
	
	public void land(){
		System.out.println("PLane " + planeID + "is landing.");
	}
	
	public void fly(){
		System.out.println("Plane " + planeID + " is flying.");
	}
}

abstract class AvionCalatori extends Avion{
	private int maxPassengers;
	
	public AvionCalatori(String planeID, int enginePower, int maxPassengers){
		super(planeID, enginePower);
		this.maxPassengers = maxPassengers;
	}
	
	public int getMaxPassengers(){
		return maxPassengers;
	}
}

abstract class AvionLupta extends Avion{
	public AvionLupta(String id, int engine){
		super(id, engine);
	}
	
	public void launchMissile(){
		System.out.println("Plane " + this.getPlaneID() + " is launching a missile.");
	}
}

class Concorde extends AvionCalatori{
	public Concorde(String planeID, int enginePower, int maxPassengers){
		super(planeID, enginePower, maxPassengers);
	}
	
	public void goSuperSonic(){
		System.out.println("Plane " + this.getPlaneID() + "is supersonic");
	}
	
	public void goSubSonic(){
		System.out.println("Plane " + this.getPlaneID() + "is supersonic");
	}
}

class Boeing extends AvionCalatori{
	public Boeing(String planeID, int enginePower, int maxPassengers){
		super(planeID, enginePower, maxPassengers);
	}
}

class Mig extends AvionLupta{
	public Mig(String id, int engine){
		super(id, engine);
	}
	
	public void highSpeedGeometry(){
		System.out.println("Plane " + this.getPlaneID() + "has high speed geometry");
	}
	
	public void normalGeometry(){
		System.out.println("Plane " + this.getPlaneID() + "has normal geometry");
	}
	
}

class TomCat extends AvionLupta{
	public TomCat(String id, int engine){
		super(id, engine);
	}
	
	public void refuel(){
		System.out.println("Plane " + this.getPlaneID() + "is refueling");
	}
}

class AvioaneMain{
	public static void main(String[] args){
		
	}
}
