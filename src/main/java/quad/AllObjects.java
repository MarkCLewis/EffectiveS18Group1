package quad;

import java.util.ArrayList;
import java.util.List;

import virtualworld.WorldObject;

/**
 * Visitor to capture all items (Nodes and WorldObjects)
 * @author dmatthe1
 */
public class AllObjects implements ElementVisitor {

	public List<Node> allNodes = new ArrayList<Node>();
	public List<WorldObject> allWorldObjects = new ArrayList<WorldObject>();
	
	@Override
	public void visit(Node n) {
		System.out.println("Parent      x: " + n.x + " z: " + n.z + " size: " + n.size + " depth: " + n.depth);
		allNodes.add(n);
		for (WorldObject content : n.contents) visit(content);
		for (Node kid : n.children) {
			System.out.println("Kid   	   x: " + kid.x + " z: " + kid.z + " size: " + kid.size + " depth: " + kid.depth + " numChildren: " + n.children.size());
			visit(kid);	
		}
	}

	@Override
	public void visit(WorldObject item) {
		allWorldObjects.add(item);
	}

	@Override
	public boolean cares(Node n) {
		return true;
	}
}
