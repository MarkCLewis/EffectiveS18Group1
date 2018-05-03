package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

/**
 * CustomMeshViewMaker uses the builder pattern, and is the director for CMVBuilder. As such, it implements ShapeMaker.
 * CustomMeshViewMaker's purpose is to return the product created by CMVBuilder through the get() method.
 * @author jfisher1
 */
public class CustomMeshViewMaker implements ShapeMaker {
	private MeshView mv;
	
	/**
	 * CMVBuilder uses the builder pattern, and is the builder for CustomMeshViewMaker. As such, it implements ShapeBuilder.
	 * CMVBuilder's purpose is to produce a MeshView object from given parameters.
	 */
	public static class CMVBuilder implements ShapeBuilder{
		private float[] points;
		private int[] faces;
		private CullFace cull;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
	    /**
	     * CMVBuilder's public constructor
	     * @param p is a float[] representing different points of the MeshView
	     * @param f is an int[] representing the different faces of the MeshView
	     */
		public CMVBuilder(float[] p, int[] f) {
			points = p;
			faces = f;
		}
		
		/**
		 * Method allows the client to provide the translation coordinates for the MeshView
		 * @param xLoc represents the translation of the MeshView on the x axis
		 * @param yLoc represents the translation of the MeshView on the y axis
		 * @param zLoc represents the translation of the MeshView on the z axis
		 * @return this
		 */
		public CMVBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		public CMVBuilder cullFace(CullFace cf) {
			cull = cf;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized PhongMaterial for the MeshView to use
		 * @param pm is the PhongMaterial that the MeshView will use
		 * @return this
		 */
		public CMVBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the MeshView. The Rotate should be on the x axis.
		 * @param rotate represents the MeshView's rotation on the x axis
		 * @return this
		 */
		public CMVBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the MeshView. The Rotate should be on the y axis.
		 * @param rotate represents the MeshView's rotation on the y axis
		 * @return this
		 */
		public CMVBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		/**
		 * Method allows the client to provide a customized Rotate object for the MeshView. The Rotate should be on the z axis.
		 * @param rotate represents the MeshView's rotation on the z axis
		 * @return this
		 */
		public CMVBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		/**
		 * Method finalizes the changes made to the MeshView and instantiates a CustomMeshViewMaker object.
		 * @return a new instance of the director CustomMeshViewMaker with the builder as the parameter
		 */
		public CustomMeshViewMaker build() {
			return new CustomMeshViewMaker(this);
		}
	}
	
	/**
	 * Private constructor, only accessed through CMVBuilder.build()
	 * Constructor creates and makes appropriate changes to a new MeshView object
	 * @param builder is a finalized CMVBuilder
	 */
	private CustomMeshViewMaker(CMVBuilder builder) {
		TriangleMesh tm = new TriangleMesh();
		
		tm.getTexCoords().addAll(0,0);
		tm.getPoints().addAll(builder.points);
		tm.getFaces().addAll(builder.faces);
		
		mv = new MeshView(tm);
		mv.setDrawMode(DrawMode.FILL);
		mv.setCullFace(builder.cull);
		mv.setMaterial(builder.material);
		mv.setTranslateX(builder.x);
		mv.setTranslateY(builder.y);
		mv.setTranslateZ(builder.z);
		mv.getTransforms().addAll(builder.xRotate, builder.yRotate, builder.zRotate);
	}
	
	/**
	 * Method returns the MeshView product
	 * @return mv is the created MeshView
	 */
	@Override
	public MeshView get() {
		return mv;
	}
}
