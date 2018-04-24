package agua;

import java.util.ArrayList;

import javafx.animation.Transition;
import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.util.Duration;

public class cloudTransition extends Transition{

	private cloud theCloud;
	private int speed; 
	
	public cloudTransition(cloud fluff)
	{
		setCycleDuration(Duration.millis(10000));
		this.theCloud = fluff;
		this.speed = theCloud.getSpeed();
	}
	
	@Override
	public void interpolate(double frame)
	{
		ObservableFloatArray currPoints = theCloud.getStartMesh().getPoints();
		float[] newPoints = new float[currPoints.size() + 1];
		currPoints.toArray(newPoints);
		ObservableFloatArray copyPoints = currPoints;
		int otherIter = 0;
		ArrayList<Float> meshDistances = getDistance(theCloud.getStartMesh(), theCloud.getEndMesh());
		double change = theCloud.getPoint().getX() + frame+speed;
		theCloud.calcNormals();
		theCloud.getMeshViewer().setTranslateX(change);
		theCloud.moveTo(new Point3D((int)change, theCloud.getPoint().getY(), theCloud.getPoint().getZ()));
		//theCloud.moveBy((int)frame*10000, 0, 0);
		//theCloud.moveTo(theCloud.moveBy((int)(frame*.005), 0, 0)); // CHANGE THIS please god please
		
//		for(int z = 1; z < meshDistances.size()*3 - 3 ; z+=3)
//		{
//				otherIter++;
//				copyPoints.set(z, (float) (frame*newPoints[z] + (meshDistances.get(otherIter)*.03)));
//		}
//		
//		currPoints.clear();
//		
//		currPoints.setAll(copyPoints);
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
}
