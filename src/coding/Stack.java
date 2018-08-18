package coding;


public class Stack {
	private class Node {
		int item;
		Node next;
	}
	private Node first;
	private int N;
	
	public boolean isEmpty() {
		return N==0;
	}
	public int size() {
		return N;
	}
	public void push(int item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	public int pop() {
		int item = first.item;
		first = first.next;
		N--;
		return item;
	}
	public int peek() {
		if(first == null) {
			return -1;
		}
		return first.item;
	}

	public boolean isPop(int[] push, int[] pop) {
		if(push == null) {
			return false;
		}
		if(pop == null) {
			return true;
		}
		Stack stack = new Stack();
		int j=0;
		for(int i=0; i<pop.length; i++) {
			while(stack.peek() != pop[i]) {
				if(j == push.length) {
					break;
				}
				stack.push(push[j++]);
			}
			if(stack.pop() != pop[i]) {
				return false;
			}
		}
		return true;
	}
	public boolean isPopOrder(int[] push, int[] pop) {
		Stack stack = new Stack();
		for(int i=0, j=0; i<push.length; i++) {
			stack.push(push[i]);
			while( j <push.length && stack.peek() == pop[j]) {
				stack.pop();
				j++;
			}
		}
		return stack.isEmpty(); 
	}
	
	public static void main(String[] arg0) {
		int[] push = {1, 2, 3, 4, 5};
		int[] pop = {5, 4, 3, 2};
		Stack stack = new Stack();
		System.out.println(stack.isPop(push, pop));
	}
}
