package citiesTesting;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape3D;
import terraintesting.TerrainObject;
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
	static ArrayList<Shape3D> lst = new ArrayList<Shape3D>();

	public static CityOne returnObj(Group group) {
		main(group);
		CityOne co = new CityOne();
		return co;
		//Silas wrote these 2 lines
	}
	
	public static CityOne returnObjTest(double myX, double myZ, Group group) {
		make(myX, myZ, group);
		CityOne co = new CityOne();
		return co;
		//Silas wrote these 2 lines
	}
	
	
	public static void main(Group group){
		setGroup(group);
		setSeed();
		setRand();
		setRoofColor();
		setSecondardRoof();
		setHouseColor();
		setSecondaryHouse();
		setX();
		setZ();
		setY();
		BuildingTypes.Tuple<Double, ArrayList<Shape3D>> tup = BuildingTypes.makeCity1(mg, roof1, roof2, house1, house2, x, y, z, rand);
		setSize(tup.getA());
		addToList(tup.getB());
	}
	
	//make it if you already know the object
	public static void make(double myX, double myZ, Group group){
		setGroup(group);
		setSeed();
		setRand();
		setRoofColor();
		setSecondardRoof();
		setHouseColor();
		setSecondaryHouse();
		setXTo(myX);
		setZTo(myZ);
		setY();
		
		BuildingTypes.Tuple<Double, ArrayList<Shape3D>> tup = BuildingTypes.makeCity1(mg, roof1, roof2, house1, house2, x, y, z, rand);
		setSize(tup.getA());
		addToList(tup.getB());
	}
	
	////////////////Setting////////////////
	
	public static void setGroup(Group g){
		mg = g;
	}
	
	public static void setRoofColor(){
		roof1 = BuildingTypes.colorAssignment(rand);
	}
	
	public static void setHouseColor(){
		house1 = BuildingTypes.colorAssignment(rand);
	}
	
	public static void setSecondardRoof(){
		roof2 = BuildingTypes.secondaryColor(roof1);
	}
	
	public static void setSecondaryHouse(){
		house2 = BuildingTypes.secondaryColor(house1);
	}
	
	/*
	public static void setColor(Color col){
		col = BuildingTypes.colorAssignment(rand);
	}
	
	
	public static void setSecondaryColor(Color col1, Color col2){
		col2 = BuildingTypes.secondaryColor(col1);
	}
	*/
	
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
		cor = MathStuff.makeCoordinate();
	}
	public static void setX(){
		x = MathStuff.makeCoordinate();
	}
	
	public static void setY(){
		//TODO
		//y = TerrainObject.getHeight(x, z);
		y = 0;
	}
	
	public static void setZ(){
		z = MathStuff.makeCoordinate();
	}
	
	public static void setXTo(double pos){
		x = pos;
	}
	
	public static void setYTo(double pos){
		//TODO
		//y = TerrainObject.getHeight(x, z);
		y = pos;
	}
	
	public static void setZTo(double pos){
		z = pos;
	}
	
	public static void addToList(ArrayList<Shape3D> arrLst){
		lst.addAll(arrLst);
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
		//gives the radius of the city
		return Math.sqrt(getMySize())/4;
	}
	
	public double getMySize() {
		//however you calculate the bounding box
		return size;
	}
	
	public int getSeed(){
		return seed;
	}

	@Override
	public double getXLoc() {
		// position of the center X
		double side = Math.sqrt(getMySize())/2;
		return x+side;
	}

	@Override
	public double getYLoc() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double getZLoc() {
		// position of the center Z
		double side = Math.sqrt(getMySize())/2;
		return z-side;
	}

	@Override
	public boolean notifyOfCamera(double x, double z) {
		// TODO Auto-generated method stub
		double dist = Math.sqrt(Math.pow((getXLoc() - x), 2) + Math.pow((getZLoc() - z), 2));
		return true;
		//double rad = (Math.sqrt(getSize())/2) + 500;
		/*
		if(dist < 500){
			return true;
		}
		else return false;
		*/
	}
	
	public void clearList(){
		lst.clear();
	}
	
	@Override
	public ArrayList<Shape3D> display() {
		// TODO Auto-generated method stub
		return lst;
	}
	
}
