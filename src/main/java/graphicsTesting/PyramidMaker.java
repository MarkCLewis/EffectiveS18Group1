package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

/**
 * PyramidMaker uses the builder pattern, and is the director for PyramidBuilder. As such, it implements ShapeMaker.
 * PyramidMaker's purpose is to return the product created by PyramidBuilder through the get() method.
 * @author jfisher1
 */
public class PyramidMaker implements ShapeMaker {
	private MeshView mv;
	
	/**
	 * PyramidBuilder uses the builder pattern, and is the builder for PyramidMaker. As such, it implements ShapeBuilder.
	 * PyramidBuilder's purpose is to produce a pyramid MeshView object from given parameters.
	 */
	public static class PyramidBuilder implements ShapeBuilder{
		private float height;
		private float side;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
	    /**
	     * PyramidBuilder's public constructor
	     * @param h represents the height of the pyramid MeshView
	     * @param s represents the side width of the pyramid MeshView
	     */
		public PyramidBuilder(float h, float s) {
			height = h;
			side = s;
		}
		
		/**
		 * Method allows the client to provide the translation coordinates for the pyramid MeshView
		 * @param xLoc represents the translation of the pyramid MeshView on the x axis
		 * @param yLoc represents the translation of the pyramid MeshView on the y axis
		 * @param zLoc represents the translation of the pyramid MeshView on the z axis
		 * @return this
		 */
		public PyramidBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized PhongMaterial for the pyramid MeshView to use
		 * @param pm is the PhongMaterial that the pyramid MeshView will use
		 * @return this
		 */
		public PyramidBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the pyramid MeshView. The Rotate should be on the x axis.
		 * @param rotate represents the pyramid MeshView's rotation on the x axis
		 * @return this
		 */
		public PyramidBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the pyramid MeshView. The Rotate should be on the y axis.
		 * @param rotate represents the pyramid MeshView's rotation on the y axis
		 * @return this
		 */
		public PyramidBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the pyramid MeshView. The Rotate should be on the z axis.
		 * @param rotate represents the pyramid MeshView's rotation on the z axis
		 * @return this
		 */
		public PyramidBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		/**
		 * Method finalizes the changes made to the pyramid MeshView and instantiates a PyramidMaker object.
		 * @return a new instance of the director PyramidMaker with the builder as the parameter
		 */
		public PyramidMaker build() {
			return new PyramidMaker(this);
		}
	}
	
	/**
	 * Private constructor, only accessed through PyramidBuilder.build()
	 * Constructor creates and makes appropriate changes to a new pyramid MeshView object
	 * @param builder is a finalized PyramidBuilder
	 */
	private PyramidMaker(PyramidBuilder builder) {
		TriangleMesh pyr = new TriangleMesh();
		
		pyr.getTexCoords().addAll(0,0);
		
		float h = builder.height;                 // Height
		float s = builder.side;                   // Side
		
		pyr.getPoints().addAll(
		        0,    0,    0,            // Point 0 - Top
		        0,    h,    -s/2,         // Point 1 - Front
		        -s/2, h,    0,            // Point 2 - Left
		        s/2,  h,    0,            // Point 3 - Back
		        0,    h,    s/2           // Point 4 - Right
		    );
		
		pyr.getFaces().addAll(
		        0,0,  2,0,  1,0,          // Front left face
		        0,0,  1,0,  3,0,          // Front right face
		        0,0,  3,0,  4,0,          // Back right face
		        0,0,  4,0,  2,0,          // Back left face
		        4,0,  1,0,  2,0,          // Bottom rear face
		        4,0,  3,0,  1,0           // Bottom front face
		    );
		
		mv = new MeshView(pyr);
		mv.setDrawMode(DrawMode.FILL);
		mv.setMaterial(builder.material);
		mv.setTranslateX(builder.x);
		mv.setTranslateY(builder.y);
		mv.setTranslateZ(builder.z);
		mv.getTransforms().addAll(builder.xRotate, builder.yRotate, builder.zRotate);
	}
	
	/**
	 * Method returns the pyramid MeshView product
	 * @return mv is the created pyramid MeshView
	 */
	@Override
	public MeshView get() {
		return mv;
	}
}
