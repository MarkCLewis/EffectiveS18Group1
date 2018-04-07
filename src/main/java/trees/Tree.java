package trees;

import java.util.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import virtualworld.WorldObject;

public class Tree extends Application implements WorldObject{
	private double ix, iy, iz, s;
	private String productionA, productionB;
	private Branch axiom;
	public Queue <Branch>q;
	
	public ArrayList <Branch> nodes=new ArrayList<Branch>();

	private int times, loops;
	
	public Tree(){
		
		ix=0;
		iy=0;
		iz=0;
		s=0;
	}
	
	
	//This is type A of tree. 
	public void treeA(){
		
		axiom= new Branch("a",ix,iy,iz,s);
		productionA="aba";
		productionB="ba";
		
		Random times=new Random();
		loops=(int)(Math.pow(3,(times.nextInt((8 - 3) + 1) + 3)-1)+Math.pow(2,(times.nextInt((8 - 3) + 1) + 3)-1));
		
	}
	
	
	//This is type B of tree.
	public void treeB(){
		
		axiom= new Branch("b",ix,iz,iz,s);
		productionA="ab";
		productionB="ba";
		Random times=new Random();
		
		loops=(int)(Math.pow(2,(times.nextInt((8 - 3) + 1) + 3)-1));
		
	}
	
	public void chooseTree(){
		Random r = new Random();
		
		if(r.nextInt((10 - 1) + 1) + 1<6){
			treeA();
		}
		
		else{
			treeB();
		}
	}
	
    
	public void buildTree(){
		chooseTree();
		q=new LinkedList<>();

		q.add(axiom);
	
	

	for (int i=0; i< loops; i++){
		
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
			
			for(Branch node : parent.getChildren()) {
				
			    System.out.print(node.getType()+ " ");
			    
			}

		}

	}
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
	

		axiom.setInitialCoordinates(250, 500, 3);
		
		Tree tree = new Tree();
		tree.treeA();
		tree.buildTree();
		
		primaryStage.setTitle("Tree");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 500, 500, true);
		

		Cylinder b = new Cylinder();
		Cylinder a = new Cylinder();
		Cylinder c = new Cylinder();


			a.setRadius(3);
			a.setHeight(50);
			a.setTranslateX(axiom.getIx());
			a.setTranslateY(axiom.getIy()-a.getHeight()/2);

			b.setRadius(2);
			b.setHeight(a.getHeight()/2);
			b.setTranslateX(a.getTranslateX()+a.getHeight()/4);
			b.setTranslateY(a.getTranslateY()-a.getHeight());
			b.getTransforms().add(new Rotate(45));

			c.setRadius(2);
			c.setHeight(b.getHeight()/2);
			c.setTranslateX(b.getTranslateX()+b.getHeight()/2);
			c.setTranslateY(b.getTranslateY()-b.getHeight()/4);
			c.getTransforms().add(new Rotate(90));

			mainGroup.getChildren().add(a);
			mainGroup.getChildren().add(b);
			mainGroup.getChildren().add(c);
		

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String args[]) {
	launch(args);
		

	}
	
	@Override
	public double getX() {
		return this.ix;
	}

	@Override
	public double getY() {
		return this.iy;
	}
	
	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return this.iz;
	}

	@Override
	public double getSize() {
		return this.s;
	}

}