package terraintesting;

public class TerrainObject implements virtualworld.WorldObject {

	private final double xLoc;
	private final double yLoc;
	private final double zLoc;
	private final double xWidth;
	private final double yWidth;
	private final double zWidth;
	private int currentScale; //close - 100; far - 1000
	// May need a field for triangleWidth/level of rendering detail, so the terrain can respond to the camera 
	// scale (size of triangles) and dimensions (how many )
	
	private final long seed;
	private final double noise;
	private static final long defaultSeed = 0L;
	private static final double defaultNoise = 0.5;
	
	public TerrainObject(double x, double y, double z, double xW, double yW, double zW, long seed, double noise) {
		if(noise<0.0 || noise>1.0)
			throw new IllegalArgumentException("Noise must be between 0 and 1");
		if(xW!=zW)
			throw new IllegalArgumentException("Terrain must be square (equal x and z width)");
		xLoc = x;
		yLoc = y;
		zLoc = z;
		xWidth = xW;
		yWidth = yW;
		zWidth = zW;
		this.seed = seed;
		this.noise = noise;
		
	}
	/**
	 * @return x-coordinate of the upper-left hand point of this terrain object
	 */
	public double getXLoc() {
		return xLoc;
	}

	/**
	 * @return y-coordinate (height) of the upper-left hand point of this terrain object -- probably should not be used
	 */
	public double getYLoc() {
		return yLoc;
	}

	/**
	 * @return z-coordinate of the upper-left hand point of this terrain object
	 */
	public double getZLoc() {
		return zLoc;
	}

	/**
	 * @return the length of an object
	 */
	public double getX() {
		return xWidth;
	}

	/**
	 * @return the height of an object -- probably not needed
	 */
	public double getY() {
		return yWidth;
	}

	/**
	 * @return the width of an object
	 */
	public double getZ() {
		return zWidth;
	}

	/**
	 * the two dimensional size of the bounding box (i.e. the size of the object
	 * if you were looking at it from above
	 * 
	 * @return the above described size
	 */
	public double getSize() {
		return xWidth*zWidth;
	}

	/**
	 * notifies a WorldObject that a camera is nearby, which allows the object
	 * to change its level of detail
	 * 
	 * @param x
	 *            of the camera
	 * @param z
	 *            of the camera
	 */
	public void notifyOfCamera(double x, double z) {
		// TODO
	}
	
	public double getHeight(double x, double z) {
		//TODO
		return 0.0;
	}
	
	public static long getDefaultSeed() {
		return defaultSeed;
	}
	
	public static double getDefaultNoise() {
		return defaultNoise;
	}
	
	public TerrainObject[] getChildren() {
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		
		//upper lefthand corner
		TerrainObject child1; 
		//upper righthand corner
		TerrainObject child2; 
		//lower lefthand corner
		TerrainObject child3;
		//lower righthand corner
		TerrainObject child4;
		
		double childXW, childYW, childZW;
		childXW = xWidth/4;
		childYW = yWidth/4;
		childZW = zWidth/4;
		
		//Common fields across all children
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		bldr.setXWidth(childXW);
		bldr.setYWidth(childYW);
		bldr.setZWidth(childZW);
		
		//Building child1
		bldr.setXLoc(xLoc);
		bldr.setYLoc(yLoc); //TODO
		bldr.setZLoc(zLoc);
		child1 = bldr.build();
		
		//Building child2
		bldr.setXLoc(xLoc+childXW);
		bldr.setYLoc(yLoc); //TODO
		bldr.setZLoc(zLoc);
		child2 = bldr.build();
		
		//Building child3
		bldr.setXLoc(xLoc);
		bldr.setYLoc(yLoc); //TODO
		bldr.setZLoc(zLoc+childZW);
		child3 = bldr.build();
		
		//Building child4
		bldr.setXLoc(xLoc+childXW);
		bldr.setYLoc(yLoc); //TODO
		bldr.setZLoc(zLoc+childZW);
		child4 = bldr.build();
		
		TerrainObject[] children = {child1, child2, child3, child4};
		return children;
	}
}

