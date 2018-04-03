package citiesTesting;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class CityMaker extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		
		
		//TODO-make a seed when generating cities to make sure
		//that they remain the same once you leave and come back
		Random rand = new Random();
		int a = rand.nextInt(2)+1;
		//int a = 2;
		//int b = rand.nextInt(100)+15;
		//how many cities ^
		
		//TODO-use random int values to create random cities
		//one for how many buildings, and one for what type of building
		
		//TODO-randomize x, y, z based on the world
		//TODO- change x, y, z so that
		//1 double = 1 meter
		double x = 400.0;
		double y = 300.0;
		double z = 200.0;
		
		/*
		Color roof1 = Color.PINK;
		Color roof2 = Color.HOTPINK;
		Color house1 = Color.LIGHTBLUE;
		Color house2 = Color.CADETBLUE;
		Color fen = Color.BROWN;
		*/
		Color roof1 = BuildingTypes.colorAssignment(rand);
		Color roof2 = BuildingTypes.secondaryColor(roof1);
		Color house1 = BuildingTypes.colorAssignment(rand);
		Color house2 = BuildingTypes.secondaryColor(house1);
		//Color fen = BuildingTypes.colorAssignment(rand);
		
		
		//city type 1 (square city)
		if(a == 1 ){
			BuildingTypes.makeCity1(mainGroup, roof1, roof2, house1, house2, x, y, z, rand);
		}
		//city type 2 (circular city)
		else if (a == 2){
			//TODO-make circular city
			BuildingTypes.makeCity2(mainGroup, roof1, roof2, house1, house2, x, y, z, rand.nextInt(3)+1, rand);
		}
		
		//BuildingTypes.makeHouse1(mainGroup, Color.HOTPINK, Color.PINK, Color.LIGHTSKYBLUE, Color.CADETBLUE, 300, 200, 200);
		//TODO-n.b. do not change y-values
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

	protected boolean isInWater(Box box, float posX, float posY){		
		return false;
	}
	
}
