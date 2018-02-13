package terraintesting;

import java.util.Random;

public class extraFunc {

	private static Random generator = new Random();
	
	public static long randLocation(int x, int y) {
		return generator.nextLong();
	}
}
