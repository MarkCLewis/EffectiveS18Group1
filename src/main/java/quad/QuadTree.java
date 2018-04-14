package quad;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.scene.Camera;
import virtualworld.WorldObject;


/**
 * terrain adds itself as top level as a single flat piece
 * traversal of the tree with the visitor (tells objects that new camera is present)
 * to start: only root node with one large terrain object
 * once notified: children created, elements added into tree (order is important)
 * insert the children and then have the visitor visit them, until the lowest level is reached
 * 
 * @author dmatthe1
 *
 */
public class QuadTree implements Element {

	private Node root;
	private int count = 0;

	//Node class stores four children, key location, and value
	static class Node {
		double x; double y;
		List<Node> children = new ArrayList<Node>();
		List<WorldObject> contents = new ArrayList<WorldObject>();
		double size;

		//Node constructor takes coordinates and a value
		public Node (double x, double y, double s) {
			this.x = x;
			this.y = y;
			this.size = s;
		}

		public double getX(Node node) {
			return node.x;
		}

		public double getY(Node node) {
			return node.y;
		}

		public static double getSize(Node node) {
			return node.size;
		}
		
		/**
		 * Gets the number of the child from a given coordinate
		 * 
		 * @param x x-coordinate
		 * @param y y-coordinate
		 * @return child the int value of the child node
		 */
		public int getChild (Double x, Double y) {
			int child = 0;
			if (x > this.x) child |= 1;
			if (y > this.y) child |= 2;
			return child;
		}
	}

	/**
     * Returns a reference to the tree's root node.  Callers shouldn't modify nodes, directly.
     *
     * @return Node The root node.
     */
	public Node getRootNode() {
		return this.root;
	}
	
	/**
	 * Returns the number of nodes in the tree
	 * 
	 * @return the number of the nodes in the tree
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * Sets the root equal to an initial value
	 * 
	 * @param item A WorldObject to be encapsulated by a node
	 * @return root The root of the QuadTree, which has just been made
	 */
	public Node insert(WorldObject item) {
		double x = item.getX();
		double y = item.getY();
		double s = item.getSize();
		count++;
		return root = new Node(x, y, s);
	}

	/**
	 * Inserts a node in the correct place, if the node passed in is null,
	 * then the WorldObject gets passed to the other insert function, if the 
	 * node is valid, then the WorldObject gets inserted into the correct child.
	 * 
	 * @param item A WorldObject to be encapsulated by a node
	 * @param n A node to encapsulate the WorldObject
	 * @return The parent node
	 */
	Node insert(WorldObject item, Node n) {
		double x = item.getX();
		double y = item.getY();
		double s = item.getSize();
		if (n == null) return insert(item);
		else {
			int child = n.getChild(x, y);
			n.children.set(child, insert(item, n));
		}
		count++;
		return n;
	}

	public static void updateCameraLocation(Camera camera) {

	}

	public static void recursiveFunc(Camera camera) {
		//(cameraX - radius) < (xMax + size/2) && (cameraX + radius) > (xMin - size/2)
	}

	public static void visitAllNeighbors(double distance) {
		// TODO Auto-generated method stub
	}

	public static void visitNeighbors(double distance) {

	}

	/**
	 * Accepts the visitor into the QuadTree and passes it to the other accept function
	 * 
	 * @param visitor the visitor being accepted
	 */
	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, root);
	}
	
	/**
	 * Checks if the visitor cares about the node, then recursively visits
	 * all of the nodes that the visitor cares about.
	 * 
	 * @param visitor the visitor being accepted
	 * @param n the node that is being visited
	 */
	private void accept(final ElementVisitor visitor, Node n) {
		if (visitor.cares(n)) {
			visitor.visit(n);
		}
	}

	/**
	 * Purely for testing purposes, prints out the structure of the QuadTree
	 */
	public void print() {
		if (root == null) {
			System.out.println("The tree is currently empty");
			return;
		} else {
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
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
				System.out.println();
			}
		}
	}
}
