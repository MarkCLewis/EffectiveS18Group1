package graphicsTesting;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

public interface ShapeBuilder {
	ShapeBuilder transCoords(double x, double y, double z);
	ShapeBuilder material(PhongMaterial material);
	ShapeBuilder xRotate(Rotate rotate);
	ShapeBuilder yRotate(Rotate rotate);
	ShapeBuilder zRotate(Rotate rotate);
	ShapeMaker build();
}
