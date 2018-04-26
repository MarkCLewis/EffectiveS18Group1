package citiesTesting;

public class MathStuff {
	
	public static double makeCoordinate(int x, int y){
		//x = start and y = end
		return Math.floor(Math.random() * ((y-x)+1) + x);
	}
	
}
