package animals;

import javafx.animation.Transition;
import javafx.geometry.Point3D;
import javafx.scene.shape.Shape3D;
import javafx.util.Duration;

public class animalTransition extends Transition{

	private Sheep theSheep;
	private int maxRange;
	private Point3D endPoint;
	static private double distance;
	public animalTransition(Sheep s)
	{
		
		theSheep = s;
		setCycleDuration(Duration.millis(10000));
		setRate(.25);
		maxRange = 100;
		//double speed = .5;
		endPoint = getRandomPoint();
		theSheep.point = new Point3D(theSheep.getX(), theSheep.getY(), theSheep.getZ());
		distance = endPoint.distance(theSheep.point);
		
		// generateRandomPoints() 
		// need to generate an array for the points to travel between
	}
	@Override
	protected void interpolate(double frac) { // frac is like the frame, but in decimal form
		// TODO Auto-generated method stub
		
		double dist = distance*frac;
		for(Shape3D sd : theSheep.list)
		{
			sd.setTranslateX(sd.getTranslateX() + (dist));
			sd.setTranslateZ(sd.getTranslateZ() + (dist));
			
		}
		distance -= dist;
		
	}
	
	public Point3D getRandomPoint()
	{
		Point3D point = new Point3D(maxRange*Math.random(), theSheep.getY(), maxRange*Math.random());
		return point;
		
	}

}
