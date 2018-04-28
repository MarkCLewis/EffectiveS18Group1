package quad;

import java.util.ArrayList;
import java.util.List;

import virtualworld.WorldObject;

public class allObjects implements ElementVisitor {

	private List<Node> allNodes = new ArrayList<Node>();
	private List<WorldObject> allWorldObjects = new ArrayList<WorldObject>();
	
	@Override
	public void visit(Node n) {
		allNodes.add(n);
		for (Node kids : n.children) {
			visit(kids);
		}
	}

	@Override
	public void visit(WorldObject item) {
		
		
	}

	@Override
	public boolean cares(Node n) {
		
		return false;
	}

}
