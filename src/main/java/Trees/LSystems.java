package Trees;
import java.io.InputStream;
import java.util.Scanner;

public class LSystems {

	public String a, b, inputA, inputB, start;

	public int levels;

	public Scanner sc;

	public void LSystems(){
		a="";
		inputA="";
		b="";
		inputB="";
		levels=0;
		start="";
	}

	public void buildTree(){

		sc=new Scanner(System.in);
		System.out.print("What do you want to start with (type a or b)? ");
		start = sc.nextLine();


		System.out.print("enter productions for a: ");
		inputA = sc.nextLine();
		int numA= inputA.length();


		System.out.print("enter productions for b: ");
		inputB = sc.nextLine();
		int numB= inputA.length();


		System.out.print("enter number of levels: ");
		levels = Integer.parseInt(sc.nextLine());

		sc.close();


		String newLevel="";



		System.out.println("Starting with "+ start);
		System.out.println("a= "+inputA);
		System.out.println("b= "+inputB);
		System.out.println("levels= "+levels);
		System.out.println("---------------------");
		System.out.println(start);
		char c='q';
		int cont=0;
		for (int i=0; i<levels; i++){

			if(start=="a"){
				c=inputA.charAt(cont);
			

				if(c=='a'){
					newLevel=newLevel+inputA;
					cont++;
				}
				if(c=='b'){
					newLevel=newLevel+inputB ;
					cont++;
				}

			}
			else{
				c=inputB.charAt(cont);
				if(c=='a'){
					newLevel=newLevel+inputA;
				}
				if(c=='b'){
					newLevel=newLevel+inputB ;
				}
				cont++;
			}



			System.out.println(newLevel);


		}


	}

	public static void main (String[] args){
		LSystems t =new LSystems();
		t.buildTree();
	}
}
