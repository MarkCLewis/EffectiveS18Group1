package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;

public class CustomMeshViewBuilder implements ShapeBuilder {
	private MeshView mv;
	
	public static class Builder {
		private float[] points;
		private int[] faces;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
		public Builder(float[] p, int[] f) {
			points = p;
			faces = f;
		}
		
		public Builder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		public Builder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		public Builder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		public Builder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		public Builder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		public CustomMeshViewBuilder build() {
			return new CustomMeshViewBuilder(this);
		}
	}
	
	private CustomMeshViewBuilder(Builder builder) {
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
