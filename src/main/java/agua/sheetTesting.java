package agua;
	

import java.awt.AWTException;
import java.awt.Robot;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.paint.*;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;



@SuppressWarnings("restriction")
public class sheetTesting extends Application {
	
	private PerspectiveCamera camera;
	 private final double cameraModifier = 50.0;
	 private final double cameraQuantity = 2.0;
	 private final double sceneWidth = 1280;
	 private final double sceneHeight = 720;
	 private double mouseXold = 0;
	 private double mouseYold = 0;
	 private final double cameraYlimit = 15;
	 private final double rotateModifier = 25; 
	 
	
	@Override
	public void start(Stage primaryStage) throws AWTException {
		
		Group sceneRoot = new Group();
		Scene scene = new Scene(sceneRoot, 1280, 720);
		scene.setFill(Color.BEIGE);
		
		
		
		 // Camera stuff
		PerspectiveCamera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		camera.setTranslateZ(0);
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
			 if(key == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change);}
			 if(key == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change);}
			 if(key == KeyCode.Z) { camera.setTranslateY(camera.getTranslateY() + change);}
			 if(key == KeyCode.X) { camera.setTranslateY(camera.getTranslateY() - change);}
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
			 if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
			 {
				 //sceneRoot.getChildren().add(createMeshView(test, faceTest, camera.getTranslateX()+10, camera.getTranslateY(), camera.getTranslateZ() ));
			 }
			 if(event.getEventType() == MouseEvent.MOUSE_EXITED)
			 {
				 mouse.mouseMove(
						 (int)scene.getWidth()/2,
						 (int)scene.getHeight()/2);
			 }
		});
		 
		 float h = 3;                    // Height
		 float s = 3;                    // Side
		 float[] test = {0f, 0f, -0.951057f, 
			        0f, 0f, 0.951057f, 
			        -0.850651f, 0f, -0.425325f, 
			        0.850651f, 0f, 0.425325f, 
			        0.688191f, -0.5f, -0.425325f, 
			        0.688191f, 0.5f, -0.425325f, 
			        -0.688191f, -0.5f, 0.425325f, 
			        -0.688191f, 0.5f, 0.425325f, 
			        -0.262866f, -0.809017f, -0.425325f, 
			        -0.262866f, 0.809017f, -0.425325f, 
			        0.262866f, -0.809017f, 0.425325f, 
			        0.262866f, 0.809017f, 0.425325f};         
		 
		 int [] faceTest = {1, 6, 11, 5, 7, 0, 
		            1, 12, 7, 11, 6, 5, 
		            1, 7, 6, 6, 10, 1, 
		            1, 13, 10, 12, 3, 6, 
		            1, 8, 3, 7, 11, 2,
		            4, 14, 8, 13, 0, 7, 
		            5, 9, 4, 8, 0, 3, 
		            9, 15, 5, 14, 0, 8, 
		            2, 10, 9, 9, 0, 4, 
		            8, 16, 2, 15, 0, 9,
		            11, 5, 9, 6, 7, 12,
		            7, 11, 2, 12, 6, 17, 
		            6, 6, 8, 7, 10, 13, 
		            10, 12, 4, 13, 3, 18, 
		            3, 7, 5, 8, 11, 14,
		            4, 13, 10, 14, 8, 19, 
		            5, 8, 3, 9, 4, 15, 
		            9, 14, 11, 15, 5, 20, 
		            2, 9, 7, 10, 9, 16, 
		            8, 15, 6, 16, 2, 21};   
		 float [] testCoords = {
				 0.181818f, 0f, 
		            0.363636f, 0f, 
		            0.545455f, 0f, 
		            0.727273f, 0f, 
		            0.909091f, 0f,
		            0.0909091f, 0.333333f,
		            0.272727f, 0.333333f, 
		            0.454545f, 0.333333f, 
		            0.636364f, 0.333333f, 
		            0.818182f, 0.333333f, 
		            1f, 0.333333f, 
		            0f, 0.666667f, 
		            0.181818f, 0.666667f, 
		            0.363636f, 0.666667f, 
		            0.545455f, 0.666667f, 
		            0.727273f, 0.666667f, 
		            0.909091f, 0.666667f, 
		            0.0909091f, 1f, 
		            0.272727f, 1f, 
		            0.454545f, 1f, 
		            0.636364f, 1f, 
		            0.818182f, 1f
		 };
		 

		sceneRoot.getChildren().add(camera);
		MeshView mv = createMeshView(test,testCoords, faceTest, 0, 0, 10 );
		RotateTransition rotator = rotator(mv);
        rotator.play();
		sceneRoot.getChildren().add(mv);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
private RotateTransition rotator(Node temp) {
	        RotateTransition rotator = new RotateTransition(Duration.millis(10000), temp);
	        
	        rotator.setAxis(Rotate.Y_AXIS); //Z/X/Y axis
	        
	        rotator.setFromAngle(0);
	        rotator.setToAngle(360); // causes it to spin in a circle
	        rotator.setInterpolator(Interpolator.LINEAR); // DISCRETE, EASE_BOTH, EASE_IN, EASE_OUT
	        rotator.setCycleCount(100);

	        return rotator;
	    }
	
private MeshView createMeshView(float [] Points,float[] texCoords, int[] Faces, int X, int Y, int Z) {
		TriangleMesh mesh = new TriangleMesh();

		mesh.getTexCoords().addAll(texCoords);
		
		mesh.getPoints().addAll(Points);
		
		mesh.getFaces().addAll(Faces);
		
		MeshView meshView = new MeshView(mesh);
		meshView.setDrawMode(DrawMode.FILL);
		//meshView.setMaterial(new PhongMaterial(Color.BLUE));
		PhongMaterial mat = new PhongMaterial();
		mat.setDiffuseMap(new Image(getClass().getResourceAsStream("waldo.png")));
		meshView.setMaterial(mat);
		meshView.setTranslateX(X/*200*/);
		meshView.setTranslateY(Y/*100*/);
		meshView.setTranslateZ(Z/*200*/);
		//Transform mesh3d = meshView.getLocalToSceneTransform();
		//mesh3d.deltaTransform(X*2, Y*2, Z*2);
		
		return meshView;
		
	}
	
private MeshView createMeshView(float [] Points, int[] Faces, double X, double Y, double Z) {
	TriangleMesh mesh = new TriangleMesh();

	mesh.getTexCoords().addAll(0,0);

	mesh.getPoints().addAll(Points);
	
	mesh.getFaces().addAll(Faces);
	
	MeshView meshView = new MeshView(mesh);
	meshView.setDrawMode(DrawMode.FILL);
	meshView.setMaterial(new PhongMaterial(Color.BLUE));
	meshView.setTranslateX(X/*200*/);
	meshView.setTranslateY(Y/*100*/);
	meshView.setTranslateZ(Z/*200*/);
	
	return meshView;
	
}
	
	public static void main(String[] args) {
		launch(args);
	}
}
