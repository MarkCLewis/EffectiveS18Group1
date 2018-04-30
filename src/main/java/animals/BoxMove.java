package animals;


import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class boxMove extends Application {

    private static final double SIZE = 300;
    private final Content content = Content.create(SIZE);

    private static final class Content {
        private static final double WIDTH = 3;
        private final Group group = new Group();
        private final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        private final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        private final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
        private final Box xAxis;
        private final Box yAxis;
        private final Box zAxis;
        private final Box box;
        //private final Box box2;

        private static Content create(double size) {
            Content c = new Content(size);
            c.group.getChildren().addAll(c.box, /*c.box2,*/ c.xAxis, c.yAxis, c.zAxis);
            c.group.getTransforms().addAll(c.rz, c.ry, c.rx);
            return c;
        }

        private Content(double size) {
            xAxis = createBox(size * 2, WIDTH, WIDTH);
            yAxis = createBox(WIDTH, size * 2, WIDTH);
            zAxis = createBox(WIDTH, WIDTH, size * 2);
            double edge = 3 * size / 4;
            box = new Box(edge, edge, edge);
            //box2 = new Box(edge, edge, 100);
            box.setMaterial(new PhongMaterial(Color.CORAL));
            box.setTranslateX(size / 2);
            box.setTranslateY(-size / 2);
            box.setTranslateZ(-size / 2);  
   
            Path path = new Path();          
            MoveTo moveTo = new MoveTo(0, 0);         
            //Creating lines to move to
            LineTo line1 = new LineTo(321, 161);      
            LineTo line2 = new LineTo(126, 232); 
            LineTo line3 = new LineTo(232, 52);        
            LineTo line4 = new LineTo(269, 250);        
            LineTo line5 = new LineTo(0, 0);       
            
            //Adding all the elements to the path  
            path.getElements().add(moveTo); 
            path.getElements().addAll(line1, line2, line3, line4, line5);     
            
            //Creating the path transition 
            PathTransition pathTransition = new PathTransition(); 
            
            //Setting the duration of the transition 
            pathTransition.setDuration(Duration.millis(6000));       
            
            //Setting the node for the transition 
            pathTransition.setNode(box); 

            //Setting the path for the transition 
            pathTransition.setPath(path); 
            
            
            //Setting the cycle count for the transition 
            pathTransition.setCycleCount(50); 
            
            //Setting auto reverse value to true 
            pathTransition.setAutoReverse(false); 
            
            //Playing the animation 
            pathTransition.play();	
        		}
        

        private Box createBox(double w, double h, double d) {
            Box b = new Box(w, h, d);
            b.setMaterial(new PhongMaterial(Color.AQUA));
            return b;
            
        }
            //primaryStage.show(b);
        }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFX 3D");
        Scene scene = new Scene(content.group, SIZE * 4, SIZE * 4, true);
        primaryStage.setScene(scene);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setFarClip(SIZE * 6);
        //camera.setTranslateX(SIZE / 2);
        camera.setTranslateY(-SIZE / 2);
        camera.setTranslateZ(-4.5 * SIZE);
        camera.setFieldOfView(100);
        scene.setCamera(camera);
        scene.setOnScroll((final ScrollEvent e) -> {
        camera.setTranslateZ(camera.getTranslateZ() + e.getDeltaY());

        });
        //Camera Movement
		double camSpeed = 10.0;
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
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
