package trees;



import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.io.InputStream;



/*
 * This program constructs the basis of an LSystem (represented by an arraylist).
 * It will be used as the structure to create Trees
 *
 * @author: Carlos Pedraza
 *
 */

public class LSystem extends JPanel{


	private String productionA, productionB, axiomLetter;
	public int x=250;
	public int y= 450;
	
	public Queue <TreeNode>q;
	public ArrayList <TreeNode> nodes=new ArrayList<TreeNode>();

	private int times;




	public void buildTree(){
		q=new LinkedList<>();


		TreeNode<String> axiom= new TreeNode<String>("a");
		q.add(axiom);



		String productionA="aba";

		String productionB="ba";


		times = 3;


		
	for (int i=0; i< Math.pow(productionA.length(),times-1)+Math.pow(productionB.length(),times-1)-1; i++){
		//for (int i=0; i<4; i++){
			TreeNode<String> parent=q.poll();
			nodes.add(parent);

		


				if(parent.getData().equals("a")){


					// chars.set(j,productionA);

					for(int k=0; k<productionA.length(); k++){
						parent.addChild(Character.toString(productionA.charAt(k)));
						q.add(parent.getChildAt(k));
						

					}

				}
				else if(parent.getData().equals("b")){
				

					for(int k=0; k<productionB.length(); k++){
						parent.addChild(Character.toString(productionB.charAt(k)));
						q.add(parent.getChildAt(k));

					}

				}
				

			System.out.println("Parent: "+parent.getData());
			//System.out.println("Children: ");
			
			for(TreeNode<String> node : parent.getChildren()) {
			
			    System.out.println(node.getData());
  
			    
			}
System.out.println(nodes.size());
		}

	}
	/*
	 public void paintComponent(Graphics g) {
		 
	     //vertical line
		
		 g.drawString(""+nodes.get(0).getData(), x, y);
		 
		 for (int i=0; i<nodes.size();i++){
			 
			 g.drawLine(x, y, x-10, y-10);
			 x-=10;
			 y-=10;
		 }
	     g.setColor(Color.red);
	     g.drawLine(20, 20, 20, 120);
	     
	 
	     //horizontal line
	     g.setColor(Color.green);
	     g.drawLine(20, 20, 120, 20);
	 
	     //diagonal line 
	     g.setColor(Color.blue);
	     g.drawLine(20, 20, 120, 120);
	 
	  }*/

	public static void main(String args[]) {
		//TEST
		LSystem test= new LSystem();
		test.buildTree();
		
		




	}
}
