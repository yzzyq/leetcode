package tree;
/*
236. Lowest Common Ancestor of a Binary Tree
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and 
q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of 
itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

就是求俩个节点的最低共同祖先


*/

public class TreeLowestCommonAncester {
    
	// 求P和Q的共同祖先，一个节点也可以是自己的祖先
	// 看根节点是否包含俩个节点
	
	// 这种写法很慢很慢。思想是对的，但是写法过于复杂了。
//	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//		if(root == null) return null;
//		boolean isOneContain = true;
//		
//		while(isOneContain) {
//			if(root == p || root == q) {
//				isOneContain = false;
//				break;
//			}
//			
//			boolean isLeft = searchNodeValue(root.left, p, q);
//			boolean isRight = searchNodeValue(root.right, p, q);
//			if(isLeft && isRight) {
//				isOneContain = false;
//			}else {
//				root = isLeft?root.left:root.right;
//			}
//		}
//		
//		return root;
//    }
//
//	private boolean searchNodeValue(TreeNode rootNode, TreeNode p, TreeNode q) {
//		if(rootNode == p || rootNode == q) {
//			return true;
//		}
//		
//		if(rootNode == null) {
//			return false;
//		}
//		
//		boolean left = searchNodeValue(rootNode.left, p, q);
//		boolean right = searchNodeValue(rootNode.right, p, q);
//		
//		if(left || right) {
//			return true;
//		}else {
//			return false;
//		}
//		
//	}
	
	
	// 思想和上面一样，都是看节点，只不过上面是查找,
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == p || root == q || root == null) return root;
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		//----------优化-------------------------------
		// 加上他反而变慢了，主要是为了返回如果不是p也不是q，也不是null，那肯定就是最小父节点，那么不用递归右节点了，不过反而慢了
		// if(left != null && left != p && left != q) return left;
		//-------------------------------
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if(left != null && right != null) return root;
		return left == null?right:left;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
