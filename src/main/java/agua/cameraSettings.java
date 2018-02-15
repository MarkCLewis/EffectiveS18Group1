package agua;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

// I don't want to have to have a giant mess at the start of all my files
// 
// this isn't perfect, but nobody will use this but me (I assume)

@SuppressWarnings("restriction")
public class cameraSettings {
	 private PerspectiveCamera camera;
	 final double cameraModifier = 50.0;
	 final double cameraQuantity = 2.0;
	 private final double sceneWidth = 1280;
	 private final double sceneHeight = 720;
	 double mouseXold = 0;
	 double mouseYold = 0;
	 final double cameraYlimit = 15;
	 final double rotateModifier = 25; 
	 private final double cameraZ = 0;
	 private final double cameraX = 0;
	 private final double cameraY = 0;
	 private final double cameraFarClip = 10000;
	 private final double cameraNearClip = 3;
	 final Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	 final Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	 
	 public PerspectiveCamera cameraSettings() 
	 {
		return getCamera();
	 }
	 
	 public PerspectiveCamera getCamera() 
	 {
		 
		camera.setTranslateX(cameraX);
		camera.setTranslateY(cameraY);
		camera.setTranslateZ(cameraZ);
		camera.setFarClip(cameraFarClip);
		camera.setNearClip(cameraNearClip);
		
		
		camera.getTransforms().addAll(xRotate,yRotate);
		
		return camera;
		
	 }
}