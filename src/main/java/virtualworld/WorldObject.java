package virtualworld;

/**
 * All objects in the world should implement this interface
 * x, y, z and dimensions
 * size is the bounding box that encapsulates your object 
 * (i.e. how much space it takes up if you were looking at it from above)
 */
public interface WorldObject {
	double getX();
	double getY();
	double getZ();
	double getSize();
	/**
	 * method that notifies a WorldObject that a camera is nearby, which allows
	 * the object to change its level of detail
	 * 
	 * @param x of the camera
	 * @param z of the camera
	 */
	void notifyOfCamera(double x, double z);
}