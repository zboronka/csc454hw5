import java.util.Collection;

public interface MooreMachine {
	void setInternal(Event e);
	Event getInternal();

	Collection<Port> getInputs();
	Collection<Port> getOutputs();

	double ta();

	void lambda();
	void deltaint();
	void deltaext(double e);
	void deltacon();
}
