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
			assertTrue(t.getY() == terr.getY()/4);
			assertTrue(t.getZ() == terr.getZ()/4);
		}
	}
}
