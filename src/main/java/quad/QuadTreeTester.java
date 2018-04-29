package quad;

import quad.Node;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadTreeTester {
	
	public static QuadTree addToTree(QuadTree quad) {
		WorldObject realisticObject = new ExampleObject(834063, 834063, 100);
		WorldObject smallerObject = new ExampleObject(200, 200, 0);
		quad.insert(realisticObject);
		quad.insert(smallerObject, quad.getRootNode());
		
		return quad;
	}
	
	public static void main(String[] args) {
		QuadTree quad = QuadTree.getInstance();
		QuadTree addedQuad = addToTree(quad);
		addedQuad.print();
		System.out.println(quad.getCount());
	}
}