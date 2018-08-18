package coding;

/**
 * 数值的整数次方：写函数
 * 方法：a的N次方等于 a的N/2 * a的N/2 (*a)   用右移代替除以2
 * @author liyn
 *
 */
public class Power {

	public double power(double base, int exponent) {
		if(base == 0.0) {
			return 0;
		}
		if(exponent == 0) {
			return 1;
		}
		if(exponent == 1) {
			return base;
		}
		boolean flag = false;
		if(exponent < 0) {
			flag = true;
			exponent = -exponent;
		}
		double result = power(base , exponent>> 1);
		result *= result;
		if(exponent % 2 != 0) {
			result *= base;
		}
		if(flag) {
			result = 1.0/ result;
		}
		return result;
	}
}
