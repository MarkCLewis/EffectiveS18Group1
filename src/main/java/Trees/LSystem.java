package Trees;

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
    
    private String valueA, valueB, startLetter;
    
    public Scanner sc;
    
    private int times;
    
    
    
    public void buildTree(){
        ArrayList<String> chars = new ArrayList<String>();
        ArrayList<String> levels=new ArrayList<String>();
        
        sc=new Scanner(System.in);
        
        System.out.print("What do you want to start with (type a or b)? ");
        String start= sc.nextLine();
        
        System.out.print("enter productions for a: ");
        String valueA=sc.nextLine();
        
        System.out.print("enter productions for b: ");
        String valueB=sc.nextLine();
       
        System.out.print("enter number of levels: ");
        times = Integer.parseInt(sc.nextLine());
       
        
        int counter=0;
        String level="";
        int stringSize=0;

     
    if(start.equals("a")){
            levels.add(0,"a");
            
          //Levels to be created (the root is icluded as the 1st level)
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

                    if(chars.get(j).charAt(0)=='a'){
                        chars.set(j,valueA);
                    }
                    if(chars.get(j).charAt(0)=='b'){
                        chars.set(j,valueB);
                    }
                    level=level+chars.get(j);

                }
                
                levels.add(level);
                System.out.println(levels);
                level="";
                
                chars.clear();
 
            }
    }
        
        if(start.equals("b")){
            levels.add(0,"b");
            for (int i=0; i<times-1; i++){
                
                String[] letters=levels.get(i).split("");
                
                stringSize=letters.length;
                
                
                for (int j=0; j<stringSize;j++){ 
                    
                    chars.add(letters[j]);
                    
                    if(chars.get(j).charAt(0)=='a'){
                        chars.set(j,valueA);
                    }
                    if(chars.get(j).charAt(0)=='b'){
                        chars.set(j,valueB);
                    }
                    level=level+chars.get(j);
                    
                }
                
                levels.add(level);
                System.out.println(levels);
                level="";
                
                chars.clear();

            }
        }
        
    }

    public static void main(String args[]) {
    	//TEST
        LSystem test= new LSystem();
        test.buildTree();
        
    }
}
