import java.util.Collection;

enum Delta { EXT, INT, CON };

public class Event implements Comparable<Event> {
	public TotalTime time;
	public MooreMachine target;
	public Delta delta;

	public Event(Delta delta, TotalTime time, MooreMachine target) {
		this.time = time;
		this.target = target;
		this.delta = delta;
	}

	public int compareTo(Event c) {
		return time.compareTo(c.time) == 0 ? target.compareTo(c.target) == 0 ? Integer.compare(delta.ordinal(), c.delta.ordinal()) : target.compareTo(c.target) : time.compareTo(c.time);
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
