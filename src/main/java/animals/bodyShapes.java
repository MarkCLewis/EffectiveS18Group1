package animals;

//import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;

public class bodyShapes {
	
	public static Cylinder createCylinder(double height, double width, double x, double y, double z) {
		Cylinder cyl = new Cylinder(height, width);
		cyl.setDrawMode(DrawMode.FILL);
		cyl.setTranslateX(x);
		cyl.setTranslateY(y);
		cyl.setTranslateZ(z);
		
		return cyl;
	}
	
	public static Box createBox(double length, double height, double width, double x, double y, double z) {
		Box box = new Box(length, height, width);
		box.setDrawMode(DrawMode.FILL);
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setTranslateZ(z);
		
		return box;
	}
	
	public static Box createBox2(double length, double height, double width, double x, double y, double z) {
		Box box = new Box(length, height, width);
		box.setDrawMode(DrawMode.FILL);
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setTranslateZ(z);
		
		return box;
	}
}
