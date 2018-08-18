package coding;


public class MinInRotated {
	public int getMin(int[] nums) {
		int i = 0;
		int j = nums.length-1;
		//when the array is rotated 0 elements,i.e. the origin array, output the first number
		int mid = 0;
		//judge whether rotated or not
		while(nums[i] >= nums[j]) {
			//when the distance <= 1, the cycle is stop
			if(j - i <= 1) {
				return nums[j];
			}
			mid = i + (j-i)/2;
			//when three numbers all equal, find in orders 
			if(nums[i] == nums[mid] && nums[mid] == nums[j]) {
				return getMinInOrder(nums, i, j);
			}
			if(nums[mid] >= nums[i]) {
				i = mid;
			}
			else {
				if(nums[mid] <= nums[j]) {
					j = mid;
				}
			}
		}
		return nums[mid];
//		while(j - i> 1) {
//			mid = i + (j-i)/2;
//			if(nums[mid] >= nums[i]) {
//				i = mid;
//			}
//			else {
//				if(nums[mid] <= nums[j]) {
//					j = mid;
//				}
//			}
//		}
//		return nums[mid];
	}

	private int getMinInOrder(int[] nums, int lo, int hi) {
		int reslut = nums[lo];
		for(int i=lo+1; i<hi; i++) {
			if(nums[i] < reslut) {
				reslut = nums[i];
			}
		}
		return reslut;
	}

	public static void main(String[] args) {
//		int[] nums = {3, 4, 5, 1, 2};
		int[] nums = {1, 2, 3};
		MinInRotated minInRotated = new MinInRotated();
		System.out.println(minInRotated.getMin(nums));
	}
}
