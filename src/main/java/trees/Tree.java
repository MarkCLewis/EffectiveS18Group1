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
		int nTimes=(times.nextInt((4 - 3) + 1) + 3)-1;
		loops=(int)((Math.pow(3,nTimes-1)+Math.pow(2,nTimes-1)-1));
		System.out.println("Type A tree generated");
		System.out.println("Tree size: "+nTimes+" levels");
	}
	
	
	//This is type B of tree.
	public void treeB(){
		
		axiom= new Branch("b",ix,iz,iz,s);
		
		productionA="ab";
		productionB="ba";
		Random times=new Random();
		int nTimes=(times.nextInt((4 - 3) + 1) + 3)-1;
		loops=(int)(Math.pow(2,nTimes)-1);
		System.out.println("Type B tree generated");
		System.out.println("Tree size: "+nTimes+" levels");
		
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
				
			    System.out.println(node.getType()+ " ");
			    
			}

		}

	}
	
	
	
	public void start(Stage primaryStage) throws Exception {
		
	

		
		
		Tree tree = new Tree();
		
		tree.buildTree();
		
		primaryStage.setTitle("Tree");
		Group mainGroup = new Group();
		Scene scene = new Scene(mainGroup, 500, 500, true);
		

		

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