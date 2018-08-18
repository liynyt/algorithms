import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RemoveKDigits {

	public String removeKdigits(String num, int k) {
        if(num.length() < k) {
        	System.out.println("The length of num " + num.length() + "is less than " + k);
        	return null;
        }
        if(num.indexOf(0) == 0) {
        	System.out.println("The num " + Integer.parseInt(num) + "is out of principle " );
        	return null;
        }
        char[] nums = num.toCharArray();
        List<Integer> remains = new ArrayList<>();
        Stack<Integer> numbers = new Stack<>();
        for(int i=0; i<nums.length; i++) {
        	numbers.push((int)nums[i]);
        }
        int index = 0;
        for(int i=0; i<numbers.size(); i++) {
        	int j=i;
        	if(numbers.size() - j >= k) {
        		for(;j< numbers.size(); j++) {
        			
        		}
        	}
        }
        
        
		return num;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
