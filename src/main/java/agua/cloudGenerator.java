package agua;

import java.util.Vector;

import javafx.geometry.Point3D;

// Generates clouds (TriangleMeshes that will then be animated and "float" (move) above the ground) 
public class cloudGenerator 
{
	//private Vector<cloud> clouds;
	
	public void cloudGenerator()
	{
		// congrats, there is nothing to construct;
		// perhaps we could make this generate a lot of clouds, based on an int that is passed in
	}
	
	public cloud generateCloud(int size, int scale, int minSpeed, int maxSpeed, int minHeight, int seed)
	{
		// the cloud needs <<somewhere>> to spawn, so a range for where to spawn it could be passed in
		// for now, the cloud does not need to be seen (as this just generates it) so the cloud will have a point of 0,0,0
		// cloud has a method, .moveTo(Point3D point) that can be called (call it before making it visible)
		Point3D trash = new Point3D(0,0,0);
		cloud fluff = new cloud(size, scale, seed, minSpeed, maxSpeed, trash);
		//this.clouds.add(fluff);
		return fluff;
		// this generates the cloud mesh, but does not make it appear
		// to make it appear, you should call the cloud's .visible method with the MeshViewer you want it in
		// 
		//new cloud()
	}
	
//	public Vector<cloud> getClouds()
//	{
//		return this.clouds;
//	}
	
//	public void deleteCloud(cloud cloudToDelete)  // not sure why you would need to do this, but may as well add it
//	{
//		for(int i = 0; i < clouds.size(); i++)
//		{
//			if(clouds.get(i) == cloudToDelete)
//				clouds.remove(i);
//		}
//	}
}
