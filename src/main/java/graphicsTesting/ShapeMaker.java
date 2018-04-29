package graphicsTesting;

import javafx.scene.shape.Shape3D;

public interface ShapeMaker {
	Shape3D get();
	
	class Builder{}
}
