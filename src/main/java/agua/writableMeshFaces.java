package agua;
import javafx.beans.value.WritableValue;
import javafx.collections.ObservableIntegerArray;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;


public class writableMeshFaces implements WritableValue{

	TriangleMesh meshV;
	public writableMeshFaces(TriangleMesh mv){this.meshV = mv;}
	
	@Override
	public Object getValue() 
	{
		return meshV.getFaces();
	}

	@Override
	public void setValue(Object arg0) {
		// TODO Auto-generated method stub
		meshV.getFaces().setAll((ObservableIntegerArray) arg0);
	}

}
