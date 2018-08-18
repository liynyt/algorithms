package coding;

public class Fibonacci {
	public int fibonacci1(int n) {
		if (n==0) {
			return 0;
		}
		if(n==1) {
			return 1;
		}
		return fibonacci1(n-1) + fibonacci1(n-2);
	}
	//O(n)
	public int fibonacci2(int n) {
		if (n==0) {
			return 0;
		}
		if(n==1) {
			return 1;
		}
		int one = 1;
		int two = 0;
		int fbN = 0;
		for(int i=2; i<= n; i++) {
			fbN = one + two;
			one = fbN;
			two = one;
		}
		return fbN;
	}

	//time :O(1)  space:O(n)
	private int[] fib = new int[100];
	public Fibonacci() {
		fib[1] = 1;
		fib[2] = 2;
		for(int i=2; i<fib.length; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
	}
	public int fibonacci(int n ) {
		return fib[n];
	}
}
