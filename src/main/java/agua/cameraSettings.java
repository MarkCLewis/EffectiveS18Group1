package agua;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

// I don't want to have to have a giant mess at the start of all my files
// 
// this isn't perfect, but nobody will use this but me (I assume)

@SuppressWarnings("restriction")
public class cameraSettings {
	 public PerspectiveCamera camera = new PerspectiveCamera(true);
	 double cameraModifier = 50.0;
	 double cameraQuantity = 2.0;
	 double sceneWidth = 1280;
	 double sceneHeight = 720;
	 double mouseXold = 0;
	 double mouseYold = 0;
	 double cameraYlimit = 15;
	 double rotateModifier = 60; 
	 double cameraZ = 0;
	 double cameraX = 0;
	 double cameraY = 0;
	 double cameraFarClip = 4000000;
	 double cameraNearClip = .05;
	 Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	 Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	 
	 public PerspectiveCamera cameraSettings() 
	 {
		camera.setTranslateX(cameraX);
		camera.setTranslateY(cameraY);
		camera.setTranslateZ(cameraZ);
		camera.setFarClip(cameraFarClip);
		camera.setNearClip(cameraNearClip);
			
		camera.getTransforms().addAll(xRotate,yRotate);
		return camera;
		//return getCamera();
	 }
	 
	 public PerspectiveCamera getCamera() 
	 {
		
		return camera;
		
	 }
}