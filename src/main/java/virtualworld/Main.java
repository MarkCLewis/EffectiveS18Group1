package virtualworld;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import citiesTesting.BuildingTypes;
import citiesTesting.CityOne;
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
 * For JavaFX the main will probably have keyboard control and an AnimationTimer that makes dynamic stuff happen.
 * 
 * Yes. It needs to do the initial setup. 
 * Then it also tells the QuadTree when the camera has moved enough that updating is needed.
 * It probably also uses the QuadTree to get the elements that should be rendered.
 */
public class Main extends Application {
	public static void main(String[] args) {
		System.out.println("Virtual world goes here.");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	//Scene
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.BLANCHEDALMOND);
	
	//QuadTree
		QuadTree quad = QuadTree.getInstance();
		//TODO insert top level terrain (one giant piece)
		
	//Visitors
		//PrintVisitor printTest = new PrintVisitor();
		//NotifyObjects camVisitor = new NotifyObjects();
		//Traverse printTest = new Traverse();
		//quad.accept(printTest);
		//quad.accept(camVisitor);
		
	//Camera
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		CameraController pCam = new CameraController.Builder(camera).build();
		
		//String[] args = null;
		//Group buildingGroup = new Group();
		//CityMaker.start();
		
		//mainGroup.getChildren().add(buildingGroup);
		mainGroup.getChildren().add(cameraGroup);
		
		
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

	//Tony's Building Testing
		/*
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
		
		Random rand1 = new Random();
		x = 1000.0;
		y = 0;
		z = 1000.0;
		roof1 = BuildingTypes.colorAssignment(rand1);
		roof2 = BuildingTypes.secondaryColor(roof1);
		house1 = BuildingTypes.colorAssignment(rand1);
		house2 = BuildingTypes.secondaryColor(house1);
		BuildingTypes.makeCity2(mainGroup, roof1, roof2, house1, house2, x, y, z, rand1.nextInt(3)+1, rand1);
		
		Random rand2 = new Random();
		x = 1000.0;
		y = 0;
		z = -1000.0;
		roof1 = BuildingTypes.colorAssignment(rand2);
		roof2 = BuildingTypes.secondaryColor(roof1);
		house1 = BuildingTypes.colorAssignment(rand2);
		house2 = BuildingTypes.secondaryColor(house1);
		BuildingTypes.makeCity3(mainGroup, roof1, roof2, house1, house2, x, y, z, rand2.nextInt(3)+1, rand2);
		*/
		CityOne co = CityOne.returnObj(mainGroup);
	//Dr. Lewis's Sphere
		Sphere sphere1 = new Sphere(10);
		Material mat1 = new PhongMaterial(Color.FORESTGREEN);
		sphere1.setMaterial(mat1);
		sphere1.setTranslateZ(100);
		mainGroup.getChildren().add(sphere1);
				
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}