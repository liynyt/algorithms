package coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkedList {
	private class Node {
		int item;
		Node next;
		Node sibling;
		
		public Node() {
			
		}
//		public Node(int val) {
//			item = val;
//		}
	}
	public Node getNode() {
		return new Node();
	}
	
	
	public List<Integer> printLink(Node node) {
		List<Integer> results = new ArrayList<>();
		Stack stack = new Stack();
		while (node.next != null) {
			stack.push(node.item);
			node = node.next;
		}
		while(!stack.isEmpty()) {
			results.add(stack.pop());
		}
		return results;
	}
	public List<Integer> print(Node node){
		List<Integer> results = new ArrayList<>();
		if(node != null) {
			results.addAll(print(node.next));
			results.add(node.item);
		}
		return results;
	}
	public List<Integer> printA(Node node){
		List<Integer> results = new ArrayList<>();
		while(node != null) {
			results.add(node.item);
			node = node.next;
		}
		Collections.reverse(results);
		return results;
	}
	public List<Integer> printAdd(Node node){
		List<Integer> results = new ArrayList<>();
		Node head= new Node();
		Node next = new Node();
		while(node != null) {
			next = node.next;
			node.next = head;
			head = node;
			node = next;
		}
		while(head != null) {
			results.add(head.item);
			head = head.next;
		}
		return results;
	}
	
	public void reverse(Node head) {
		Node pre = null;
		Node next = head;
		while(head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
	}
	
	public Node merge(Node node1, Node node2) {
		if(node1 == null ) {
			return node2;
		}
		if(node2 == null) {
			return node1;
		}
		Node head = new Node();
		if(node1.item < node2.item) {
			head = node1;
			node1 = node1.next;
		}
		else {
			head = node2;
			node2 = node2.next;
		}
		Node curNode = head;
		while(true) {
			if(node1 == null && node2 == null) {
				break;
			}
			if(node1 == null) {
				curNode.next = node2;
				node2 = node2.next;
			}
			else {
				if(node2 == null) {
					curNode.next = node1;
					node1 = node1.next;
				}
				else {
					if(node1.item < node2.item) {
						curNode.next= node1;
						node1 = node1.next;
					}
					else {
						curNode.next = node2;
						node2 = node2.next;
					}
				}
			}
			curNode = curNode.next;
		}
		return head;
	}
	
	public void deleteNode(Node head, Node node) {
		if(head == null || head.next == null || node == null) {
			return;
		}
		if(node.next == null) {
			Node next = head;
			while(next.next != node) {
				next = next.next;
			}
			next.next = null;
		}
		else {
			Node next = node.next;
			node.item= next.item;
			node.next = next.next;
		}
		
	}
	public void deleteDuplication(Node head) {
		if(head == null || head.next == null) {
			return;
		}
		Node curNode = head;
		Node preNode = head;
		while(curNode.next != null) {
			if(curNode.next.item != curNode.item) {
				preNode = curNode;
				curNode = curNode.next;
			}
			else {
				Node nextNode = curNode.next;
				while(nextNode.item == curNode.item) {
					nextNode = nextNode.next;
				}
				preNode.next = nextNode;
				curNode = nextNode;
			}
		}
	}
	public Node deletDuplication(Node head) {
		if(head == null) {
			return null;
		}
		if(head.next == null) {
			return head;
		}
		if(head.next.item == head.item) {
			while(head.next != null && head.next.item == head.item) {
				head = head.next;
			}
			return deletDuplication(head.next);
		}
		else {
			head.next = deletDuplication(head.next);
			return head;
		}
	}

	
	public Node lastKnumber(Node head, int k) {
		Node one = head;
		Node two = head;
//		for(int i=0; i< k-1; i++) {
//			if(one.next == null) {
//				return null;
//			}
//			one = one.next;
//		}
//		if(one.next == null) {
//			return null;
//		}
		while(one != null && k-->0) {
			one= one.next;
		}
		if(k > 0) {
			return null;
		}
		while(one != null) {
			one = one.next;
			two = two.next;
		}
		return two;
	}
	
	public Node getEntry(Node head) {
		if(head == null || head.next == null) {
			return null;
		}
		Node fast = head;
		Node slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow) {
				fast = head;
				while(fast != slow) {
					fast = fast.next;
					slow = slow.next;
				}
				return fast;
			}
		}
		return null;
	}
	
	public Node clone(Node head) {
		Node node = head;
		while(node != null) {
			Node clone = new Node();
			clone.next = node.next;
			clone.item = node.item;
			clone.sibling = null;
			node.next = clone;
			node = clone.next;
		}
		node  = head;
		while(node != null) {
			Node clone = node.next;
			if(node.sibling != null) {
				clone.sibling = node.sibling;
			}
			node = clone.next;
		}
		Node head1 = head.next;
		node = head;
		while(node.next != null) {
			Node next = node.next;
			node.next = next.next;
			node = next;
		}
		return head1;
	}

	public static void main(String[] arg0) {
		LinkedList linkedList = new LinkedList();
		LinkedList.Node head = linkedList.getNode();
		head.item = 1;
		LinkedList.Node two = linkedList.getNode();
		two.item = 3;
		head.next = two;
		LinkedList.Node three = linkedList.getNode();
		three.item = 5;
		two.next = three;
//		LinkedList.Node threee1 = linkedList.getNode();
//		threee1.item = 7;
//		three.next = threee1;
		LinkedList.Node four = linkedList.getNode();
		four.item = 5;
		three.next =four;
		LinkedList.Node node = head;
		while(node != null) {
			System.out.println(node.item);
			node = node.next;
		}
		LinkedList.Node head2 = linkedList.getNode();
		head2.item = 4;
		LinkedList.Node two2 = linkedList.getNode();
		two2.item = 4;
		head2.next = two2;
		LinkedList.Node three2 = linkedList.getNode();
		three2.item = 6;
		two2.next = three2;
		LinkedList.Node four2 = linkedList.getNode();
		four2.item = 8;
		three2.next =four2;
//		linkedList.deleteDuplication(head);
//		linkedList.deletDuplication(head);
//		node = head;
//		while(node != null) {
//			System.out.println(node.item);
//			node = node.next;
//		}
		System.out.println();
		LinkedList.Node node2 = head2;
		while(node2 != null) {
			System.out.println(node2.item);
			node2 = node2.next;
		}
//		System.out.println(linkedList.lastKnumber(head, 4).item);
//		linkedList.reverse(head);
//		node = four;
//		while(node != null) {
//			System.out.println(node.item);
//			node = node.next;
//		}
		node2 =linkedList.merge(head, head2);
		while(node2 != null) {
			System.out.println(node2.item);
			node2 = node2.next;
		}
	}
}
