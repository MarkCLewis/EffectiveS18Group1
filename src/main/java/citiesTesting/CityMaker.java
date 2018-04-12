package citiesTesting;

import java.util.Random;

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

public class CityMaker extends Application{
	public static void main(String[] args){
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception{
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		
		/*
		Camera cam = new PerspectiveCamera(true);
		scene.setCamera(cam);
		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(cam);
		mainGroup.getChildren().add(cameraGroup);
		
		CameraController pCam = new CameraController.Builder(cam).build();
		
		scene.setOnKeyPressed(event ->{
			 KeyCode key = event.getCode();
			 if(key == KeyCode.W) {
				 pCam.moveForward();
			 }
			 if(key == KeyCode.S) {
				 pCam.moveBackward();
			 }
			 if(key == KeyCode.A) {
				 pCam.moveLeft();
			 }
			 if(key == KeyCode.D) {
				 pCam.moveRight();
			 }
			 
			 if(key == KeyCode.RIGHT) {
				 pCam.rotateRight();
			 }
			 if(key == KeyCode.LEFT) {
				 pCam.rotateLeft();
			 }
			 if(key == KeyCode.UP) {
				 pCam.rotateUp();
			 }
			 if(key == KeyCode.DOWN) {
				 pCam.rotateDown();
			 }
			 if(key == KeyCode.R) {
				 pCam.moveUp();
			 }
			 if(key == KeyCode.F) {
				 pCam.moveDown();
			 }
			});
		*/
		
		//TODO-make a seed when generating cities to make sure
		//that they remain the same once you leave and come back
		Random rand = new Random();
		int a = rand.nextInt(3)+1;
		//int a = 1;
		
		//int b = rand.nextInt(50)+15;
		//how many cities ^
		
		//TODO-use random int values to create random cities
		//one for how many buildings, and one for what type of building
		
		//TODO-randomize x, y, z based on the world
		//1 double = 1 meter
		double x = 400.0;
		double y = 300.0;
		double z = 200.0;

		double p = BuildingTypes.makeCoordinate(-2000, 2000);
		//making random coordinates^
		//TODO-find out how big the world is so that I can make random coordinates
		
		//Math.floor(Math.random() * ((1000-(-1000))+1) + (-1000));
		
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
		else if (a == 3){
			BuildingTypes.makeCity3(mainGroup, roof1, roof2, house1, house2, x, y, z, rand.nextInt(3)+1, rand);
		}
		
		System.out.println(p);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

	protected boolean isInWater(Box box, float posX, float posY){		
		return false;
		//TODO
	}
	
}
