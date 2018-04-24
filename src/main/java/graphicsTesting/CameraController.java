/* CameraController:
 * This class holds and controls a PerspectiveCamera passed by the user.
 * A PerspectiveCamera should be created and added to scenes in groups in the client object 
   before it is passed to the CameraController.
 * CameraController contains methods focused on moving the Camera and a number of getters and setters for
   math and interactions with WorldObjects.
 */

package graphicsTesting;

import javafx.scene.Camera;
import javafx.scene.transform.Rotate;

public class CameraController {
	private boolean boost;
	private Camera camera;
	private double x1;
	private double y1;
	private double x2;
    private double y2;
    private double camSpeed;
	private Rotate xRotate;
    private Rotate yRotate;
    private double rotateModifier;
    private double cameraYlimit;
    private double cameraFarClip;
	private double cameraNearClip;
	private double z;
	private double x;
	private double theta; 
	
	// CameraController uses a builder pattern for creation.
	// PerspectiveCamera is a mandatory parameter.
	//All other parameters will be set to a default value if not explicitly provided.
	public static class Builder {
		private Camera camera;
		private double x1 = 0;
		private double y1 = 0;
		private double camSpeed = 1.0;
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
		private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
		private double rotateModifier = 25;
		private double cameraYlimit = 15;
		private double cameraFarClip = 10000;
		private double cameraNearClip = 3;
		
		public Builder(Camera cam) {
			camera = cam;
		}
		
		public Builder mouseX(double val) {
			x1 = val;
			return this; 
		}
		public Builder mouseY(double val) {
			y1 = val;
			return this;
		}
		public Builder xRotate(Rotate val) {
			xRotate = val;
			return this;
		}
		public Builder yRotate(Rotate val) {
			yRotate = val;
			return this;
		}
		public Builder rotateModifier(double val) {
			rotateModifier = val;
			return this;
		}
		public Builder cameraYlimit(double val) {
			cameraYlimit = val;
			return this;
		}
		public Builder cameraFarClip(double val) {
			cameraFarClip = val;
			return this;
		}
		public Builder cameraNearClip(double val) {
			cameraNearClip = val;
			return this;
		}
		
		public Builder camSpeed(double val) {
			camSpeed = val;
			return this;
		}
		
		public CameraController build() {
			return new CameraController(this);
		}
		
	}
	
	//Private constructor (for builder)
	private CameraController(Builder builder) {
		camera = builder.camera;
		x1 = builder.x1;
		y1 = builder.y1;
		x2 = x1;
	    y2 = x2;
	    camSpeed = builder.camSpeed;
		xRotate = builder.xRotate;
	    yRotate = builder.yRotate;
	    rotateModifier = builder.rotateModifier;
	    cameraYlimit = builder.cameraYlimit;
	    cameraFarClip = builder.cameraFarClip;
		cameraNearClip = builder.cameraNearClip;
		camera.setFarClip(cameraFarClip);
		camera.setNearClip(cameraNearClip);		
		camera.getTransforms().addAll(xRotate,yRotate);	 
		boost = false;
	}
	
//MOVEMENT METHODS
//Moves:
	
	//moveForward() moves the camera towards where it is facing (on the x-z plane)
	public void moveForward() {
		z = camera.getTranslateZ();
		x = camera.getTranslateX();
		theta = yRotate.getAngle()/360.0*2*Math.PI;
		
		camera.setTranslateZ(z+camSpeed*Math.cos(theta));
		camera.setTranslateX(x+camSpeed*Math.sin(theta));
	}
	
	//moveBackward() moves the camera backwards from where it is facing (on the x-z plane)
	public void moveBackward() {
		z = camera.getTranslateZ();
		x = camera.getTranslateX();
		theta = yRotate.getAngle()/360.0*2*Math.PI;
		
		camera.setTranslateZ(z-camSpeed*Math.cos(theta));
		camera.setTranslateX(x-camSpeed*Math.sin(theta));
	}
	
	//moveLeft() moves the camera left from where it is facing (on the x-z plane)
	public void moveLeft() {
		z = camera.getTranslateZ();
		x = camera.getTranslateX();
		theta = yRotate.getAngle()/360.0*2*Math.PI;
		
		camera.setTranslateZ(z+Math.sin(theta));
		camera.setTranslateX(x-Math.cos(theta));
	}
	
