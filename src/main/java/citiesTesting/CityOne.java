package citiesTesting;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import virtualworld.WorldObject;

public class CityOne implements WorldObject {

	//City Relevant
	public static Random rand = new Random();
	static Group mg;
	static Color roof1;
	static Color roof2;
	static Color house1;
	static Color house2;
	static int seed;
	
	
	//WorldObject Relevant
	static double x;
	static double y = 0.0;
	//TODO-make it so that y is based on terrain
	static double z;
	static double size;

	public static CityOne returnObj(Group group) {
		main(group);
		CityOne co = new CityOne();
		return co;
		//Silas wrote these 2 lines
	}
	
	public static void main(Group group){
		setGroup(group);
		setSeed();
		setRand();
		setColor(roof1);
		setSecondaryColor(roof1, roof2);
		setColor(house1);
		setSecondaryColor(house1, house2);
		setX();
		setZ();
		
		double sz = BuildingTypes.makeCity1(mg, roof1, roof2, house1, house2, x, y, z, rand);
		setSize(sz);

	}
	
	//make it if you already know the object
	public static void make(int s, Group group){
		setGroup(group);
		setSeed(s);
		setRand();
		setColor(roof1);
		setSecondaryColor(roof1, roof2);
		setColor(house1);
		setSecondaryColor(house1, house2);
		setCoordinate(x);
		setCoordinate(z);
		
		double sz = BuildingTypes.makeCity1(mg, roof1, roof2, house1, house2, x, y, z, rand);
		setSize(sz);
	}
	
	////////////////Setting////////////////
	
	public static void setGroup(Group g){
		mg = g;
	}
	
	public static void setColor(Color col){
		col = BuildingTypes.colorAssignment(rand);
	}
	
	public static void setSecondaryColor(Color col1, Color col2){
		col2 = BuildingTypes.secondaryColor(col1);
	}
	
	public static void setSize(double num){
		size = num;
	}
	
	public static void setSeed(int s){
		seed = s;
	}
	
	public static void setSeed(){
		seed = rand.nextInt(700) + 1;
	}
	
	public static void setRand(){
		rand = new Random(seed);
	}
	
	public static void setCoordinate(double cor){
		cor = MathStuff.makeCoordinate(-4000, 4000);
	}
	public static void setX(){
		x = MathStuff.makeCoordinate(-4000, 4000);
	}
	public static void setZ(){
		z = MathStuff.makeCoordinate(-4000, 4000);
	}
	
	///////////////Getting//////////////////
	
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
		return size;
	}
	
	
	public int getSeed(){
		return seed;
	}

	@Override
	public void notifyOfCamera(double x, double z) {
		
	}
	
}
