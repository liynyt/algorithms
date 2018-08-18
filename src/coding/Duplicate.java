package coding;

/**
 * 找出数组中重复数字
 * @author liyn
 *
 */
public class Duplicate {
	/**
	 * 题目：长度为n的数组里有0-n-1范围内的数字，找出任意一个重复的数字
	 * 方法：将i换到第i个位置上
	 * @author liyn
	 *
	 */
	//find duplicate directly
	public boolean getDuplicate(int[] nums, int[] duplicate) {
		int length = nums.length;
		for(int i=0;i<length; i++) {
			//move the ith number into the i 
			while(i != nums[i] && nums[i] != nums[nums[i]]) {
				int temp = nums[i];
				nums[i] = nums[nums[i]];
				nums[nums[i]] = temp;
			}
			//judge the duplicate
			if(nums[i] != i && nums[i] == nums[nums[i]]) {
				duplicate[0] = nums[i];
				return true;
			}
		}
		return false;
	}

	/**
	 * 题目：不改变数组找出重复数字
	 * 方法：根据范围内个数与范围大小循环（二分）
	 * @author liyn
	 *
	 */
	//find duplicate without changing order of array
	//calculate the number in range
	public int getDuplicate(int[] nums, int length) {
		int begin=1;
		int end = length-1;
		while(begin <= end) {
			int mid = begin + (end-begin)/2;
			int count=0;
			for(int i=0; i<length; i++) {
				if(nums[i] >= begin && nums[i] <= mid) {
					count++;
				}
			}
			if(begin == end) {
				if(count>1) {
					return begin;
				}
				else
					break;
			}
			if(count > (mid - begin + 1)) {
				end = mid;
			}
			else {
				begin = mid +1;
			}
		}
		return -1;
	}
}
