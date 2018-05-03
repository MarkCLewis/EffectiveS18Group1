	private Mesh generateTerrain(int dimension, float noise, int scale){  
		float[][] generatedYValues = generateYValues(dimension, noise);  
		      return createTerrainMesh(dimension, scale, generatedYValues);  
		    }  
		    private float[][] generateYValues(int dimension, float noise) {  
		      float[][] generatedYValues = new float[dimension][dimension];  
		      float xoff = 0.0f;  
		      for (int x = 0; x < dimension; x++) {  
		        float zoff = 0.0f;  
		        for (int z = 0; z < dimension; z++) {  
		          generatedYValues[x][z] = PApplet.map(utils.noise(xoff, zoff), 0, 1, 0, dimension);  
		          zoff += noise;  
		        }  
		        xoff += noise;  
		      }  
		      return generatedYValues;  
		    }  
		    private TriangleMesh createTerrainMesh(int dimension, float scale, float[][] generatedYValues) {  
		      ObservableFloatArray points = FXCollections.observableFloatArray();  
		ObservableIntegerArray faces = FXCollections.observableIntegerArray();  
		Integer[][] vertexID = new Integer[dimension][dimension];  
		int ctr = 0;  
		for (int x = 0; x < dimension; x++) {  
		for (int z = 0; z < dimension; z++) {  
		float tmpX = x * scale;  
		float tmpY = generatedYValues[x][z] * scale;  
		float tmpZ = z * scale;  
		if (z + 1 < dimension && x + 1 < dimension) {  
		Integer vCurrent = vertexID[x][z];  
		 Integer vDown = vertexID[x][z + 1];  
		 Integer vRight = vertexID[x + 1][z];  
		if (vCurrent == null) {  
		points.addAll(tmpX);  
		points.addAll(tmpY);  
		points.addAll(tmpZ);  
		vertexID[x][z] = ctr++;  
		vCurrent = vertexID[x][z];  
		}  
		if (vDown == null) {  
		//point above  
		points.addAll(tmpX);  
		points.addAll(generatedYValues[x][z + 1] * scale);  
		points.addAll(tmpZ + scale);  
		vertexID[x][z + 1] = ctr++;  
		vDown = vertexID[x][z + 1];  
		}  
		if (vRight == null) {  
		//point to the right  
		points.addAll(tmpX + scale);  
		points.addAll(generatedYValues[x + 1][z] * scale);  
		points.addAll(tmpZ);  
		vertexID[x + 1][z] = ctr++;  
		vRight = vertexID[x + 1][z];  
		}  
		faces.addAll(vCurrent);  
		faces.addAll(0);  
		faces.addAll(vDown);  
		faces.addAll(0);  
		faces.addAll(vRight);  
		faces.addAll(0);  
		}  
		if (z - 1 >= 0 && x - 1 >= 0) {  
		Integer vCurrent = vertexID[x][z];  
		Integer vUp = vertexID[x][z - 1];  
		Integer vLeft = vertexID[x - 1][z];  
		if (vCurrent == null) {  
		//current  
		points.addAll(tmpX);  
		points.addAll(tmpY);  
		points.addAll(tmpZ);  
		vertexID[x][z] = ctr++;  
		vCurrent = vertexID[x][z];  
		}  
		if (vUp == null) {  
		//point to the left  
		points.addAll(tmpX - scale);  
		points.addAll(generatedYValues[x - 1][z] * scale);  
		points.addAll(tmpZ);  
		vertexID[x][z - 1] = ctr++;  
		vUp = vertexID[x][z - 1];  
		}  
		if (vLeft == null) {  
		
		points.addAll(tmpX);  
		points.addAll(generatedYValues[x][z - 1] * scale);  
		points.addAll(tmpZ - scale);  
		vertexID[x - 1][z] = ctr++;  
		vLeft = vertexID[x - 1][z];  
		}  
		faces.addAll(vCurrent);  
		faces.addAll(0);  
		faces.addAll(vUp);  
		faces.addAll(0);  
		faces.addAll(vLeft);  
		faces.addAll(0);  
		}  
		}  
		}  
		TriangleMesh mesh = new TriangleMesh();  
		mesh.getTexCoords().addAll(0, 0);  
		mesh.getPoints().addAll(points);  
		mesh.getFaces().addAll(faces);  
		return mesh;  
		    }  