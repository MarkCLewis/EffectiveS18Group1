package animals;

import javafx.animation.Transition;
import javafx.scene.shape.Shape3D;
import javafx.util.Duration;

public class animalTransition extends Transition{

	private sheep theSheep;
	public animalTransition(sheep s)
	{
		theSheep = s;
		setCycleDuration(Duration.millis(10000));
		
		// generateRandomPoints() 
		// need to generate an array for the points to travel between
	}
	@Override
	protected void interpolate(double arg0) {
		// TODO Auto-generated method stub
		for(Shape3D sd : theSheep.list)
		{
			sd.setTranslateX(sd.getTranslateX()+.10);
			sd.setTranslateZ(sd.getTranslateZ() + .10);
		}
		
	}

}
