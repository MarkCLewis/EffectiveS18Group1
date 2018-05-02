package quad;

import java.util.LinkedList;
import java.util.Queue;
import virtualworld.WorldObject;

public class QuadTree implements Element {

	// Singleton
	private QuadTree() {
	}

	private static class QuadTreeHolder {
		private static final QuadTree INSTANCE = new QuadTree();
	}

	public static QuadTree getInstance() {
		return QuadTreeHolder.INSTANCE;
	}

	// Private tree variables
	private Node root;
	private int depth = 0;
	private int maxDepth = 40;
	private int count = 0;

	// Private camera variables
	public static double cameraX;
	public static double cameraZ;
	// TODO private double updateDistance = 10;

	//QuadTree Functions
	/**
	 * Inserts an object in the correct place, creating nodes if necessary
	 * @param item - A WorldObject to be encapsulated by a node
	 * @param n - An initial node
	 */
	public void insert(WorldObject item, Node n) {
		if (root == null) {
			root = new Node(item.getXLoc(), item.getZLoc(), item.getSize(), 1);
			root.contents.add(item);
			count++;
			depth++;
		} else if (item.getSize() < n.size/2 && this.depth < maxDepth) {
			if (n.children.size() == 0) {
				double nodeSize = n.size;
				n.children.add(new Node(n.x - n.size/2, n.z - n.size/2, nodeSize/2, n.depth + 1));
				n.children.add(new Node(n.x + n.size/2, n.z - n.size/2, nodeSize/2, n.depth + 1));
				n.children.add(new Node(n.x - n.size/2, n.z + n.size/2, nodeSize/2, n.depth + 1));
				n.children.add(new Node(n.x + n.size/2, n.z + n.size/2, nodeSize/2, n.depth + 1));
				depth++;
				count += 4;
			}
			int child = n.getChild(item.getXLoc(), item.getZLoc());
			insert(item, n.children.get(child));
		} else {
			n.contents.add(item);
		}
	}

	//Visitor Accepts
	/**
	 * Accepts the visitor into the QuadTree and passes it to the other accept function
	 * @param visitor - the visitor being accepted
	 */
	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, root);
	}

	/**
	 * Checks if the visitor cares about the node then visits
	 * @param visitor - the visitor being accepted
	 * @param n - the node that is being visited
	 */
	private void accept(final ElementVisitor visitor, Node n) {
		visitor.visit(n);
	}

	// Getters
	/**
	 * Returns a reference to the tree's root node.
	 * @return Node The root node.
	 */
	public Node getRootNode() {
		return this.root;
	}

	/**
	 * Returns the number of nodes in the tree
	 * @return the number of the nodes in the tree
	 */
	public int getCount() {
		return this.count;
	}

	// Testing
	public void print() {
		if (root == null) {
			System.out.println("The tree is currently empty");
			return;
		} else {
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
			while (true) {
				int count = queue.size();
				if (count == 0)
					break;
				while (count > 0) {
					Node node = queue.peek();
					System.out.println(node.size + " ");
					queue.remove();
					for (Node child : node.children) {
						queue.add(child);
					}
					count--;
				}
				System.out.println();
			}
		}
	}
}