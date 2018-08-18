package coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class BinaryTree {
	private class TreeNode{
		private int val;
		private TreeNode left, right;
		private TreeNode father;
		public TreeNode(int val) {
			this.val = val;
		}
		public TreeNode() {
			
		}
	}
	public TreeNode getTreeNode() {
		return new TreeNode();
	}
	public TreeNode reconstructTree(int[] pre, int[] in) {
		 return reconstructTree(pre,0,pre.length-1, in, 0, in.length-1);
	}
	private TreeNode reconstructTree(int[] pre, int preL, int preR, int[] in, int inL, int inR) {
		if(preL == preR) {
			return new TreeNode(pre[preL]);
		}
		if(preL > preR) {
			return null;
		}
		if(inL > inR) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preL]);
		int index = inL;
		while(in[index] != pre[preL]) {
			index++;
		}
		int leftSize = index-inL;
		root.left = reconstructTree(pre, preL+1, preL+leftSize, in, inL, inL + leftSize -1);
		root.right = reconstructTree(pre, preL+leftSize + 1, preR, in, index+1, inR);
		return root;
	}

	public TreeNode getMidNext(TreeNode treeNode) {
		if(treeNode.right != null) {
			treeNode = treeNode.right;
			while(treeNode.left != null) {
				treeNode = treeNode.left;
			}
			return treeNode;
		}
		else {
			while(treeNode.father != null) {
				if(treeNode.father.left == treeNode) {
					return treeNode.father;
				}
				treeNode = treeNode.father;
			}
		}
		return null;
	}
	
	public boolean hasSubTree(TreeNode root1, TreeNode root2) {
		boolean flag = false;
		if(root1 != null && root2 != null) {
			if(root1.val == root2.val) {
				flag = isSubTree(root1, root2);
			}
			if (!flag) {
				flag = hasSubTree(root1.left, root2);
			}
			if(!flag) {
				flag = hasSubTree(root1.right, root2);
			}
		}
		return flag;
	}
	public boolean isSubTree(TreeNode root1, TreeNode root2) {
		if(root1== null && root2 == null) {
			return true;
		}
		if(root2 == null) {
			return true;
		}
		if(root1 == null) {
			return false;
		}
		if(root1.val != root2.val) {
			return false;
		}
		return isSubTree(root1.left, root2) && isSubTree(root1.right, root2);
	}
	
