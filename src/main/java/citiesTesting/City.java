package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import virtualworld.WorldObject;

public class City implements WorldObject {

	Group mg;
	Color roof1;
	Color roof2;
	Color house1;
	Color house2;
	Random rand = new Random();
	
	//WorldObject Relevant
	double x;
	double y;
	double z;
	double size;
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		//however you calculate the bounding box
		return 0;
	}

	@Override
	public void notifyOfCamera(double x, double z) {
		// TODO Auto-generated method stub
		
	}

}
