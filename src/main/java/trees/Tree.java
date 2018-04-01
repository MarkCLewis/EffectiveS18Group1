package trees;

import java.util.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import javafx.stage.Stage;
import virtualworld.WorldObject;

public class Tree extends Application implements WorldObject{
	private double ix, iy, iz, fx, fy, fz;
	public double x;
	public double y;
	public double size;
	private String productionA, productionB;
	public Queue <Branch>q;
	
	public ArrayList <Branch> nodes=new ArrayList<Branch>();

	private int times;
	
	public Tree(){
		ix=0;
		iy=0;
		iz=0;
		fx=0;
		fy=0;
		fz=0;
	}
    
	public void buildTree(Branch axiom){
		q=new LinkedList<>();

		q.add(axiom);
		
		productionA="aba";
		productionB="ba";

		times = 3;

	for (int i=0; i< Math.pow(productionA.length(),times-1)+Math.pow(productionB.length(),times-1)-1; i++){
		
			Branch parent=q.poll();
			nodes.add(parent);


				if(parent.getType().equals("a")){

					for(int k=0; k<productionA.length(); k++){
						
						parent.addChild(Character.toString(productionA.charAt(k)));
						q.add(parent.getChildAt(k));
					}

				}
				else if(parent.getType().equals("b")){
				

					for(int k=0; k<productionB.length(); k++){
					
						parent.addChild(Character.toString(productionB.charAt(k)));
						q.add(parent.getChildAt(k));

					}

				}
				

			System.out.println("Parent: "+parent.getType());
			//System.out.println("Children: ");
			
			for(Branch node : parent.getChildren()) {
			
			    System.out.print(node.getType()+ " ");
			    System.out.print("ix: "+ node.getix());
			    System.out.print("iy: "+ node.getiy());
			    
			}
System.out.println(nodes.size());
		}

	}
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
		Branch axiom= new Branch();

		axiom.setType("a");
		axiom.setInitialCoordinates(250, 500, 3);
		axiom.seFinalCoordinates(250, 450, 3);
		
		
		Tree tree = new Tree();
		tree.buildTree(axiom);
		
		primaryStage.setTitle("Tree");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 500, 500, true);
		

		Line line = new Line();
		line.setStartX(axiom.getix());
		line.setStartY(axiom.getiy());
		line.setEndX(axiom.getfx());
		line.setEndY(axiom.getfy());
		line.setStrokeWidth(1.0);
		line.setStroke(Color.BLACK);
				
		mainGroup.getChildren().add(line);
		// TODO Your stuff goes here.

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String args[]) {
	launch(args);
		

	}
	
	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public double getSize() {
		return this.size;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

}