package coding;

/**
 * 题目：两个栈实现队列
 * 方法：stack1：插入  stack2：删除（空、非空）
 * @author liyn
 *
 */
public class Queue1 {
	private Stack stack1;
	private Stack stack2;
	private Node first;
	private Node last;
	
	private class Node{
		private int val;
		private Node next;
		public Node(int val) {
			this.val = val;
		}
	}

	public void push(int val) {
		stack1.push(val);
	}
	public int pop() {
		if(stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				int item = stack1.pop();
				stack2.push(item);
			}
		}
		return stack2.pop();
	}
}
