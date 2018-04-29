package quad;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Shape3D;
import virtualworld.WorldObject;

//notifies objects of when a viewer is close by so that the correct detail can be rendered
//knows where the camera is and generates a list of 3Ds
public class NotifyObjects implements ElementVisitor {
	
	List<Shape3D> toBeDrawn = new ArrayList<Shape3D>();
	
    @Override
  	public void visit(Node n) {
    	if (cares(n)) {
    		for (WorldObject content : n.contents) visit(content);
    		for (Node kid : n.children) visit(kid);	
    	}
  	}

  	@Override
  	public void visit(WorldObject item) {
  		toBeDrawn.addAll(item.display());
  	}

  	@Override
  	public boolean cares(Node n) {
  		double xLoc = n.x;
  		double zLoc = n.z;
  		double cameraX = QuadTree.cameraX;
  		double cameraZ = QuadTree.cameraZ;
  		if (Math.sqrt(Math.pow((xLoc - cameraX), 2) + Math.pow((zLoc - cameraZ), 2)) <= 500) return true;
  		else return false;
  	}
}
