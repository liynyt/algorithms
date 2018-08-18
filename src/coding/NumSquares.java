package coding;

import java.util.ArrayList;
import java.util.List;

public class NumSquares {

	public int numSquare(int num) {
		List<Integer> squares = getSquares(num);
		int[] dp = new int[num+1];
		dp[0] = 0;
		dp[1] = 1;
		for(int i=2; i<= num; i++) {
			int min = Integer.MAX_VALUE;
			for(int square : squares) {
				if(square > i) {
					break;
				}
				min = Math.min(min, dp[i - square] +1);
			}
			dp[i] = min;
		}
		return dp[num];
	}
	public List<Integer> getSquares(int num){
		List<Integer> squares = new ArrayList<>();
		int diff = 3;
		int square = 1;
		while(square <= num) {
			squares.add(square);
			square += diff;
			diff+= 2;
		}
		return squares;
	}
}
