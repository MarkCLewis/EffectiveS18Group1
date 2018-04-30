package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

/**
 * BoxMaker uses the builder pattern, and is the director for BoxBuilder. As such, it implements ShapeMaker.
 * BoxMaker's purpose is to return the product created by BoxBuilder through the get() method.
 * @author jfisher1
 */
public class BoxMaker implements ShapeMaker {
	private Box box;
	
	/**
	 * BoxBuilder uses the builder pattern, and is the builder for BoxMaker. As such, it implements ShapeBuilder.
	 * BoxBuilder's purpose is to produce a Box object from given parameters.
	 */
	public static class BoxBuilder implements ShapeBuilder{
		private double depth;
		private double height;
		private double width;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
	    /**
	     * BoxBuilder's public constructor
	     * @param w represents the width of the Box
	     * @param h represents the height of the Box
	     * @param d represents the depth of the Box
	     */
		public BoxBuilder(double w, double h, double d) {
			depth = d;
			height = h;
			width = w;
		}
		
		/**
		 * Method allows the client to provide the translation coordinates for the Box
		 * @param xLoc represents the translation of the Box on the x axis
		 * @param yLoc represents the translation of the Box on the y axis
		 * @param zLoc represents the translation of the Box on the z axis
		 * @return this
		 */
		public BoxBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}

		/**
		 * Method allows the client to provide a customized PhongMaterial for the Box to use
		 * @param pm is the PhongMaterial that the Box will use
		 * @return this
		 */
		public BoxBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the Box. The Rotate should be on the x axis.
		 * @param rotate represents the Box's rotation on the x axis
		 * @return this
		 */
		public BoxBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the Box. The Rotate should be on the y axis.
		 * @param rotate represents the Box's rotation on the y axis
		 * @return this
		 */
		public BoxBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the Box. The Rotate should be on the z axis.
		 * @param rotate represents the Box's rotation on the z axis
		 * @return this
		 */
		public BoxBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		/**
		 * Method finalizes the changes made to the Box and instantiates a BoxMaker object.
		 * @return a new instance of the director BoxMaker with the builder as the parameter
		 */
		public BoxMaker build() {
			return new BoxMaker(this);
		}
	}
	
	/**
	 * Private constructor, only accessed through BoxBuilder.build()
	 * Constructor creates and makes appropriate changes to a new Box object
	 * @param builder is a finalized BoxBuilder
	 */
	private BoxMaker(BoxBuilder builder) {
		box = new Box(builder.width, builder.height, builder.depth);
		box.setDrawMode(DrawMode.FILL);
		box.setMaterial(builder.material);
		box.setTranslateX(builder.x);
		box.setTranslateY(builder.y);
		box.setTranslateZ(builder.z);
		box.getTransforms().addAll(builder.xRotate, builder.yRotate, builder.zRotate);
	}
	
	/**
	 * Method returns the Box product
	 * @return box is the created Box
	 */
	@Override
	public Box get() {
		return box;
	}
}
