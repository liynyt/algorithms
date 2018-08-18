package coding;

import java.util.Arrays;

/**
 * 跳台阶
 * 方法：DP：dp[n]=dp[n-1] + dp[n-2]
 * @author liyn
 *
 */
public class JumpFloor {
	
	public int jumpFloor1(int n ) {
		if(n==1) {
			return 1;
		}
		int[] dp = new int[n];
		dp[0] = 0;
		dp[1] = 1;
		for(int i=2;i<n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[n-1];
	}

	public int jumpFloor2(int n) {
		if(n==1) {
			return 1;
		}
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for(int i=1;i<n; i++) {
			for(int j=0; j<i; j++) {
				dp[i] += dp[j];
			}
		}
		return dp[n-1];
	}
}
