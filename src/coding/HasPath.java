package coding;

/**
 * 回溯法？？？
 * 题目：矩阵中的路径：上下左右一格，经过了某一格，路径不能再次进入
 * @author liyn
 *
 */
public class HasPath {
	private int row;
	private int col;
	private int[][] next = {{0, -1}, {0, 1}, {1, -1}, {1, 1}};
	public boolean hasPath(char[] matrics, int row, int col, char[] str) {
		this.col = col;
		this.row = row;
		char[][] charMatrics = new char[row][col];
		int idx= 0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				charMatrics[i][j] = matrics[idx++];
			}
		}

		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(backTrack(charMatrics, i, j ,0, new boolean[row][col], str)) {
					return true;
				}
			}
		}
		
		return false;
	}
	public boolean backTrack(char[][] matrics, int curR, int curC, int pathLength, boolean[][] marked, char[] str) {
		if(pathLength == str.length) {
			return true;
		}
		if(curR < 0 || curR >= row || curC <0 || curC>= col) {
			return false;
		}
		if(matrics[curR][curC] != str[pathLength]) {
			return false;
		}
		if(marked[curR][curC]) {
			return false;
		}
		marked[curR][curC] = true;
		for(int i=0; i<next.length; i++) {
			if(backTrack(matrics, curR+next[i][0], curC+next[i][1], pathLength + 1, marked, str)) {
				return true;
			}
		}
		marked[curR][curC] = false;
		return false;
	}

}
