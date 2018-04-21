package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import virtualworld.WorldObject;

public class CityOne implements WorldObject {

	//City Relevant
	Random rand = new Random();
	Group mg;
	Color roof1;
	Color roof2;
	Color house1;
	Color house2;
	
	int seed; // = rand.nextInt(700) + 1;
	
	//WorldObject Relevant
	double x;
	double y;
	double z;
	double size;
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		return z;
	}

	@Override
	public double getSize() {
		//however you calculate the bounding box
		return 0;
	}

	@Override
	public void notifyOfCamera(double x, double z) {
		
	}
}
