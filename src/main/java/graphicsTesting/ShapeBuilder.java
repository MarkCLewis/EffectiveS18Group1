package graphicsTesting;

import javafx.scene.shape.Shape3D;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;

public interface ShapeBuilder {
	Shape3D get();
	
	class Builder{}
}
