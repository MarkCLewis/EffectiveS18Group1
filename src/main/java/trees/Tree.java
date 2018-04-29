package trees;
/**
 * This class generates Tree WorldObjects from 2 types of trees based on some principles of LSystems.
 * 
 * Each Tree is made up of Branch objects
 * Each Tree has a min Size of 3 levels and a max Size of 10 levels.
 */

import java.util.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import virtualworld.WorldObject;

public class Tree extends Application implements WorldObject{
	public double x, y, z;
	private double w,h,d,a,s;
	private String productionA, productionB;
	public Branch axiom;
	public Queue <Branch>q;
	static Group mainGroup = new Group();

	//for testing camera notification
	public boolean cameraClose = false;
	
	public ArrayList <Branch> nodes=new ArrayList<Branch>();
	public ArrayList <Box> branches= new ArrayList<Box>();

	private int loops;

	public Tree(){


		s=0;
	}
	
	/**
	 * Function to generate random shades of green
	 * The possible colors are OLIVE, DARKOLIVEGREEN, GREEN, FORESTGREEN, OLIVEDRAB
	 */
	public static Color colorAssignment() {
		Random r = new Random();
		int color = r.nextInt(5)+1;
		Color c;
		//Color item = null;
		
		if(color == 1){
			c=Color.OLIVE;
		}
		else if(color == 2){
			c=Color.DARKOLIVEGREEN;
		}
		else if(color == 3){
			c=Color.GREEN;
		}
		else if(color == 4){
			c=Color.FORESTGREEN;
		}
		
		
		else{
			c=Color.OLIVEDRAB;
		}
		return c;
		
	}
	/**
	 * Visual object that represents a branch of the Tree
	 * 
	 */
	public Box makeBranch(double width, double height, double depth, double posX,double posY, double posZ, double angle){
		Material mat = new PhongMaterial(colorAssignment());
		Box b=new Box(width,height,depth);
		b.setTranslateX(posX);
		b.setTranslateY(posY);
		b.setTranslateZ(posZ);
		b.setMaterial(mat);
		b.setRotationAxis(Rotate.Z_AXIS);
		b.setRotate(angle);
		return b;
	}


	
	/**
	 * Type A of Tree
	 * axiom is the First branch to be created.
	 */
	public void treeA(){
		
		axiom= new Branch("a",15,80,15,250,460,0,0,s);
		x=axiom.getPositionX();
		y=axiom.getPositionY();
		z=axiom.getPositionZ();

		productionA="aba";
		productionB="ba";

		Random times=new Random();
		
		int nTimes=(times.nextInt((10 - 3) + 1) + 3)-1;
		loops=(int)((Math.pow(3,nTimes-1)+Math.pow(2,nTimes-1)-1));
		System.out.println("Type A tree generated");
		System.out.println("Tree size: "+nTimes+" levels");

	}


	/**
	 * Type B of Tree
	 * axiom is the First branch to be created.
	 */
	public void treeB(){


		axiom= new Branch("b",15,80,15,250,460,0,0,s);
		x=axiom.getPositionX();
		y=axiom.getPositionY();
		z=axiom.getPositionZ();

		productionA="ba";
		productionB="bb";
		Random times=new Random();
		//int nTimes=2;
		int nTimes=(times.nextInt((10 - 3) + 1) + 3)-1;
		loops=(int)(Math.pow(2,nTimes)-1);
		System.out.println("Type B tree generated");
		System.out.println("Tree size: "+nTimes+" levels");

		

	}
	
	/**
	 * Function that randomizes which Tree is going to be created
	 * Calls whatever either treeA or treeB based on the randomization
	 */
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
		Branch branch;
		chooseTree();
		q=new LinkedList<>();

		q.add(axiom);


