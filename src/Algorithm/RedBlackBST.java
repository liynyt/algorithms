package Algorithm;

public class RedBlackBST <Key extends Comparable<Key>, Value>{
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private class Node {
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private boolean color;
		private int N;
		public Node(Key key, Value value, int N, boolean color) {
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color ;
		}
	}
	public int size() {
		return size(root);
	}
	public int size(Node node) {
		return node.N;
	}
	public boolean isRed(Node node) {
		if(node == null) {
			return false;
		}
		return node.color == RED;
	}
	public Node rotateLeft(Node node) {
		Node node2 = node.right;
		node.right = node2.left;
		node2.left = node;
		node2.color = node.color;
		node.color = RED;
		node2.N = node.N;
		node.N = size(node.left) + size(node.right) + 1;
		return node2;
	}
	public Node rotateRight(Node node) {
		Node node2 = node.left;
		node.left = node2.right;
		node2.right = node;
		node2.color = node.color;
		node.color = RED;
		node2.N = node.N;
		node.N = size(node.left) + size(node.right) + 1;
		return node2;
	}
	public void flipColor(Node node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node node, Key key) {
		while(node != null) {
			int cmp = key.compareTo(node.key);
			if(cmp < 0) {
				node= node.left;
			}
			else {
				if(cmp >0 ) {
					node =node.right;
				}
				else {
					return node.value;
				}
			}
		}
		return null;
	}
	public void put(Key key, Value value ) {
		root = put(root, key , value);
		root.color = BLACK ;
	}
	private Node put(Node node , Key key , Value value) {
		if(node == null) {
			return new Node(key, value, 1, RED);
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			node.left= put(node.left, key, value);
		}
		else {
			if(cmp >0 ) {
				node.right = put( node.right, key, value);
			}
			else {
				node.value = value;
			}
		}
		
		//中间：左旋
		if(isRed(node.right) && !isRed(node.left)) {
			node = rotateLeft(node);
		}
		//左，最小：右旋
		if(isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		//右，最大：颜色转换
		if(isRed(node.left) && isRed(node.right)) {
			flipColor(node);
		}
		node.N = size(node.left) + size(node.right) +1;
		return node;
	}
	public Key floor(Key key) {
		Node  node = floor(root, key);
		if(node == null) {
			return null;
		}
		return node.key;
	}
	private Node floor(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			return floor(node.left, key);
		}
		if(cmp == 0) {
			return node;
		}
		Node node2 = floor(node.right, key);
		if(node2 != null) {
			return node2;
		}
		else {
			return node;
		}
	}
	public Key ceiling(Key key) {
		Node node  = ceiling(root, key);
		if(node == null) {
			return null;
		}
		return node.key;
	}
	private Node ceiling(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp == 0) {
			return node;
		}
		if(cmp > 0) {
			return ceiling(node.right, key);
		}
		Node node2 = floor(node.left, key);
		if(node2 != null) {
			return node2;
		}
		else {
			return node;
		}
	}
	public void delMin() {
		
	}

}
