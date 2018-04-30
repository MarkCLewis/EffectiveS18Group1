package virtualworld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import animals.sheep;
import citiesTesting.BuildingTypes;
import citiesTesting.CityOne;
import citiesTesting.CityThree;
import citiesTesting.CityTwo;
import graphicsTesting.CameraController;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import quad.QuadTree;
import terraintesting.TerrainObject;
import terraintesting.TerrainObjectBuilder;

public class Main extends Application {
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
	
	//QuadTree Setup
		QuadTree quad = QuadTree.getInstance();
		quad.cameraX = pCam.getCameraX();
		quad.cameraZ = pCam.getCameraZ();
		quad.insert(new ExampleObject(0, 0, 400), null); //TODO replace with large terrain piece
		
	//Visitors
		//PrintVisitor printTest = new PrintVisitor();
		//NotifyObjects camVisitor = new NotifyObjects();
		//Traverse printTest = new Traverse();
		//quad.accept(printTest);
		//quad.accept(camVisitor);
		
		
		//String[] args = null;
		//Group buildingGroup = new Group();
		//CityMaker.start();
		
		//mainGroup.getChildren().add(buildingGroup);
		mainGroup.getChildren().add(cameraGroup);
		
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
		
		CityOne cOne = CityOne.returnObj(mainGroup);
		System.out.println(cOne.getX() + " " + cOne.getZ());
		System.out.println(cOne.display().size());
		for (Shape3D shape : cOne.display()) {
			mainGroup.getChildren().add(shape);
		}
		
		//List<sheep> sheepList = new ArrayList<sheep>();
		//for (int i = 0; i < 100; i++) sheepList.add(animals.sheep.returnObj(mainGroup));
		sheep sheeps = animals.sheep.returnObj(mainGroup);
		//for (sheep sheeps : sheepList) {
		for (Shape3D sheepShape : sheeps.display()) {
			mainGroup.getChildren().add(sheepShape);
		}
		//}
		
		primaryStage.setScene(scene);
		primaryStage.show();
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