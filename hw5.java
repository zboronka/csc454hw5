import java.util.Scanner;
import java.util.ArrayList;

public class hw5 {
	public static void main(String[] args) {
		TotalTime current = new TotalTime(0,0);

		Port<Integer> input = new Port<>();

		Machine press = new Machine(1);
		press.input = new Port<Integer>();
		press.output = new Port<Integer>();

		Machine press2 = new Machine(2);
		press2.input = new Port<Integer>();
		press2.output = new Port<Integer>();

		Pipe<Integer> netin = new Pipe<>(input, press.input, null, press);
		ArrayList<Pipe<Integer>> pipes = new ArrayList<>();
		pipes.add(netin);
		pipes.add(new Pipe<Integer>(press.output, press2.input, press, press2));

		MinHeap<Event> pqueue = new MinHeap<>();

		pqueue.offer(new Event(Delta.EXT, new TotalTime(1,0), press, 0, 1));
		pqueue.offer(new Event(Delta.EXT, new TotalTime(3,0), press, 0, 2));

		while(pqueue.peek() != null) {
			Event e = pqueue.poll();
			if(pqueue.peek() != null && e.time.compareTo(pqueue.peek().time) == 0 && e.target == pqueue.peek().target) {
				pqueue.poll();
				pqueue.offer(new Event(Delta.CON, e.time, e.target, e.elapse, e.input));
				continue;
			}
			if(e.input != null) {
				netin.input.set(e.input);
				netin.pipe();
				System.out.println("NETIN " + e.input + " @ " + e.time.toString());
				System.out.println();
			}
			System.out.println(e.toString());
			switch(e.delta) {
				case EXT:
					e.target.deltaext(e.elapse);
					System.out.println();
					if(e.target.getInternal() != null) {
						pqueue.remove(e.target.getInternal());
					}
					break;
				case INT:
					e.target.lambda();
					e.target.deltaint();
					System.out.println();
					break;
				case CON:
					e.target.lambda();
					e.target.deltacon();
					System.out.println();
				default:
					break;
			}

			for(Pipe pipe : pipes) {
				if(pipe.pipe()) {
					pqueue.offer(new Event(Delta.EXT, e.time, pipe.oMachine, current.length(e.time)));
				}
			}

			if(e.target.ta() > 0) {
				e.target.setInternal(new Event(Delta.INT, e.time.advance(e.target.ta()), e.target, e.elapse));
				pqueue.offer(e.target.getInternal());
			}

			current = e.time;
		}
	}
}
