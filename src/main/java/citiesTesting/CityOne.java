package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import virtualworld.WorldObject;

public class CityOne implements WorldObject {

	//City Relevant
	public Random rand = new Random();
	Group mg;
	Color roof1;
	Color roof2;
	Color house1;
	Color house2;
	
	int seed = rand.nextInt(700) + 1;
	
	
	//WorldObject Relevant
	double x;
	double y;
	double z;
	double size;

	
	public void main(){
		setSeed();
		double sz = BuildingTypes.makeCity1(mg, roof1, roof2, house1, house2, x, y, z, rand);
		setSize(sz);
	}
	
	public void setSize(double num){
		size = num;
	}
	
	public void setSeed(){
		rand = new Random(seed);
	}
	
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
	
	public int getSeed(){
		return seed;
	}

	@Override
	public void notifyOfCamera(double x, double z) {
		
	}
}
