import java.util.Collection;

enum Delta { EXT, INT, CON };

public class Event implements Comparable<Event> {
	public TotalTime time;
	public MooreMachine target;
	public Delta delta;
	public double elapse;
	public Integer input;

	public Event(Delta delta, TotalTime time, MooreMachine target, double elapse) {
		this.time = time;
		this.target = target;
		this.delta = delta;
		this.elapse = elapse;
	}

	public Event(Delta delta, TotalTime time, MooreMachine target, double elapse, Integer input) {
		this.time = time;
		this.target = target;
		this.delta = delta;
		this.elapse = elapse;
		this.input = input;
	}

	public int compareTo(Event c) {
		return time.compareTo(c.time) == 0 ? Integer.compare(target.hashCode(), c.target.hashCode()) == 0 ? Integer.compare(delta.ordinal(), c.delta.ordinal()) : Integer.compare(target.hashCode(), c.target.hashCode()) : time.compareTo(c.time);
	}

	public String toString() {
		String ret = "";
		switch(delta) {
			case EXT:
				ret += "\u001b[32mEXT\u001b[39;49m";
				break;
			case INT:
				ret += "\u001b[31mINT\u001b[39;49m";
				break;
			case CON:
				ret += "\u001b[33mCON\u001b[39;49m";
			default:
				break;
		}
		ret += "\nCurrent time: " + time.toString();
		ret += "\nMACHINE " + target;
		return ret;
	}
}
