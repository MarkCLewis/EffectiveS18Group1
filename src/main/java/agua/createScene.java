package agua;
import java.awt.AWTException;
import java.awt.Robot;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;



public class createScene {
		
	
	public final Scene mainScene;
	///scene.setFill(Color.BEIGE);
	
	cameraSettings cameraSetup = new cameraSettings();
	PerspectiveCamera camera = cameraSetup.getCamera();
	//public Scene createScene() throws AWTException {	return getScene();}
	public createScene() throws AWTException
	{
		Group sceneRoot = new Group();
		Scene scene = new Scene(sceneRoot, 1280, 720);
		scene.setOnKeyPressed(event-> {
			double change = cameraSetup.cameraQuantity;
			if(event.isShiftDown()) { change = cameraSetup.cameraModifier;}
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
				double pitchRotate =cameraSetup.xRotate.getAngle()+(mouseYnew - cameraSetup.mouseYold) / cameraSetup.rotateModifier;
				//set min/max camera pitch to prevent camera flipping
				pitchRotate = pitchRotate > cameraSetup.cameraYlimit ? cameraSetup.cameraYlimit : pitchRotate;
				pitchRotate = pitchRotate < -cameraSetup.cameraYlimit ? -cameraSetup.cameraYlimit : pitchRotate;
				//replace the old camera pitch rotation with the new one.
				cameraSetup.xRotate.setAngle(pitchRotate);
				//calculate the rotational change of the camera yaw
				double yawRotate=cameraSetup.yRotate.getAngle()-(mouseXnew - cameraSetup.mouseXold) / cameraSetup.rotateModifier;
				cameraSetup.yRotate.setAngle(yawRotate);
			
				cameraSetup.mouseXold = mouseXnew;
				cameraSetup.mouseYold = mouseYnew;
				scene.setCursor(Cursor.NONE);
		 		}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
			{
				mouse.mouseMove(
						(int)scene.getWidth()/2,
						(int)scene.getHeight()/2);
				//camera.setTranslateX(change);
				//sceneRoot.getChildren().add(createMeshView(test, faceTest, camera.getTranslateX()+10, camera.getTranslateY(), camera.getTranslateZ() ));
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED)
			{
			
			}
		});
	 
		sceneRoot.getChildren().add(camera);
		
		this.mainScene = scene;
	}

}