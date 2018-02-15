package agua;
import java.awt.AWTException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//
//			Testing closeup generation of water
//			Likely, this will involve some sort of difficult math to create multiple meshes that are then sewn together to form a body of water
//

@SuppressWarnings("restriction")
public class closeupRendering extends Application {
	
	@Override
	public void start(Stage primaryStage) throws AWTException {
		
		createScene sceneSetup = new createScene(); // Making this a sceneSetup so that later on i can add in the ability to easily adjust the scene settings 
		Scene theScene = sceneSetup.mainScene;
		
		theScene.setFill(Color.ANTIQUEWHITE);
		primaryStage.setScene(theScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		System.out.println("hello");
		launch(args);
	}
}