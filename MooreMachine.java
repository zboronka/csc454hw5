import java.util.Collection;

public interface MooreMachine extends Comparable<MooreMachine> {
	void setInternal(Event e);
	Event getInternal();
	int getPriority();

	Collection<Port> getInputs();
	Collection<Port> getOutputs();

	double ta();

	void lambda();
	void deltaint();
	void deltaext(double e);
	void deltacon();

	int compareTo(MooreMachine m);
}
