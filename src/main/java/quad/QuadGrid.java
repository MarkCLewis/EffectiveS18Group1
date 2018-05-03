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

	private int boost = 1;

	// Quadtree variables
	static QuadTree quad = QuadTree.getInstance();

	// Various Lists
	static List<Node> quadNodes = new ArrayList<Node>();
	static List<ExampleObject> quadObjects = new ArrayList<ExampleObject>();
	static List<ExampleObject> toBeRendered = new ArrayList<ExampleObject>();
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
		root.getChildren().add(canvas);
		Scene scene = new Scene(root, width, height, true);
		primaryStage.setScene(scene);
		primaryStage.show();

		Set<KeyCode> keySet = new HashSet<KeyCode>();

		scene.setOnKeyPressed(event -> {
			KeyCode key = event.getCode();
			keySet.add(key);
			System.out.println("Key Pressed");
			if (keySet.contains(KeyCode.UP))
				QuadTree.cameraZ -= 5*boost;
			if (keySet.contains(KeyCode.DOWN))
				QuadTree.cameraZ += 5*boost;
			if (keySet.contains(KeyCode.RIGHT))
				QuadTree.cameraX += 5*boost;
			if (keySet.contains(KeyCode.LEFT))
				QuadTree.cameraX -= 5*boost;
			if (keySet.contains(KeyCode.SHIFT))
				boost = 5;
		});

		scene.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			keySet.remove(key);
			if (keySet.contains(KeyCode.SHIFT))
				boost = 1;
		});

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				drawShapes(gc);
			}
		}.start();

		System.out.println("Number of Nodes: " + quadNodes.size());
	}

	private void drawShapes(GraphicsContext gc) {
		gc.clearRect(0, 0, width, height);

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

		gc.setLineWidth(3);
		gc.setStroke(Color.FORESTGREEN);
		for (ExampleObject obj : toBeRendered) {
			double radius = obj.getRadius() + 3;
			double x = obj.getXLoc() + 500;
			double z = obj.getZLoc() + 500;
			gc.strokeOval(x - radius, z - radius, radius * 2, radius * 2);
		}

		// Camera
		gc.setStroke(Color.CHOCOLATE);
		gc.setLineWidth(5);
		gc.strokeOval(QuadTree.cameraX + 200, QuadTree.cameraZ + 200, 600, 600);
		System.out.println(QuadTree.cameraX + " " + QuadTree.cameraZ);

		// List Clears
		toBeRendered.clear();
	}

	public static void populate() {
		Random rand = new Random();
		ExampleObject firstOb = new ExampleObject(0, 0, 500);
		quad.insert(firstOb, null);
		for (int i = 0; i < 50; i++) {
			ExampleObject testObject = new ExampleObject(rand.nextInt(400 + 1 + 400) - 400,
					rand.nextInt(400 + 1 + 400) - 400, rand.nextInt(100));
			quad.insert(testObject, quad.getRootNode());
		}
		// ExampleObject topLeft = new ExampleObject (0, 0, 20);
		// quad.insert(topLeft, quad.getRootNode());
		AllObjects nodeCollector = new AllObjects();
		nodeCollector.visit(quad.getRootNode());

		for (Node nodes : nodeCollector.allNodes) {
			quadNodes.add(nodes);
		}

		for (WorldObject items : nodeCollector.allWorldObjects) {
			quadObjects.add((ExampleObject) items);
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