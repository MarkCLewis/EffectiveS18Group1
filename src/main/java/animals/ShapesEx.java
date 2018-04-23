package animals;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Sphere;
//import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration; 

public class ShapesEx extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Create a Box
/*      Box box = new Box(100, 100, 100);
        box.setTranslateX(150);
        box.setTranslateY(0);
        box.setTranslateZ(400);*/

        // Create a Sphere
        Sphere sphere = new Sphere(50);
        sphere.setTranslateX(300);
        sphere.setTranslateY(-5);
        sphere.setTranslateZ(400);
        
        // Create a Cylinder
/*        Cylinder cylinder = new Cylinder(40, 120);
        cylinder.setTranslateX(500);
        cylinder.setTranslateY(-25);
        cylinder.setTranslateZ(600);*/
        
        // Create a color Light
        PointLight blueLight = new PointLight();
        blueLight.setColor(Color.BLUE);
        blueLight.setTranslateX(250);
        blueLight.setTranslateY(-100);
        blueLight.setTranslateZ(250);

        // Create a Camera to view the 3D Shapes
        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(20);
        
        Path path = new Path(); 
        
        //Moving to the starting point 
        MoveTo moveTo = new MoveTo(150, 100);               
        
        //Creating lines to move to
        LineTo line1 = new LineTo(321, 161);        
        LineTo line2 = new LineTo(126, 232); 
        LineTo line3 = new LineTo(232, 52);        
        LineTo line4 = new LineTo(269, 250);        
        LineTo line5 = new LineTo(150, 100);       
        
        //Adding all the elements to the path  
        path.getElements().add(moveTo); 
        path.getElements().addAll(line1, line2, line3, line4, line5);     
        
        //Creating the path transition 
        PathTransition pathTransition = new PathTransition(); 
        
        //Setting the duration of the transition 
        pathTransition.setDuration(Duration.millis(6000));       
        
        //Setting the node for the transition 
        pathTransition.setNode(sphere); 

        //Setting the path for the transition 
        pathTransition.setPath(path); 
        
        
        //Setting the cycle count for the transition 
        pathTransition.setCycleCount(50); 
        
        //Setting auto reverse value to true 
        pathTransition.setAutoReverse(false); 
        
        //Playing the animation 
        pathTransition.play();
 
        // Add the Shapes and the Light to the Group
        Group root = new Group(/*box,*/ sphere, /*cylinder,*/ blueLight);

        // Create a Scene with depth buffer enabled
        Scene scene = new Scene(root, 600, 600, true);
        
        // Add the Camera to the Scene
        scene.setCamera(camera);
        
        // Add the Scene to the Stage
        stage.setScene(scene);
        stage.setTitle("Trying to get some movement in some shapes");
        stage.show();      
        
		//Camera Movement
		double camSpeed = 3.0;
		scene.setOnKeyPressed(event ->{
			KeyCode key = event.getCode();
			 if(key == KeyCode.Q) {camera.setTranslateZ(camera.getTranslateZ() + camSpeed);}
			 if(key == KeyCode.E) {camera.setTranslateZ(camera.getTranslateZ() - camSpeed);}
			 if(key == KeyCode.W) {camera.setTranslateY(camera.getTranslateY() + camSpeed);}
			 if(key == KeyCode.S) {camera.setTranslateY(camera.getTranslateY() - camSpeed);}
			 if(key == KeyCode.A) {camera.setTranslateX(camera.getTranslateX() + camSpeed);}
			 if(key == KeyCode.D) {camera.setTranslateX(camera.getTranslateX() - camSpeed);}
			 if(key == KeyCode.C) {camera.setRotate(camera.getRotate() + camSpeed);}
			 if(key == KeyCode.Z) {camera.setRotate(camera.getRotate() - camSpeed);}
		});
		
		
    }

}
