import java.util.Queue;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class MinHeap<E extends Comparable<E>> implements Queue<E> {
	private ArrayList<E> tree = new ArrayList<>(); 

	// Inserts the specified element into this heap if it is possible to do so immediately without violating capacity restrictions, returning true upon success and throwing an IllegalStateException if no space is currently available.
	public boolean add(E e) {
		tree.add(e);
		siftUp(tree.size() - 1);
		return true;
	}

	// Adds all of the elements in the specified collection to this heap.
	public boolean addAll(Collection<? extends E> c) {
		for(E n : c) {
			add(n);
		}

		return true;
	}

	// Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
	public boolean offer(E e) {
		tree.add(e);
		siftUp(tree.size() - 1);
		return true;
	}

	// Retrieves and removes the head of this heap. This method differs from poll only in that it throws an exception if this heap is empty.
	public E remove() {
		E ret = tree.get(0);
		siftDown(0);
		return ret;
	}

	// Removes a single instance of the specified element from this collection, if it is present.
	public boolean remove(Object o) {
		for(int i = 0; i < tree.size(); i++) {
			if(o.equals(tree.get(i))) {
				siftDown(i);
				return true;
			}
		}

		return false;
	}

	// Removes all of this heap's elements that are also contained in the specified collection.
	public boolean removeAll(Collection<?> c) {
		boolean remove = false;
		for(Object o : c) {
			remove(o);
			remove = true;
		}

		return remove;
	}

	// Retains only the elements in this heap that are contained in the specified collection.
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		for(Object n : tree) {
			if(!c.contains(n)) {
				remove(n);
				changed = true;
			}
		}

		return changed;
	}

	// Retrieves and removes the head of this heap.
	public E poll() {
		if(tree.isEmpty()) {
			return null;
		}

		E ret = tree.get(0);
		siftDown(0);
		return ret;
	}

	// Retrieves, but does not remove, the head of this heap. This method differs from peek only in that it throws an exception if this heap is empty.
	public E element() {
		return tree.get(0);
	}

	// Retrieves, but does not remove, the head of the heap.
	public E peek() {
		return !tree.isEmpty() ? tree.get(0) : null;
	}

	// Removes all of the elements from this heap.
	public void clear() {
		tree.clear();
	}

	// Returns true if this heap contains the specified element.
	public boolean contains(Object o) {
		return tree.contains(o);
	}

	// Returns true if this heap contains all of the elements in the specified collection.
	public boolean containsAll(Collection<?> c) {
		return tree.containsAll(c);
	}

	//TODO write this 
	public boolean equals(Object o) {
		return false;
	}

	//TODO write this 
	public int hashCode() {
		return 0;
	}

	// Returns true if this collection contains no elements.
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	// Returns the number of elements in this collection.
	public int size() {
		return tree.size();
	}

	//TODO write this 
	public <T> T[] toArray(T[] a) {
		return a;
	}

	// Returns an array containing all of the elements in this collection.
	public Object[] toArray() {
		return tree.toArray();
	}

	// Returns an iterator over the elements in this collection.
	public Iterator<E> iterator() {
		return tree.iterator();
	}

	// Move a node up in the tree, as long as needed; used to restore heap condition after insertion.
	private void siftUp(int index) {
		int parent = (index-1)/2;
		if(parent >= 0 && tree.get(index).compareTo(tree.get(parent)) < 0) {
			tree.set(parent, tree.set(index, tree.get(parent)));
			siftUp(parent);
		}
	}
	
	// Move a node don in the tree, as long as needed; used to restore heap condition after deletion.
	private void siftDown(int index) {
		int left  = 2*index+1;
		int right = 2*index+2;
		if(right < tree.size()) {
			if(tree.get(left).compareTo(tree.get(right)) <= 0) {
				tree.set(index, tree.get(left));
				siftDown(left);
			}
			else {
				tree.set(index, tree.get(right));
				siftDown(right);
			}
		}
		else if(left < tree.size()) {
			tree.set(index, tree.get(left));
			tree.remove(left);
		}
		else {
			tree.remove(index);
		}
	}
}
