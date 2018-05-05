package citiesTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import citiesTesting.CityMaker.Tuple;
import graphicsTesting.CameraController;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class CityMaker extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
    //public List<Tuple<Integer, Integer, Double, Double, Double, Double>> start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);

		// TODO-make a seed when generating cities to make sure
		// that they remain the same once you leave and come back
		Random rand = new Random();
		int seed = rand.nextInt(700) + 1;
		rand = new Random(seed);

		int a = rand.nextInt(3) + 1;

		int numberOfCities = rand.nextInt(30) + 20;

		List<Tuple<Integer, Integer, Double, Double, Double, Double>> cities = 
				new ArrayList<Tuple<Integer, Integer, Double, Double, Double, Double>>();

		// TODO-use random int values to create random cities
		// one for how many buildings, and one for what type of building

		// TODO-randomize x, y, z based on the world
		// 1 double = 1 meter
		double x = 400.0;
		double y = 300.0;
		double z = 200.0;
		
		//Cities
		//Making city objects
		CityOne cOne = CityOne.returnObj(mainGroup);
		//grid type city
		CityTwo cTwo = CityTwo.returnObj(mainGroup);
		//circular city
		CityThree cThree = CityThree.returnObj(mainGroup);
		//diamond city
		
		
		//double p = MathStuff.makeCoordinate(-2000, 2000);
		// making random coordinates^
		/*
		for (int c = 0; c < numberOfCities; c++) {

			Color roof1 = BuildingTypes.colorAssignment(rand);

			Color house1 = BuildingTypes.colorAssignment(rand);

			
			// city type 1 (square city)
			if (a == 1) {
				double size = BuildingTypes.makeCity1(mainGroup, roof1, BuildingTypes.secondaryColor(roof1), house1,
						BuildingTypes.secondaryColor(house1), x, y, z, rand);
				Tuple<Integer, Integer, Double, Double, Double, Double> tup = new Tuple<>(1, seed, size, x - 10.0, y, z - 10.0);
				cities.add(tup);
			}
			
			// city type 2 (circular city)
			else if (a == 2) {
				// TODO-make circular city
				double size = BuildingTypes.makeCity2(mainGroup, roof1, BuildingTypes.secondaryColor(roof1), house1,
						BuildingTypes.secondaryColor(house1), x, y, z, rand.nextInt(3) + 1, rand);
				Tuple<Integer, Integer, Double, Double, Double, Double> tup = new Tuple<>(2, seed, size, x, y, z);
				cities.add(tup);
			} 
			//city type 3 (Diamond city)
			else if (a == 3) {
				double size = BuildingTypes.makeCity3(mainGroup, roof1, BuildingTypes.secondaryColor(roof1), house1,
						BuildingTypes.secondaryColor(house1), x, y, z, rand.nextInt(3) + 1, rand);
				Tuple<Integer, Integer, Double, Double, Double, Double> tup = new Tuple<>(3, seed, size, x, y, z);
				cities.add(tup);
			}
			// TODO-find out how big the world is so that I can make random
			// coordinates
			
			x = MathStuff.makeCoordinate();
			z = MathStuff.makeCoordinate();
			
			boolean bool = true;
			while(bool == true){
				if(Location.isOverlapping(x, z, cities)){
					x = MathStuff.makeCoordinate();
					z = MathStuff.makeCoordinate();
				}
				else bool = false;
			}
			
			//TODO:
			//1. Find a way to get the Y value based on terrain
			
			//seed = rand.nextInt(700) + 1;
			a = rand.nextInt(3) + 1;
			
		}
		*/
		/*
		System.out.println(p);
		System.out.println(seed);
		*/
		//TODO-make cities able to index & able to search for cities based on coordinates
		
		//System.out.println(numberOfCities);
		
		/*
		for(Tuple<Integer, Integer, Double, Double, Double, Double> j : cities){
			System.out.println("City type: " + j.getCity());
			System.out.println("Random seed " + j.getSeed());
			System.out.println("Size of City " + j.getSize());
			System.out.println("X coordinate " + j.getX());
			System.out.println("Y coordinate " + j.getY());
			System.out.println("Z coordinate " + j.getZ() + "\n");
		}
		*/
		
		primaryStage.setScene(scene);
		primaryStage.show();
		//return cities
		
	}

	class Tuple<U, V, W, X, Y, Z> {
		public final U cityType;
		public final V seed;
		public final W size;
		public final X x;
		public final Y y;
		public final Z z;

		Tuple(U cityType, V seed, W size, X x, Y y, Z z) {
			this.cityType = cityType;
			this.seed = seed;
			this.size = size;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		U getCity() {
			return cityType;
		}
		V getSeed() {
			return seed;
		}
		W getSize(){
			return size;
		}
		X getX(){
			return x;
		}
		Y getY(){
			return y;
		}
		Z getZ(){
			return z;
		}
		
	}

}
