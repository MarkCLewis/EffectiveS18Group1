package quad;

import java.util.ArrayList;
import java.util.List;
import quad.QuadTree.Node;
import virtualworld.WorldObject;
import graphicsTesting.CameraController;

//notifies objects of when a viewer is closeby so that the correct detail can be rendered
//knows where the camera is and generates a list of 3Ds
public class NotifyObjects implements ElementVisitor {

	private List<Node> passedThroughNodes = new ArrayList<Node>();
	private List<WorldObject> passedThroughObs = new ArrayList<WorldObject>();
	
    @Override
  	public void visit(Node n) {
  		passedThroughNodes.add(n);
  		//traverse to the rest of the nodes
  	}

  	@Override
  	public void visit(WorldObject item) {
  		passedThroughObs.add(item);
  		//traverse to the rest of the WorldObjects
  		//double cameraX = getCameraX();
  		//double cameraZ = getCameraZ();
  		//item.notifyOfCamera(cameraX, cameraZ);
  	}

  	@Override
  	public boolean cares(Node n) {
  		
  		return true;
  	}
}
