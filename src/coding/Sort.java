package coding;

public class Sort {

	public void QuickSort(int[] nums, int start, int end) {
		if(start <= end) {
			return;
		}
		int index = partition(nums, start, end);
		QuickSort(nums, start, index-1);
		QuickSort(nums, index+1, end);
	}

	private int partition(int[] nums, int start, int end) {
		int i = start;
		int j = end + 1;
		int v = nums[0] ;
		while(true) {
			while(nums[++i] < v) {
				if(i == end) {
					break;
				}
			}
			while(nums[--j] > v) {
				if(j == start) {
					break;
				}
			}
			if(i >= j) {
				break;
			}
			swap(nums, i, j);
		}
		swap(nums, start, j);
		return j;
	}
	private void swap(int nums[], int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
