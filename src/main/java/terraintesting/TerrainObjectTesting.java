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
		long seed = 12345687654L;
		double noise = 0.75;
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
		if(z1-s1/2 < z2+s2/2 && (x1+s1/2 > x2-s2/2 || x1-s1/2 < x2+s2/2)) {
			System.out.println("1");
			return true;
		}
		if(x1+s1/2 > x2-s2/2 && (z1+s1/2 > z2-s2/2 || z1-s1/2 < z2+s2/2)) {
			System.out.println("2");
			return true;
		}
		if(z1+s1/2 > z2-s2/2 && (x1+s1/2 > x2-s2/2 || x1-s1/2 < x2+s2/2)) {
			System.out.println("3");
			return true;
		}
		if(x1-s1/2 < x2+s2/2 && (z1+s1/2 > z2-s2/2 || z1-s1/2 < z2+s2/2)) {
			System.out.println("4");
			return true;
		}
		else 
			return false;
	}
	@Test
	public void squareCompareTest() {
		Square s1 = new Square(1.0, 1.0, 2.0);
		assertTrue(squareCompare(s1.cX, s1.cZ, s1.s, s1.cX, s1.cZ, s1.s));
		Square s2 = new Square(3.0, 3.0, 1.0);
		assertFalse(squareCompare(s1.cX, s1.cZ, s1.s, s2.cX, s2.cZ, s2.s));
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
