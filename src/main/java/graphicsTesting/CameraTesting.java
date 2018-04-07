package graphicsTesting;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class CameraTesting extends Application{
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
		
		scene.setOnKeyPressed(event ->{
			 KeyCode key = event.getCode();
			 if(key == KeyCode.W) {
				 pCam.moveForward();
			 }
			 if(key == KeyCode.S) {
				 pCam.moveBackward();
			 }
			 if(key == KeyCode.A) {
				 pCam.moveLeft();
			 }
			 if(key == KeyCode.D) {
				 pCam.moveRight();
			 }
			 
			 if(key == KeyCode.RIGHT) {
				 pCam.rotateRight();
			 }
			 if(key == KeyCode.LEFT) {
				 pCam.rotateLeft();
			 }
			 if(key == KeyCode.UP) {
				 pCam.rotateUp();
			 }
			 if(key == KeyCode.DOWN) {
				 pCam.rotateDown();
			 }
			 if(key == KeyCode.R) {
				 pCam.moveUp();
			 }
			 if(key == KeyCode.F) {
				 pCam.moveDown();
			 }
			});
		
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
		MeshView pyramid = new MeshView(pyramidMesh);
		pyramid.setDrawMode(DrawMode.FILL);
		pyramid.setMaterial(new PhongMaterial(Color.BLUE));
		pyramid.setTranslateX(0);
		pyramid.setTranslateY(0);
		pyramid.setTranslateZ(10);
		
		mainGroup.getChildren().add(pyramid);
		stage.setScene(scene);
		stage.show();
	}
	
}
