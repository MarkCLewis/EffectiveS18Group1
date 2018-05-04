package trees;
/**
 * This class generates Tree WorldObjects from 2 types of trees based on some principles of LSystems.
 * 
 * Each Tree is made up of Branch objects
 * Each Tree has a min Size of 3 levels and a max Size of 10 levels.
 */

import java.util.*;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import virtualworld.WorldObject;
import graphicsTesting.CameraController;
import graphicsTesting.DrawFacade;

public class Tree extends Application implements WorldObject {

	public double x, y, z;
	private double w, h, d, a, s, ix, fx,height, depth;
	private String productionA, productionB;
	public Branch axiom;
	public Queue<Branch> q;
	static Group mainGroup = new Group();
	private static boolean tC;

	// for testing camera notification
	public boolean cameraClose = false;

	public ArrayList<Branch> nodes = new ArrayList<Branch>();
	public ArrayList<Box> branches = new ArrayList<Box>();
	public ArrayList<Shape3D> shapes = new ArrayList<Shape3D>();

	private int loops;

	public Tree() {

		s = 0;
	}

	/**
	 * Function to generate random shades of green or brown This will create a
	 * healthy tree or a dry tree
	 */
	public static Color colorAssignment() {

		Color c;

		// Healthy tree
		if (tC) {
			c = healthyTree();
		}

		// Dry tree
		else {
			c = dryTree();
		}

		return c;

	}

	public static Color healthyTree() {
		Random r = new Random();
		int color = r.nextInt(8) + 1;
		Color c;
		if (color == 1) {
			c = Color.rgb(51, 102, 25);
		} else if (color == 2) {
			c = Color.rgb(0, 102, 0);
		} else if (color == 3) {
			c = Color.rgb(0, 102, 51);
		} else if (color == 4) {
			c = Color.rgb(76, 153, 0);
		} else if (color == 5) {
			c = Color.rgb(0, 153, 0);
		} else if (color == 6) {
			c = Color.rgb(0, 153, 76);
		} else if (color == 7) {
			c = Color.rgb(102, 204, 0);
		} else if (color == 8) {
			c = Color.rgb(0, 204, 0);
		} else {
			c = Color.rgb(0, 204, 102);
		}
		return c;
	}

	public static Color dryTree() {
		Random r = new Random();
		int color = r.nextInt(8) + 1;
		Color c;
		if (color == 1) {
			c = Color.rgb(117, 89, 35);
		} else if (color == 2) {
			c = Color.rgb(142, 110, 44);
		} else if (color == 3) {
			c = Color.rgb(119, 94, 44);
		} else if (color == 4) {
			c = Color.rgb(137, 97, 18);
		} else if (color == 5) {
			c = Color.rgb(158, 131, 78);
		} else if (color == 6) {
			c = Color.rgb(168, 123, 33);
		} else if (color == 7) {
			c = Color.rgb(186, 147, 69);
		} else if (color == 8) {
			c = Color.rgb(172, 145, 92);
		} else {
			c = Color.rgb(186, 161, 111);
		}
		return c;
	}

	/**
	 * Visual object that represents a branch of the Tree
	 * 
	 */
	public Box makeAxiom(double width, double height, double depth, double posX, double posY, double posZ,
			double angle) {

		Random r = new Random();
		double an = (double) r.nextInt(30) + 1;

		if (Math.random() < 0.5) {
			an = an * -1;
		}

		Rotate zR = new Rotate(angle, Rotate.Z_AXIS);
		Rotate xR = new Rotate(angle, Rotate.X_AXIS);
		Rotate yR = new Rotate(an, Rotate.Y_AXIS);
	
		PhongMaterial material = new PhongMaterial(Color.rgb(111, 81, 22));

		Box b = DrawFacade.getBoxBuilder(width, height, depth).transCoords(posX, posY, posZ).zRotate(zR).xRotate(xR)
				.yRotate(yR).material(material).build().get();


		return b;
	}

