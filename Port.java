public class Port<T> {
	private T payload;
	private boolean open = false;

	public void set(T t) {
		payload = t;
		open = true;
	}

	public T get() {
		if(open) {
			open = false;
			return payload;
		}

		return null;
	}

	public boolean available() {
		return open;
	}
}
