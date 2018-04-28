package virtualworld;

/**
 * All objects in the world should implement this interface
 * x, y, z are dimensions
 * size is the bounding box that encapsulates your object 
 * (i.e. how much space it takes up if you were looking at it from above)
 */
public interface WorldObject {

	/**
	 * Enum that signifies what kind of geometric object this WorldObject should be drawn as
	 */
	/*
	enum DrawType {
		TRIANGLE_MESH, BOX, PYRAMID, SPHERE, CYLINDER, ;
	}
	 */
	/**
	 * @return the DrawType of an object
	 */
	/*
	DrawType getDrawType();
	 */
	
	/**
	 * @return the length of an object
	 */
	double getX();
	
	/**
	 * @return the width of an object
	 */
	double getY();
	
	/**
	 * @return the height of an object
	 */
	double getZ();
	
	/**
	 * the two dimensional size of the bounding box 
	 * (i.e. the size of the object if you were looking at it from above
	 * @return the above described size
	 */
	double getSize();
	
	/**
	 * notifies a WorldObject that a camera is nearby, which allows
	 * the object to change its level of detail
	 * 
	 * @param x of the camera
	 * @param z of the camera
	 */
	void notifyOfCamera(double x, double z);
}