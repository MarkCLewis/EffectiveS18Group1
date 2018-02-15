package agua;
	

import java.awt.AWTException;
import java.awt.Robot;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.AmbientLight;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.paint.*;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Mesh;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



@SuppressWarnings("restriction")
public class sheetTesting extends Application {
	
	private PerspectiveCamera camera;
	 private final double cameraModifier = 50.0;
	 private final double cameraQuantity = 2.0;
	 private final double sceneWidth = 1280;
	 private final double sceneHeight = 720;
	 private double mouseXold = 0;
	 private double mouseYold = 0;
	 private final double cameraYlimit = 15;
	 private final double rotateModifier = 25; 
	 
	private static final int width = 60;
	private static final int height = 60;
	private static final int maxElevation = 2000;
	private static int[][] elevation = new int[width][height];
	
	Text text = new Text();
	
	 float h = 3;                    // Height
	 float s = 3;                    // Side
	 float[] test = {0f, 0f, -0.951057f, 
		        0f, 0f, 0.951057f, 
		        -0.850651f, 0f, -0.425325f, 
		        0.850651f, 0f, 0.425325f, 
		        0.688191f, -0.5f, -0.425325f, 
		        0.688191f, 0.5f, -0.425325f, 
		        -0.688191f, -0.5f, 0.425325f, 
		        -0.688191f, 0.5f, 0.425325f, 
		        -0.262866f, -0.809017f, -0.425325f, 
		        -0.262866f, 0.809017f, -0.425325f, 
		        0.262866f, -0.809017f, 0.425325f, 
		        0.262866f, 0.809017f, 0.425325f};         
	 
	 int [] faceTest = {1, 6, 11, 5, 7, 0, 
	            1, 12, 7, 11, 6, 5, 
	            1, 7, 6, 6, 10, 1, 
	            1, 13, 10, 12, 3, 6, 
	            1, 8, 3, 7, 11, 2,
	            4, 14, 8, 13, 0, 7, 
	            5, 9, 4, 8, 0, 3, 
	            9, 15, 5, 14, 0, 8, 
	            2, 10, 9, 9, 0, 4, 
	            8, 16, 2, 15, 0, 9,
	            11, 5, 9, 6, 7, 12,
	            7, 11, 2, 12, 6, 17, 
	            6, 6, 8, 7, 10, 13, 
	            10, 12, 4, 13, 3, 18, 
	            3, 7, 5, 8, 11, 14,
	            4, 13, 10, 14, 8, 19, 
	            5, 8, 3, 9, 4, 15, 
	            9, 14, 11, 15, 5, 20, 
	            2, 9, 7, 10, 9, 16, 
	            8, 15, 6, 16, 2, 21};
	 
	 float [] testCoords = {
			 0.181818f, 0f, 
	            0.363636f, 0f, 
	            0.545455f, 0f, 
	            0.727273f, 0f, 
	            0.909091f, 0f,
	            0.0909091f, 0.333333f,
	            0.272727f, 0.333333f, 
	            0.454545f, 0.333333f, 
	            0.636364f, 0.333333f, 
	            0.818182f, 0.333333f, 
	            1f, 0.333333f, 
	            0f, 0.666667f, 
	            0.181818f, 0.666667f, 
	            0.363636f, 0.666667f, 
	            0.545455f, 0.666667f, 
	            0.727273f, 0.666667f, 
	            0.909091f, 0.666667f, 
	            0.0909091f, 1f, 
	            0.272727f, 1f, 
	            0.454545f, 1f, 
	            0.636364f, 1f, 
	            0.818182f, 1f
	 };
		int counter = 0;
	
