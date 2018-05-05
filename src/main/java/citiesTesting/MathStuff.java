package citiesTesting;

public class MathStuff {
	
	public static double makeCoordinate(){
		//x = start and y = end
		//return Math.floor(Math.random() * ((y-x)+1) + x);
		int x = -6000;
		int y = 6000;
		return Math.floor(Math.random() * ((y-x)+1) + x);
	}
	
}
