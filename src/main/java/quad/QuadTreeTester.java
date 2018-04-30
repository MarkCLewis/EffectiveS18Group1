package quad;

import citiesTesting.CityOne;
import javafx.scene.Group;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadTreeTester {
	
	public static QuadTree addToTree(QuadTree quad) {
		WorldObject realisticObject = new ExampleObject(300, 300, 100);
		WorldObject smallerObject = new ExampleObject(200, 200, 30);
		quad.insert(realisticObject, null);
		System.out.println(quad.getRootNode().contents.size());
		quad.insert(smallerObject, quad.getRootNode());
		System.out.println(quad.getRootNode().contents.size());
		
		Group mainGroup = new Group();
		CityOne cOne = CityOne.returnObj(mainGroup);
		//quad.insert(cOne);
		return quad;
	}
	
	public static void main(String[] args) {
		QuadTree quad = QuadTree.getInstance();
		QuadTree addedQuad = addToTree(quad);
		addedQuad.print();
		System.out.println(addedQuad.getCount());
		System.out.println(addedQuad.getRootNode().contents.size());
	}
}