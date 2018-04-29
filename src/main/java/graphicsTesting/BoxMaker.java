package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public class BoxMaker implements ShapeMaker {
	private Box box;
	
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
		
		public BoxBuilder(double w, double h, double d) {
			depth = d;
			height = h;
			width = w;
		}
		
		public BoxBuilder transCoords(double xLoc, double yLoc, double zLoc) {
			x = xLoc;
			y = yLoc;
			z = zLoc;
			return this;
		}
		
		public BoxBuilder material(PhongMaterial pm) {
			material = pm;
			return this;
		}
		
		public BoxBuilder xRotate(Rotate rotate) {
			xRotate = rotate;
			return this;
		}
		
		public BoxBuilder yRotate(Rotate rotate) {
			yRotate = rotate;
			return this;
		}
		
		public BoxBuilder zRotate(Rotate rotate) {
			zRotate = rotate;
			return this;
		}
		
		public BoxMaker build() {
			return new BoxMaker(this);
		}
	}
	
	private BoxMaker(BoxBuilder builder) {
		box = new Box(builder.width, builder.height, builder.depth);
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
