package coding;

public class Match {
	public boolean match(String string, String pattern) {
		if(string.length() == 0) {
			return false;
		}
		int length = string.length();
		char[] string1 = new char[length];
		char[] pattern1 = new char[pattern.length()];
		string1 = string.toCharArray();
		pattern1 = pattern.toCharArray();
		return macth(string1, pattern1, 0, 0);
	}
	private boolean macth(char[] str, char[] pattern, int indexs, int indexp) {
		if(indexs == str.length && indexp == pattern.length) {
			return true;
		}
		if(indexs != str.length && indexp == pattern.length) {
			return false;
		}
		if(str[indexs] == pattern[indexp] || pattern[indexp] == '.') {
			return macth(str, pattern, indexs+1, indexp+1);
		}
		if(pattern[indexp + 1] == '*') {
			if(pattern[indexp] == str[indexs] || pattern[indexp] == '.') {
				return macth(str, pattern, indexs, indexp+2) || macth(str, pattern, indexs+1, indexp+2) || macth(str, pattern, indexs+1, indexp);
			}
			else {
				return macth(str, pattern, indexs, indexp+2);
			}
		}
		return false;
	}
	public boolean matchDp(String string, String pattern) {
		if(string.length() == 0) {
			return false;
		}
		int n = string.length();
		int m = pattern.length();
		char[] string1 = new char[n];
		char[] pattern1 = new char[m];
		boolean[][] dp = new boolean[n+1][m+1];
		dp[0][0] = true;
		for(int i=1; i<=m; i++) {
			if(pattern1[i-1] == '*') {
				dp[0][i] = dp[0][i-2];
			}
		}
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(string1[i-1] == pattern1[j-1] || pattern1[j-1] == '.') {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					if(pattern1[j-1] == '*') {
						if(string1[i-1] == pattern1[j-2] || pattern1[j-2] == '.') {
							dp[i][j] = dp[i][j-1] || dp[i-1][j] || dp[i][j-2];
						}
						else {
							dp[i][j] = dp[i][j-2];
						}
					}
				}
			}
		}
		return dp[n][m];
	}
	public boolean isNumeric(String string) {
		if(string == null) {
			return false;
		}
		
		return false;
	}

	public static void main(String[] arg0) {
		Match match = new Match();
		String string = "aaa";
		String pattern = "ab*a";
		System.out.println(match.match(string, pattern));;
	}
}
