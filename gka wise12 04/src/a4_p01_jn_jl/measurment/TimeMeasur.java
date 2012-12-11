package a4_p01_jn_jl.measurment;

public class TimeMeasur {

	private Long startTime;
	private Long endTime;
	private Double resultTime;

	public void startTimeMeasuring() {
		this.startTime = System.nanoTime();
	}

	public Double endTimeMeasuring() {
		this.endTime = System.nanoTime();
		return resultTime = ((endTime - startTime) / 1000000.0);
	}

}
