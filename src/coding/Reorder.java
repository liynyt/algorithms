package coding;

public class Reorder {

	public void reorder(int[] nums) {
		if(nums.length == 0) {
			return;
		}
		int i = 0;
		int j = nums.length-1;
		while( true ) {
			while(nums[i] % 2 == 1) {
				i++;
				if(i == nums.length) {
					break;
				}
			}
			while(nums[j] % 2 == 0) {
				j--;
				if(j == 0) {
					break;
				}
			}
			if(i >= j) {
				break;
			}
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}
	public void reoderAsOreder(int[] nums) {
		for(int i=0; i<nums.length; i++) {
			if(nums[i] % 2 ==0) {
				int j = i+1;
				if(j == nums.length) {
					return;
				}
				while(nums[j] %2 == 0) {
					j++;
					if(j == nums.length) {
						return;
					}
				}
				int temp = nums[j];
				for(int k = j; k>i; k--) {
					nums[k] = nums[k -1];
				}
				nums[ i ] = temp;
			}
		}
	}
	public static void main(String[] arg0) {
		int[] nums = {1, 2, 5, 4, 3};
		Reorder reorder = new Reorder();
//		reorder.reorder(nums);
		reorder.reoderAsOreder(nums);
		for(int i=0; i<nums.length; i++) {
			System.out.println(nums[i]);
		}
		
	}
}
