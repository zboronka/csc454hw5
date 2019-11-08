public class Pipe<T> {
	public Port<T> input;
	public Port<T> output;
	public MooreMachine iMachine;
	public MooreMachine oMachine;

	public Pipe(Port<T> in, Port<T> out, MooreMachine i, MooreMachine o) {
		input = in;
		output = out;
		iMachine = i;
		oMachine = o;
	}

	public boolean pipe() {
		if(input.available()) {
			output.set(input.get());
			return true;
		}
		return false;
	}
}
