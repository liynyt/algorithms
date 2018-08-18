package coding;


public class Decode {

	public int minDecoding(String string) {
		int n = string.length();
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = string.charAt(0) == '0' ? 0 : 1;
		for(int i =2; i<= n; i++) {
			int one = Integer.parseInt(string.substring(i-1, i));
			if(one != 0) {
				dp[i] += dp[i-1];
			}
			if(string.charAt(i-2) == '0'){
				continue;
			}
			int two = Integer.parseInt(string.substring(i-2, i));
			if(two <= 26) {
				dp[i] += dp[i-2];
			}
		}
		return dp[n];
	}
	public static void main(String[] arg0) {
		Decode decode = new Decode();
		System.out.println(decode.minDecoding("100"));
	}
}
