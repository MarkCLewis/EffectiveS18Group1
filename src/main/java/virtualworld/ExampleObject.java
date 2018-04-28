package virtualworld;

public class ExampleObject implements WorldObject {

	private double x;
	private double z;
	private double y;
	private double size;
	
	public ExampleObject (double x, double z, double y) {
		this.x = x;
		this.z = z;
		this.y = y;
		this.size = x*z;
	}
	
	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
	}
	
	@Override
	public double getZ() {
		return this.z;
	}

	@Override
	public double getSize() {
		return this.size;
	}

	@Override
	public void notifyOfCamera(double x, double z) {
		this.size = 1000000;		
	}
}
