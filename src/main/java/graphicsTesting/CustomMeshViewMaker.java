package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class CustomMeshViewMaker implements ShapeMaker {
	private MeshView mv;
	
	public static class CMVBuilder {
		private float[] points;
		private int[] faces;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
		public CMVBuilder(float[] p, int[] f) {
			points = p;
			faces = f;
		}
		
		public CMVBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		public CMVBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		public CMVBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		public CMVBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		public CMVBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		public CustomMeshViewMaker build() {
			return new CustomMeshViewMaker(this);
		}
	}
	
	private CustomMeshViewMaker(CMVBuilder builder) {
		TriangleMesh tm = new TriangleMesh();
		
		tm.getTexCoords().addAll(0,0);
		tm.getPoints().addAll(builder.points);
		tm.getFaces().addAll(builder.faces);
		
		mv = new MeshView(tm);
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
