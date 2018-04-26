package terraintesting;

public class Point {
	private final double x;
	private final double z;
	
	public Point (double x, double z) {
		this.x = x;
		this.z = z;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getZ() {
		return this.z;
	}
	
	public boolean equalsPoint (Point other) {
		return (this.z == other.getZ() && this.x == other.getX());
	}
}