package terraintesting;

// TODO May have to fix this to assume a right-handed coordinate system
	// If y grows upwards from the xz-plane, and x grows to the right, then z would grow down 
public class TerrainObjectBuilder {
	private double x;
	private double y;
	private double z;
	private double xW;
	private double yW;
	private double zW;
	
	private int lod = 0;
	private long seed = TerrainObject.getDefaultSeed();
	private double noise = TerrainObject.getDefaultSeed();
	
	// Keeps track of which fields have been set
	private boolean[] paramsSet = {false, false, false, false, false, false, false, false, false};
	
	public TerrainObjectBuilder() {
		
	}
	
	public TerrainObject build() {
		//All fields but seed and noise must be set by caller
		for(int i=0; i<paramsSet.length-3; i++)
			if(!paramsSet[i])
				throw new IllegalArgumentException("Needed parameter (No. "+i+") for TerrainObject hasn't been set");
		return new TerrainObject(x, y, z, xW, yW, zW, lod, seed, noise);
	}
	
	public void setXLoc(double x) {
		paramsSet[0] = true;
		this.x = x;
	}
	
	public void setYLoc(double y) {
		paramsSet[1] = true;
		this.y = y;
	}
	
	public void setZLoc(double z) {
		paramsSet[2] = true;
		this.z = z;
	}
	
	public void setXWidth(double xW) {
		paramsSet[3] = true;
		this.xW = xW;
	}
	
	public void setYWidth(double yW) {
		paramsSet[4] = true;
		this.yW = yW;
	}
	
	public void setZWidth(double zW) {
		paramsSet[5] = true;
		this.zW = zW;
	}
	
	public void setLOD(int lod) {
		paramsSet[6] = true;
		this.lod = lod;
	}
	
	public void setSeed(long seed) {
		paramsSet[7] = true;
		this.seed = seed;
	}
	
	public void setNoise(double noise) {
		paramsSet[8] = true;
		this.noise = noise;
	}
}
