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
}
