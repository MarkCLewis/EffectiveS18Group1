package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

/**
 * CylinderMaker uses the builder pattern, and is the director for CylinderBuilder. As such, it implements ShapeMaker.
 * CylinderMaker's purpose is to return the product created by CylinderBuilder through the get() method.
 * @author jfisher1
 */
public class CylinderMaker implements ShapeMaker {
	private Cylinder cyl;
	
	/**
	 * CylinderBuilder uses the builder pattern, and is the builder for CylinderMaker. As such, it implements ShapeBuilder.
	 * CylinderBuilder's purpose is to produce a Cylinder object from given parameters.
	 */
	public static class CylinderBuilder implements ShapeBuilder{
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
	     * CylinderBuilder's public constructor
	     * @param w represents the width (radius) of the Cylinder
	     * @param h represents the height of the Cylinder
	     */
		public CylinderBuilder(double w, double h) {
			height = h;
			width = w;
		}
		
		/**
		 * Method allows the client to provide the translation coordinates for the Cylinder
		 * @param xLoc represents the translation of the Cylinder on the x axis
		 * @param yLoc represents the translation of the Cylinder on the y axis
		 * @param zLoc represents the translation of the Cylinder on the z axis
		 * @return this
		 */
		public CylinderBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized PhongMaterial for the Cylinder to use
		 * @param pm is the PhongMaterial that the Cylinder will use
		 * @return this
		 */
		public CylinderBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the Cylinder. The Rotate should be on the x axis.
		 * @param rotate represents the Cylinder's rotation on the x axis
		 * @return this
		 */
		public CylinderBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the Cylinder. The Rotate should be on the y axis.
		 * @param rotate represents the Cylinder's rotation on the y axis
		 * @return this
		 */
		public CylinderBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the Cylinder. The Rotate should be on the z axis.
		 * @param rotate represents the Cylinder's rotation on the z axis
		 * @return this
		 */
		public CylinderBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		/**
		 * Method finalizes the changes made to the Cylinder and instantiates a CylinderMaker object.
		 * @return a new instance of the director CylinderMaker with the builder as the parameter
		 */
		public CylinderMaker build() {
			return new CylinderMaker(this);
		}
	}
	
	/**
	 * Private constructor, only accessed through CylinderBuilder.build()
	 * Constructor creates and makes appropriate changes to a new Cylinder object
	 * @param builder is a finalized CylinderBuilder
	 */
	private CylinderMaker(CylinderBuilder builder) {
		cyl = new Cylinder(builder.width, builder.height);
		cyl.setDrawMode(DrawMode.FILL);
		cyl.setMaterial(builder.material);
		cyl.setTranslateX(builder.x);
		cyl.setTranslateY(builder.y);
		cyl.setTranslateZ(builder.z);
		cyl.getTransforms().addAll(builder.xRotate, builder.yRotate, builder.zRotate);
	}
	
	/**
	 * Method returns the Cylinder product
	 * @return cyl is the created Cylinder
	 */
	@Override
	public Cylinder get() {
		return cyl;
	}

}
