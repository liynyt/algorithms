package coding;

public class NumberOf1 {

	public int getNumber(int num) {
		int count = 0;
		while(num != 0) {
			count++;
			num = num & (num-1);
		}
		return count;
	}
}
