package coding;

/**
 * 二进制中1的个数
 * 1、与1左移后进行与运算
 * 
 */
public class NumberOf1 {

	/**
	 * 2、将最右边的1变为0（n = n &（n-1）），直到所有都为0
	 * @param num
	 * @return
	  * 扩展： 1. 2的整数次方：二进制中有且只有一位1，即判断n &（n-1）是否为0；
	  *       2. m->n改变围巾之中的几位：异或->异或之后有几个1.
	 */
	public int getNumber(int num) {
		int count = 0;
		while(num != 0) {
			count++;
			num = num & (num-1);
		}
		return count;
	}
}
