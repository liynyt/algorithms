package coding;

public class PrintMaxN {

	public void print(int n ) {
		if(n <= 0) {
			return;
		}
		char[] nums = new char[n];
		for(int i=0; i<nums.length; i++) {
			nums[i] = '0';
		}
//		while(increment(nums)) {
//			printNum(nums);
//		}
		incrementRecursive(nums, -1);
	}

	public void incrementRecursive(char[] nums, int idx) {
		if(idx == nums.length-1) {
			printNum(nums);
			return;
		}
		for(int i=0; i<10; i++) {
			nums[idx + 1] = (char)(i +'0');
			incrementRecursive(nums, idx+1);
		}
	}
	public boolean increment(char[] nums) {
		boolean flag = true;
		for(int i=nums.length-1; i>= 0; i--) {
			int num = nums[i] - '0';
			if(i == nums.length -1) {
				num ++;
			}
			if(num == 10) {
				if(i == 0) {
					flag = false;
				}
				else {
					num -= 10;
					nums[i] = (char)(num + '0');
					nums[i-1] =(char)(nums[i-1] + 1);
				}
			}
			else {
				nums[i] = (char)(num + '0');
				break;
			}
		}
		return flag;
	}
	public void printNum(char[] nums) {
		boolean isBegin0 = true;
		for(int i=0; i<nums.length ; i++) {
			if(isBegin0 && nums[i] != '0') {
				isBegin0 = false;
			}
			if(!isBegin0) {
				System.out.print(nums[i]);
			}
		}
		System.out.println();
	}
	public static void main(String[] args) {
		PrintMaxN printMaxN = new PrintMaxN();
		printMaxN.print(2);
	}

}
