package graphicsTesting;

import javafx.scene.shape.Shape3D;

/**
 * All builder pattern directors that contain an inner builder class should implement this interface.
 * @author jfisher1
 */
public interface ShapeMaker {
	/**
	 * Method for returning the product of the contained ShapeBuilder
	 * @return a Shape3D created by a ShapeBuilder
	 */
	Shape3D get();
}
