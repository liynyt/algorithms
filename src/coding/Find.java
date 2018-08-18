package coding;

public class Find {

	/**
	 * 题目：二维数组中的查找
	 * 方法：左下 or 右上 缩小查找范围
	 * @author liyn
	 *
	 */
	public boolean find(int[][] nums, int num) {
		int i = 0;
		int j = nums[0].length - 1;
		while(i < nums.length && j >= 0) {
			if(num == nums[i][j]) {
				return true;
			}
			else {
				if(num > nums[i][j]) {
					i++;
				}
				else {
					j--;
				}
			}
		}
		return false;
	}
}

