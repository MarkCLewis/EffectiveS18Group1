package graphicsTesting;

import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;

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
		
		//Camera Movement
		double camSpeed = 1.0;
		scene.setOnKeyPressed(event ->{
			KeyCode key = event.getCode();
			 if(key == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + camSpeed);}
			 if(key == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - camSpeed);}
			 if(key == KeyCode.A) {camera.setTranslateX(camera.getTranslateX() - camSpeed);}
			 if(key == KeyCode.D) {camera.setTranslateX(camera.getTranslateX() + camSpeed);}
		});
		
		double x1;
		double y2;
		double x2 = event.getSceneX();
        double y2 = event.getSceneY();
        double dx;
        double dy;
	    scene.setOnMouseMoved((MouseEvent event) -> {
	        if(pressed){
	             x1 = x2;
	             y1 = y2;
	             x2 = event.getSceneX();
	             y2 = event.getSceneY();
	             dx = x2 -x1;
	             dy = y2 -y1;

	             camera.setTranslateX(camera.getTranslateX() - dx*0.1);
	             camera.setTranslateY(camera.getTranslateY() - dy*0.1);      
	        }           
	    });
		
		mainGroup.getChildren().add(getTestMesh());
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	private MeshView getTestMesh() {
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
		pyramid.setTranslateX(0/*200*/);
		pyramid.setTranslateY(0/*100*/);
		pyramid.setTranslateZ(10/*200*/);
		
		return pyramid;
		
	}
}
