package terraintesting;

public class TerrainObject implements virtualworld.WorldObject {

	private double xLoc;
	private double yLoc;
	private double zLoc;
	private double xWidth;
	private double yWidth;
	private double zWidth;
	private Long seed;
	private double noise;
	
	public double getXLoc() {
		return xLoc;
	}

	public double getYLoc() {
		return yLoc;
	}

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
	 * @return the width of an object
	 */
	public double getY() {
		return yWidth;
	}

	/**
	 * @return the height of an object
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
}
