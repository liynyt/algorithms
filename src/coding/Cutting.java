package coding;

/**
 * 剪绳子，最大乘积
 * @author liyn
 *
 */
public class Cutting {
	/**
	 * dp
	 * @param length
	 * @return
	 */
	public int cutDp(int length) {
		if(length < 2) {
			return 0;
		}
		if(length ==2) {
			return 1;
		}
		if(length == 3) {
			return 2;
		}
		int[] products = new int[length +1];
		products[0] = 0;
		products[1] = 1;
		products[2] = 2;
		products[3] = 3;
		for(int i = 4; i<=length; i++) {
			for(int j =1 ;j<i; j++) {
				products[i] = Math.max(products[i], products[j] * products[i-j]);
			}
		}
		return products[length];
	}

	/**
	 * 贪心算法：先剪3，若剩1，则将一个3与1补成4，剪2个2.
	 * @param length
	 * @return
	 */
	public int cutting(int length) {
		if(length < 2) {
			return 0;
		}
		if(length ==2) {
			return 1;
		}
		if(length == 3) {
			return 2;
		}
		int timesOf3 = length/3;
		if(length - timesOf3 *3 ==1) {
			timesOf3--;
		}
		int timesOf2 = (length - timesOf3*3) / 2;
		return (int) (Math.pow(3, timesOf3)) * (int)(Math.pow(2, timesOf2));
	}
	public static void main(String[] arg0) {
		Cutting cutting = new Cutting();
		System.out.println(cutting.cutDp(10));
		System.out.println(cutting.cutting(10));
	}
}