	@Override
	public void start(Stage primaryStage) throws AWTException {
		
		Canvas canvas = new Canvas(100,200);
		GraphicsContext gc = canvas.getGraphicsContext2D(); // Probably gonna remove this stuff later, just using it to get text on the screen
        gc.setLineWidth(1.0);
        gc.setFill(Color.BLANCHEDALMOND);


		
		Group sceneRoot = new Group();
		Scene scene = new Scene(sceneRoot, 1280, 720);
		scene.setFill(Color.BEIGE);
		
		text.setFont(new Font(20));
		
		 // Camera stuff
		PerspectiveCamera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		camera.setTranslateZ(-100);
		camera.setFarClip(10000);
		camera.setNearClip(7.5);
		
		Rotate xRotate = new Rotate(0,0,0,0,Rotate.X_AXIS);
		Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
		camera.getTransforms().addAll(xRotate,yRotate);
		buildElevation();

		 scene.setOnKeyPressed(event-> {
			 double change = cameraQuantity;
			 if(event.isShiftDown()) { change = cameraModifier;}
			 KeyCode key = event.getCode();
			 if(key == KeyCode.Q) {System.exit(0);}
			 if(key == KeyCode.W) {camera.setTranslateZ(camera.getTranslateZ() + change);}
			 if(key == KeyCode.S) {camera.setTranslateZ(camera.getTranslateZ() - change);}
			 if(key == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change);}
			 if(key == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change);}
			 if(key == KeyCode.Z) { camera.setTranslateY(camera.getTranslateY() + change);}
			 if(key == KeyCode.X) { camera.setTranslateY(camera.getTranslateY() - change);}
			 
			 if(key == KeyCode.G) {			
				 String output = "" + elevation[counter][counter] + ""; 
				 gc.strokeText(output, 10, 50);
				 counter++;
			 }
		 });
		 
		 Robot mouse = new Robot();
		 Cursor pointy;
		
		 scene.addEventHandler(MouseEvent.ANY, event -> {
			 if (event.getEventType() == MouseEvent.MOUSE_MOVED)
			 	{
				 double mouseXnew = event.getSceneX();
				 double mouseYnew = event.getSceneY();
				 
				//calculate the rotational change of the camera pitch
				 double pitchRotate =xRotate.getAngle()+(mouseYnew - mouseYold) / rotateModifier;
				 //set min/max camera pitch to prevent camera flipping
				 pitchRotate = pitchRotate > cameraYlimit ? cameraYlimit : pitchRotate;
				 pitchRotate = pitchRotate < -cameraYlimit ? -cameraYlimit : pitchRotate;
				 //replace the old camera pitch rotation with the new one.
				 xRotate.setAngle(pitchRotate);
				 //calculate the rotational change of the camera yaw
				 double yawRotate=yRotate.getAngle()-(mouseXnew - mouseXold) / rotateModifier;
				 yRotate.setAngle(yawRotate);
				
				 mouseXold = mouseXnew;
				 mouseYold = mouseYnew;
				 scene.setCursor(Cursor.NONE);
			 	}
			 if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
			 {
				 mouse.mouseMove(
						 (int)scene.getWidth()/2,
						 (int)scene.getHeight()/2);
				 //camera.setTranslateX(change);
				 //sceneRoot.getChildren().add(createMeshView(test, faceTest, camera.getTranslateX()+10, camera.getTranslateY(), camera.getTranslateZ() ));
			 }
			 if(event.getEventType() == MouseEvent.MOUSE_EXITED)
			 {
				
			 }
		});
		 
		 
		 

		sceneRoot.getChildren().add(camera);
		int numWaves = 10;
		int numWaveRows = 13;
		MeshView[][] wavePool = buildBody(numWaves, numWaveRows);
		for(int z=0; z<numWaveRows;z++)
		{
			for(int i=0;i<numWaves;i++)
			{
				RotateTransition rotator = rotator(wavePool[z][i]);
				//scene.strokePolygon(z, i, 6);
				rotator.play();
				sceneRoot.getChildren().add(wavePool[z][i]);
			}
		}
/*		MeshView mv = createMeshView(test,testCoords, faceTest, 0, 0, 10 );
		RotateTransition rotator = rotator(mv);
        rotator.play();*/

        
        /*PointLight pointLight = new PointLight(Color.WHITE);
        pointLight.setTranslateX(camera.getTranslateX());
        pointLight.setTranslateY(camera.getTranslateY());
        pointLight.setTranslateZ(camera.getTranslateZ());
        pointLight.setRotate(90);*/
        
        AmbientLight ambience = new AmbientLight(); // default color white
        ambience.setLightOn(true); // switch it off and everything is black
        
        //sceneRoot.getChildren().add(pointLight);
        sceneRoot.getChildren().add(ambience);
        sceneRoot.getChildren().add(canvas);
		//sceneRoot.getChildren().add(mv);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
