package quad;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import quad.Node;
import virtualworld.WorldObject;
import graphicsTesting.CameraController;

//notifies objects of when a viewer is close by so that the correct detail can be rendered
//knows where the camera is and generates a list of 3Ds
public class CollectObjects implements ElementVisitor {

	private Queue<Node> passedThroughNodes = new LinkedList<Node>();
	private List<WorldObject> passedThroughObs = new ArrayList<WorldObject>();
	
    @Override
  	public void visit(Node n) {
  		passedThroughNodes.add(n);
  		while (true) {
			int count = passedThroughNodes.size();
			if (count == 0) break;
			while (count > 0) {
				Node node = passedThroughNodes.peek();
				System.out.println(node.size + " ");
				passedThroughNodes.remove();
				for (Node child : node.children) {
					passedThroughNodes.add(child);
				}
				count--;
			}
		}
  		//traverse to the rest of the nodes
  	}

  	@Override
  	public void visit(WorldObject item) {
  		//if (within 100 meters of camera)
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
