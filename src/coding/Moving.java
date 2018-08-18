package coding;

/**
 * 回溯法？？？
 * 题目：机器人的运动范围：上下左右运动一个，不能进入行坐标和列坐标数位之和大于K的格子
 * @author liyn
 *
 */
public class Moving {
	private int[][] next = {{0, -1}, {0, 1}, {1, -1}, {1, 1}};
	public int movingCount(int k, int row, int col) {
		int count = 0;
		int[][] digitSum = new int[row][col];
		boolean[][] marked = new boolean[row][col];
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				digitSum[i][j] = getDigitSum(j) + getDigitSum(i);
			}
		}
		count += moving(k, row, col, 0, 0, marked, digitSum);
		
		return count;
	}
	public int moving(int k,int row, int col, int curR, int curC, boolean[][] marked, int[][] digitSum) {
		int cnt = 0;
		if (curR < 0 || curR >= row || curC < 0 || curC >= col) {
			return 0;
		}
	    if (marked[curR][curC]) {
	    	return 0;
	    }
	    marked[curR][curC] = true;
	    if (digitSum[curR][curC] > k) {
	    	return 0;
	    }
	    cnt++;
	    for (int i = 0; i < this.next.length; i++) {
	    	moving(k, row, col, curR+ next[i][0], curC + next[i][1], marked, digitSum);
	    }
		return cnt;
	}
	public int getDigitSum(int num) {
		int sum = 0;
		while(num > 0) {
			sum += num % 10;
			num = num / 10;
		}
		return sum;
	}

}
