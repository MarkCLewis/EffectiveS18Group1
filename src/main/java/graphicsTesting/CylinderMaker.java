package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public class CylinderMaker implements ShapeMaker {

	private Cylinder cyl;
	
	public static class CylinderBuilder {
		private double height;
		private double width;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
		public CylinderBuilder(double w, double h) {
			height = h;
			width = w;
		}
		
		public CylinderBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		public CylinderBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		public CylinderBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		public CylinderBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		public CylinderBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		public CylinderMaker build() {
			return new CylinderMaker(this);
		}
	}
	
	private CylinderMaker(CylinderBuilder builder) {
		cyl = new Cylinder(builder.height, builder.width);
		cyl.setDrawMode(DrawMode.FILL);
		cyl.setMaterial(builder.material);
		cyl.setTranslateX(builder.x);
		cyl.setTranslateY(builder.y);
		cyl.setTranslateZ(builder.z);
		cyl.getTransforms().addAll(builder.xRotate, builder.yRotate, builder.zRotate);
	}
	
	@Override
	public Cylinder get() {
		return cyl;
	}

}
