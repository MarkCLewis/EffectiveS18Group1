package agua;
	

import java.awt.AWTException;
import java.awt.Robot;

import com.sun.prism.MeshView;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
import javafx.scene.transform.Rotate;
import javafx.scene.paint.*;



@SuppressWarnings("restriction")
public class Main extends Application {
	
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
		scene.setFill(Color.BLACK);
		PerspectiveCamera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		camera.setTranslateZ(-1000);
		camera.setFarClip(100000);
		camera.setNearClip(0.1);
		
		Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
		Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
		camera.getTransforms().addAll(xRotate,yRotate);

		
		final Cylinder cylinder = new Cylinder(50, 100);
		cylinder.setVisible(true);
		PhongMaterial blue = new PhongMaterial();
		blue.setDiffuseColor(Color.DARKBLUE);
		blue.setSpecularColor(Color.BLUE);
		cylinder.setMaterial(blue);
		
		cylinder.setRotationAxis(Rotate.X_AXIS);
		cylinder.setRotate(45);
		cylinder.setTranslateZ(-200);
		
		final Box cube = new Box(50, 50, 50);
		 cube.setMaterial(blue);
		 cube.setRotationAxis(Rotate.Y_AXIS);
		 cube.setRotate(45);
		 cube.setTranslateX(-150);
		 cube.setTranslateY(-150);
		 cube.setTranslateZ(150);
		 
		TriangleMesh sheet = new TriangleMesh();
		sheet.setVertexFormat(VertexFormat.POINT_TEXCOORD);
		float[] points = {
                -5, 5, 0,
                -5, -5, 0,
                5, 5, 0,
                5, -5, 0
        };
        float[] texCoords = {
                0, 0,
                0, 1,
                1, 0,
                1, 1
        };
        int[] faces = {
                0, 0, 1, 1, 2, 2,
                2, 2, 3, 3, 1, 1
        };
        
		sheet.getTexCoords().addAll(texCoords);
		sheet.getPoints().addAll(points);
		sheet.getFaces().addAll(faces);
		//Smoothing group adjusts the normal on the vertices for the face to either be smooth or faceted.
		//If every single face has a differing smoothing group, 
		//then the mesh will be very faceted. 
		//If every single face has the same smoothing group, then the mesh will look very smooth.
		
		javafx.scene.shape.MeshView sheetView = new javafx.scene.shape.MeshView(); // Issue with using mesh view (?)
		sheetView.setDrawMode(DrawMode.FILL);
		sheetView.setCullFace(CullFace.BACK);
	    PhongMaterial material = new PhongMaterial();
	    material.setSpecularColor(Color.WHITE);
	    material.setDiffuseColor(Color.BEIGE);

		
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
				 //mouse.mouseMove((int)scene.getWidth()/2, (int)scene.getHeight()/2);
				  	
				// if(mouseXnew > scene.getWidth() - 10 || mouseXnew < 10) { mouse.mouseMove((int) mouseXnew/2, (int) mouseYnew);}
			 	// if(mouseYnew > scene.getHeight() - 10 || mouseYnew < 10) { mouse.mouseMove((int) mouseYnew/2, (int) mouseYnew);}
			 	}
			 if(event.getEventType() == MouseEvent.MOUSE_EXITED)
			 {
				 mouse.mouseMove((int)scene.getWidth()/2, (int)scene.getHeight()/2);
			 }
		});
		 
		 Group shapes = new Group(cylinder, cube);
		 
		 //sceneRoot.getChildren().addAll(cylinder,cube);
		 sheetView.setMaterial(material);
		 sceneRoot.getChildren().add(sheetView);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