		for (int i=0; i< loops; i++){
			
			branch=q.poll();
			
			nodes.add(branch);

			
			if(i==0){
			//	System.out.println("H: "+branch.getHeight());
			
				//mainGroup.getChildren().add(makeBranch(branch.getWidth(), branch.getHeight(), branch.getDepth(), branch.getPositionX(), branch.getPositionY(), branch.getPositionZ(),branch.getAngle()));
				branches.add(makeBranch(branch.getWidth(), branch.getHeight(), branch.getDepth(), branch.getPositionX(), branch.getPositionY(), branch.getPositionZ(),branch.getAngle()));
			}
			
			if(branch.getType().equals("a")){

				for(int k=0; k<productionA.length(); k++){


					branch.addChild(Character.toString(productionA.charAt(k)));
					q.add(branch.getChildAt(k));
				
					}
			}
			else if(branch.getType().equals("b")){

				for(int k=0; k<productionB.length(); k++){

					branch.addChild(Character.toString(productionB.charAt(k)));
					q.add(branch.getChildAt(k));

					}
			}

			//System.out.println("Parent: "+branch.getType());
			//branches.add(makeBranch(branch.getWidth(), branch.getHeight(), branch.getDepth(), branch.getPositionX(), branch.getPositionY(),branch.getPositionZ(), branch.getAngle()));
			int index=0;
			
			/**
			 * Each new branch's characteristics are based on its parent branch
			 * Width, Height and Depth are 75% big compared to the parent branch
			 * Each branch has a different rotation compared to its parent
			 */
			for(Branch node : branch.getChildren()) {

			//	System.out.println(node.getType()+ " ");
				
				if(branch.getType()=="a"){
					if(index==0){
						
						branch.getChildAt(index).setAngle(branch.getAngle()-45);
			
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						branch.getChildAt(0).setInitialSize(w, h, d);
						a=branch.getChildAt(index).getAngle();
						x=branch.getPositionX()-(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2);
					//	mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
						branch.getChildAt(0).setInitialCoordinates(x, y, 0);
						branches.add(makeBranch(w, h, d, x, y, 0, a));	
							
					}
					
					if(index==1){
					
						branch.getChildAt(1).setAngle(branch.getAngle());
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						branch.getChildAt(1).setInitialSize(w, h, d);
						a=branch.getChildAt(1).getAngle();
						x=branch.getPositionX();
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y=(branch.getPositionY()-branch.getHeight()/2-h/2);
					//	mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
						branch.getChildAt(1).setInitialCoordinates(x, y, 0);
						branches.add(makeBranch(w, h, d, x, y, 0, a));	
							
					}
					
					
					
					
					if(index==2){
			
						branch.getChildAt(2).setAngle(branch.getAngle()+45);
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						branch.getChildAt(2).setInitialSize(w, h, d);
						a=branch.getChildAt(2).getAngle();
						x=branch.getPositionX()+(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2);
						//mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
						branch.getChildAt(2).setInitialCoordinates(x, y, 0);
						branches.add(makeBranch(w, h, d, x, y, 0, a));	
							
					}
				}
				else{
					if(index==0){
						
						branch.getChildAt(0).setAngle(branch.getAngle()-45);
			
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						branch.getChildAt(0).setInitialSize(w, h, d);
						a=branch.getChildAt(0).getAngle();
						x=branch.getPositionX()-(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2);
					//	mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
						branch.getChildAt(0).setInitialCoordinates(x, y, 0);
						branches.add(makeBranch(w, h, d, x, y, 0, a));	
							
					}
					
					if(index==1){
						branch.getChildAt(1).setAngle(branch.getAngle()+45);
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						branch.getChildAt(1).setInitialSize(w, h, d);
						a=branch.getChildAt(1).getAngle();
						x=branch.getPositionX()+(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2);
						//mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
						branch.getChildAt(1).setInitialCoordinates(x, y, 0);
						branches.add(makeBranch(w, h, d, x, y, 0, a));	
					}
				}
				
				
			index++;
			}
			
			
		}
		
		System.out.println("LOOPS: "+loops);
		for (int j=0; j<loops;j++){
			mainGroup.getChildren().add(branches.get(j));
		}
		System.out.println("x:"+branches.get(1).getTranslateX());
		System.out.println("y:"+branches.get(1).getTranslateY());
		System.out.println("z:"+branches.get(1).getTranslateZ());
		System.out.println("Width:"+branches.get(1).getWidth());
		System.out.println("Height:"+branches.get(1).getHeight());
		System.out.println("Depth:"+branches.get(1).getDepth());
	//	mainGroup.getChildren().add(makeBranch(branches.get(1).getWidth()*.75, branches.get(1).getHeight()*.75, branches.get(1).getDepth()*.75, branches.get(1).getTranslateX()-(branches.get(1).getHeight()*.75*(Math.cos(-90))), 400, 0,-90));

	}


	public void start(Stage primaryStage) throws Exception {
		

		Tree tree = new Tree();
		
		primaryStage.setTitle("Tree");
		Scene scene = new Scene(mainGroup, 500, 500, true);
		primaryStage.setScene(scene);

		tree.buildTree();
		
		//makeBranch(double width, double height, double depth, double posX,double posY, double posZ)
		
//		mainGroup.getChildren().add(tree.makeBranch(15, 80, 15, 250, 460, 0,0));
		
			
			//left									    w   h   d    x    y   a
//			mainGroup.getChildren().add(tree.makeBranch(15*.75, 80*.75, 15*.75, (250-(80*.75)*(Math.cos(Math.toRadians(-45)))/2), 460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2), 0,-45));
			
				
//				mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 230, 380, 0,0));
////			//center	
////			mainGroup.getChildren().add(tree.makeBranch(10, 60, 10, 250, 400, 0,0));
////			//	mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 233, 370, 0,-45));
////			//	mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 250, 350, 0,0));
////			
////			//right
//			mainGroup.getChildren().add(tree.makeBranch(10, 60, 10, 270, 400, 0,45));
//	
		
		primaryStage.show();
//		System.out.println("POS: "+(250-(80*.75)*(Math.cos(Math.toRadians(-45)))/2));
	}

	public static void main(String args[]) {
		launch(args);
		

	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public double getSize() {
		return this.s;
	}


	@Override
	public void notifyOfCamera(double x, double z) {
		if ((Math.abs(this.getX() - x) < 10) || (Math.abs(this.getZ() - z) < 10)) {
			cameraClose = true;
		}
	}

	@Override
	public double getXLoc() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYLoc() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZLoc() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	public ArrayList<Shape3D> display() {
		// TODO Auto-generated method stub
		return null;
	}

}