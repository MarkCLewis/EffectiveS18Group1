package terraintesting;

import org.junit.Test;
import static org.junit.Assert.*;

public class TerrainObjectTesting {
	@Test
	public void terrainBuildTest() {
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 5; 
		double z = 10; 
		double y = 12;
		double xW = 200; 
		double zW = 200; 
		double yW = 500;
		long seed = 3838;
		double noise = 0.60;
		bldr.setXLoc(x);
		bldr.setYLoc(y);
		bldr.setZLoc(z);
		bldr.setXWidth(xW);
		bldr.setYWidth(yW);
		bldr.setZWidth(zW);
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		
		TerrainObject terr = bldr.build();
		
		assertTrue(terr.getXLoc()==x);
		assertTrue(terr.getYLoc()==y);
		assertTrue(terr.getZLoc()==z);
		assertTrue(terr.getX()==xW);
		assertTrue(terr.getY()==yW);
		assertTrue(terr.getZ()==zW);
		
		TerrainObject[] children = terr.getChildren();
		for(TerrainObject t:children) {
			assertTrue(t.getX() == terr.getX()/4);
			assertTrue(t.getY() == terr.getY());
			assertTrue(t.getZ() == terr.getZ()/4);
		}
	}
	
	// UUT Copied from TerrainObject file because I don't know what I'm doing
	private boolean squareCompare(double x1, double z1, double s1, double x2, double z2, double s2) {
		
		if(x1 == x2 && z1 == z2) {
			return true;
		}
		
		double x1lo = x1-s1/2;
		double x1hi = x1+s1/2;
		double z1lo = z1-s1/2;
		double z1hi = z1+s1/2;
		
		double x2lo = x2-s2/2;
		double x2hi = x2+s2/2;
		double z2lo = z2-s2/2;
		double z2hi = z2+s2/2;
		
		if(x2lo < x1hi && x1hi < x2hi && ((z2lo < z1hi && z1hi < z2hi) || (z2lo < z1lo && z1lo < z2hi))) {
			System.out.println("1");
			return true;
		}
		if(x2lo < x1lo && x1lo < x2hi && ((z2lo < z1hi && z1hi < z2hi) || (z2lo < z1lo && z1lo < z2hi))) {
			System.out.println("2");
			return true;
		}
		else {
			System.out.println("3");
			return false;
		}
	}
	private boolean squareCompHelper(Square s1, Square s2) {
		return squareCompare(s1.cX, s1.cZ, s1.s, s2.cX, s2.cZ, s2.s);
	}
	@Test
	public void squareCompareTest() {
		Square s1 = new Square(0.0, 0.0, 2.0);
		assertTrue(squareCompHelper(s1, s1));
		
		Square s2 = new Square(2.0, 2.0, 2.0);
		assertFalse(squareCompHelper(s1, s2));
		
		Square s3 = new Square(0.0, 2.0, 2.0);
		assertFalse(squareCompHelper(s1, s3));
		
		Square s4 = new Square(0.0, -2.0, 2.0);
		assertFalse(squareCompHelper(s1, s4));
		
		Square s5 = new Square(-2.0, 0.0, 2.0);
		assertFalse(squareCompHelper(s1, s5));
		
		Square s6 = new Square(-2.0, -2.0, 2.0);
		assertFalse(squareCompHelper(s1, s6));
		
		Square s7 = new Square(0.0, -2.0, 2.0);
		assertFalse(squareCompHelper(s1, s7));
		
		Square s8 = new Square(2.0, -2.0, 2.0);
		assertFalse(squareCompHelper(s1, s8));
		
		Square s9 = new Square(2.0, 0.0, 2.0);
		assertFalse(squareCompHelper(s1, s9));
		
		// AssertTrue tests
		
		Square s10 = new Square(2.0, 2.0, 2.1);
		assertTrue(squareCompHelper(s1, s10));
		
		Square s11 = new Square(0.0, 2.0, 2.1);
		assertTrue(squareCompHelper(s1, s11));
		
		Square s12 = new Square(0.0, -2.0, 2.1);
		assertTrue(squareCompHelper(s1, s12));
		
		Square s13 = new Square(-2.0, 0.0, 2.1);
		assertTrue(squareCompHelper(s1, s13));
		
		Square s14 = new Square(-2.0, -2.0, 2.1);
		assertTrue(squareCompHelper(s1, s14));
		
		Square s15 = new Square(0.0, -2.0, 2.1);
		assertTrue(squareCompHelper(s1, s15));
		
		Square s16 = new Square(2.0, -2.0, 2.1);
		assertTrue(squareCompHelper(s1, s16));
		
		Square s17 = new Square(2.0, 0.0, 2.1);
		assertTrue(squareCompHelper(s1, s17));
	}
	
	public static class Square {
		//Yes I know this is poorly encapsulated
		//It's just for testing
		public double cX;
		public double cZ;
		public double s; //side length
		public Square(double cX, double cZ, double s) {
			this.cX = cX;
			this.cZ = cZ;
			this.s = s;
		}	
	}
}
