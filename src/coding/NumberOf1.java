package coding;

/**
 * ��������1�ĸ���
 * 1����1���ƺ����������
 * 
 */
public class NumberOf1 {

	/**
	 * 2�������ұߵ�1��Ϊ0��n = n &��n-1������ֱ�����ж�Ϊ0
	 * @param num
	 * @return
	  * ��չ�� 1. 2�������η���������������ֻ��һλ1�����ж�n &��n-1���Ƿ�Ϊ0��
	  *       2. m->n�ı�Χ��֮�еļ�λ�����->���֮���м���1.
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
