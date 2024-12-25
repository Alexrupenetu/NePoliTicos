import java.util.*;

abstract class Memorie{
	private byte[] tablou;
	
	public Memorie(int dimensiune) throws IllegalArgumentException{
		if(dimensiune<=0)
			throw new IllegalArgumentException();
		else
			tablou = new byte[dimensiune];
	}
	
	public byte[] read(int start, int end) throws IllegalArgumentException{
		
		if((start<0 && end<0) || (start>end) || (start-end+1>tablou.length))
			throw new IllegalArgumentException();
		else
		{
		int size =0;
		byte[] temp = new byte[start-end+1];
		for(int i=start; i<=end; i++)
			temp[size++] = tablou[i];
		return temp;
		}			
	}
}



class MemorieMain{
	public static void main(String[] args){
		
	}
}
