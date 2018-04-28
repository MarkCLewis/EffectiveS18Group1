package trees;

import java.util.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import virtualworld.WorldObject;

public class Tree extends Application implements WorldObject{
	private double w,h,d,x,y,z,a,s;
	private String productionA, productionB;
	private Branch axiom;
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
	
	public Box makeBranch(double width, double height, double depth, double posX,double posY, double posZ, double angle){
		Material mat = new PhongMaterial(Color.GREEN);
		Box b=new Box(width,height,depth);
		b.setTranslateX(posX);
		b.setTranslateY(posY);
		b.setTranslateZ(posZ);
		b.setMaterial(mat);
		b.setRotationAxis(Rotate.Z_AXIS);
		b.setRotate(angle);
		return b;
	}


	//This is type A of tree. 
	public void treeA(){
		
		


		axiom= new Branch("a",15,80,15,250,460,0,0,s);
		productionA="aba";
		productionB="ba";

		Random times=new Random();
		int nTimes=3;
		//int nTimes=(times.nextInt((6 - 3) + 1) + 3)-1;
		loops=(int)((Math.pow(3,nTimes-1)+Math.pow(2,nTimes-1)-1));
		System.out.println("Type A tree generated");
		System.out.println("Tree size: "+nTimes+" levels");

	}


	//This is type B of tree.
	public void treeB(){


		axiom= new Branch("b",15,80,15,250,460,0,0,s);

		productionA="ba";
		productionB="bb";
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
		Branch branch;
		chooseTree();
		q=new LinkedList<>();

		q.add(axiom);
	//	System.out.println("W: "+axiom.getWidth());
	//	System.out.println("H: "+axiom.getHeight());
	//	System.out.println("D: "+axiom.getDepth());
	//	System.out.println(q.poll().getHeight());

		
		for (int i=0; i< loops; i++){
			System.out.println(i);
			branch=q.poll();
			
			nodes.add(branch);
			//System.out.println("W: "+branch.getWidth());
			//System.out.println("H: "+branch.getHeight());
			//System.out.println("D: "+branch.getDepth());
			
			if(i==0){
				System.out.println("H: "+branch.getHeight());
				//mainGroup.getChildren().add(makeBranch(branch.getWidth(), 80, branch.getDepth(), branch.getPositionX(), branch.getPositionY(), branch.getPositionZ(),0));
				mainGroup.getChildren().add(makeBranch(branch.getWidth(), branch.getHeight(), branch.getDepth(), branch.getPositionX(), branch.getPositionY(), branch.getPositionZ(),branch.getAngle()));

			}
			
			if(branch.getType().equals("a")){

				for(int k=0; k<productionA.length(); k++){


					branch.addChild(Character.toString(productionA.charAt(k)));
					q.add(branch.getChildAt(k));
					
//
//					
					}
			}
			else if(branch.getType().equals("b")){

				for(int k=0; k<productionB.length(); k++){

					branch.addChild(Character.toString(productionB.charAt(k)));
					q.add(branch.getChildAt(k));

//					parent.setInitialCoordinates(parent.getPx(), parent.getParent().getPy()-parent.getParent().getH()/2, 0);
//					box=new Box();
//					box.setHeight(h);
//					box.setWidth(w);
//					box.setLayoutY(parent.getPy()-box.getHeight()/2);
//					box.setLayoutX(parent.getPx());
//					box.setMaterial(mat);
//					h=h*9/10;
//					w=w/3;
//					mainGroup.getChildren().add(box);
					//branches.add(makeBranch(parent.getWidth(),parent.getHeight(),parent.getDepth(),parent.getPositionX(),parent.getPositionY(),parent.getPositionZ()));
				}
			}

			System.out.println("Parent: "+branch.getType());
			int index=0;
			for(Branch node : branch.getChildren()) {

				System.out.println(node.getType()+ " ");
				
				if(branch.getType()=="a"){
					if(index==0){
						
						branch.getChildAt(index).setAngle(branch.getAngle()-45);
			
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						a=branch.getChildAt(index).getAngle();
						x=branch.getPositionX()-(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2);
						mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
							
					}
					
					if(index==1){
					
						branch.getChildAt(index).setAngle(branch.getAngle());
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						a=branch.getChildAt(index).getAngle();
						x=branch.getPositionX();
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y=(branch.getPositionY()-branch.getHeight()/2-h/2);
						mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
							
					}
					
					
					
					
					if(index==2){
			
						branch.getChildAt(index).setAngle(branch.getAngle()+45);
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						a=branch.getChildAt(index).getAngle();
						x=branch.getPositionX()+(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2);
						mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
							
					}
				}
				else{
if(index==0){
						
						branch.getChildAt(index).setAngle(branch.getAngle()-45);
			
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						a=branch.getChildAt(index).getAngle();
						x=branch.getPositionX()-(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))+(h*(Math.sin(Math.toRadians(a)))/2);
						mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
							
					}
					
					if(index==1){
						branch.getChildAt(index).setAngle(branch.getAngle()+45);
						w=branch.getWidth()*.75;
						h=branch.getHeight()*.75;
						d=branch.getDepth()*.75;
						a=branch.getChildAt(index).getAngle();
						x=branch.getPositionX()+(h)*(Math.cos(Math.toRadians(a)))/2;
						//460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
					//	System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y=((branch.getPositionY()-branch.getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2);
						mainGroup.getChildren().add(makeBranch(w, h, d, x, y, 0, a));
							
					}
				}
				
				
			index++;
			}
			


		}

	}


	public void start(Stage primaryStage) throws Exception {
		Material mat = new PhongMaterial(Color.GREEN);

		Tree tree = new Tree();
		
		primaryStage.setTitle("Tree");
		Scene scene = new Scene(mainGroup, 500, 500, true);
		primaryStage.setScene(scene);

		tree.buildTree();
		
		//makeBranch(double width, double height, double depth, double posX,double posY, double posZ)
		
//		mainGroup.getChildren().add(tree.makeBranch(15, 80, 15, 250, 460, 0,0));
		
			
			//left									    w   h   d    x    y   a
//			mainGroup.getChildren().add(tree.makeBranch(15*.75, 80*.75, 15*.75, (250-(80*.75)*(Math.cos(Math.toRadians(-45)))/2), 460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2), 0,-45));
////			
////				mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 210, 400, 0,-90));
////				mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 230, 380, 0,0));
////			//center	
////			mainGroup.getChildren().add(tree.makeBranch(10, 60, 10, 250, 400, 0,0));
////			//	mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 233, 370, 0,-45));
////			//	mainGroup.getChildren().add(tree.makeBranch(7, 45, 10, 250, 350, 0,0));
////			
////			//right
//			mainGroup.getChildren().add(tree.makeBranch(10, 60, 10, 270, 400, 0,45));
//	
		for(int i=0; i<branches.size();i++){
			mainGroup.getChildren().add(branches.get(i));
			
		}

//		Box bo=new Box(20,70,10);
//		bo.setTranslateX(250);
//		bo.setTranslateY(500);
//		bo.setMaterial(mat);
//		mainGroup.getChildren().add(bo);
//		System.out.println("Children:"+mainGroup.getChildren());
		primaryStage.show();
		System.out.println("POS: "+(250-(80*.75)*(Math.cos(Math.toRadians(-45)))/2));
	}

	public static void main(String args[]) {
		launch(args);
		

	}

	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double getY() {
		return 0;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
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

}