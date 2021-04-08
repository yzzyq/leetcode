package tree;
import java.util.LinkedList;
import java.util.Queue;

/*
110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

就是判断是否为平衡二叉树

个人想法，如果小子树不平衡的话，那么大树一定不平衡。所以判断左右子树高度是否相同即可

*/

public class TreeBalancedBianry {
	
	// 速度很快，就是空间使用有点大了
    public boolean isBalanced(TreeNode root) {
    	if(maxDepth(root) == -1) {
    		return false;
    	}
    	return true;
    }

    // 深度的简化版
	private int maxDepth(TreeNode root) {
		if(root == null) return 0;
		
		int left_depth = maxDepth(root.left);
		int right_depth = maxDepth(root.right);
		
		if(left_depth == -1 || right_depth == -1 || Math.abs(left_depth - right_depth) > 1) {
			return -1;
		}
	
		return Math.max(left_depth + 1, right_depth + 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
