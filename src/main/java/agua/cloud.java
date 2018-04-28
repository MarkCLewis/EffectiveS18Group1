package agua;

import java.util.Arrays;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.shape.VertexFormat;
import virtualworld.WorldObject;

public class cloud implements WorldObject
{
	private int size;
	private int scale;
	private int minSpeed;
	private int maxSpeed;
	private int height;
	private double xPos;
	private double zPos;
	private Point3D location;
	private Point3D initialSpawn;
	private TriangleMesh cloudMesh;
	private TriangleMesh endAnimationMesh;
	private MeshView meshViewer;
	private cloudTransition transition;
	
	public waveTransition waveT;
	public waveTransition backT;
	
	public cloud(int size, int scale, int seed, int minSpeed, int maxSpeed, Point3D spawnPoint)
	{
		
		this.size = size;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
		this.scale = scale;
		this.xPos = spawnPoint.getX();
		this.zPos = spawnPoint.getZ(); // this is pointless because we already have the point3d, but whatever
		this.initialSpawn = spawnPoint;
		this.location = spawnPoint;
		
		generateTerrain cloudTerrain = new generateTerrain();
		
		createViewer(); // this is questionable (could be bad for performance???)
		
		float[][] coords = cloudTerrain.generateCoordinates(size, size, size, scale, (float).20, seed);  // play with the noise value
		// is there any way to get rid of this performance drain? (below)
		// not currently if using the aaaaa unfortunately 
		float[][] endCoords = cloudTerrain.generateCoordinates(size, size, size, scale, (float).23, seed+14);
		this.cloudMesh = cloudTerrain.generateTerrain(size, scale, coords);
		this.endAnimationMesh = cloudTerrain.generateTerrain(size, scale, endCoords);
		
	}
	
	public MeshView createViewer()
	{
		
		MeshView meshView = new MeshView(this.cloudMesh);
		meshView.setOpacity(1); // play with this value, find the best one!
		PhongMaterial material = new PhongMaterial(Color.AQUA);
		material.setSpecularColor(Color.BLUE);
		// all of this is crappy and hardcoded so will need to be fixed l8r
		meshView.setDrawMode(DrawMode.LINE);
		meshView.setScaleX(scale/10);
		meshView.setScaleZ(scale/10);
		meshView.setScaleY(scale*2);
		meshView.setTranslateX(0);
		meshView.setTranslateY(0);
		meshView.setTranslateZ(0);
		meshView.setMaterial(material);
		
		meshView.setCullFace(CullFace.BACK);
		
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
	
	public Point3D moveBy(int x, int y, int z) // does it need to return the point3D if it has the location updated? hmmmmmmmmmmmmm
	{
		Point3D tempPoint = new Point3D(this.location.getX()+x, this.location.getY()+y, this.location.getZ()+z);
		this.location = tempPoint;
		meshViewer.setTranslateX(x);
		meshViewer.setTranslateY(y);
		meshViewer.setTranslateZ(z);

		
		return tempPoint;
	}
	
	public void makeVisible() // must have a meshviewer attached to this object!
	{
		this.meshViewer.setVisible(true);
	}
	
	public void makeInvisible()
	{
		this.meshViewer.setVisible(false);
	}
	
	public Point3D getPoint() 
	{
		return this.location;
	}

	public TriangleMesh getStartMesh()
	{
		return this.cloudMesh;
	}
	
	public TriangleMesh getEndMesh()
	{
		return this.endAnimationMesh;
	}
	
	public void setTransition(cloudTransition ct)
	{
		this.transition = ct;
	}
	
	public MeshView getMeshViewer()
	{
		return this.meshViewer;
	}
	
	public int getSpeed()
	{
		int speed = (int) (Math.random()*(this.maxSpeed - this.minSpeed));
		return speed;
	}
	
//	public void setCoords(Point3D newCoords)
//	{
//		this.cloudMesh.
//	}
	
	public void calcNormals()
	{
		//System.out.println(cloudMesh.getFaces().size());
		cloudMesh.setVertexFormat(VertexFormat.POINT_TEXCOORD);
		int[] faceSmoothingGroup = new int[cloudMesh.getFaces().size()/cloudMesh.getFaceElementSize()];
		int group = 1;
	    for(int i =0; i < faceSmoothingGroup.length; i++)
	    	{
	    	if(i%cloudMesh.getFaceElementSize() == 0)
	    	{
	    		group*=2;
	    	}
	    	faceSmoothingGroup[i] = group;
	    	}
	    //System.out.println(faceSmoothingGroup.length);
	    cloudMesh.getFaceSmoothingGroups().clear();
	    cloudMesh.getFaceSmoothingGroups().addAll(faceSmoothingGroup);
//	    if(cloudMesh.getFaceSmoothingGroups().size() == cloudMesh.getFaces().size())
//	    	System.out.println("+");//startMesh.getFaceSmoothingGroups().size());
//	    else
//		  System.out.println("-");
	}
	
	public cloudTransition getTransition()
	{
		return this.transition;
	}
	
	public void playTransition() // DO NOT USE THIS! THEY NEED TO BE IN PARALLEL TRANSITIONS!
	{
		this.transition.play();
	}
	// set cloudTransition (from the factory w/ fly weight)

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return location.getX();
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return location.getY();
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return location.getZ();
	}

	@Override
	public double getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public void notifyOfCamera(double x, double z) {
		// TODO Auto-generated method stub
		this.makeVisible();
	}
}
