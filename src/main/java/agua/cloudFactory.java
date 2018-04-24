package agua;

import java.util.HashMap;
import java.util.Vector;

import javafx.scene.shape.TriangleMesh;

public class cloudFactory 
{
	private int[] sizes;
	private int scale;
	private int minSpeed;
	private int maxSpeed;
	private int minHeight;
	private int seed;
	private int number;
	private Vector<cloud> atmosphere;
	private int cloudNum;
	
	// creating a hashmap of different clouds based on size. 
	// This means that if you need to create a bunch of clouds of the same size, it will save time and resources
	private static final HashMap<Integer,cloud> clouds = new HashMap<Integer,cloud>();
	
	// When the world is created, call this cloudFactory with how many clouds you want and an array of different sizes they can be
	// THIS DOES NOT SPAWN VISIBLE CLOUDS
	// clouds will NOT be visible unless you explicitly cloud.createViewer() to get a meshViewer THEN YOU MUST cloud.makeVisible(true)
	// I should probably just rewrite cloudGenerator, but for now it is used in here.
	
	// the 
	public Vector<cloud> createClouds(int number, int[] sizes)
	{
		this.sizes = sizes;
		this.number = number;
		atmosphere = new Vector<cloud>(number+1);
		for(int i = 0; i < sizes.length; i++)
		{
			for(int y = 0; y < number; y++)
			{
			if(clouds.get(sizes[i]) != null)
				atmosphere.add(clouds.get(sizes[i]));
				else
				{
					cloud tempCloud = formCloud(sizes[i]);
					atmosphere.add(tempCloud);
					clouds.put(sizes[i], atmosphere.get(y));
				}
			}	
		}
		return atmosphere;
		
	}
	
	public cloudFactory(int[] sizes, int scale, int minSpeed, int maxSpeed, int minHeight, int seed)
	{
		this.sizes = sizes;
		this.scale = scale;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.minHeight = minHeight;
		this.atmosphere = new Vector<cloud>(number);
		this.seed = seed;
		this.cloudNum = 0;
	}
	
	public cloud formCloud(int size)
	{
		if(clouds.get(size) != null)
			 return clouds.get(size);
		
		//int size, int scale, int minSpeed, int maxSpeed, int minHeight, int seed
		cloudGenerator tempGen = (cloudGenerator) new cloudGenerator(); // terrible rotten no good code
		cloud fluff = tempGen.generateCloud(this.sizes[this.cloudNum], this.scale, this.minSpeed, this.maxSpeed, this.minHeight, this.seed); // disgusting
		
		createTransition(fluff);
		
		//clouds.put(size, fluff); // add it to the hashmap, so that we may use it again 
		cloudNum++;
		return fluff;
	}
	

	public cloudTransition createTransition(cloud c)
	{
		cloudTransition ct = new cloudTransition(c);
		//ct.setAutoReverse(true);
		ct.setCycleCount(ct.INDEFINITE);
		ct.setRate(1);
		
		
		
		c.setTransition(ct);
		
		return ct;
	}
	
	
	
	public Vector<cloud> getClouds() { return this.atmosphere;}
}
