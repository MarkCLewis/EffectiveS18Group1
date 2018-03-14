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
		
		Random rand = new Random();
		int a = rand.nextInt(10)+1;
		//int b = rand.nextInt(10)+1;
		//TODO-use random int values to create random cities
		//one for how many buildings, and one for what type of building
		
		//BuildingTypes.makeHouse1(mainGroup, Color.HOTPINK, Color.PINK, Color.LIGHTSKYBLUE, Color.CADETBLUE, 300, 200, 200);
		//TODO-n.b. do not change y-values
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	protected boolean isInWater(Box box, float posX, float posY){		
		return false;
	}
	
}
