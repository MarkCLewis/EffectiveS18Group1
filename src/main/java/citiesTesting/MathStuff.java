package citiesTesting;

public class MathStuff {
	
	public static double makeCoordinate(){
		//x = start and y = end
		//return Math.floor(Math.random() * ((y-x)+1) + x);
		int x = -4000;
		int y = 4000;
		return Math.floor(Math.random() * ((y-x)+1) + x);
	}
	
}