private RotateTransition rotator(Node temp) {
	        RotateTransition rotator = new RotateTransition(Duration.millis(10000), temp);
	        
	        rotator.setAxis(Rotate.X_AXIS); //Z/X/Y axis
	        
	        rotator.setFromAngle(0);
	        rotator.setToAngle(360); // causes it to spin in a circle
	        rotator.autoReverseProperty();
	        rotator.setInterpolator(Interpolator.LINEAR); // DISCRETE, EASE_BOTH, EASE_IN, EASE_OUT
	        rotator.setCycleCount(100);

	        return rotator;
	    }
	
	
private MeshView createMeshView(float [] Points, float[] texCoords, int[] Faces, double X, double Y, double Z) {
	TriangleMesh mesh = new TriangleMesh();

	mesh.getTexCoords().addAll(texCoords);

	mesh.getPoints().addAll(Points);
	
	mesh.getFaces().addAll(Faces);
	
	MeshView meshView = new MeshView(mesh);
	meshView.setDrawMode(DrawMode.FILL);
	meshView.setMaterial(new PhongMaterial(Color.BLUE));
	
	//PhongMaterial imageMat = new PhongMaterial();
	
    //imageMat.setDiffuseMap(new Image(getClass().getResourceAsStream("testsurface_04.png")));
    //meshView.setMaterial(imageMat);
    
	meshView.setTranslateX(X/*200*/);
	meshView.setTranslateY(Y/*100*/);
	meshView.setTranslateZ(Z/*200*/);
	
	//meshView.setBlendMode(BlendMode.DARKEN);
	meshView.resize(10, 10);
	
	return meshView;
	
}
private MeshView[][] buildBody(int numOfParts, int numRows)
{
	MeshView[][] wave = new MeshView[numRows+1][numOfParts+1];
	//int rowxcolumn = numOfParts/numRows;
	double shake;
	for(int z = 0; z<numRows; z++)
	{
		for(int i = 0; i<numOfParts; i++)
		{

			/*if(i%2==0) 
				if(z%2==0)
					shake = (z)*-.025;
				else
					shake = (z)*-.03;
			else
				shake = (z)*.025;*/

			wave[z][i] = createMeshView(test,testCoords, faceTest, i*1.33, z, z*3);
																// ^         ^      ^Eventually this will be an array with coordinates based on the escalation (that were procedurally generated)
																//					 Right now they are random 
		}
	}
	return wave;
	
}

// This code is borrowed from the heatmap demo

public static void buildElevation() {
	simpleHillAndValleyWNeighborAvgSmoothing();//neighborAverageSmoothing(); //Modify this method call to try out different strategies
}
public static void simpleHillAndValleyWNeighborAvgSmoothing() {
	for(int x=0; x<width; x++)
		for(int y=0; y<height; y++)
			elevation[x][y] = simpleHillAndValley(x,y);
	for(int x=0; x<width; x+=2)
		for(int y=0; y<height; y+=2)
			elevation[x][y] = getNeighborAvg(x,y);
}

public static int simpleHillAndValley(int x, int y) {
	return (int) ((maxElevation/4*Math.sin((double)x/width*12)+maxElevation/4) + 
	 (maxElevation/4*Math.sin((double)y/height*12)+maxElevation/4));
}

private static int getNeighborAvg(int x, int y) {
	int sum = 0;
	int count = 0;
	int[] points = new int[8];
	points[0] = getNeighborElev(x, y, -1, -1);
	points[1] = getNeighborElev(x, y, -1, 0);
	points[2] = getNeighborElev(x, y, -1, 1);
	points[3] = getNeighborElev(x, y, 0, -1);
	points[4] = getNeighborElev(x, y, 0, 1);
	points[5] = getNeighborElev(x, y, 1, -1);
	points[6] = getNeighborElev(x, y, 1, 0);
	points[7] = getNeighborElev(x, y, 1, 1);
	
	for(int i=0; i<points.length; i++) {
		if(points[i]!=-1) {
			sum+=points[i];
			count++;
		}
	}
	
	return sum/count;
}

private static int getNeighborElev(int x, int y, int deltaX, int deltaY) {
	int x2 = x+deltaX;
	int y2 = y+deltaY;
	if(x2<0 || y2<0 || x2>=width || y2>=height)
		return -1;
	else
		return elevation[x2][y2];
}



	public static void main(String[] args) {
		launch(args);
	}
}
