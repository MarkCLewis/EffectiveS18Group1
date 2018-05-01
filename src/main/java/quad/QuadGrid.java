package quad;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadGrid extends Application implements Element {

	//Scene variables
	private static final int width = 1000;
	private static final int height = 1000;
	
	//Quadtree variables
	static QuadTree quad = QuadTree.getInstance();
	
	//Various Lists
	static List<Node> quadNodes = new ArrayList<Node>();
	static List<ExampleObject> quadObjects = new ArrayList<ExampleObject>();
	static List<ExampleObject> tobeRendered = new ArrayList <ExampleObject>();
 	List<ElementVisitor> visitorList;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("QuadTree Test");
		Group root = new Group();
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		populate();
		drawShapes(gc);
		root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        System.out.println("Number of Nodes: " + quadNodes.size());
	}	
     
	private void drawShapes(GraphicsContext gc) {
		gc.setFill(Color.BLANCHEDALMOND);
		
		//Nodes
		gc.setLineWidth(1);
		gc.setStroke(Color.BLACK);
		for (Node n : quadNodes) {
			double x = n.x - n.size + 500;
			double z = n.z - n.size + 500;
			gc.strokeRect(x, z, n.size*2, n.size*2);
			System.out.println(n.x+" "+n.z+" "+n.size);
			//System.out.println("x: " + x + " z: " + z);
		}
		
		//WorldObjects
		gc.setStroke(Color.RED);
		for(ExampleObject obj : quadObjects) {
			double radius = obj.getRadius();
			double x = obj.getXLoc() + 500;
			double z = obj.getZLoc() + 500;
			System.out.println(obj.getSize());
			gc.strokeOval(x-radius, z-radius, radius*2, radius*2);
		}
		
		gc.setStroke(Color.BLUE);
		for (ExampleObject obj : tobeRendered) {
			double radius = obj.getRadius() + 2;
			double x = obj.getXLoc() + 500;
			double z = obj.getZLoc() + 500;
			System.out.println(obj.getSize());
			gc.strokeOval(x-radius, z-radius, radius*2, radius*2);
		}
		
		gc.setStroke(Color.DEEPPINK);
		gc.setLineWidth(5);
		gc.strokeOval(quad.cameraX, quad.cameraZ, 600, 600);
	}

	public static void populate() {
		
		QuadTree.cameraX = 0;
		QuadTree.cameraZ = 0;
		
		Random rand = new Random();
		ExampleObject firstOb = new ExampleObject(0, 0, 500);
		quad.insert(firstOb, null);
		for (int i = 0; i < 50; i++) {
			ExampleObject testObject = new ExampleObject(rand.nextInt(400 + 1 + 400) - 400, rand.nextInt(400 + 1 + 400) - 400, rand.nextInt(100));
			quad.insert(testObject, quad.getRootNode());
		}
//		ExampleObject topLeft = new ExampleObject (0, 0, 20);
//		quad.insert(topLeft, quad.getRootNode());
		AllObjects nodeCollector = new AllObjects();
		nodeCollector.visit(quad.getRootNode());
		
		for (Node nodes : nodeCollector.allNodes) {
			quadNodes.add(nodes);
		}
		
		for (WorldObject items : nodeCollector.allWorldObjects) {
			quadObjects.add((ExampleObject) items);
		}
		
		NotifyObjects renderCollector = new NotifyObjects();
		renderCollector.visit(quad.getRootNode());
		
		for (WorldObject items : renderCollector.validObjects) {
			tobeRendered.add((ExampleObject) items);
		}
		
		System.out.println(quadNodes.size());
	}
	
	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, quad.getRootNode());
	}
	
	private void accept(final ElementVisitor visitor, Node n) {
		visitor.visit(n);
	}
}