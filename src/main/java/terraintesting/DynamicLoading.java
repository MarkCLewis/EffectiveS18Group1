package terraintesting;

//import java.util.Random;

public class DynamicLoading {
	long seed = 0x327a96;

	long posRandomLong(double x, double y) {
		long lx = Double.doubleToLongBits(x);
		long ly = Double.doubleToLongBits(y);

		return lx ^ ly ^ (seed & (lx >> 32)) ^ (seed & (lx << 32)) ^ (seed & (ly >> 32)) ^ (seed & (ly << 32));
	}

	double posRandomDouble(double x, double y) {
		//double dx = Long.doubleValue(x);
		//double dy = Long.doubleValue(y);
		return 0.0;
	}
}
