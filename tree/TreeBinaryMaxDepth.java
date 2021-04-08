package tree;
/*
104. Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to 
the farthest leaf node.

Note: A leaf is a node with no children.

*/


public class TreeBinaryMaxDepth {
    
	// ��������ܿ죬���ҿռ�Ҳ����
	public int maxDepth(TreeNode root) {
		// ����ڵ���ǿսڵ�
		if(root == null) return 0;
		
		if(root.left != null || root.right != null) {
			int left_depth = 1;
			int right_depth = 1;
			if(root.left != null) {
				left_depth = maxDepth(root.left) + 1;
			}
			
			if(root.right != null) {
				right_depth = maxDepth(root.right) + 1; 
			}
			
			return Math.max(left_depth, right_depth);
		}else {
			return 1;
		} 
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
