package virtualworld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import animals.Giraffe;
import animals.Pig;
import animals.Sheep;
import citiesTesting.CityOne;
import citiesTesting.CityThree;
import citiesTesting.CityTwo;
import graphicsTesting.CameraController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;
import quad.AllObjects;
import quad.ElementVisitor;
import quad.NotifyObjects;
import quad.QuadTree;
import terraintesting.TerrainObjectBasic;
import trees.Tree;

public class Main extends Application {
	static List<WorldObject> itemRendered = new ArrayList<WorldObject>();
	static List<Shape3D> toBeDrawn = new ArrayList<Shape3D>();
	static ArrayList<ElementVisitor> visitList = new ArrayList<ElementVisitor>();
	private double x1;
	private double z1;
	private double x2;
	private double z2;
	private static final int terrainSize = 1000;
	private static ArrayList<MeshView> mvCopies = new ArrayList<MeshView>();
	private static int centerTerrX = 0;
	private static int centerTerrZ = 0;
	private static TerrainObjectBasic[] world = setWorldTerrains(centerTerrX, centerTerrZ);
	
	private double worldSize = 6000;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	//Scene Setup
		primaryStage.setTitle("Virtual World");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 1280, 720, true);
		scene.setFill(Color.LIGHTBLUE);
	
	//Camera Setup
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		CameraController pCam = new CameraController.Builder(camera).build();
		x1 = pCam.getCameraX();
		z1 = pCam.getCameraZ();
		
		
	//QuadTree Setup
		QuadTree quad = QuadTree.getInstance();
		QuadTree.cameraX = pCam.getCameraX();
		QuadTree.cameraZ = pCam.getCameraZ();
		
		quad.insert(new ExampleObject(0, 0, worldSize + 10000), null); //TODO replace with large terrain piece
		
		//CityOne exampleCity3 = CityOne.returnObj(mainGroup);
		//CityOne exampleCity4 = CityOne.returnObj(mainGroup);
		//CityOne exampleCity5 = CityOne.returnObj(mainGroup);
		
		
		
		//quad.insert(exampleCity3, quad.getRootNode());
		//quad.insert(exampleCity4, quad.getRootNode());
		//quad.insert(exampleCity5, quad.getRootNode());
		
		System.out.println(itemRendered.size());
		
	// Terrain 
		/*int actualWorldSize = (int) (worldSize*2);
		int terrainSize = 1000;
		float noise = (float)0.3;
		int seed = 3838;
		int scale = 10;
		/*ArrayList<TerrainObjectBasic> world = new ArrayList<TerrainObjectBasic>();
		for(int x=(int) -worldSize; x<worldSize; x+=terrainSize) {
			for(int z=(int) -worldSize; z<worldSize; z+=terrainSize) {
				world.add(new TerrainObjectBasic(x, z, terrainSize, scale, noise, seed));
			}
		}*/
		//for(TerrainObjectBasic terr : world) {
		//TerrainObjectBasic terr = new TerrainObjectBasic(0, 0, terrainSize, scale, noise, seed);
		//	quad.insert(terr, quad.getRootNode());
		//}*/
		
	//Visitors
		/**
		visitList.add((ElementVisitor) renderCollector);
		renderCollector.visit(quad.getRootNode());
		for (WorldObject items : renderCollector.validObjects) {
			itemRendered.add((WorldObject) items);
			toBeDrawn.addAll(items.display());
		}
		**/
		System.out.println(itemRendered.size());
		
		//String[] args = null;
		//Group buildingGroup = new Group();
		//CityMaker.start();
		
		//mainGroup.getChildren().add(buildingGroup);
		mainGroup.getChildren().add(cameraGroup);
		mainGroup.getChildren().addAll(toBeDrawn);
		
		//Calls appropriate movement methods from pCam when key press is detected
		//KeyCodes are stored in a set so multiple commands can be executed at once
		Set<KeyCode> keySet = new HashSet<KeyCode>();
		scene.setOnKeyPressed(event ->{ 
			 KeyCode key = event.getCode();
			 keySet.add(key);
			 
			 if(keySet.contains(KeyCode.W)) {
				 pCam.moveForward();
			 }
			 if(keySet.contains(KeyCode.S)) {
				 pCam.moveBackward();
			 }
			 if(keySet.contains(KeyCode.A)) {
				 pCam.moveLeft();
			 }
			 if(keySet.contains(KeyCode.D)) {
				 pCam.moveRight();
			 }
			 if(keySet.contains(KeyCode.RIGHT)) {
				 pCam.rotateRight();
			 }
			 if(keySet.contains(KeyCode.LEFT)) {
				 pCam.rotateLeft();
			 }
			 if(keySet.contains(KeyCode.UP)) {
				 pCam.rotateUp();
			 }
			 if(keySet.contains(KeyCode.DOWN)) {
				 pCam.rotateDown();
			 }
			 if(keySet.contains(KeyCode.R)) {
				 pCam.moveUp();
			 }
			 if(keySet.contains(KeyCode.F)) {
				 pCam.moveDown();
			 }
			 if(keySet.contains(KeyCode.SHIFT)) {
				 pCam.boostOn();
			 }
			 if(keySet.contains(KeyCode.L)) {
				 System.out.println("X = "+camera.getTranslateX()+" Z = "+camera.getTranslateZ()+" Y = "+camera.getTranslateY());
			 }
		});
		
		//If a key is released, it is removed from the set and the associated method stops being called
		//Manually disables boost
		scene.setOnKeyReleased(event ->{
			KeyCode key = event.getCode();
			if(key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);
			
		});
		
		/*Cities
		//Making city objects
		CityOne cOne = CityOne.returnObj(mainGroup);
		//grid type city
		CityTwo cTwo = CityTwo.returnObj(mainGroup);
		//circular city
		CityThree cThree = CityThree.returnObj(mainGroup);
		//diamond city
		*/
		
		/*Terrain
		TerrainObjectBuilder bldr = new TerrainObjectBuilder();
		double x = 5; 
		double z = 10; 
		double y = 12;
		double xW = 200; 
		double zW = 200; 
		double yW = 500;
		long seed = 12345687654L;
		double noise = 0.75;
		bldr.setXLoc(x);
		bldr.setYLoc(y);
		bldr.setZLoc(z);
		bldr.setXWidth(xW);
		bldr.setYWidth(yW);
		bldr.setZWidth(zW);
		bldr.setSeed(seed);
		bldr.setNoise(noise);
		
		TerrainObject terr = bldr.build();
		mainGroup.getChildren().addAll(terr.display());
		*/
		
		/*
		CityOne cOne = CityOne.returnObj(mainGroup);
		System.out.println(cOne.getX() + " " + cOne.getZ());
		System.out.println(cOne.display().size());
		for (Shape3D shape : cOne.display()) {
			mainGroup.getChildren().add(shape);
		}
		*/
		//List<sheep> sheepList = new ArrayList<sheep>();
		//for (int i = 0; i < 100; i++) sheepList.add(animals.sheep.returnObj(mainGroup));
		
		//for (int i = 0; i < 10; i++) {
			//CityOne exampleCity = CityOne.returnObj(mainGroup);
			CityOne exampleCity = CityOne.returnObj(mainGroup);
			System.out.println(exampleCity.getXLoc());
			System.out.println(exampleCity.getZLoc());
			//CityTwo exampleCity1 = CityTwo.returnObj(mainGroup);
			//CityThree exampleCity2 = CityThree.returnObj(mainGroup);
			quad.insert(exampleCity, quad.getRootNode());
			//quad.insert(exampleCity1, quad.getRootNode());
			//quad.insert(exampleCity2, quad.getRootNode());
			CityTwo exampleCity2 = CityTwo.returnObj(mainGroup);
			quad.insert(exampleCity2, quad.getRootNode());
			System.out.println(exampleCity2.getXLoc());
			System.out.println(exampleCity2.getZLoc());
		//}
		
		for (int i = 0; i < 1000; i++) {
			Tree tree = new Tree();
			tree.buildTree();
			quad.insert(tree, quad.getRootNode());
		}
	
		
		for (int i = 0; i < 300; i++)  {
			Sheep sheep = Sheep.returnObj(mainGroup);
			Pig pig = Pig.returnObj(mainGroup);
			Giraffe giraffe = Giraffe.returnObj(mainGroup);
			quad.insert(sheep, quad.getRootNode());
			quad.insert(pig, quad.getRootNode());
			quad.insert(giraffe, quad.getRootNode());
		}
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				x2 = pCam.getCameraX();
				z2 = pCam.getCameraZ();
				
				if(euclid(x1,x2,z1,z2)) {
					x1 = x2;
					z1 = z2;
					System.out.println("x: " + x2 + " z: " + z2);
					QuadTree.cameraX = pCam.getCameraX();
					QuadTree.cameraZ = pCam.getCameraZ();
					itemRendered.clear();
					toBeDrawn.clear();
					mainGroup.getChildren().clear();
					
					AllObjects allCollector = new AllObjects();
					allCollector.visit(quad.getRootNode());
					System.out.println("Nodes: " + allCollector.allNodes.size());
					System.out.println("WorldObjects: " + allCollector.allWorldObjects.size());
					
					for (WorldObject items : allCollector.allWorldObjects) {
						//System.out.println("WorldObject Coor: " + items.getXLoc() + ", " + items.getZLoc());
						//System.out.println(items.getSize());
					}
					
					NotifyObjects renderCollector = new NotifyObjects();
					renderCollector.visit(quad.getRootNode());
					for (WorldObject items : renderCollector.validObjects) {
						itemRendered.add((WorldObject) items);
						toBeDrawn.addAll(items.display());
					}
					
					System.out.println(renderCollector.validObjects.size());
					System.out.println("SIZE" + itemRendered.size());
					//System.out.println("SIZE" + toBeDrawn.size());
					
					
					//Point3D lightRotation = new Point3D(mesh.getPoints().get(50), mesh.getPoints().get(51) + 100, mesh.getPoints().get(52));
					//light.setRotationAxis();
					
					
					/*
					Light.Distant light = new Light.Distant();
					light.setAzimuth(0); 
				    light.setElevation(100);
				    Lighting lighting = new Lighting();
				    lighting.setLight(light);
				    mv.setEffect(lighting);
					*/
					
					//mv.setDrawMode(DrawMode.FILL);
					//mv.setMaterial(pm);
					
					
					
					//mainGroup.getChildren().add(light);
					
					scene.setCamera(camera);
					scene.setRoot(mainGroup);
					mainGroup.getChildren().addAll(toBeDrawn);
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				
				/*
				for(TerrainObjectBasic terr : world) {
					MeshView mv = terr.getMeshview();
					mvCopies.add(mv);
					mainGroup.getChildren().add(mv);
				}*/
				PointLight light = new PointLight();
				light.setRotate(45);
				light.setLayoutX(500);
				light.setLayoutY(500);
				light.setTranslateZ(500);
				light.setScaleX(5);
				//light.setScaleY(10);
				
				//camera.setTranslateX(500);
				//camera.setTranslateZ(500);
				
				//if(checkCamPosition((int)camera.getTranslateX(), (int)camera.getTranslateZ())) {
            	//	updateTerrains((int)camera.getTranslateX(), (int)camera.getTranslateZ(), mainGroup);
            	//}
				
				
			}
		}.start();
	}
	
	private boolean euclid(double x1, double x2, double z1, double z2) {
		//System.out.println(Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(z2-z1, 2)));
		return (Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(z2-z1, 2)) > 10);
	}
	
	public static TerrainObjectBasic[] setWorldTerrains(int x, int z) {
		//Forward strip
		TerrainObjectBasic terr1 = new TerrainObjectBasic(x-terrainSize, z+terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr2 = new TerrainObjectBasic(x, z+terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr3 = new TerrainObjectBasic(x+terrainSize, z+terrainSize, terrainSize, 10, (float) 0.3, 3838);
		//Middle strip
		TerrainObjectBasic terr4 = new TerrainObjectBasic(x-terrainSize, z, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr5 = new TerrainObjectBasic(x, z, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr6 = new TerrainObjectBasic(x+terrainSize, z, terrainSize, 10, (float) 0.3, 3838);
		//Backward strip
		TerrainObjectBasic terr7 = new TerrainObjectBasic(x-terrainSize, z-terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr8 = new TerrainObjectBasic(x, z-terrainSize, terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr9 = new TerrainObjectBasic(x+terrainSize, z-terrainSize, terrainSize, 10, (float) 0.3, 3838);
		/*
		TerrainObjectBasic terr5 = new TerrainObjectBasic(x, 		z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr4 = new TerrainObjectBasic(x+1000, 	z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr6 = new TerrainObjectBasic(x+1000, 	z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr2 = new TerrainObjectBasic(x, 		z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		//TerrainObjectBasic terr1 = new TerrainObjectBasic(x-1000,	z+1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr3 = new TerrainObjectBasic(x-1000,	z, 		terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr7 = new TerrainObjectBasic(x, 		z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr8 = new TerrainObjectBasic(x-1000,	z-1000, 	terrainSize, 10, (float) 0.3, 3838);
		TerrainObjectBasic terr9 = new TerrainObjectBasic(x+1000,		z-1000, 	terrainSize, 10, (float) 0.3, 3838);*/
		TerrainObjectBasic[] world = {terr1, terr2, terr3, terr4, terr5, terr6, terr7, terr8, terr9};
		return world;
	}
	
	public boolean checkCamPosition(int camX, int camZ) {
		return camX<centerTerrX || camZ<centerTerrZ || camX>terrainSize+centerTerrX || camZ>terrainSize+centerTerrZ;
	}
	
	//updates terrains and graphics
		public void updateTerrains(int camX, int camZ, Group g) {
			if(camZ>terrainSize+centerTerrZ) {
				//unload backstrip
				g.getChildren().remove(mvCopies.get(7-1));
				g.getChildren().remove(mvCopies.get(8-1));
				g.getChildren().remove(mvCopies.get(9-1));
				centerTerrZ+=terrainSize;
				//Build new forward strip
				TerrainObjectBasic terr1 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr2 = new TerrainObjectBasic(centerTerrX, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr3 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
				
				world[7-1] = world[4-1];
				world[8-1] = world[5-1];
				world[9-1] = world[6-1];
				
				world[4-1] = world[1-1];
				world[5-1] = world[2-1];
				world[6-1] = world[3-1];
				
				world[1-1] = terr1;
				world[2-1] = terr2;
				world[3-1] = terr3;

				mvCopies.remove(9-1);
				mvCopies.remove(8-1);
				mvCopies.remove(7-1);
				
				mvCopies.add(1-1, terr1.getMeshview());
				mvCopies.add(2-1, terr2.getMeshview());
				mvCopies.add(3-1, terr3.getMeshview());
				
				/*mvCopies.add(1-1, terr1.getMeshview());
				mvCopies.add(2-1, terr2.getMeshview());
				mvCopies.add(3-1, terr3.getMeshview());*/
				
				g.getChildren().add(mvCopies.get(1-1));
				g.getChildren().add(mvCopies.get(2-1));
				g.getChildren().add(mvCopies.get(3-1));
			}
			else if(camZ<centerTerrZ) {
				g.getChildren().remove(mvCopies.get(1-1));
				g.getChildren().remove(mvCopies.get(2-1));
				g.getChildren().remove(mvCopies.get(3-1));
				centerTerrZ-=terrainSize;
				TerrainObjectBasic terr7 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr8 = new TerrainObjectBasic(centerTerrX, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr9 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
				
				world[1-1] = world[4-1];
				world[2-1] = world[5-1];
				world[3-1] = world[6-1];
				
				world[4-1] = world[7-1];
				world[5-1] = world[8-1];
				world[6-1] = world[9-1];
				
				world[7-1] = terr7;
				world[8-1] = terr8;
				world[9-1] = terr9;
				
				mvCopies.remove(3-1);
				mvCopies.remove(2-1);
				mvCopies.remove(1-1);
				
				mvCopies.add(7-1, terr7.getMeshview());
				mvCopies.add(8-1, terr8.getMeshview());
				mvCopies.add(9-1, terr9.getMeshview());
				
				g.getChildren().add(mvCopies.get(7-1));
				g.getChildren().add(mvCopies.get(8-1));
				g.getChildren().add(mvCopies.get(9-1));
				System.out.println(mvCopies.size());
			}
			else if(camX>terrainSize+centerTerrX) {
				g.getChildren().remove(mvCopies.get(1-1));
				g.getChildren().remove(mvCopies.get(4-1));
				g.getChildren().remove(mvCopies.get(7-1));
				centerTerrX+=terrainSize;
				TerrainObjectBasic terr3 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr6 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr9 = new TerrainObjectBasic(centerTerrX+terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
				
				world[1-1] = world[2-1];
				world[4-1] = world[5-1];
				world[7-1] = world[8-1];
				
				world[2-1] = world[3-1];
				world[5-1] = world[6-1];
				world[8-1] = world[9-1];
				
				world[3-1] = terr3;
				world[9-1] = terr6;
				world[9-1] = terr9;
				
				mvCopies.remove(7-1);
				mvCopies.remove(4-1);
				mvCopies.remove(1-1);
				
				mvCopies.add(3-1, terr3.getMeshview());
				mvCopies.add(6-1, terr6.getMeshview());
				mvCopies.add(9-1, terr9.getMeshview());
				
				g.getChildren().add(mvCopies.get(3-1));
				g.getChildren().add(mvCopies.get(6-1));
				g.getChildren().add(mvCopies.get(9-1));
			}
			else if(camX<centerTerrX) {
				g.getChildren().remove(mvCopies.get(3-1));
				g.getChildren().remove(mvCopies.get(6-1));
				g.getChildren().remove(mvCopies.get(9-1));
				centerTerrX-=terrainSize;
				TerrainObjectBasic terr1 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ+terrainSize, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr4 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ, terrainSize, 10, (float) 0.3, 3838);
				TerrainObjectBasic terr7 = new TerrainObjectBasic(centerTerrX-terrainSize, centerTerrZ-terrainSize, terrainSize, 10, (float) 0.3, 3838);
				
				world[3-1] = world[2-1];
				world[6-1] = world[5-1];
				world[9-1] = world[8-1];
				
				world[2-1] = world[1-1];
				world[5-1] = world[4-1];
				world[8-1] = world[7-1];
				
				world[1-1] = terr1;
				world[4-1] = terr4;
				world[7-1] = terr7;
				
				
				mvCopies.remove(9-1);
				mvCopies.remove(6-1);
				mvCopies.remove(3-1);
				
				mvCopies.add(1-1, terr1.getMeshview());
				mvCopies.add(4-1, terr4.getMeshview());
				mvCopies.add(7-1, terr7.getMeshview());
				
				g.getChildren().add(mvCopies.get(1-1));
				g.getChildren().add(mvCopies.get(4-1));
				g.getChildren().add(mvCopies.get(7-1));
			}
		}
	
}


	

//Key Controls
	/*
	 * W - Forward
	 * S - Backward
	 * A - Left
	 * D - Right
	 * Up - Look up
	 * R - Ascend
	 * F - Descend
	 * Down - Look Down
	 * Left - Look Left
	 * Right - Look Right
	 * Hold Shift - Speed Boost
	 */