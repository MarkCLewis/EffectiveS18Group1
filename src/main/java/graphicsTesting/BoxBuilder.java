package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public class BoxBuilder implements ShapeBuilder {
	private Box box;
	
	public static class Builder {
		private double length;
		private double height;
		private double width;
		private double x = 0;
		private double y = 0;
		private double z = 0;
		private PhongMaterial material = new PhongMaterial();
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    private Rotate zRotate = new Rotate(0,0,0,0,Rotate.Z_AXIS);
		
		public Builder(double l, double h, double w) {
			length = l;
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
		
		public BoxBuilder build() {
			return new BoxBuilder(this);
		}
	}
	
	private BoxBuilder(Builder builder) {
		box = new Box(builder.length, builder.height, builder.width);
		box.setDrawMode(DrawMode.FILL);
		box.setMaterial(builder.material);
		box.setTranslateX(builder.x);
		box.setTranslateY(builder.y);
		box.setTranslateZ(builder.z);
		box.getTransforms().addAll(builder.xRotate, builder.yRotate, builder.zRotate);
	}
	
	@Override
	public Box get() {
		return box;
	}
}
