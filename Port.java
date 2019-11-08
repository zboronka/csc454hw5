public class Port<T> {
	private T payload;
	private boolean open = false;

	public void set(T t) {
		payload = t;
		open = true;
	}

	public T get() {
		open = false;
		return payload;
	}

	public boolean available() {
		return open;
	}
}
