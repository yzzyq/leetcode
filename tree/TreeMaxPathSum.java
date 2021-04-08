package tree;
/*
124. Binary Tree Maximum Path Sum
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree 
along the parent-child connections. The path must contain at least one node and does not need to go through 
the root.

就是求最长路径

*/

public class TreeMaxPathSum {
	int max_path_sum = Integer.MIN_VALUE;
	
    public int maxPathSum(TreeNode root) {
    	searchMaxPathSum(root);
        return max_path_sum;
    }
	
    // 使用递归的思想来做，这里返回是路径的话。你只能选择其中一条路走
    // 但是在求最大路径的时候，就可以三个都加起来
    // 负数被省去了，那么左右子树就不会是负数，
	private int searchMaxPathSum(TreeNode root) {
		if(root == null) return 0;
		int left_sum = Math.max(searchMaxPathSum(root.left),0);
		int right_sum = Math.max(searchMaxPathSum(root.right),0);
		// 包含了左子树加节点最大，右子树加上节点最大，左右子树是最大的，左右子树相加外加节点是最大的
		max_path_sum = Math.max(left_sum + right_sum + root.val,max_path_sum);
		return Math.max(left_sum, right_sum) + root.val;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
