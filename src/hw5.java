import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class hw5 {
	public static void main(String[] args) {
		TotalTime current = new TotalTime(0,0);

		Port<Integer> input = new Port<>();
		Port<Integer> output = new Port<>();

		Machine press = new Machine(1, 0);
		press.input = new Port<Integer>();
		press.output = new Port<Integer>();

		Machine drill = new Machine(2, 1);
		drill.input = new Port<Integer>();
		drill.output = new Port<Integer>();

		Pipe<Integer> netin = new Pipe<>(input, press.input, null, press);
		Pipe<Integer> netout = new Pipe<>(drill.output, output, drill, null);
		ArrayList<Pipe<Integer>> pipes = new ArrayList<>();
		pipes.add(netin);
		pipes.add(netout);
		pipes.add(new Pipe<Integer>(press.output, drill.input, press, drill));

		MinHeap<Event> pqueue = new MinHeap<>();

		HashMap<Event,Integer> trajectory = new HashMap<>();
		Pattern p = Pattern.compile("\\((\\d*\\.\\d*|\\d*),(\\d*)\\)");
		Matcher match;

		Scanner sc = new Scanner(System.in);
		String command;
		while(sc.hasNextLine()) {
			command = sc.nextLine();
			if(command.compareTo("run") == 0) {
				break;
			}

			match = p.matcher(command);
			while(match.find()) {
				Event next = new Event(Delta.EXT, new TotalTime(Double.parseDouble(match.group(1)), 0), press);
				pqueue.offer(next);
				trajectory.put(next, Integer.parseInt(match.group(2)));
			}
		}

		while(pqueue.peek() != null) {
			Event e = pqueue.poll();
			double elapse = current.length(e.time);
			if(trajectory.containsKey(e)) {
				netin.input.set(trajectory.get(e));
				netin.pipe();
				System.out.println("\u001b[36mNETWORK IN\u001b[39;49m");
				System.out.println("Current time: " + e.time);
				System.out.println(trajectory.get(e) + "\n");
			}

			if(pqueue.peek() != null && e.time.compareTo(pqueue.peek().time) == 0 && e.target == pqueue.peek().target) {
				pqueue.poll();
				pqueue.offer(new Event(Delta.CON, e.time, e.target));
				continue;
			}

			System.out.println(e.toString());
			System.out.println();
			switch(e.delta) {
				case EXT:
					e.target.deltaext(elapse);
					System.out.println();
					if(e.target.getInternal() != null) {
						pqueue.remove(e.target.getInternal());
					}
					break;
				case INT:
					e.target.lambda();
					e.target.deltaint();
					break;
				case CON:
					e.target.lambda();
					e.target.deltacon();
				default:
					break;
			}

			for(Pipe pipe : pipes) {
				if(pipe.pipe() && pipe.oMachine != null) {
					pqueue.offer(new Event(Delta.EXT, e.time, pipe.oMachine));
				}
			}

			if(e.target.ta() > 0) {
				e.target.setInternal(new Event(Delta.INT, e.time.advance(e.target.ta()), e.target));
				pqueue.offer(e.target.getInternal());
			}

			if(pqueue.peek() == null || e.time.compareTo(pqueue.peek().time) != 0) {
				current = current.advance(elapse);
			}

			if(output.available()) { 
				System.out.println("\u001b[35mNETWORK OUT\u001b[39;49m");
				System.out.println("Current time: " + current);
				System.out.println(output.get() + "\n");
			}
		}
	}
}
