package virtualworld;

import java.util.ArrayList;

import javafx.scene.shape.Shape3D;

public class ExampleObject implements WorldObject {
	
	//dimensions
	private double x;
	private double z;
	private double y;
	
	//coordinates
	private double xLoc;
	private double zLoc;
	private double yLoc;
	
	private double size;
	
	public ExampleObject (double x, double z, double y) {
		this.x = x;
		this.z = z;
		this.y = y;
		this.size = x*z;
	}
	
	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}
	
	@Override
	public double getZ() {
		return this.z;
	}

	@Override
	public double getSize() {
		return this.size;
	}

	@Override
	public double getXLoc() {
		return this.xLoc;
	}

	@Override
	public double getYLoc() {
		return this.yLoc;
	}

	@Override
	public double getZLoc() {
		return this.zLoc;
	}

	@Override
	public boolean notifyOfCamera(double x, double z) {
		if (Math.sqrt(Math.pow((this.xLoc - x), 2) + Math.pow((this.zLoc - z), 2)) <= 500) return true;
		else return false;
	}

	@Override
	public ArrayList<Shape3D> display() {
		// TODO Auto-generated method stub
		return null;
	}
}
