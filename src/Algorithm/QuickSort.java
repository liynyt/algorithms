package Algorithm;

public class QuickSort {
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length);
	}
	private static void sort(Comparable[] a, int lo, int hi) {
		if(lo>= hi ) {
			return;
		}
		int j= partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	private static int partition(Comparable[] a, int lo, int hi) {
		int i=lo;
		int j = hi+1;
		Comparable v = a[lo];
		while(true) {
			while(a[++i].compareTo(v) < 0) {
				if(i== hi) {
					break;
				}
			}
			while(v.compareTo(a[--j])<0) {
				if(j==lo) {
					break;
				}
			}
			if(i>=j) {
				break;
			}
			Comparable temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		Comparable temp = a[j];
		a[j] = a[lo];
		a[lo] = temp;
		return j;
	}

}
