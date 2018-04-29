package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

/**
 * All builder pattern Builders that build Shape3D objects should implement this interface
 * @author jfisher1
 */
public interface ShapeBuilder {
	/**
	 * Method to set the x, y, and z translation coordinates of the Shape3D object 
	 * @param x is the Shape3D object's x location in the scene
	 * @param y is the Shape3D object's y location in the scene
	 * @param z is the Shape3D object's z location in the scene
	 * Should always @return this
	 */
	ShapeBuilder transCoords(double x, double y, double z);
	
	/**
	 * Method to set the material of the Shape3D object to a given PhongMaterial
	 * @param material is a PhongMaterial created and passed by the client
	 * Should always @return this
	 */
	ShapeBuilder material(PhongMaterial material);
	
	/**
	 * Method to rotate the Shape3D object on the x axis
	 * @param rotate is a Rotate object provided by the client. Should be on the x axis.
	 * Should always @return this
	 */
	ShapeBuilder xRotate(Rotate rotate);
	
	/**
	 * Method to rotate the Shape3D object on the y axis
	 * @param rotate is a Rotate object provided by the client. Should be on the y axis.
	 * Should always @return this
	 */
	ShapeBuilder yRotate(Rotate rotate);
	
	/**
	 * Method to rotate the Shape3D object on the z axis
	 * @param rotate is a Rotate object provided by the client. Should be on the z axis.
	 * Should always @return this
	 */
	ShapeBuilder zRotate(Rotate rotate);
	
	/**
	 * Method to create a new director object and pass this as a parameter.
	 * @return new ShapeMaker(this)
	 */
	ShapeMaker build();
}
