package quad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import virtualworld.ExampleObject;
import virtualworld.WorldObject;

public class QuadGrid extends Application implements Element {

	// Scene variables
	private static final int width = 1000;
	private static final int height = 1000;

	// Camera movement variables
	private int boost = 1;
	private boolean showCamera = false;
	private boolean showRendered = false;
	private boolean showPerson = false;
	
	// Quadtree variables
	static QuadTree quad = QuadTree.getInstance();

	// Various lists
	static List<Node> quadNodes = new ArrayList<Node>();
	static List<ExampleObject> quadObjects = new ArrayList<ExampleObject>();
	static List<ExampleObject> toBeRendered = new ArrayList<ExampleObject>();
	List<ElementVisitor> visitorList;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Scene Setup
		primaryStage.setTitle("QuadTree Test");
		Group root = new Group();
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		populate();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, width, height, true);
		primaryStage.setScene(scene);
		primaryStage.show();

		// Key Controls
		Set<KeyCode> keySet = new HashSet<KeyCode>();

		scene.setOnKeyPressed(event -> {
			KeyCode key = event.getCode();
			keySet.add(key);
			if (keySet.contains(KeyCode.UP))
				QuadTree.cameraZ -= 5 * boost;
			if (keySet.contains(KeyCode.DOWN))
				QuadTree.cameraZ += 5 * boost;
			if (keySet.contains(KeyCode.RIGHT))
				QuadTree.cameraX += 5 * boost;
			if (keySet.contains(KeyCode.LEFT))
				QuadTree.cameraX -= 5 * boost;
			if (keySet.contains(KeyCode.SHIFT))
				boost = 5;
			if (keySet.contains(KeyCode.C))
				showCamera = !showCamera;
			if (keySet.contains(KeyCode.V))
				showRendered = !showRendered;
			if (keySet.contains(KeyCode.X))
				showPerson = !showPerson;
		});

		scene.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			keySet.remove(key);
			if (keySet.contains(KeyCode.SHIFT))
				boost = 1;
		});

		// Animation Timer for drawing
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				drawShapes(gc);
			}
		}.start();
	}

	private void drawShapes(GraphicsContext gc) {
		
		// Clear the rectangles between frames
		gc.clearRect(0, 0, width, height);
		toBeRendered.clear();
		
		// Visitor to collect objects to be rendered
		NotifyObjects renderCollector = new NotifyObjects();
		renderCollector.visit(quad.getRootNode());
		for (WorldObject items : renderCollector.validObjects)
			toBeRendered.add((ExampleObject) items);

		gc.setFill(Color.BLANCHEDALMOND);

		// Nodes
		gc.setLineWidth(1);
		gc.setStroke(Color.BLACK);
		for (Node n : quadNodes) {
			double x = n.x - n.size + 500;
			double z = n.z - n.size + 500;
			gc.strokeRect(x, z, n.size * 2, n.size * 2);
		}

		// WorldObjects
		gc.setStroke(Color.DARKCYAN);
		for (ExampleObject obj : quadObjects) {
			double radius = obj.getRadius();
			double x = obj.getXLoc() + 500;
			double z = obj.getZLoc() + 500;
			gc.strokeOval(x - radius, z - radius, radius * 2, radius * 2);
		}

		// Rendered WorldObjects
		gc.setLineWidth(3);
		gc.setStroke(Color.FORESTGREEN);
		for (ExampleObject obj : toBeRendered) {
			double radius = obj.getRadius() + 3;
			double x = obj.getXLoc() + 500;
			double z = obj.getZLoc() + 500;
			if (showRendered) gc.strokeOval(x - radius, z - radius, radius * 2, radius * 2);
		}
		
		//Viewer
		if (showPerson) {
			gc.setFill(Color.CHOCOLATE);
			gc.fillOval(QuadTree.cameraX+495, QuadTree.cameraZ+495, 10, 10);
		}
		
		// Camera
		if (showCamera) {
			gc.setLineWidth(5);
			gc.setStroke(Color.CHOCOLATE);
			gc.strokeOval(QuadTree.cameraX+200,QuadTree.cameraZ+200,600,600);
		}
	}

	public static void populate() {
		
		Random rand = new Random();
		
		//Initial Object
		ExampleObject firstOb = new ExampleObject(0, 0, 500);
		quad.insert(firstOb, null);
		
		//Random Objects
		for (int i = 0; i < 50; i++) {
			ExampleObject testObject = new ExampleObject(rand.nextInt(400 + 1 + 400) - 400,
					rand.nextInt(400 + 1 + 400) - 400, rand.nextInt(100));
			quad.insert(testObject, quad.getRootNode());
		}
		
		// Visitor to collect all Nodes and WorldObjects
		AllObjects nodeCollector = new AllObjects();
		nodeCollector.visit(quad.getRootNode());
		for (Node nodes : nodeCollector.allNodes) quadNodes.add(nodes);
		for (WorldObject items : nodeCollector.allWorldObjects) quadObjects.add((ExampleObject) items);
	}

	@Override
	public void accept(final ElementVisitor visitor) {
		accept(visitor, quad.getRootNode());
	}

	private void accept(final ElementVisitor visitor, Node n) {
		visitor.visit(n);
	}
}