package animals;

import java.util.HashSet;
import java.util.Set;

import graphicsTesting.CameraController;
import graphicsTesting.DrawFacade;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;

public class sheepMaker extends Application{
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
		
		
		
		int[] transCords = {0,0,0};
		mainGroup.getChildren().add(DrawFacade.createCylinder(new PhongMaterial(), Color.WHITE, Color.GRAY, 2, 15, 20, 0, 0));
		mainGroup.getChildren().add(DrawFacade.createCylinder(new PhongMaterial(), Color.WHITE, Color.GRAY, 2, 15, 20, 0, 5));
		mainGroup.getChildren().add(DrawFacade.createCylinder(new PhongMaterial(), Color.WHITE, Color.GRAY, 2, 15, 0, 0, 5));
		mainGroup.getChildren().add(DrawFacade.createCylinder(new PhongMaterial(), Color.WHITE, Color.GRAY, 2, 15, 0, 0, 0));
		mainGroup.getChildren().add(DrawFacade.createBox(new PhongMaterial(), Color.WHITE, Color.GRAY, 25, 8, 15, 10.25, -10, 0));
		mainGroup.getChildren().add(DrawFacade.createBox(new PhongMaterial(), Color.WHITE, Color.GRAY, 6, 6, 6, 23, -16, 0));

		stage.setScene(scene);
		stage.show();
	}
	
}
