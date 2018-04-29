package terraintesting;

public class TerrainObjectBuilder {
	private double x;
	private double y;
	private double z;
	private double xW;
	private double yW;
	private double zW;
	
	private long seed = TerrainObject.getDefaultSeed();
	private double noise = TerrainObject.getDefaultSeed();
	private boolean[] paramsSet = {false, false, false, false, false, false, false, false};
	
	public TerrainObjectBuilder() {
		
	}
	
	public TerrainObject build() {
		for(int i=0; i<paramsSet.length-2; i++)
			if(!paramsSet[i])
				throw new IllegalArgumentException("Needed parameter (No. "+i+") for TerrainObject hasn't been set");
		return new TerrainObject(x, y, z, xW, yW, zW, seed, noise);
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
	
	public void setSeed(long seed) {
		paramsSet[6] = true;
		this.seed = seed;
	}
	
	public void setNoise(double noise) {
		paramsSet[7] = true;
		this.noise = noise;
	}
}
