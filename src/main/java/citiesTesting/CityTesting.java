package citiesTesting;

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
import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CityTesting extends Application {

	// public class cityTest extends Application{
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
		scene.setOnKeyPressed(event -> {
			KeyCode key = event.getCode();
			keySet.add(key);

			if (keySet.contains(KeyCode.W)) {
				pCam.moveForward();
			}
			if (keySet.contains(KeyCode.S)) {
				pCam.moveBackward();
			}
			if (keySet.contains(KeyCode.A)) {
				pCam.moveLeft();
			}
			if (keySet.contains(KeyCode.D)) {
				pCam.moveRight();
			}
			if (keySet.contains(KeyCode.RIGHT)) {
				pCam.rotateRight();
			}
			if (keySet.contains(KeyCode.LEFT)) {
				pCam.rotateLeft();
			}
			if (keySet.contains(KeyCode.UP)) {
				pCam.rotateUp();
			}
			if (keySet.contains(KeyCode.DOWN)) {
				pCam.rotateDown();
			}
			if (keySet.contains(KeyCode.R)) {
				pCam.moveUp();
			}
			if (keySet.contains(KeyCode.F)) {
				pCam.moveDown();
			}
			if (keySet.contains(KeyCode.SHIFT)) {
				pCam.boostOn();
			}
		});

		scene.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			if (key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);

		});

		//ArrayList<Shape3D> tempList = new ArrayList<Shape3D>();
		CityOne cOne = CityOne.returnObjTest(-800, 700, mainGroup);
		//System.out.println((cOne.display().size()));
		//CityOne cOne1 = CityOne.returnObj(mainGroup);
		//System.out.println(cOne1.display().size());
		CityTwo cTwo = CityTwo.returnObjTest(700, 700, mainGroup);
		CityThree cThree = CityThree.returnObjTest(-700, -700, mainGroup);
		
		mainGroup.getChildren().addAll(cOne.display());
		mainGroup.getChildren().addAll(cTwo.display());
		mainGroup.getChildren().addAll(cThree.display());
		stage.setScene(scene);
		stage.show();
	}

}
