package graphicsTesting;

import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class TriangleMeshDemo extends Application {
	public static void main(String[] args) {
		launch();
	}
    
	@Override
	public void start(Stage stage) {
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.GRAY);
		
		//Camera
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(camera);
		mainGroup.getChildren().add(cameraGroup);
		CameraController pCam = new CameraController.Builder(camera).camSpeed(5.0).build();
		
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
			});
		
		scene.setOnKeyReleased(event ->{
			KeyCode key = event.getCode();
			keySet.remove(key);
			
		});

		scene.addEventHandler(MouseEvent.ANY, event -> {
			if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
				pCam.mouseMove(event.getSceneX(), event.getSceneY());
		 	}
		});	
		
		addMesh(mainGroup, getTestMesh(), new PhongMaterial(Color.BLUE), new int[]{0, 0, 10});
		stage.setScene(scene);
		stage.show();
		
	}
	public void addMesh(Group group, TriangleMesh tMesh, PhongMaterial material, int[] transCords) {
		group.getChildren().add(getMeshView(tMesh, material, transCords));
	}
	
	private MeshView getMeshView(TriangleMesh tMesh, PhongMaterial material, int[] transCords) {
		MeshView mv = new MeshView(tMesh);
		mv.setDrawMode(DrawMode.FILL);
		mv.setMaterial(material);
		mv.setTranslateX(transCords[0]);
		mv.setTranslateY(transCords[1]);
		mv.setTranslateZ(transCords[2]);
		
		return mv;
	}
	
	public TriangleMesh getTestMesh() {
		TriangleMesh pyramidMesh = new TriangleMesh();

		pyramidMesh.getTexCoords().addAll(0,0);
		
		float h = 1;                    // Height
		float s = 3;                    // Side
		pyramidMesh.getPoints().addAll(
		        0,    0,    0,            // Point 0 - Top
		        0,    h,    -s/2,         // Point 1 - Front
		        -s/2, h,    0,            // Point 2 - Left
		        s/2,  h,    0,            // Point 3 - Back
		        0,    h,    s/2           // Point 4 - Right
		    );
		
		pyramidMesh.getFaces().addAll(
		        0,0,  2,0,  1,0,          // Front left face
		        0,0,  1,0,  3,0,          // Front right face
		        0,0,  3,0,  4,0,          // Back right face
		        0,0,  4,0,  2,0,          // Back left face
		        4,0,  1,0,  2,0,          // Bottom rear face
		        4,0,  3,0,  1,0           // Bottom front face
		    );
		
		return pyramidMesh;
		/*
		MeshView pyramid = new MeshView(pyramidMesh);
		pyramid.setDrawMode(DrawMode.FILL);
		pyramid.setMaterial(new PhongMaterial(Color.BLUE));
		pyramid.setTranslateX(0);
		pyramid.setTranslateY(0);
		pyramid.setTranslateZ(10);
		
		return pyramid;
		*/
	}
}
