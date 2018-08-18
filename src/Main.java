import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int m = in.nextInt();
//		int n = in.nextInt();
//		if( m<=0 || m>= 2*10e6 || n<=0 || n>= 2*10e6) {
//			return;
//		}
//		in.nextLine();
//		List<String > dict = new ArrayList<>();
//		for(int i=0; i<m; i++) {
//			String string = in.nextLine();
//			try {
//				string = new String(string.getBytes("gbk"), "utf-8");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			dict.add(string);
//		}
//		in.nextLine();
//		List<String> find = new ArrayList<>();
//		for(int i=0; i<n; i++) {
//			String string = in.nextLine();
//			try {
//				string = new String(string.getBytes("gbk"), "utf-8");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			find.add(string);
//		}
//		for(int i=0; i<n; i++) {
//			String string = find.get(i);
//			boolean flag = false;
//			for(int j=0; j<m; j++) {
//				String cmp = dict.get(j);
//				if(string.startsWith(cmp)) {
//					flag = true;
//					System.out.println("1");
//					break;
//				}
//			}
//			if(!flag) {
//				System.out.println("-1");
//			}
//		}
//		System.out.println();
//	}
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		String key = in.nextLine();
//		String doc = in.nextLine();
//		int length = key.length();
//		int length1 = doc.length();
//		int[] pos = new int[length];
//		boolean flag = false;
//		for(int i=0; i<length; i++) {
//			int j=0;
//			for(; j<length1; j++) {
//				if(key.charAt(i) == doc.charAt(j)) {
//					pos[i] = j;
//					break;
//				}
//			}
//			if(j == length1) {
//				flag = true;
//				break;
//			}
//		}
//		if(flag) {
//			System.out.println(0);
//		}
//		else {
//			int result = 100;
//			for(int i = 1; i<length; i++) {
//				result = result -(pos[i] - pos[i-1] - 1);
//			}
//			System.out.println(result);
//		}
//	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[][] matrix = new int[m][n];
		int max = 0;
		for(int i=0; i<m; i++) {
			for(int j =0; j<n; j++){
				matrix[i][j] = in.nextInt();
			}
		}
		for(int j=0; j<n; j++) {
			boolean[] flag = new boolean[m];
			for(int i=0; i<m; i++) {
				matrix[i][j] = - matrix[i][j];
				flag[i] = true;
			}
			int[][] dp = new int[m][n];
			for(int k=0; k<n; k++) {
				for(int i=0; i<m; i++) {
					if(k == 0) {
						dp[i][k] = matrix[i][k];
					}
					else {
						if(i ==0) {
							dp[i][k] = Math.max(dp[i][k-1], dp[i+1][k-1]) + matrix[i][k];
						}
						else {
							if(i == m-1) {
								dp[i][k] = Math.max(dp[i][k-1], dp[i-1][k-1]) + matrix[i][k];
							}
							else {
								dp[i][k] =Math.max(Math.max(dp[i][k-1], dp[i-1][k-1]), dp[i+1][k-1]) + matrix[i][k];
							}
						}
						max = Math.max(max, dp[i][k]);
					}
				}
			}
			for(int i=0; i<m; i++) {
				if(flag[i]) {
					matrix[i][j] = -matrix[i][j];
				}
			}
		}
		System.out.println(max);
	}
}
