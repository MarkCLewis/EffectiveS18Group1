package agua;

import javafx.beans.value.WritableValue;
import javafx.collections.ObservableFloatArray;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.shape.TriangleMesh;

public class writableMeshPoints implements WritableValue {

	TriangleMesh meshV;
	public writableMeshPoints(TriangleMesh mv){this.meshV = mv;}
	
	@Override
	public Object getValue() {

		// TODO Auto-generated method stub
		return meshV.getPoints();
	}

	@Override
	public void setValue(Object arg0) {
		// TODO Auto-generated method stub
		meshV.getPoints().setAll((ObservableFloatArray) arg0);
	}
}
