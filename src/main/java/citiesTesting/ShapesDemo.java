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

public class ShapesDemo extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		/*
		 * Camera camera = new PerspectiveCamera(true); scene.setCamera(camera);
		 * Group cameraGroup = new Group();
		 * cameraGroup.getChildren().add(camera);
		 * mainGroup.getChildren().add(cameraGroup);
		 */
		/*
		 * Cylinder cyl = Shapes.makeCylinder(200, 100, Color.LIGHTBLUE,
		 * Color.BLUE, 350, 300, 200); mainGroup.getChildren().add(cyl);
		 * 
		 * Cylinder cyl2 = Shapes.makeCylinder(200, 100, Color.RED,
		 * Color.DARKRED, 250, 350, 0); mainGroup.getChildren().add(cyl2);
		 */
		/*
		 * //TODO //Demo for cylinder houses int myX = 1000; int myY = 100; int
		 * myZ = 600; for(int i = 0; i < 6; i++){ //Cylinder cyl =
		 * Shapes.makeCylinder(200, 100, Color.SANDYBROWN, Color.BROWN, myX,
		 * myY, myZ); Cylinder cyl = Shapes.makeCylinder(200, 100,
		 * Color.LIGHTBLUE, Color.BLUE, myX, myY, myZ);
		 * mainGroup.getChildren().add(cyl); myX -= 50; myY += 50; myZ -= 200; }
		 * 
		 * myX = 500; myY = 100; myZ = 600; for(int i = 0; i < 6; i++){
		 * //Cylinder cyl = Shapes.makeCylinder(200, 100, Color.SANDYBROWN,
		 * Color.BROWN, myX, myY, myZ); Cylinder cyl = Shapes.makeCylinder(200,
		 * 100, Color.LIGHTBLUE, Color.BLUE, myX, myY, myZ);
		 * mainGroup.getChildren().add(cyl); myX -= 50; myY += 50; myZ -= 200; }
		 */

		/*
		 * Rectangle r = Shapes.makeRectangle(100, 200, 500, 100,
		 * Color.LIGHTBLUE, Color.BLUE); mainGroup.getChildren().add(r);
		 */

		/*
		 * Box b = Shapes.makeBox(900, 900, 900, Color.LIGHTBLUE, Color.BLUE,
		 * 200, 250, 200); b.setRotationAxis(Rotate.Y_AXIS); b.setRotate(25);
		 * mainGroup.getChildren().add(b);
		 * 
		 * 
		 * MeshView p = Shapes.makePyramid(100, 250, Color.PALEVIOLETRED,
		 * Color.RED, 200, 100, 200); mainGroup.getChildren().add(p);
		 */

		/*
		 * makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE,
		 * Color.BLUE, 200, 250, 200); //Makes a house structure
		 * makeHouse(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE,
		 * Color.BLUE, 450, 250, 200);
		 */
		/*
		 * int temp = 200; for(int i = 0; i < 4; i++){ makeHouse(mainGroup,
		 * Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, temp,
		 * 250, 200); temp += 250; }
		 */

		// TODO
		// Demo for basic houses in neighborhood
		/*
		Cylinder cyl = Shapes.makeCylinder(50, 500, Color.LIGHTBLUE, Color.BLUE, 350, 400, 200); 
		mainGroup.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(50, 500, Color.LIGHTBLUE, Color.BLUE, 700, 400, 200);
		mainGroup.getChildren().add(cyl2);
		Rectangle rect = Shapes.makeRectangle(500, 75, 275, 75, Color.AQUA);
		mainGroup.getChildren().add(rect);
		*/
		
		/*
		Cylinder cyl = Shapes.makeCylinder(50, 300, Color.LIGHTBLUE, Color.BLUE, 450, 200, 300); 
		mainGroup.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(50, 300, Color.LIGHTBLUE, Color.BLUE, 850, 200, 300);
		mainGroup.getChildren().add(cyl2);
		Cylinder cyl3 = Shapes.makeCylinder(50, 300, Color.LIGHTBLUE, Color.BLUE, 350, 300, 100);
		mainGroup.getChildren().add(cyl3);
		Cylinder cyl4 = Shapes.makeCylinder(50, 300, Color.LIGHTBLUE, Color.BLUE, 750, 300, 100);
		mainGroup.getChildren().add(cyl4);
		*/
		
		/*
		Cylinder cyl = Shapes.makeCylinder(50, 200, Color.LIGHTBLUE, Color.BLUE, 350, 400, 200); 
		mainGroup.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(50, 200, Color.LIGHTBLUE, Color.BLUE, 700, 400, 200);
		mainGroup.getChildren().add(cyl2);
		
		//public static Rectangle makeRectangle(int w, int h, int x, int y, Color color1)
		Rectangle rect = Shapes.makeRectangle(500, 75, 275, 275, Color.AQUA);
		mainGroup.getChildren().add(rect);
		*/
		
		
		//TODO - in the real version, do not change y value
		//the Y is only changed to show depth
		
		//makeThing(mainGroup, Color.LIGHTBLUE, Color.BLUE, Color.PURPLE, Color.LIGHTPINK, 500, 200, 300);
		/*
		Cylinder cyl = Shapes.makeCylinder(400, 100, Color.CRIMSON, Color.DARKKHAKI, 500, 500, 200);
		mainGroup.getChildren().add(cyl);
		*/

		
		makeGate(mainGroup, Color.ORANGE, Color.CORAL, Color.RED, Color.DARKRED, 100, 400, -500);
		
		
		int tempX = 450;
		int tempY = 200;
		int tempZ = 300;
		for (int i = 0; i < 4; i++) {
			makeHouse1(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, tempX, tempY, tempZ);
			tempX -= 100;
			tempY += 100;
			tempZ -= 200;

		}
		tempX = 850;
		tempY = 200;
		tempZ = 300;
		for (int i = 0; i < 4; i++) {
			makeHouse2(mainGroup, Color.PALEVIOLETRED, Color.RED, Color.LIGHTBLUE, Color.BLUE, tempX, tempY, tempZ);
			tempX -= 100;
			tempY += 100;
			tempZ -= 200;
		}
		
		
		/*
		Cylinder cyl = Shapes.makeCylinder(10, 100, Color.BROWN, Color.BROWN, 500, 300, 200);
		mainGroup.getChildren().add(cyl);
		*/
		//makeFences(mainGroup, 400, 800, Color.BROWN, Color.BROWN, 50, 600, -500);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	
	public void makeFences(Group mg, int l, int w, Color color1, Color color2, int x, int y, int z){
		int newL = l + 200;
		int newW = w + 200;
			
		int newX = x - 100;
		int newZ = z - 100;
		
		//make front fences
		while(newX <= (x-100) + newL){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newX += 100;
		}
			
		//make left fences
		while(newZ <= (z-100) + newW){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newZ += 100;
		}
			
		//make back fences
		while(newX >= (x-100)){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newX -= 100;
		}
			
		//make right fences
		while(newZ >= (z-100)){
			mg.getChildren().add(Shapes.makeCylinder(10, 100, color1, color2, newX, y, newZ));
			newZ -= 100;
		}
	}
	
	
	public void makeHouse1(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		MeshView p = Shapes.makePyramid(100, 250, roof1, roof2, x, y - 150, z);
		mg.getChildren().add(p);
		// return temp;
	}
	
	public void makeHouse2(Group mg, Color roof1, Color roof2, Color house1, Color house2, int x, int y, int z) {
		Box b = Shapes.makeBox(100, 100, 100, house1, house2, x, y, z);
		b.setRotationAxis(Rotate.Y_AXIS);
		b.setRotate(25);
		mg.getChildren().add(b);

		Cylinder c = Shapes.makeCylinder(100, 50, roof1, roof2, x, y-75, z);
		mg.getChildren().add(c);
	}
	
	public void makeRect(Group mg, int l, int h, Color color1, Color color2, int x, int y, int z){
		int tmpX = x;
		int tmpY = y;
		if(l > h){
			int len = l / h;
			for(int i = 0; i < len; i++){
				mg.getChildren().add(Shapes.makeBox(h, h, h, color1, color2, tmpX, y, z));
				tmpX += 100;
			}
		}
		else if (l < h){
			int height = h / l;
			for(int i = 0; i < height; i ++){
				mg.getChildren().add(Shapes.makeBox(l, l, l, color1, color2, x, tmpY, z));
				tmpY -= 100;
			}
		}
	}
	
	//makeGate(mainGroup, Color.ORANGE, Color.CORAL, Color.DARKRED, 350, 400, 200, 500);
	public void makeGate(Group mg, Color cColor1, Color cColor2, Color rColor1, Color rColor2, int x, int y, int z){
		int height = 500;
		Cylinder cyl = Shapes.makeCylinder(50, height, cColor1, cColor2, x, y, z); 
		mg.getChildren().add(cyl);
		Cylinder cyl2 = Shapes.makeCylinder(50, height, cColor1, cColor2, x + 350, y, z);
		mg.getChildren().add(cyl2);

		Box b = Shapes.makeBox(500, 75, 75, rColor1, rColor2, x+175, y-285, z);
		mg.getChildren().add(b);

		
	}
	
	public void makeCentralSpiral(Group mg, Color color1, Color color2, Color color3, Color color4, int x, int y, int z){
		MeshView p = Shapes.makePyramid(100, 250, color3, color4, x, y-350, z);
		mg.getChildren().add(p);
		
		Cylinder cyl = Shapes.makeCylinder(50, 500, color1,color2, x, y, z); 
		mg.getChildren().add(cyl);
		
		int temp = y - 200;
		for(int i = 0; i < 9; i++){
			mg.getChildren().add(Shapes.makeCylinder(100, 25, color3, color4, x, temp, z));
			temp += 50;
		}
	}
	
	public void makeCentralCone(Group mg, Color color1, Color color2, int x, int y, int z, int w){
		/*
		int tempX = 500;
		int tempY = 600;
		int tempW = 400;
		*/
		for(int i = 0; i < 7; i++){
			mg.getChildren().add(Shapes.makeCylinder(w, 100, color1, color2, x, y, z));
			x += 15;
			y -= 100;
			w -= 50;
		}
	}
}
