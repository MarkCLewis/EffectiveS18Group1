package animals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import graphicsTesting.CameraController;
import graphicsTesting.DrawFacade;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PigMaker extends Application{
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.GRAY);
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(camera);
		mainGroup.getChildren().add(cameraGroup);
		
		CameraController pCam = new CameraController.Builder(camera).build();
		
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
		
		/*ArrayList<Shape3D> tempList = new ArrayList<Shape3D>();
		tempList.add(bodyShapes.createCylinder(.2, 1, 1.5, 0, .5));
		tempList.add(bodyShapes.createCylinder(.2, 1, 1.5, 0, 1.5));
		tempList.add(bodyShapes.createCylinder(.2, 1, 0, 0, 1.5));
		tempList.add(bodyShapes.createCylinder(.2, 1, 0, 0, .5));
		// so 0-4 are the limbs
		tempList.add(bodyShapes.createBox(2, 1, 1.5, .75, -1, 1));
		tempList.add(bodyShapes.createBox(.7, .7, .7, 2, -1.75, 1));
		sheep testSheep = new sheep();
		sheep.list.addAll(tempList);
		
		// this is hardcoded for testing purposes. Sheep needs an shape3d array of the limbs, so that they can rotate
		sheep.limbs.add(tempList.get(0));
		sheep.limbs.add(tempList.get(1));
		sheep.limbs.add(tempList.get(2));
		sheep.limbs.add(tempList.get(3));
		
		ArrayList<RotateTransition> rt = new ArrayList<RotateTransition>();
		
		// Again, this is just temporary.
		// Ideally, sheep should have A parallel transition field and an animalTransition field (plus getters and setters for each)
		
		for(Shape3D sd : sheep.limbs)
		{
			RotateTransition temp = new RotateTransition(Duration.millis(500), sd);
			temp.setByAngle(90);
			temp.setAutoReverse(true);
			temp.setCycleCount(temp.INDEFINITE);
			rt.add(temp);
		}
		// also making the head move, why not
		RotateTransition head = new RotateTransition(Duration.millis(2000), sheep.list.get(5));
		head.setByAngle(50);
		head.setAutoReverse(true);
		head.setCycleCount(head.INDEFINITE);
		
		animalTransition moveTest = new animalTransition(testSheep);
		ParallelTransition unison = new ParallelTransition(rt.get(0), rt.get(1), rt.get(2), rt.get(3), moveTest, head);
		//moveTest.setCycleCount(moveTest.INDEFINITE);
		unison.play();
		//moveTest.play();
*/		

		//int[] transCords = {0,0,0};
        //Sheep
		mainGroup.getChildren().add(bodyShapes.createCylinder(.2, 1, 16.5, 0, .5)); //LFL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.2, 1, 16.5, 0, 1.5)); //RFL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.2, 1, 15, 0, 1.5)); //RBL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.2, 1, 15, 0, .5));  //LBL
		mainGroup.getChildren().add(bodyShapes.createBox(2, 1, 1.5, 15.75, -1, 1)); //body
		mainGroup.getChildren().add(bodyShapes.createBox(.7, .7, .7, 17, -1.75, 1)); //head
		
		//Chicken
		mainGroup.getChildren().add(bodyShapes.createCylinder(.07, .4, 10, 0, .85)); //RBL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.07, .4, 10, 0, .55));  //LBL
		mainGroup.getChildren().add(bodyShapes.createBox(.5, .5, .5, 10.1, -.4, .7)); //body
		mainGroup.getChildren().add(bodyShapes.createBox(.3, .5, .3, 10.2, -.8, .7)); //head
		mainGroup.getChildren().add(bodyShapes.createBox(.3, .1, .2, 10.06, .2, .55)); //LFoot
		mainGroup.getChildren().add(bodyShapes.createBox(.3, .1, .2, 10.06, .2, .85)); //RFoot
		mainGroup.getChildren().add(bodyShapes.createBox(.25, .08, .2, 10.45, -.75, .71)); //Top beak
		mainGroup.getChildren().add(bodyShapes.createBox(.25, .08, .2, 10.45, -.85, .71)); //Bottom beak
		mainGroup.getChildren().add(bodyShapes.createBox(.4, .3, .08, -10.1, -.35, 1)); //Right wing
		mainGroup.getChildren().add(bodyShapes.createBox(.4, .3, .08, -10.1, -.35, .40)); //Left wing
		
		//Giraffe
		mainGroup.getChildren().add(bodyShapes.createCylinder(.18, 2.2, 6.25, 0, 0.5)); //lfL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.18, 2.2, 6.25, 0, 1)); //rfL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.18, 2.2, 5, 0, 1)); //RBL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.18, 2.2, 5, 0, .5));  //LBL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.06, 0.2, 6.25, -3.3, .65));  //L ant
		mainGroup.getChildren().add(bodyShapes.createCylinder(.06, 0.2, 6.25, -3.3, .84));  //R ant
		mainGroup.getChildren().add(bodyShapes.createBox(1.75, 0.8, 1, 5.63, -.7, .73)); //body
		mainGroup.getChildren().add(bodyShapes.createBox(.3, 1.75, .3, 6.25, -2, .73)); //neck
		mainGroup.getChildren().add(bodyShapes.createBox(.7, .4, .4, 6.4, -3, .73)); //head
		
		//Pig
		mainGroup.getChildren().add(bodyShapes.createCylinder(.15, .7, 1.5, 0, .5)); //LFL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.15, .7, 1.5, 0, 1)); //RFL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.15, .7, 0, 0, 1)); //RBL
		mainGroup.getChildren().add(bodyShapes.createCylinder(.15, .7 , 0, 0, .5));  //LBL
		mainGroup.getChildren().add(bodyShapes.createBox(2, .8, 1, .75, -.7, .73)); //body
		mainGroup.getChildren().add(bodyShapes.createBox(.6, .6, .6, 2, -1.2, .73)); //head
		mainGroup.getChildren().add(bodyShapes.createBox(.3, .3, .3, 2.3, -1.1, .73)); //Snout

		
		//mainGroup.getChildren().addAll(testSheep.list);
		stage.setScene(scene);
		stage.show();
	}
	
}
