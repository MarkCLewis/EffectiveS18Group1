package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class PyramidMaker implements ShapeMaker {
	private MeshView mv;
	
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
		
		public PyramidBuilder(float h, float s) {
			height = h;
			side = s;
		}
		
		public PyramidBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		public PyramidBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		public PyramidBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		public PyramidBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		public PyramidBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		public PyramidMaker build() {
			return new PyramidMaker(this);
		}
	}
	
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
	
	@Override
	public MeshView get() {
		return mv;
	}
}
