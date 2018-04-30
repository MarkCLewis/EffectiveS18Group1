package animals;

import java.util.ArrayList;

import citiesTesting.MathStuff;
//import animals.SheepBuild;
import javafx.scene.Group;
import javafx.scene.shape.Shape3D;
import virtualworld.WorldObject;


public class sheep implements WorldObject {

	static Group mainGroup = new Group();
	static double x;
	static double y;
	static double z;
	static double size;
	static ArrayList<Shape3D> list = new ArrayList<Shape3D>();
	
	public static sheep returnObj(Group sgroup) {
		main(sgroup);
		sheep sh = new sheep();
		return sh;
	}
	
	public static void main(Group sgroup){
		setX();
		setY();
		setZ();
		
		ArrayList<Shape3D> Bs = SheepBuild.makeSheep(x,y,z);
		list.addAll(Bs);
	}
	
	////////////SETTING////////////
	
	public static void setSize(double num){
		size = num;
	}
	
	public static void setCoordinate(double cor){
		cor = MathStuff.makeCoordinate();
	}
	
	public static void setX(){
		x = MathStuff.makeCoordinate();
	}
	
	public static void setY(){
		y = MathStuff.makeCoordinate();
	}
	
	public static void setZ(){
		z = MathStuff.makeCoordinate();
	}
	
	public static void addToList(ArrayList<Shape3D> arrLst){
		list.addAll(arrLst);
	}
	
	////////////GETTING////////////

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
		return Math.sqrt(getMySize())/2;
	}
	
	public double getMySize() {
		//however you calculate the bounding box
		return size;
	}

	@Override
	public double getXLoc() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		double side = Math.sqrt(getMySize())/2;
		return z-side;
	}

	@Override
	public boolean notifyOfCamera(double x, double z) {
		// TODO Auto-generated method stub
		double dist = Math.sqrt(Math.pow((getXLoc() - x), 2) + Math.pow((getZLoc() - z), 2));
		
		if(dist < 50){
			return true;
		}
		else return false;
	}

	@Override
	public ArrayList<Shape3D> display() {
		// TODO Auto-generated method stub
		return list;
	}

}
