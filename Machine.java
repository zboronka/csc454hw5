import java.util.Collection;
import java.util.ArrayList;

public class Machine implements MooreMachine {
	private int e;
	private int p;

	private double s;
	private double t;
	
	private Event internal;
	private int priority;

	public Port<Integer> input;
	public Port<Integer> output;

	public Machine(double t, int priority) {
		p = 0;
		s = 0;
		this.t = t;
		this.priority = priority;
	}

	public void setInternal(Event e) { internal = e; }
	public Event getInternal() { return internal; }
	public int getPriority() { return priority; }

	public Collection<Port> getInputs() {
		ArrayList<Port> ret = new ArrayList<>();
		ret.add(input);
		return ret;
	}

	public Collection<Port> getOutputs() {
		ArrayList<Port> ret = new ArrayList<>();
		ret.add(output);
		return ret;
	}

	public double ta() {
		return p > 0 ? s : -1;
	}

	public void lambda() {
		output.set(1);
	}

	public void deltaint() {
		p -= 1;
		s = t;
	}

	public void deltaext(double e) {
		s = p == 0 ? t : s - e;
		p += input.get();
	}

	public void deltacon() {
		p += input.get() - 1;
		s = t;
	}

	public int compareTo(MooreMachine m) {
		return Integer.compare(priority, m.getPriority());
	}
}
