package virtualworld;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import citiesTesting.BuildingTypes;
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
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import quad.QuadTree;

/**
 * This is just a starter, place holder for the group.
 * 
 * For JavaFX the main will probably have keyboard control and an AnimationTimer that makes dynamic stuff happen.
 * 
 * Yes. It needs to do the initial setup. 
 * Then it also tells the quadtree when the camera has moved enough that updating is needed.
 * It probably also uses the quadtree to get the elements that should be rendered.
 */
public class Main extends Application {
	public static void main(String[] args) {
		System.out.println("Virtual world goes here.");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Create group, scene, and camera
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.GRAY);
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(camera);
		mainGroup.getChildren().add(cameraGroup);
		
		//Create CameraController pCam
		CameraController pCam = new CameraController.Builder(camera).build();
		
		//QuadTree Initialization
		QuadTree quad = new QuadTree();
		
		//insert top level terrain (one giant piece)
		
		
		//Camera Movement
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
		
		scene.setOnKeyReleased(event ->{
			KeyCode key = event.getCode();
			if(key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);
			
		});

		//Test shapes
		//Dr. Lewis's Sphere
		Sphere sphere = new Sphere(2);
		Material mat = new PhongMaterial(Color.BLUE);
		sphere.setMaterial(mat);
		sphere.setTranslateZ(10);
		mainGroup.getChildren().add(sphere);
		// TODO Your stuff goes here.

		//Tony's Building Testing
		Random rand = new Random(777);
		int a = rand.nextInt(3)+1;
		
		double x = -1000.0;
		double y = 0;
		double z = 1000.0;

		Color roof1 = BuildingTypes.colorAssignment(rand);
		Color roof2 = BuildingTypes.secondaryColor(roof1);
		Color house1 = BuildingTypes.colorAssignment(rand);
		Color house2 = BuildingTypes.secondaryColor(house1);
		BuildingTypes.makeCity1(mainGroup, roof1, roof2, house1, house2, x, y, z, rand);
		
		rand = new Random(532);
		x = 1000.0;
		y = 0;
		z = 1000.0;
		roof1 = BuildingTypes.colorAssignment(rand);
		roof2 = BuildingTypes.secondaryColor(roof1);
		house1 = BuildingTypes.colorAssignment(rand);
		house2 = BuildingTypes.secondaryColor(house1);
		BuildingTypes.makeCity2(mainGroup, roof1, roof2, house1, house2, x, y, z, rand.nextInt(3)+1, rand);
		
		rand = new Random(313);
		x = 1000.0;
		y = 0;
		z = -1000.0;
		roof1 = BuildingTypes.colorAssignment(rand);
		roof2 = BuildingTypes.secondaryColor(roof1);
		house1 = BuildingTypes.colorAssignment(rand);
		house2 = BuildingTypes.secondaryColor(house1);
		BuildingTypes.makeCity3(mainGroup, roof1, roof2, house1, house2, x, y, z, rand.nextInt(3)+1, rand);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}