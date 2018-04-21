package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import virtualworld.WorldObject;

public class CityThree implements WorldObject {

	//City Relevant
	Group mg;
	Color roof1;
	Color roof2;
	Color house1;
	Color house2;
	int center;
	Random rand = new Random();
	
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