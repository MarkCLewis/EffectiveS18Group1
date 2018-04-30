package quad;

import java.util.ArrayList;
import java.util.List;

import virtualworld.WorldObject;

//Visitor to capture all items (Nodes and WorldObjects)
public class AllObjects implements ElementVisitor {

	//Lists to collect all Nodes and WorldObjects
	public List<Node> allNodes = new ArrayList<Node>();
	public List<WorldObject> allWorldObjects = new ArrayList<WorldObject>();
	
	//Adds all Nodes and WorldObjects recursively, adding them to the public lists abov
	@Override
	public void visit(Node n) {
		allNodes.add(n);
		for (WorldObject content : n.contents) visit(content);
		for (Node kid : n.children) visit(kid);	
	}

	//Adds all WorldObjects through Node visit
	@Override
	public void visit(WorldObject item) {
		allWorldObjects.add(item);
	}

	//Always cares because collecting everything
	@Override
	public boolean cares(Node n) {
		return true;
	}
}

//Testing
//System.out.println("Parent      x: " + n.x + " z: " + n.z + " size: " + n.size + " depth: " + n.depth);
//System.out.println("Kid   	   x: " + kid.x + " z: " + kid.z + " size: " + kid.size + " depth: " + kid.depth + " numChildren: " + n.children.size());