package Algorithm;

public class BST <Key extends Comparable<Key>, Value>{
	private Node root;
	
	private class Node{
		private Key key;
		private Value value;
		private Node left;
		private Node right;
		private int N;
		public Node(Key key, Value value, int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	public int size() {
		return size(root);
	}
	public int size(Node node) {
		if(node == null) {
			return 0;
		}
		return node.N;
	}
	public Value get(Key key) {
		return get(root, key);
	}
	public Value get(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp = node.key.compareTo(key);
		if(cmp < 0) {
			return get(node.right, key);
		}
		else {
			if (cmp > 0) {
				return get(node.left, key);
			}
			else {
				return node.value;
			}
		}
	}
	public void put(Key key, Value value) {
		put(root, key, value);
	}
	public Node put(Node node, Key key, Value value) {
		if(node == null) {
			return new Node(key, value , 1);
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			node.left = put(node.left, key, value);
		}
		else {
			if(cmp > 0) {
				node.right = put(node.right, key, value);
			}
			else {
				node.value = value;
			}
		}
		node.N = size(node.left) + size(node.right) +1;
		return node;
	}
	/**
	 * 只有当右子树中存在 <= key,才会出现在右子树中
	 * @param key
	 * @return
	 */
	public Key floor(Key key) {
		Node node = floor(root, key);
		if(node == null) {
			return null;
		}
		return node.key;
	}
	public Key min() {
		return min(root).key;
	}
	private Node min(Node node) {
		if(node.left == null) {
			return node;
		}
		return min(node.left);
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
	
	public Key select(int k) {
		return select(root, k).key;
	}
	private Node select(Node node , int k) {
		int t = size(node.left);
		if(t==k) {
			return node;
		}
		else {
			if(t>k) {
				return select(node.left, k);
			}
			else {
				return select(node.right, k-t-1);
			}
		}
	}
	public int rank(Key key) {
		return rank(root, key);
	}
	private int rank(Node node, Key key) {
		if(node == null) {
			return 0;
		}
		int cmp = key.compareTo(node.key);
		if(cmp< 0) {
			return rank(node.left , key);
		}
		else {
			if(cmp > 0) {
				return 1+size(node.left) + rank(node.right,  key);
			}
			else {
				return size(node.left);
			}
		}
	}
	/**
	 * 递归：持续跟进左结点，直到左结点为空，返回右
	 */
	public void delMin() {
		delMin(root);
	}
	private Node delMin(Node node ) {
		if(node.left == null) {
			return node.right;
		}
		node.left = delMin(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	/**
	 * 保存被删除连接->后继节点min(t.right)->右=delMin(t.right)把后继补在node处->左 = t.left
	 * @param key
	 */
	public void delete(Key key) {
		delete(root, key);
	}
	private Node delete(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			node.left = delete(node.left,key);
		}
		else {
			if(cmp > 0) {
				node.right = delete(node.right, key);
			}
			else {
				if(node.left == null) {
					return node.left;
				}
				if(node.right == null) {
					return node.right;
				}
				Node temp = node;
				node = min(temp.right);
				node.right = delMin(temp.right);
				node.left = temp.left;
			}
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}

}