	//moveRight() moves the camera right from where it is facing (on the x-z plane)
	public void moveRight() {
		z = camera.getTranslateZ();
		x = camera.getTranslateX();
		theta = yRotate.getAngle()/360.0*2*Math.PI;
		
		camera.setTranslateZ(z-Math.sin(theta));
		camera.setTranslateX(x+Math.cos(theta));
	}
	
	//moveUp() moves the camera upward (on the y plane)
	public void moveUp() {
		camera.setTranslateY((camera.getTranslateY()-camSpeed));
	}
	
	//moveDown() moves the camera downward (on the y plane)
	public void moveDown() {
		camera.setTranslateY((camera.getTranslateY()+camSpeed));
	}
	
//Rotates:
	
	//rotateRight() rotates the camera right from where it is facing
	public void rotateRight() {
		yRotate.setAngle(yRotate.getAngle()+camSpeed);
	}
	
	//rotateLeft() rotates the camera left from where it is facing
	public void rotateLeft() {
		yRotate.setAngle(yRotate.getAngle()-camSpeed);
	}
	
	//rotateUp() rotates the camera upward from where it is facing
	public void rotateUp() {
		xRotate.setAngle(xRotate.getAngle()+camSpeed);
	}
	
	//rotateDown() rotates the camera downward from where it is facing
	public void rotateDown() {
		xRotate.setAngle(xRotate.getAngle()-camSpeed);
	}
	
//Boost:
	
	//boostOn() increases the camSpeed and sets boost = true as long as boost is not already true
	public void boostOn() {
		if(!boost) {
			boost = true;
			camSpeed += 10;
		}
	}
	
	//boostOff() decreases the camSpeed and sets boost = false as long as boost is true
	public void boostOff() {
		if(boost) {
			boost = false;
			camSpeed -= 10;
		}
	}
	
//Mouse Movement:
	
	//mouseMove(nx, ny) takes the current x and y of the mouse cursor and moves the camera accordingly
	//Currently nonfunctional and low priority
	public void mouseMove(double nx, double ny) {
		x2 = nx;
		y2 = ny;
		

		//calculate the rotational change of the camera pitch
		double pitchRotate =xRotate.getAngle()+(y2 - y1) / rotateModifier;
		
		//set min/max camera pitch to prevent camera flipping
		pitchRotate = pitchRotate > cameraYlimit ? cameraYlimit : pitchRotate;
		pitchRotate = pitchRotate < -cameraYlimit ? -cameraYlimit : pitchRotate;
		
		//replace the old camera pitch rotation with the new one.
		xRotate.setAngle(pitchRotate);
		
		//calculate the rotational change of the camera yaw
		double yawRotate=yRotate.getAngle()-(x2 - x1) / rotateModifier;
		yRotate.setAngle(yawRotate);
	
		x1 = x2;
		y1 = y2;
		
		/*try {
			Robot bot = new Robot();
			bot.mouseMove(960,540);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
//GETTERS AND SETTERS
	
	public double getMouseXOld() {
		return x1;
	}
	
	public void setMouseXOld(int newVal) {
		x1 = newVal;
	}
	
	public double getMouseXNew() {
		return x2;
	}
	
	public void setMouseXNew(int newVal) {
		x2 = newVal;
	}
	
	public double getMouseYOld() {
		return y1;
	}
	
	public void setMouseYOld(int newVal) {
		y1 = newVal;
	}
	
	public double getMouseYNew() {
		return y2;
	}
	
	public void setMouseYNew(int newVal) {
		y2 = newVal;
	}
	
	public double getXRotateAngle() {
		return xRotate.getAngle();
	}
	
	public void setXRotateAngle(double newVal) {
		xRotate.setAngle(newVal);
	}
	
	public double getYRotateAngle() {
		return yRotate.getAngle();
	}
	public void setYRotateAngle(double newVal) {
		yRotate.setAngle(newVal);
	}
	public double getRotateModifier() {
		return rotateModifier;
	}
	
	public void setRotateModifier(double newVal) {
		rotateModifier = newVal;
	}
	public double getYLimit() {
		return cameraYlimit;
	}
	
	public void setYLimit(double newVal) {
		cameraYlimit = newVal;
	}
	
	public double getCameraX() {
		return camera.getTranslateX();
	}
	
	public double getCameraZ() {
		return camera.getTranslateZ();
	}
	
	
}
