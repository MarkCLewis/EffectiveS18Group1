package quad;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Shape3D;
import virtualworld.WorldObject;

//notifies objects of when a viewer is close by so that the correct detail can be rendered
//knows where the camera is and generates a list of 3Ds
public class NotifyObjects implements ElementVisitor {
	
	//List of all Shapes3Ds to be drawn
	public List<Shape3D> toBeDrawn = new ArrayList<Shape3D>();
	
	//Iterates through the QuadTree and visits relevant WorldObjects
    @Override
  	public void visit(Node n) {
    	if (cares(n)) {
    		for (WorldObject content : n.contents) visit(content);
    		for (Node kid : n.children) visit(kid);	
    	}
  	}

    //Adds all relevant WorldObjects to the list
  	@Override
  	public void visit(WorldObject item) {
  		toBeDrawn.addAll(item.display());
  	}

  	//Cares if Euclidean distance is less than 500 meters
  	@Override
  	public boolean cares(Node n) {
  		if (Math.sqrt(Math.pow((n.x - QuadTree.cameraX), 2) + Math.pow((n.z - QuadTree.cameraZ), 2)) <= 500) return true;
  		else return false;
  	}
}
