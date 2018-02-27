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

public class LSystem {

    private String productionA, productionB, axiomLetter;

    public Scanner sc;

    private int times;




    public void buildTree(){
        ArrayList<String> chars = new ArrayList<String>();
        ArrayList<String> levels=new ArrayList<String>();

       // sc=new Scanner(System.in);

       // System.out.print("What do you want to axiom with (type a or b)? ");
        String axiom= "a";

        //System.out.print("enter productions for a: ");
        String productionA="aba";

        //System.out.print("enter productions for b: ");
        String productionB="ba";


        times = 4;


        String level="";
        int stringSize=0;

        // TreeNode<String> temp = new TreeNode<String>(null);
       // TreeNode<String> axiomNode = new TreeNode<String>(axiom); //node root
            levels.add(0,"a");
      //  temp=axiomNode;

        //Levels to be created (the root is included as the 1st level)
            for (int i=0; i<times-1; i++){

            	//Splits the most recent level String into characters and places them into Array "letters"
                String[] letters=levels.get(i).split("");
                stringSize=letters.length;

                //Adds each element on array "letters" to the arraylist "chars".
                //Replaces the element for what its value is
                //Makes a new level by joining the replaced elements
                //Adds the new element to the arraylist "Levels"

                for (int j=0; j<stringSize;j++){

                    chars.add(letters[j]);
                  //  temp.addChild(letters[j]);


                    if(chars.get(j).charAt(0)=='a'){
                        chars.set(j,productionA);
                    }
                    if(chars.get(j).charAt(0)=='b'){
                        chars.set(j,productionB);
                    }
                    level=level+chars.get(j);

                }
             
                levels.add(level);
                System.out.println(levels);
                level="";

                chars.clear();

            }




    }

    public static void main(String args[]) {
    	//TEST
        LSystem test= new LSystem();
        test.buildTree();

    }
}
