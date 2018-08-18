package coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺时针打印矩阵
 * 注意边界和条件
 * @author liyn
 *
 */
public class PrintMatrix {

	public void printClockWisely(int[][] nums) {
		int row = nums.length;
		int col = nums[0].length;
//		for(int i = 0; i<row; i++) {
		int i=0;
		while(i*2 <row && i*2 <col) {
			int r = i;
			int c = i;
			while(c < col-i) {
				System.out.println(nums[r][c++]);
			}
			if(i< row-1-i) {
				while(r < row - i -1) {
					System.out.println(nums[++r][c-1]);
				}
			}
			c = c-1;
			if(i <col -1- i && i<row -1-i) {
				while(c > i) {
					System.out.println(nums[r][--c]);
				}
			}
			if(i <col -1- i && i<row -1-i-1) {
				while(r > i+1) {
					System.out.println(nums[--r][c]);
				}
			}
			i++;
		}
	}
	public List<Integer> printMatrix(int[][] nums){
		List<Integer> results = new ArrayList<>();
		int r1 = 0;
		int r2 = nums.length-1;
		int c1 = 0;
		int c2 = nums[0].length-1;
		while(r1 <= r2 && c1 <= c2) {
			for(int i = c1; i <= c2; i++) {
				results.add(nums[r1][i]);
			}
			for(int i = r1+1;i<= r2; i++ ) {
				results.add(nums[i][c2]);
			}
			if(r1 != r2) {
				for(int i = c2-1; i >= c1; i--) {
					results.add(nums[r2][i]);
				}
			}
			if(c1 != c2) {
				for(int i = r2-1; i > r1; i--) {
					results.add(nums[i][c1]);
				}
			}
			r1++;
			r2--;
			c1++;
			c2--;
		}
		return results;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},{13, 14, 15, 16}};
		int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8},};
		PrintMatrix printMatrix = new PrintMatrix();
//		printMatrix.printClockWisely(nums);
		List<Integer> list = printMatrix.printMatrix(nums);
		for(int i:list) {
			System.out.println(i);
		}
	}

}
