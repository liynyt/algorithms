package Algorithm;

public class MaxPQ <E extends Comparable<E>>{
	private E[] pq;
	private int N=0;
	public MaxPQ(int max) {
		pq = (E[]) new Comparable[max + 1];
	}
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	public void insert(E element) {
		pq[++N] = element;
		swim(N);
	}
	public E delMax() {
		E max = pq[1];
		pq[1]= pq[N--];
		pq[N+1] = null;
		sink(1);
		return max;
	}

	private void swim(int num) {
		while(num>1 && pq[num/2].compareTo(pq[num]) <0) {
			E temp = pq[num/2];
			pq[num/2] = pq[num];
			pq[num] = temp;
			num = num/2;
		}
	}
	private void sink(int num) {
		while(num*2 <= N) {
			int j = num*2;
			if(j<N && pq[j].compareTo(pq[j+1]) <0) {
				j++;
			}
			if(!(pq[num].compareTo(pq[j]) <0)) {
				break;
			}
			num = j;
		}
	}
}
