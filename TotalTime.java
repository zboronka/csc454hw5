public class TotalTime implements Comparable<TotalTime> {
	private double real_time;
	private int discrete_time;

	public TotalTime(double rt, int dt) {
		real_time = rt;
		discrete_time = dt;
	}

	public int compareTo(TotalTime o) {
		return real_time == o.real_time ? Integer.compare(discrete_time, o.discrete_time) : Double.compare(real_time, o.real_time);
	}

	public TotalTime advance(TotalTime b) {
	 	return b.real_time == 0 ? new TotalTime(real_time, discrete_time + b.discrete_time) : new TotalTime(real_time + b.real_time, 0);
	}

	public TotalTime advance(double b) {
		return new TotalTime(real_time + b, 0);
	}

	public double length(TotalTime b) {
		return b.real_time - real_time;
	}

	public String toString() {
		String ret = "";
		ret += "(" + Double.toString(real_time) + ", " + discrete_time + ")";
		return ret;
	}
}
