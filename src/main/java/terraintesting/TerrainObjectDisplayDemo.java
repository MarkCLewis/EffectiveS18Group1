package terraintesting;

public class TerrainObjectDisplayDemo {

	public static void main(String[] args) {
		System.out.println("app start");
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 0.0; 
		double z = 0.0; 
		double y = 0.0;
		double xW = 200; 
		double zW = 200; 
		double yW = 200;
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
		
		terr.display(); // gives list of Shape3D
	}

}
