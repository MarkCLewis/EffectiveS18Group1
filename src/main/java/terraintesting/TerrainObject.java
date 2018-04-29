package terraintesting;

public class TerrainObject implements virtualworld.WorldObject {

	private final double xLoc;
	private final double yLoc;
	private final double zLoc;
	private final double xWidth;
	private final double yWidth;
	private final double zWidth;
	
	private final long seed;
	private final double noise;
	private static final long defaultSeed = 0L;
	private static final double defaultNoise = 0.5;
	
	public TerrainObject(double x, double y, double z, double xW, double yW, double zW, long seed, double noise) {
		if(noise<0.0 || noise>1.0)
			throw new IllegalArgumentException("Noise must be between 0 and 1");
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
	
	public static long getDefaultSeed() {
		return defaultSeed;
	}
	
	public static double getDefaultNoise() {
		return defaultNoise;
	}
}

