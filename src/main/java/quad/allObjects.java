package quad;

import java.util.ArrayList;
import java.util.List;

import virtualworld.WorldObject;

/**
 * Visitor to capture all items (Nodes and WorldObjects)
 * @author dmatthe1
 */
public class allObjects implements ElementVisitor {

	public List<Node> allNodes = new ArrayList<Node>();
	public List<WorldObject> allWorldObjects = new ArrayList<WorldObject>();
	
	@Override
	public void visit(Node n) {
		allNodes.add(n);
		for (Node kids : n.children) {
			visit(kids);
			for (WorldObject content : n.contents) {
				visit(content);
			}
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
