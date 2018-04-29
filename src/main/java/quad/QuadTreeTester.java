package quad;

import quad.Node;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadTreeTester {
	
	public static QuadTree addToTree(QuadTree quad) {
		WorldObject testObject1 = new ExampleObject(50, 50, 400);
		WorldObject testObject2 = new ExampleObject(5, 5, 5);
		WorldObject testObject3 = new ExampleObject(76, 80, 5);
		quad.insert(testObject1);
		quad.insert(testObject2, quad.getRootNode());
		quad.insert(testObject3, quad.getRootNode());
	
		WorldObject realisticObject = new ExampleObject(834063, 834063, 100);
		quad.insert(realisticObject);
		
		return quad;
	}
	
	public static void main(String[] args) {
		QuadTree quad = QuadTree.getInstance();
		QuadTree addedQuad = addToTree(quad);
		addedQuad.print();
		System.out.println(quad.getCount());
	}
}