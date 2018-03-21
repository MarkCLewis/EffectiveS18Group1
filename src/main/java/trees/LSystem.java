package trees;



import java.util.*;
import java.io.InputStream;



/*
 * This program constructs the basis of an LSystem (represented by an arraylist).
 * It will be used as the structure to create Trees
 *
 * @author: Carlos Pedraza
 *
 */

public class LSystem{


	private String productionA, productionB, axiomLetter;

	public Scanner sc;
	public Queue <TreeNode>q;
	public ArrayList <TreeNode> nodes=new ArrayList<TreeNode>();

	private int times, lvl, id, parentid;




	public void buildTree(){
		q=new LinkedList<>();


		TreeNode<String> axiom= new TreeNode<String>("a");
		q.add(axiom);



		//  System.out.println(axiom.getData());

		String productionA="ab";

		String productionB="ba";


		ArrayList<String> chars = new ArrayList<String>();
		ArrayList<String> levels=new ArrayList<String>();



		//  TreeNode<String> axiomNode = new TreeNode<String>(axiom);
		//  TreeNode<String> child = new TreeNode<String>(null);
		times = 3;


		String level="";
		int stringSize=0;


		levels.add(0,"a");


		//Levels to be created (the root is included as the 1st level)
	for (int i=0; i< Math.pow(productionA.length(),times)-1; i++){
	
			TreeNode<String> parent=q.poll();
			


			//Adds each element on array "letters" to the arraylist "chars".
			//Replaces the element for what its value is
			//Makes a new level by joining the replaced elements
			//Adds the new element to the arraylist "Levels"




				if(parent.getData().equals("a")){


					// chars.set(j,productionA);

					for(int k=0; k<productionA.length(); k++){
						parent.addChild(Character.toString(productionA.charAt(k)));
						q.add(parent.getChildAt(k));

					}



				}
				else if(parent.getData().equals("b")){
				//	chars.set(j,productionB);

					for(int k=0; k<productionB.length(); k++){
						parent.addChild(Character.toString(productionB.charAt(k)));
						q.add(parent.getChildAt(k));

					}

				}
				//        level=level+chars.get(j);

			


			//levels.add(level);
			//System.out.println(levels);
			level="";
			System.out.println("Parent: "+parent.getData());
			//parent.getChildren();
			
			for(TreeNode<String> node : parent.getChildren()) {
				System.out.print("Parent:");
			    System.out.println(node.getData());
			    System.out.println(parent.getChildren());
			    System.out.println("--------");
			    
			}

			System.out.println(q.size());
			chars.clear();


		}







	}

	public static void main(String args[]) {
		//TEST
		LSystem test= new LSystem();
		test.buildTree();





	}
}
