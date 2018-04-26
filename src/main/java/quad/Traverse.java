package quad;

import java.util.LinkedList;
import java.util.Queue;

import quad.Node;
import virtualworld.WorldObject;

public class Traverse implements ElementVisitor{

	@Override
	public void visit(Node n) {
		//System.out.println("Node " + Node.getSize(n) + " ");
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(n);
		while (true) {
			int count = queue.size();
			if (count == 0) break;
			while (count > 0) {
				Node node = queue.peek();
				System.out.println(node.size + " ");
				queue.remove();
				for (Node child : node.children) {
					queue.add(child);
				}
				count--;
			}
		}
		System.out.println();
	}

	@Override
	public void visit(WorldObject item) {
		System.out.println("WorldObject " + item.getSize() + " ");
	}

	@Override
	public boolean cares(Node n) {
		
		return true;
	}

}
