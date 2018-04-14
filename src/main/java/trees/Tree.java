package trees;

import java.util.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import virtualworld.WorldObject;

public class Tree extends Application implements WorldObject{
	private double h,w,d,px,py,pz,a,s;
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


		h=70;
		w=20;
		d=0;
		px=250;
		py=500;
		pz=0;
		a=0;
		s=0;
	}
	
	public Box makeBranch(double width, double height, double depth, double posX,double posY, double posZ){
		Material mat = new PhongMaterial(Color.GREEN);
		Box b=new Box(width,height,depth);
		b.setTranslateX(posX);
		b.setTranslateY(posY);
		b.setTranslateZ(posZ);
		b.setMaterial(mat);
		return b;
	}


	//This is type A of tree. 
	public void treeA(){

		axiom= new Branch("a",h,w,d,px,py,pz,a,s);
		productionA="aba";
		productionB="ba";

		Random times=new Random();
		int nTimes=(times.nextInt((6 - 3) + 1) + 3)-1;
		loops=(int)((Math.pow(3,nTimes-1)+Math.pow(2,nTimes-1)-1));
		System.out.println("Type A tree generated");
		System.out.println("Tree size: "+nTimes+" levels");
	}


	//This is type B of tree.
	public void treeB(){

		axiom= new Branch("b",h,w,d,px,py,pz,a,s);

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

			parent.setInitialCoordinates(250, 500, 0);
			parent.setInitialSize(20, 70, 0);

			if(parent.getType().equals("a")){

				for(int k=0; k<productionA.length(); k++){


					parent.addChild(Character.toString(productionA.charAt(k)));
					q.add(parent.getChildAt(k));
					
					
//
//					parent.setInitialCoordinates(parent.getPx(), parent.getPy()-parent.getH()/2, 0);
//					box=new Box();
//					box.setHeight(h);
//					box.setWidth(w);
//					box.setLayoutY(parent.getPy()-box.getHeight()/2);
//					box.setLayoutX(parent.getPx());
//					box.setMaterial(mat);
//					h=h*9/10;
//					w=w/3;
					branches.add(makeBranch(parent.getWidth(),parent.getHeight(),parent.getDepth(),parent.getPositionX(),parent.getPositionY(),parent.getPositionZ()));
				}
			}
			else if(parent.getType().equals("b")){

				for(int k=0; k<productionB.length(); k++){

					parent.addChild(Character.toString(productionB.charAt(k)));
					q.add(parent.getChildAt(k));

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
					branches.add(makeBranch(parent.getWidth(),parent.getHeight(),parent.getDepth(),parent.getPositionX(),parent.getPositionY(),parent.getPositionZ()));
				}
			}

			System.out.println("Parent: "+parent.getType());

			for(Branch node : parent.getChildren()) {

				System.out.println(node.getType()+ " ");

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
		for(int i=0; i<branches.size();i++){
			mainGroup.getChildren().add(branches.get(i));
			
		}

//		
//		Box bo=new Box(20,70,10);
//		bo.setTranslateX(250);
//		bo.setTranslateY(500);
//		bo.setMaterial(mat);
//		mainGroup.getChildren().add(bo);
//		System.out.println("Children:"+mainGroup.getChildren());
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args);


	}

	@Override
	public double getX() {
		return this.px;
	}

	@Override
	public double getY() {
		return this.py;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return this.pz;
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