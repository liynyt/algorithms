package Algorithm;

public class  MemoCut{
	private final int[] price;
	private int length;
	private int cost;
	public MemoCut(int[] price, int length) {
		this.price = price;
		this.length = length;
		int[] r = new int[price.length+1];
		for(int i=0; i<r.length; i++) {
			r[i] = Integer.MIN_VALUE;
				
		}
//		cost = cutRod(price, length, r);
	}
	public int getCost() {
		return cost;
	}
	public int cutRod(int[] price, int num, int[] r) {
		if(r[num] >= 0) {
			return r[num];//return value saved before
		}
		int q;
		if(num == 0) {
			q = 0;
		}
		else {
			q = Integer.MIN_VALUE;
			for(int i = 1; i<=num; i++) {
				q = Math.max(q, price[i-1] + cutRod(price, num-i, r));
			}
		}
		r[num] = q;
		return q;
	}
	public int bottomCutRod(int[] price, int num, int[] s) {
		int[] r = new int[price.length+1];//save cost of num
		r[0] = 0;
//		for(int j=1; j<= num; j++) {//save from the bottom
//			int q= Integer.MIN_VALUE;
//			for(int i=1; i<= j; i++) {
//				q = Math.max(q, price[i-1] + r[j-i]);
//			}
//			r[j] = q;
//		}
		for(int j=1; j<= num; j++) {//save from the bottom
			int q= Integer.MIN_VALUE;
			for(int i=1; i<= j; i++) {
				if(q < price[i-1] + r[j-i]) {
					q = price[i-1] + r[j-i];
					s[j] = i;
				}
			}
			r[j] = q;
		}
		return r[num];
	}
	public static void main(String[] args) {
		int[] price = {1,5,8,9,10};
		int length = 4;
		int[] s = new int[price.length + 1];
		MemoCut memoCut = new MemoCut(price, length);
//		System.out.println(memoCut.getCost());
//		System.out.println(memoCut.bottomCutRod(price, length));
		System.out.println(memoCut.bottomCutRod(price, length, s));
		for(int i=0; i<s.length; i++) {
			System.out.println(s[i]);
		}
		
	}
}
