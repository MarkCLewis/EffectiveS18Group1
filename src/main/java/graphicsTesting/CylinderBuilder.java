package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public class CylinderBuilder implements ShapeBuilder {

	private Cylinder cyl;
	
	public static class Builder {
		private double height;
		private double width;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
		public Builder(double h, double w) {
			height = h;
			width = w;
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
		
		public CylinderBuilder build() {
			return new CylinderBuilder(this);
		}
	}
	
	private CylinderBuilder(Builder builder) {
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
