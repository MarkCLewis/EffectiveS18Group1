package agua;

import java.util.ArrayList;
import java.util.Vector;

import javafx.animation.Transition;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableFloatArray;
import javafx.event.EventHandler;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.util.Duration;

public class waveTransition extends Transition
{
	private Vector<TriangleMesh> meshFrames;
	private ObservableFloatArray original;
	private int highestPoint;
	private MeshView mv;
	private TriangleMesh endMesh;
	private TriangleMesh startMesh;
	private TriangleMesh currMesh;
	private ArrayList<Float> meshDistances;
	private ObservableFloatArray newPoints;
	private int totalFrames;
	private int cycleCount;
	
	public waveTransition(MeshView tempMV, TriangleMesh start, TriangleMesh end, int frames)
	{
		
		mv = tempMV;
		this.endMesh = end;
		this.currMesh = start;
		this.startMesh = start;
		ArrayList<Float> distances = getDistance(this.startMesh, this.endMesh);
		this.meshDistances = distances;
		this.totalFrames = frames;
		setCycleDuration(Duration.millis(1500));
		this.cycleCount = 1;
		//this.meshFrames = wTransition(start, end, frames);
	}
	
	@Override
	protected void interpolate(double frame) 
	{
		ObservableFloatArray currPoints = currMesh.getPoints();
		float[] newPoints = new float[currPoints.size() + 1];
		currPoints.toArray(newPoints);
		ObservableFloatArray copyPoints = currPoints;
		int otherIter = 0;
		for(int z = 1; z < meshDistances.size()*3 - 3 ; z+=3)
		{
			otherIter++;
//			if(this.cycleCount%100 == 0)
//			{
				
				copyPoints.set(z, (float) (frame*newPoints[z] + (meshDistances.get(otherIter)*.03)));
//			}
//			else
//			{
//				copyPoints.set(z, (float) (newPoints[z] - (meshDistances.get(otherIter)*(frame*.03))));
//			}
		}
		//currPoints.clear();
		currPoints.setAll(copyPoints);
		//this.cycleCount++;
		//newPoints.addAll();
//		int numFrame = 0;
//		if(frame != 0.0)
//			 numFrame = Math.round((float)(meshFrames.size()*frame));
//		
//		TriangleMesh tempMesh = meshFrames.get(numFrame);
//		TriangleMesh runningMesh = (TriangleMesh) mv.getMesh();
//		
//		runningMesh.getPoints().setAll(tempMesh.getPoints());
//		runningMesh.getFaces().setAll(tempMesh.getFaces());
	}
	
	public ArrayList<Float> getDistance(TriangleMesh start, TriangleMesh end)
	{
		ObservableFloatArray startPoints = start.getPoints();
		ObservableFloatArray endPoints = end.getPoints();
		//Float[] distances = new Float[startPoints.size()/3 +1];
		ArrayList<Float> startZ = new ArrayList<Float>(startPoints.size()/3 + 1);
		ArrayList<Float> endZ = new ArrayList<Float>(startPoints.size()/3 + 1);
		ArrayList<Float> distances = new ArrayList<Float>(startPoints.size()/3 + 10);
		for(int i = 1; i < startPoints.size() - 3; i += 3)
		{
			startZ.add(startPoints.get(i));
			endZ.add(endPoints.get(i));
			distances.add(endPoints.get(i) - startPoints.get(i));
		}
		return distances;
	}

//	public Vector<TriangleMesh> wTransition(TriangleMesh start, TriangleMesh end, int frames)
//	{
//		
//		TriangleMesh endMesh = null;
//		Vector<TriangleMesh> meshes = new Vector<TriangleMesh>();
//		
//		ObservableFloatArray startPoints = start.getPoints();
//		ObservableFloatArray newPoints = start.getPoints(); // maybe change this later (?)
//		ObservableFloatArray endPoints = end.getPoints();
//		Vector<ObservableFloatArray> waveFrames = new Vector<ObservableFloatArray>();
//		for(int z = 0; z < frames; z++)
//	{
//		for(int i = 0; i < startPoints.size(); i++) // +2 or ++?
//		{
//			if(startPoints.get(i) > endPoints.get(i))
//			{
//				float between = startPoints.get(i) - endPoints.get(i);
//				//between*(1/60); // divide the distance between the two points by the number of frames (between start and end)
//				float newP = startPoints.get(i) + between/frames; // 120 is the number of frames
//				newPoints.set(i, newP);
//			}
//			
//			if(startPoints.get(i) < endPoints.get(i))
//			{
//				float between =  endPoints.get(i) - startPoints.get(i);
//				float newP = startPoints.get(i) + between/frames; // 120 = num of frames
//				newPoints.set(i,  newP);
//			}			
//
//		}
//		
//		waveFrames.add(newPoints);
//		newPoints.clear();
//		
//		endMesh.getPoints().setAll(waveFrames.get(z));
//		// need to somehow generate the faces 
//		//endMesh.getFaces().setAll(start.getFaces()); 
//		meshes.add(endMesh);
//		
//		// clear the endMesh, just to be safe
//		
//		endMesh.getPoints().clear();
//		endMesh.getFaces().clear();
//	}
//		
//		return meshes;
//	}
	
	
	
}
