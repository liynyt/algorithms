package Algorithm;

public class BinarySearchST <Key extends Comparable<Key>, Value>{
	private Key[] keys;
	private Value[] values;
	private int n;
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	public int size() {
		return n;
	}
	public Value get(Key key) {
		int index = rank(key);
		if(index < n && key.equals(keys[n]))
			return values[index];
		else {
			return null;
		}
		
	}
	public int rank(Key key) {
		int lo =0;
		int hi = keys.length-1;
		while(lo <= hi) {
			int mid = lo + (hi-lo)/2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp <0) {
				hi = mid-1;
			}
			else {
				if(cmp > 0) {
					lo = mid + 1;
				}
				else {
					return mid;
				}
			}
		}
		return lo;
	}
	public void put(Key key, Value value) {
		int index = rank(key);
		if(index <n && key.compareTo(keys[index]) == 0) {
			values[index] = value;
			return;
		}
		for(int j= n; j>index; j--) {
			keys[j] = keys[j-1];
			values[j] = values[j-1];
		}
		keys[index] = key;
		values[index] = value;
		n++;
	}
	public void delete(Key key) {
		int index = rank(key);
		if(index <n && key.compareTo(keys[index]) == 0) {
			values[index] = null;
		}
		for(int j= index; j<n-1; j++) {
			keys[j] = keys[j+1];
			values[j] = values[j+1];
		}
		n--;
	}
	public Key min() {
        return keys[0]; 
    }
    public Key max() {
        return keys[n-1];
    }

	public Key floor(Key key) {
		int index = rank(key);
		if(index < n && keys[index].compareTo(key) == 0) {
			return keys[index];
		}
		if(index > 0 ) {
			return keys[index - 1];
		}
		else {
			return null;
		}
	}
	public Key ceiling(Key key) {
		int index = rank(key); 	
		if(index == n ) {
			return null;
		}
		else {
			return keys[index];
		}
	}

}