//	public void mirror(TreeNode root) {
//		if(root == null) {
//			return;
//		}
//		TreeNode temp = root.left;
//		root.left = root.right;
//		root.right = temp;
//		mirror(root.left);
//		mirror(root.right);
//	}
	public TreeNode mirror(TreeNode root) {
		if(root == null) {
			return null;
		}
		TreeNode temp = root.left;
		root.left = mirror(root.right);
		root.right = mirror(temp);
		return root;
	}
	
	public boolean isSymmetrical(TreeNode root) {
		if(root == null) {
			return true;
		}
		return isSymmetrical(root.left, root.right);
	}
	private boolean isSymmetrical(TreeNode left, TreeNode right) {
		if(left == null && right == null) {
			return true;
		}
		if(left == null) {
			return false;
		}
		if(right == null) {
			return false;
		}
		if(left.val != right.val) {
			return false;
		}
		return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
	}
	
	private boolean isBalanced = true;
	public boolean isBalance(TreeNode root) {
		height(root);
		return isBalanced;
	}
	private int height(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int heightL = height(root.left);
		int heightR = height(root.right);
		if(Math.abs(heightR - heightL) > 1) {
			isBalanced = false;
		}
		return 1+ Math.max(heightR, heightL);
	}
	
	public List<Integer> printFromTopToBottom(TreeNode root){
		List<Integer> results = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if(root == null) {
			return results;
		}
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if(node != null) {
				results.add(node.val);
				queue.offer(node.left);
				queue.offer(node.right);
			}
		}
		return results;
	}
	public List<List<Integer>> printAsRow(TreeNode root){
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> results = new ArrayList<>();
		if(root == null) {
			return results;
		}
		queue.offer(root);
		while(!queue.isEmpty()) {
			int count = queue.size();
			List<Integer> list = new ArrayList<>();
			for(int i=0; i<count; i++) {
				TreeNode node = queue.poll();
				if(node != null) {
//					System.out.print(node.val);
					list.add(node.val);
					queue.offer(node.left);
					queue.offer(node.right);
				}
			}
			results.add(list);
//			System.out.println();
		}
		return results;
	}
	public List<List<Integer>> printAs(TreeNode root){
		List<List<Integer>> results = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if(root == null) {
			return results;
		}
		queue.offer(root);
		while(!queue.isEmpty()) {
			int count = queue.size();
			List<Integer> list = new ArrayList<>();
			for(int i=0; i<count; i++) {
				TreeNode node = queue.poll();
				if(node != null) {
					list.add(node.val);
					queue.offer(node.left);
					queue.offer(node.right);
				}
			}
			if(results.size() %2 !=0) {
				Collections.reverse(list);
			}
			results.add(list);
		}
		return results;
	}
	
	public boolean verifySequenceOfBST(int[] sequence) {
		if(sequence.length == 0) {
			return false;
		}
		return verifySequenceOfBST(sequence, 0, sequence.length-1);
	}
	private boolean verifySequenceOfBST(int[] sequence, int begin, int end) {
		int length = end - begin +1;
		if(length <= 1) {
			return true;
		}
		int root = sequence[end];
		int leftSize = 0;
		int rightSize = 0;
		for(int i=begin; i<=end ; i++) {
			if(sequence[i] < root) {
				leftSize ++;
			}
			else {
				break;
			}
		}
		rightSize = length - 1 -leftSize;
		for(int i = 0; i<rightSize; i++) {
			if(sequence[i+begin+leftSize] < root) {
				return false;
			}
		}
		return verifySequenceOfBST(sequence, begin, begin + leftSize-1) && verifySequenceOfBST(sequence, begin + leftSize, end-1);
	}
	
	private List<List<Integer>> results = new ArrayList<>();
	public List<List<Integer>> findPath(TreeNode root, int value){
		findPath(root, value, 0, new ArrayList<>());
		return results;
	}
	private void findPath(TreeNode root, int value,int number, List<Integer> path){
		if(root == null) {
			return;
		}
		number += root.val;
		path.add(root.val);
		if(root.left == null && root.right == null && number == value) {
				results.add(new ArrayList<>(path));
		}
		else {
			findPath(root.left, value, number, path);
			findPath(root.right, value, number, path);
		}
		path.remove(path.size() - 1);
	}
	
	private TreeNode pre = new TreeNode();
	public TreeNode convert(TreeNode root) {
		if(root == null) {
			return null;
		}
		inOrder(root);
		while(root.left != null) {
			root = root.left;
		}
		return root;
	}
	private void inOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		inOrder(root.left);
		root.left = pre;
		if(pre != null) {
			pre.right = root;
		}
		pre = root;
		inOrder(root.right);
	}
	
	public void serialize(TreeNode root, List<String> nums) {
		if(root == null) {
			nums.add("$");
			return;
		}
		nums.add(String.valueOf(root.val));
		serialize(root.left, nums);
		serialize(root.right, nums);
	}
	public TreeNode deserialize(List<String> nums) {
		String string = nums.remove(0);
		if(string == "$") {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(string));
		root.left = deserialize( nums);
		root.right = deserialize( nums);
		return root;
	}
	
	
	
	public void print(TreeNode root) {
		if(root == null) {
			return;
		}
		System.out.println(root.val);
		print(root.left);
		print(root.right);
	}
	public static void main(String[] arg0) {
		BinaryTree binaryTree = new BinaryTree();
		BinaryTree.TreeNode root = binaryTree.getTreeNode();
		root.val = 8;
		BinaryTree.TreeNode left1 = binaryTree.getTreeNode();
		left1.val = 10;
		root.left = left1;
		BinaryTree.TreeNode right1 = binaryTree.getTreeNode();
		right1.val = 6;
		root.right = right1;
		BinaryTree.TreeNode left12 = binaryTree.getTreeNode();
		left12.val = 5;
		left1.left = left12;
		BinaryTree.TreeNode right12 = binaryTree.getTreeNode();
		right12.val = 7;
		left1.right = right12;
		BinaryTree.TreeNode left22 = binaryTree.getTreeNode();
		left22.val = 9;
		right1.left = left22;
		BinaryTree.TreeNode right22 = binaryTree.getTreeNode();
		right22.val = 11;
		right1.right = right22;
		binaryTree.print(root);
//		TreeNode treeNode = root;
//		while(treeNode != null) {
//			System.out.println(treeNode.val);
//		}
		
//		binaryTree.mirror(root);
		System.out.println();
//		binaryTree.print(root);
		
//		System.out.println(binaryTree.isSymmetrical(root));
//		List<Integer> nodes = binaryTree.printFromTopToBottom(root);
//		List<Integer> nodes = binaryTree.printAsRow(root);
//		List<List<Integer>> nodes = binaryTree.printAs(root);
//		for(List<Integer> list:nodes) {
//			for(int val : list) {
//				System.out.print(val);
//			}
//			System.out.println();
//		}
//		int[] sequence = {9,6,9,11,10,8};
//		System.out.println(binaryTree.verifySequenceOfBST(sequence));
		
//		List<List<Integer>> nodes = binaryTree.findPath(root, 23);
//		for(List<Integer> list:nodes) {
//			for(int val : list) {
//				System.out.print(val);
//			}
//			System.out.println();
//		}
		
		List<String> strings = new ArrayList<>();
		binaryTree.serialize(root, strings);
		for(String string: strings) {
			System.out.println(string);
		}
		BinaryTree.TreeNode root1 = binaryTree.deserialize(strings);
		
		binaryTree.print(root1);
	}
}
