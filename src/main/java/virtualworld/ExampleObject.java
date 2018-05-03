package virtualworld;

import java.util.ArrayList;

import javafx.scene.shape.Shape3D;

public class ExampleObject implements WorldObject {
	
	private ArrayList<Shape3D> toBeDrawn = new ArrayList<Shape3D>();
	
	//coordinates
	private double xLoc;
	private double zLoc;
	private double yLoc;
	private double size;
	
	public ExampleObject (double x, double z, double radius) {
		this.xLoc = x;
		this.zLoc = z;
		this.size = radius;
	}
	
	public double getRadius() {
		return this.size;
	}
	
	@Override
	public double getX() {
		return this.size;
	}

	@Override
	public double getY() {
		return this.size;
	}
	
	@Override
	public double getZ() {
		return this.size;
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
		return true;
	}

	@Override
	public ArrayList<Shape3D> display() {
		return toBeDrawn;
	}
}
