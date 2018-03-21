package virtualworld;

public class ExampleObject implements WorldObject {

	double x;
	double y;
	double size;
	
	public ExampleObject (double x, double y, double s) {
		this.x = x;
		this.y = y;
		this.size = s;
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
	public double getSize() {
		return this.size;
	}

}
