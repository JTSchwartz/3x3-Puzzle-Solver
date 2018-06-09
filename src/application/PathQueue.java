package application;

public class PathQueue<T> {

	private int size = 0;
	private int root = 0;
	private int last = 0;
	private T[] q;
	
	public PathQueue() {
		q = (T[]) new Object[10];
	}
	
	public int getSize() {
		return size;
	}
	
	public int getRoot() {
		return root;
	}
	
	public int getLast() {
		return last;
	}
	
	public T[] getQ() {
		return q;
	}
	
	public void add(T x) {
		size++;
		
		if(size == q.length) {
			resize(true);
		}
		
		q[last++] = x;
		
		if(last == q.length) {
			last = 0;
		}
	}
	
	public T remove() {
		T temp = q[root++];
		size--;
		
		if(root == q.length) {
			root = 0;
		}
		
		return temp;
	}
	
	public void resize(boolean enlarge) {
		int n;
		
		if(enlarge) {
			n = q.length * 2;
		} else {
			n = (int) (q.length * .75);
		}
		
		T[] temp = (T[]) new Object[n];
		
		for (int i = 0; i < size; i++) {
            temp[i] = q[(root + i) % q.length];
		}
		
		q = temp;
		root = 0;
		last = size;
	}
	
	
}
