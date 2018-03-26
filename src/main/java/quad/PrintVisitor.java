package quad;

import quad.QuadTree.Node;
import virtualworld.WorldObject;

public class PrintVisitor implements ElementVisitor {

	@Override
	public void visit(Node n) {
		//print something out that is parenthesized
		//maybe hashcode of the node and all the objects in it
		System.out.println("This is a node");
	}

	@Override
	public void visit(WorldObject item) {
		System.out.println("This is a WorldObject");
	}

	@Override
	public boolean cares(Node n) {
		return true;
	}
}