	public Box makeBranch(double width, double height, double depth, double posX, double posY, double posZ,
			double angle) {

		Random r = new Random();
		double an = (double) r.nextInt(30) + 1;

		if (Math.random() < 0.5) {
			an = an * -1;
		}

		Rotate zR = new Rotate(angle, Rotate.Z_AXIS);
		Rotate xR = new Rotate(angle, Rotate.X_AXIS);
		Rotate yR = new Rotate(an, Rotate.Y_AXIS);
	
		PhongMaterial material = new PhongMaterial(colorAssignment());

		Box b = DrawFacade.getBoxBuilder(width, height, depth).transCoords(posX, posY, posZ).zRotate(zR).xRotate(xR)
				.yRotate(yR).material(material).build().get();
	

		return b;
	}

	private double nextInt(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Type A of Tree axiom is the First branch to be created.
	 */
	public Color treeA() {

		axiom = new Branch("a", 1, 5, 1, 0, 0, 350, 0, s);
		// axiom = new Branch("b", 7.5, 40, 7.5, xzCoordinate(), 0,
		// xzCoordinate(), 0, s);
		x = axiom.getPositionX();
		y = axiom.getPositionY();
		z = axiom.getPositionZ();

		productionA = "aba";
		productionB = "ba";

		Random times = new Random();

		int nTimes = (times.nextInt((6 - 3) + 1) + 3) - 1;
		loops = (int) ((Math.pow(3, nTimes - 1) + Math.pow(2, nTimes - 1) - 1));
		System.out.println("Type A tree generated");
		System.out.println("Tree size: " + nTimes + " levels");

		return healthyTree();
	}

	/**
	 * Type B of Tree axiom is the First branch to be created.
	 */
	public Color treeB() {

		axiom = new Branch("b", 1, 5, 1, 0, 0, 350, 0, s);

		// axiom = new Branch("b", 7.5, 40, 7.5, xzCoordinate(), 0,
		// xzCoordinate(), 0, s);
		x = axiom.getPositionX();
		y = axiom.getPositionY();
		z = axiom.getPositionZ();

		productionA = "ba";
		productionB = "ab";
		Random times = new Random();

		int nTimes = (times.nextInt((6 - 3) + 1) + 3) - 1;
		loops = (int) (Math.pow(2, nTimes) - 1);
		System.out.println("Type B tree generated");
		System.out.println("Tree size: " + nTimes + " levels");

		return dryTree();
	}

	/**
	 * Function that randomizes which Tree is going to be created Calls whatever
	 * either treeA or treeB based on the randomization
	 */
	public boolean chooseTree() {

		if (Math.random() < .5) {
			treeA();
			return true;
		} else {
			treeB();
			return false;
		}
	}

	/**
	 * Randomly assign x,z coordinates to and how many levels the tree will
	 * have.
	 */
	public static double xzCoordinate() {

		int x = -4000;
		int y = 4000;
		return Math.floor(Math.random() * ((y - x) + 1) + x);
	}

	/**
	 * Function that builds the tree based on the axiom given and how many
	 * levels the tree will have.
	 */
	public void buildTree() {
		if (Math.random() < .5) {
			tC = true;

		} else {
			tC = false;
		}
		Branch branch;
		chooseTree();
		q = new LinkedList<>();

		q.add(axiom);

		for (int i = 0; i < loops; i++) {

			branch = q.poll();

			nodes.add(branch);

			if (i == 0) {
		

				branches.add(makeAxiom(branch.getWidth(), branch.getHeight(), branch.getDepth(), branch.getPositionX(),
						branch.getPositionY(), branch.getPositionZ(), branch.getAngle()));
			}

			if (branch.getType().equals("a")) {

				for (int k = 0; k < productionA.length(); k++) {

					branch.addChild(Character.toString(productionA.charAt(k)));
					q.add(branch.getChildAt(k));

				}
			} else if (branch.getType().equals("b")) {

				for (int k = 0; k < productionB.length(); k++) {

					branch.addChild(Character.toString(productionB.charAt(k)));
					q.add(branch.getChildAt(k));

				}
			}

			int index = 0;

			/**
			 * Each new branch's characteristics are based on its parent branch
			 * Width, Height and Depth are 75% big compared to the parent branch
			 * Each branch has a different rotation compared to its parent
			 */
			for (Branch node : branch.getChildren()) {

				if (branch.getType() == "a") {
					if (index == 0) {

						branch.getChildAt(0).setAngle(branch.getAngle() - 45);

						w = branch.getWidth() * .75;
						h = branch.getHeight() * .75;
						d = branch.getDepth() * .75;
						branch.getChildAt(0).setInitialSize(w, h, d);
						a = branch.getChildAt(0).getAngle();
						x = branch.getPositionX() - (h) * (Math.cos(Math.toRadians(a))) / 2;
						y = ((branch.getPositionY() - branch.getHeight() / 2))
								+ (h * (Math.sin(Math.toRadians(a))) / 2);
						z = branch.getPositionZ();
						
						branch.getChildAt(0).setInitialCoordinates(x, y, z);

						branches.add(makeBranch(w, h, d, x, y, z, a));

					}

					if (index == 1) {

						branch.getChildAt(1).setAngle(branch.getAngle());
						w = branch.getWidth() * .75;
						h = branch.getHeight() * .50;
						d = branch.getDepth() * .75;
						branch.getChildAt(1).setInitialSize(w, h, d);
						a = branch.getChildAt(1).getAngle();
						x = branch.getPositionX();
						// 460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
						// System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y = (branch.getPositionY() - branch.getHeight() / 2 - h / 2);
						z = branch.getPositionZ();
						// mainGroup.getChildren().add(makeBranch(w, h, d, x, y,
						// 0, a));
						branch.getChildAt(1).setInitialCoordinates(x, y, z);
						branches.add(makeBranch(w, h, d, x, y, z, a));

					}

					if (index == 2) {

						branch.getChildAt(2).setAngle(branch.getAngle() + 45);
						w = branch.getWidth() * .75;
						h = branch.getHeight() * .75;
						d = branch.getDepth() * .75;
						branch.getChildAt(2).setInitialSize(w, h, d);
						a = branch.getChildAt(2).getAngle();
						x = branch.getPositionX() + (h) * (Math.cos(Math.toRadians(a))) / 2;
						// 460-40+ ((80*.75)*(Math.sin(Math.toRadians(-45)))/2)
						// System.out.println(((branch.getParent().getPositionY()-branch.getParent().getHeight()/2))-(h*(Math.sin(Math.toRadians(a)))/2));
						y = ((branch.getPositionY() - branch.getHeight() / 2))
								- (h * (Math.sin(Math.toRadians(a))) / 2);
						z = branch.getPositionZ();
						// mainGroup.getChildren().add(makeBranch(w, h, d, x, y,
						// 0, a));
						branch.getChildAt(2).setInitialCoordinates(x, y, z);

						branches.add(makeBranch(w, h, d, x, y, z, a));

					}
				} else {
					if (index == 0) {

						branch.getChildAt(0).setAngle(branch.getAngle() - 45);

						w = branch.getWidth() * .75;
						h = branch.getHeight() * .75;
						d = branch.getDepth() * .75;
						branch.getChildAt(0).setInitialSize(w, h, d);
						a = branch.getChildAt(0).getAngle();
						x = branch.getPositionX() - (h) * (Math.cos(Math.toRadians(a))) / 2;
						y = ((branch.getPositionY() - branch.getHeight() / 3))
								+ (h * (Math.sin(Math.toRadians(a))) / 2);
						z = branch.getPositionZ();

						branch.getChildAt(0).setInitialCoordinates(x, y, z);
						branches.add(makeBranch(w, h, d, x, y, z, a));
					}

					if (index == 1) {
						branch.getChildAt(1).setAngle(branch.getAngle() + 45);
						w = branch.getWidth() * .75;
						h = branch.getHeight() * .75;
						d = branch.getDepth() * .75;
						branch.getChildAt(1).setInitialSize(w, h, d);
						a = branch.getChildAt(1).getAngle();
						x = branch.getPositionX() + (h) * (Math.cos(Math.toRadians(a))) / 2;

						y = ((branch.getPositionY() - branch.getHeight() / 3))
								- (h * (Math.sin(Math.toRadians(a))) / 2);

						z = branch.getPositionZ();
						branch.getChildAt(1).setInitialCoordinates(x, y, z);
						branches.add(makeBranch(w, h, d, x, y, z, a));
					}
				}

				index++;
			}

		}
		height=0;
		for (int j = 0; j < loops; j++) {

			shapes.add(branches.get(j));
			
			mainGroup.getChildren().add(shapes.get(j));
			if(height>shapes.get(j).getTranslateY()){
				height=shapes.get(j).getTranslateY();
			}
			
		}
		
		height=Math.abs(height)+axiom.getHeight()/2;
		
		fx = 160 * .75 * (Math.sin(Math.toRadians(45))) / 2;
		ix = 160 * .75 * (Math.sin(Math.toRadians(-45))) / 2;
	}

	public void start(Stage primaryStage) throws Exception {

		Tree tree = new Tree();
		primaryStage.setTitle("Tree");
		Scene scene = new Scene(mainGroup, 1280, 720, true);

		// tree.buildTree();
		tree.display();
		Camera camera = new PerspectiveCamera(true);
		scene.setCamera(camera);
		Group cameraGroup = new Group();
		CameraController pCam = new CameraController.Builder(camera).build();

		mainGroup.getChildren().add(cameraGroup);

		Set<KeyCode> keySet = new HashSet<KeyCode>();
		scene.setOnKeyPressed(event -> {
			KeyCode key = event.getCode();
			keySet.add(key);

			if (keySet.contains(KeyCode.W)) {
				pCam.moveForward();
			}
			if (keySet.contains(KeyCode.S)) {
				pCam.moveBackward();
			}
			if (keySet.contains(KeyCode.A)) {
				pCam.moveLeft();
			}
			if (keySet.contains(KeyCode.D)) {
				pCam.moveRight();
			}
			if (keySet.contains(KeyCode.RIGHT)) {
				pCam.rotateRight();
			}
			if (keySet.contains(KeyCode.LEFT)) {
				pCam.rotateLeft();
			}
			if (keySet.contains(KeyCode.UP)) {
				pCam.rotateUp();
			}
			if (keySet.contains(KeyCode.DOWN)) {
				pCam.rotateDown();
			}
			if (keySet.contains(KeyCode.R)) {
				pCam.moveUp();
			}
			if (keySet.contains(KeyCode.F)) {
				pCam.moveDown();
			}
			if (keySet.contains(KeyCode.SHIFT)) {
				pCam.boostOn();
			}
		});

		scene.setOnKeyReleased(event -> {
			KeyCode key = event.getCode();
			if (key == KeyCode.SHIFT) {
				pCam.boostOff();
			}
			keySet.remove(key);

		});

		primaryStage.setScene(scene);
		primaryStage.show();

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
		fx = 160 * .75 * (Math.sin(Math.toRadians(45))) / 2;
		ix = 160 * .75 * (Math.sin(Math.toRadians(-45))) / 2;
		s = (fx - ix)/2;
		return s;
	}

	@Override
	public boolean notifyOfCamera(double x, double z) {
		if ((Math.abs(this.getX() - x) < 10) || (Math.abs(this.getZ() - z) < 10)) {
			cameraClose = true;
		}
		return true;
	}

	@Override
	public double getXLoc() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getYLoc() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double getZLoc() {
		// TODO Auto-generated method stub
		return z;
	}

	@Override
	public ArrayList<Shape3D> display() {
		buildTree();
		return shapes;
	}
}