package quad;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Shape3D;
import virtualworld.WorldObject;

//Notifies objects of when a viewer is close by so that the correct detail can be rendered
//Knows where the camera is and generates a list of 3Ds
public class NotifyObjects implements ElementVisitor {
	
	//List of all Shapes3Ds to be drawn
	public List<Shape3D> toBeDrawn = new ArrayList<Shape3D>();
	
	//List of all WorldObjects within range
	public List<WorldObject> validObjects = new ArrayList<WorldObject>();
	
	//Camera Variables
	double xCam = QuadTree.cameraX;
	double zCam = QuadTree.cameraZ;
	
	//Iterates through the QuadTree and visits relevant WorldObjects
    @Override
  	public void visit(Node n) {
    	if (euclideanCheck (n)) {
    		for (WorldObject content : n.contents) visit(content);
    		for (Node kid : n.children) visit(kid);	
    	}
    }

    //Adds all relevant WorldObjects to the list
  	@Override
  	public void visit(WorldObject item) {
  		boolean notified = item.notifyOfCamera(xCam, zCam);
  		if (circleCheck(item) && notified) {
	  		validObjects.add(item);
	  		toBeDrawn.addAll(item.display());
  		}
  	}
  	
  	public boolean circleCheck(WorldObject item) {
  		double xLoc = item.getXLoc();
  		double zLoc = item.getZLoc();
  		double itemR = item.getSize();
  		double camR = 2000;
  		
  		double distance = Math.sqrt(Math.pow((xLoc - xCam), 2) + Math.pow((zLoc - zCam), 2));
  		return (camR > (distance + itemR));
  	}
  	
  	public boolean euclideanCheck(Node n) {
  		return (Math.sqrt(Math.pow((n.x - xCam), 2) + Math.pow((n.z - zCam), 2)) <= 10000);
  	}
}
