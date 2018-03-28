package graphicsTesting;

import javafx.scene.Cursor;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
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
	
	public static class Builder {
		private double x1 = 0;
		private double y1 = 0;
		private double x2 = x1;
		private double y2 = x2;
		private Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
		private Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
		private double rotateModifier = 25;
		private double cameraYlimit = 15;
		private double cameraFarClip = 10000;
		private double cameraNearClip = 3;
		
		public Builder() {}
		
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
		
		public PlayerCamera build() {
			return new PlayerCamera(this);
		}
		
	}
	public PlayerCamera(Builder builder) {
		x1 = builder.x1;
		y1 = builder.y1;
		x2 = x1;
	    y2 = x2;
		xRotate = builder.xRotate;
	    yRotate = builder.yRotate;
	    rotateModifier = builder.rotateModifier;
	    cameraYlimit = builder.cameraYlimit;
	    cameraFarClip = builder.cameraFarClip;
		cameraNearClip = builder.cameraNearClip;
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
}
