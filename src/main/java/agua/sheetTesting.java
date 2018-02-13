package agua;
	

import java.awt.AWTException;
import java.awt.Robot;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.paint.*;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;



@SuppressWarnings("restriction")
public class sheetTesting extends Application {
	
	private PerspectiveCamera camera;
	 private final double cameraModifier = 50.0;
	 private final double cameraQuantity = 10.0;
	 private final double sceneWidth = 600;
	 private final double sceneHeight = 600;
	 private double mouseXold = 0;
	 private double mouseYold = 0;
	 private final double cameraYlimit = 15;
	 private final double rotateModifier = 25; 
	
	@Override
	public void start(Stage primaryStage) throws AWTException {
		
		Group sceneRoot = new Group();
		Scene scene = new Scene(sceneRoot, 1000, 850);
		scene.setFill(Color.AQUA);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		camera.setTranslateZ(-1000);
		camera.setFarClip(100000);
		camera.setNearClip(0.1);
		
		Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
		Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
		camera.getTransforms().addAll(xRotate,yRotate);

		 scene.setOnKeyPressed(event-> {
			 double change = cameraQuantity;
			 if(event.isShiftDown()) { change = cameraModifier;}
			 KeyCode key = event.getCode();
			 if(key == KeyCode.Q) {System.exit(0);}
			 if(key == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + change);}
			 if(key == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - change);}
			 if(key == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change); }
			 if(key == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change); }
		 });
		 
		 Robot mouse = new Robot();
		 Cursor pointy;
		
		 scene.addEventHandler(MouseEvent.ANY, event -> {
			 if (event.getEventType() == MouseEvent.MOUSE_MOVED)
			 	{
				 double mouseXnew = event.getSceneX();
				 double mouseYnew = event.getSceneY();
				 
				//calculate the rotational change of the camera pitch
				 double pitchRotate =xRotate.getAngle()+(mouseYnew - mouseYold) / rotateModifier;
				 //set min/max camera pitch to prevent camera flipping
				 pitchRotate = pitchRotate > cameraYlimit ? cameraYlimit : pitchRotate;
				 pitchRotate = pitchRotate < -cameraYlimit ? -cameraYlimit : pitchRotate;
				 //replace the old camera pitch rotation with the new one.
				 xRotate.setAngle(pitchRotate);
				 //calculate the rotational change of the camera yaw
				 double yawRotate=yRotate.getAngle()-(mouseXnew - mouseXold) / rotateModifier;
				 yRotate.setAngle(yawRotate);
				
				 mouseXold = mouseXnew;
				 mouseYold = mouseYnew;
				 scene.setCursor(Cursor.NONE);
			 	}
			 if(event.getEventType() == MouseEvent.MOUSE_EXITED)
			 {
				 mouse.mouseMove((int)scene.getWidth()/2, (int)scene.getHeight()/2);
			 }
		});
		 

		 sceneRoot.getChildren().add(camera);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
private MeshView createMeshView(float [] Points, int[] Faces, int X, int Y, int Z) {
		TriangleMesh mesh = new TriangleMesh();

		mesh.getTexCoords().addAll(0,0);
		
		float h = 1;                    // Height
		float s = 3;                    // Side
		
		mesh.getPoints().addAll(Points);
		
		mesh.getFaces().addAll(Faces);
		
		MeshView meshView = new MeshView(mesh);
		meshView.setDrawMode(DrawMode.FILL);
		meshView.setMaterial(new PhongMaterial(Color.BLUE));
		meshView.setTranslateX(0/*200*/);
		meshView.setTranslateY(0/*100*/);
		meshView.setTranslateZ(10/*200*/);
		
		return meshView;
		
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
