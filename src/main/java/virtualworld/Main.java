package virtualworld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import animals.Sheep;
import citiesTesting.CityOne;
import graphicsTesting.CameraController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;
import quad.ElementVisitor;
import quad.NotifyObjects;
import quad.QuadTree;

public class Main extends Application {
	static List<WorldObject> itemRendered = new ArrayList<WorldObject>();
	static List<Shape3D> toBeDrawn = new ArrayList<Shape3D>();
	static ArrayList<ElementVisitor> visitList = new ArrayList<ElementVisitor>();
	private double x1;
	private double z1;
	private double x2;
	private double z2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	//Scene Setup
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.THISTLE);
	
	//Camera Setup
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		CameraController pCam = new CameraController.Builder(camera).build();
		x1 = pCam.getCameraX();
		z1 = pCam.getCameraZ();
		
		
	//QuadTree Setup
		QuadTree quad = QuadTree.getInstance();
		QuadTree.cameraX = pCam.getCameraX();
		QuadTree.cameraZ = pCam.getCameraZ();
		
		
		CityOne exampleCity = CityOne.returnObj(mainGroup);
		
		quad.insert(new ExampleObject(0, 0, 6000), null); //TODO replace with large terrain piece
		quad.insert(exampleCity, quad.getRootNode());
		
		System.out.println(itemRendered.size());
		
	//Visitors
		NotifyObjects renderCollector = new NotifyObjects();
		visitList.add((ElementVisitor) renderCollector);
		renderCollector.visit(quad.getRootNode());
		for (WorldObject items : renderCollector.validObjects) {
			itemRendered.add((WorldObject) items);
			toBeDrawn.addAll(items.display());
		}
		
		System.out.println(itemRendered.size());
		
		//String[] args = null;
		//Group buildingGroup = new Group();
		//CityMaker.start();
		
		//mainGroup.getChildren().add(buildingGroup);
		mainGroup.getChildren().add(cameraGroup);
		mainGroup.getChildren().addAll(toBeDrawn);
		
		//Calls appropriate movement methods from pCam when key press is detected
		//KeyCodes are stored in a set so multiple commands can be executed at once
		Set<KeyCode> keySet = new HashSet<KeyCode>();
		scene.setOnKeyPressed(event ->{ 
			 KeyCode key = event.getCode();
			 keySet.add(key);
			 
			 if(keySet.contains(KeyCode.W)) {
				 pCam.moveForward();
			 }
			 if(keySet.contains(KeyCode.S)) {
				 pCam.moveBackward();
			 }
			 if(keySet.contains(KeyCode.A)) {
				 pCam.moveLeft();
			 }
			 if(keySet.contains(KeyCode.D)) {
				 pCam.moveRight();
			 }
			 if(keySet.contains(KeyCode.RIGHT)) {
				 pCam.rotateRight();
			 }
			 if(keySet.contains(KeyCode.LEFT)) {
				 pCam.rotateLeft();
			 }
			 if(keySet.contains(KeyCode.UP)) {
				 pCam.rotateUp();
			 }
			 if(keySet.contains(KeyCode.DOWN)) {
				 pCam.rotateDown();
			 }
			 if(keySet.contains(KeyCode.R)) {
				 pCam.moveUp();
			 }
			 if(keySet.contains(KeyCode.F)) {
				 pCam.moveDown();
			 }
			 if(keySet.contains(KeyCode.SHIFT)) {
				 pCam.boostOn();
			 }
		});
		
		//If a key is released, it is removed from the set and the associated method stops being called
		//Manually disables boost
		scene.setOnKeyReleased(event ->{
			KeyCode key = event.getCode();
			if(key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);
			
		});
		
		/*Cities
		//Making city objects
		CityOne cOne = CityOne.returnObj(mainGroup);
		//grid type city
		CityTwo cTwo = CityTwo.returnObj(mainGroup);
		//circular city
		CityThree cThree = CityThree.returnObj(mainGroup);
		//diamond city
		*/
		
		/*Terrain
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 5; 
		double z = 10; 
		double y = 12;
		double xW = 200; 
		double zW = 200; 
		double yW = 500;
		long seed = 12345687654L;
		double noise = 0.75;
		bldr.setXLoc(x);
		bldr.setYLoc(y);
		bldr.setZLoc(z);
		bldr.setXWidth(xW);
		bldr.setYWidth(yW);
		bldr.setZWidth(zW);
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		
		TerrainObject terr = bldr.build();
		mainGroup.getChildren().addAll(terr.display());
		*/
		
		/*
		CityOne cOne = CityOne.returnObj(mainGroup);
		System.out.println(cOne.getX() + " " + cOne.getZ());
		System.out.println(cOne.display().size());
		for (Shape3D shape : cOne.display()) {
			mainGroup.getChildren().add(shape);
		}
		*/
		//List<sheep> sheepList = new ArrayList<sheep>();
		//for (int i = 0; i < 100; i++) sheepList.add(animals.sheep.returnObj(mainGroup));
		Sheep sheeps = animals.Sheep.returnObj(mainGroup);
		//for (sheep sheeps : sheepList) {
		for (Shape3D sheepShape : sheeps.display()) {
			mainGroup.getChildren().add(sheepShape);
		}
		//}
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				x2 = pCam.getCameraX();
				z2 = pCam.getCameraZ();
				
				if(euclid(x1,x2,z1,z2)) {
					itemRendered.clear();
					toBeDrawn.clear();
					mainGroup.getChildren().clear();
					mainGroup.getChildren().add(cameraGroup);
				}
			}
		}.start();
	}
	
	private boolean euclid(double x1, double x2, double z1, double z2) {
		return (Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(z2-z1, 2)) > 10);
	}
	
}


	

//Key Controls
	/*
	 * W - Forward
	 * S - Backward
	 * A - Left
	 * D - Right
	 * Up - Look up
	 * R - Ascend
	 * F - Descend
	 * Down - Look Down
	 * Left - Look Left
	 * Right - Look Right
	 * Hold Shift - Speed Boost
	 */