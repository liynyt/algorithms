package coding;

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
