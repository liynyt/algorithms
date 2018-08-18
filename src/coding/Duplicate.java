package coding;


public class Duplicate {
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
