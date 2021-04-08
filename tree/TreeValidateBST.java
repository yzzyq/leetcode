package tree;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/*
98. Validate Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

判断是不是二叉排序树

*/

public class TreeValidateBST {
    	
	// 1. 找出左子树最小值和右子树的最大值来比较,分治法，但是复杂度太高了
//	public boolean isValidBST(TreeNode root) {
//		
//		if(root == null) return true;
//		
//        if((root.left != null && root.val <= findMax(root.left)) || 
//        		(root.right != null && root.val >= findMin(root.right))) {
//      	  return false;
//        }
//        boolean isLeft = isValidBST(root.left);
//        boolean isRight = isValidBST(root.right);
//        return isLeft&&isRight;
//	}
//
//	private int findMax(TreeNode node) {
//        int max_value = node.val; 
//        Queue<TreeNode> tree_queue = new LinkedList<TreeNode>();
//        tree_queue.offer(node);
//        while(!tree_queue.isEmpty()) {
//			TreeNode one_node = tree_queue.poll();
//			if(max_value < one_node.val) max_value = one_node.val;
//			if (one_node.left != null)
//				tree_queue.offer(one_node.left);
//			if (one_node.right != null)
//				tree_queue.offer(one_node.right);
//        }
//        return max_value;
//	}
//
//	private int findMin(TreeNode node) {
//		int min_value = node.val; 
//        Queue<TreeNode> tree_queue = new LinkedList<TreeNode>();
//        tree_queue.offer(node);
//        while(!tree_queue.isEmpty()) {
//			TreeNode one_node = tree_queue.poll();
//			if(min_value > one_node.val) min_value = one_node.val;
//			if (one_node.left != null)
//				tree_queue.offer(one_node.left);
//			if (one_node.right != null)
//				tree_queue.offer(one_node.right);
//        }
//        return min_value;
//	}
	
	// 2. 上面有简化版，不过思想变了一下，就是根节点是左子树的最大值，右子树的最小值，这个效率高
//	public boolean isValidBST(TreeNode root) {
//		
//		// 这是因为输入可能是，[2147483647]
//	    if(root == null) return true;
//
//        return validBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
//    }
//
//	private boolean validBst(TreeNode root, long min_value, long max_value) {
//		if(root == null) return true;
//		if(root.val <= min_value || root.val >= max_value) return false;
//		return validBst(root.left, min_value, root.val) && validBst(root.right, root.val, max_value);
//	}
	
	
	// 3. 利用中序遍历来做,这个使用了递归，速度还是有点慢的
//    public boolean isValidBST(TreeNode root) {
//    	List<Integer> list_value = new ArrayList<Integer>();
//    	TraversalTree(root,list_value);
//    	for(int i = 0; i < list_value.size()-1; i++) {
//    		if(list_value.get(i) >= list_value.get(i+1)) {
//    			return false;
//    		}
//    	}
//    	return true;
//    }
//
//	private void TraversalTree(TreeNode root, List<Integer> list_value) {
//		if(root == null) return;
//		TraversalTree(root.left, list_value);
//		list_value.add(root.val);
//		TraversalTree(root.right, list_value);
//	}
	
	// 4. 也可以使用非递归中序遍历，这样的话，还可以直接比较是不是比前面大，不用存储
//	public boolean isValidBST(TreeNode root) {
//		Stack<TreeNode> stack_node = new Stack<TreeNode>();
//    	TreeNode pre_node = null;
//    	
//    	while(root != null || !stack_node.empty()) {
//    		while(root != null) {
//    			stack_node.push(root);
//    			root = root.left;
//    		}
//    		root = stack_node.pop();
//    		if(pre_node != null && pre_node.val >= root.val) return false;
//    		pre_node = root;
//    		root = root.right;
//    	}
//    	return true;
//    }
    
	// 5. 还可以使用morris遍历
	public boolean isValidBST(TreeNode root) {
		TreeNode preNode = null;
		while(root != null) {
			if(root.left == null) {
				if(preNode != null && preNode.val >= root.val) {
					return false;
				}
				preNode = root;
				root = root.right;
			}else {
				TreeNode rightNode = root.left;
				while(rightNode.right != null && rightNode.right != root) {
					rightNode = rightNode.right;
				}
				
				if(rightNode.right == null) {
					rightNode.right = root;
					root = root.left;
					
				}else {
					if(preNode != null && preNode.val >= root.val) {
						return false;
					}
					preNode = root;
					rightNode.right = null;
					root = root.right;
				}
				
			}
		}
		return true;
    }
    
	

}
