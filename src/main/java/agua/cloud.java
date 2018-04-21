package agua;

import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class cloud 
{
	private int size;
	private int minSpeed;
	private int maxSpeed;
	private int height;
	private double xPos;
	private double zPos;
	private Point3D location;
	private Point3D initialSpawn;
	private TriangleMesh cloudMesh;
	private MeshView meshViewer;
	
	public cloud(int size, int scale, int seed, int minSpeed, int maxSpeed, Point3D spawnPoint)
	{
		
		this.size = size;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		
		this.xPos = spawnPoint.getX();
		this.zPos = spawnPoint.getZ(); // this is pointless because we already have the point3d, but whatever
		this.initialSpawn = spawnPoint;
		this.location = spawnPoint;
		
		generateTerrain cloudTerrain = new generateTerrain();
		
		createViewer(); // this is questionable (could be bad for performance???)
		
		float[][] coords = cloudTerrain.generateCoordinates(size, size, size, scale, (float).24, seed);  // play with the noise value
		this.cloudMesh = cloudTerrain.generateTerrain(size, scale, coords);
	}
	
	public MeshView createViewer()
	{
		MeshView meshView = new MeshView(this.cloudMesh);
		meshView.setOpacity(.5); // play with this value, find the best one!
		this.meshViewer = meshView;
		
		// THE CLOUD MUST SILL BE ADDED TO THE SCENE GRAPH!!! 
		// ALL THIS DOES IS PUTS THE CLOUD MESH INTO A MESHVIEWER!
		return meshView;
	}
	
	public void moveTo(Point3D point)
	{
		this.meshViewer.setTranslateX(point.getX());
		this.meshViewer.setTranslateY(point.getY()); // y or z? who knows
		this.meshViewer.setTranslateZ(point.getZ());
		
		this.location = point;
	}
	
	public void makeVisible() // must have a meshviewer attached to this object!
	{
		this.meshViewer.setVisible(true);
	}
	
	public void makeInvisible()
	{
		this.meshViewer.setVisible(false);
	}

}
