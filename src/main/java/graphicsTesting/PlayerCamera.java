package graphicsTesting;

import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;

public class PlayerCamera extends PerspectiveCamera {
	private double x1;
	private double y1;
	private double x2;
    private double y2;
	private Rotate xRotate;
    private Rotate yRotate;
    private double rotateModifier;
    private double cameraYlimit;
    private double cameraFarClip;
	private double cameraNearClip;
	
	public PlayerCamera() {
		x1 = 0;
		y1 = 0;
		x2 = x1;
	    y2 = x2;
		xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
	    yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
	    rotateModifier = 25;
	    cameraYlimit = 15;
	    cameraFarClip = 10000;
		cameraNearClip = 3;
		this.setFarClip(cameraFarClip);
		this.setNearClip(cameraNearClip);		
		this.getTransforms().addAll(xRotate,yRotate);
	}
	//Builder pattern goes here
	//public PlayerCamera(){}
	
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
}
