package quad;

import quad.QuadTree.Node;
import virtualworld.WorldObject;

//notifies objects of when a viewer is closeby so that the correct detail can be rendered
//knows where the camera is and generates a list of 3Ds
public class NotifyObjects implements ElementVisitor {

    @Override
  	public void visit(Node n) {
  		
  	}

  	@Override
  	public void visit(WorldObject item) {
  
  	}

  	@Override
  	public boolean cares(Node n) {
  		return true;
  	}
}
