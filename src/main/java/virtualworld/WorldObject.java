package virtualworld;

import java.util.ArrayList;

import javafx.scene.shape.Shape3D;

/**
 * All objects in the world should implement this interface
 * x, y, z are dimensions
 * size is the bounding box that encapsulates your object 
 * (i.e. how much space it takes up if you were looking at it from above)
 */
public interface WorldObject {

	/**
	 * the center x-coordinate of the object
	 * @return x-coordinate
	 */
	double getXLoc();
	  
	/**
	 * the center y-coordinate of the object
	 * @return y-coordinate
	 */
	double getYLoc();
	
	/**
	 * the center z-coordinate of the object
	 * @return z-coordinate
	 */
	double getZLoc();
	  	
	/**
	 * @return the length of an object
	 */
	double getX();
	
	/**
	 * @return the height of an object
	 */
	double getY();
	
	/**
	 * @return the width of an object
	 */
	double getZ();
	
	/**
	 * the radius of the bounding circle
	 * @return the above described size
	 */
	double getSize();
	
	/**
	 * notifies a WorldObject that a camera is nearby, which allows
	 * the object to change its level of detail
	 * @param x of the camera
	 * @param z of the camera
	 */
	boolean notifyOfCamera(double x, double z);
	
	/**
	 * asks the object to return a list of all the shapes it has that need to be drawn
	 * @return list of shapes
	 */
	ArrayList<Shape3D> display();
}