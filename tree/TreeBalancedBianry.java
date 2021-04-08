package tree;
import java.util.LinkedList;
import java.util.Queue;

/*
110. Balanced Binary Tree

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

�����ж��Ƿ�Ϊƽ�������

�����뷨�����С������ƽ��Ļ�����ô����һ����ƽ�⡣�����ж����������߶��Ƿ���ͬ����

*/

public class TreeBalancedBianry {
	
	// �ٶȺܿ죬���ǿռ�ʹ���е����
    public boolean isBalanced(TreeNode root) {
    	if(maxDepth(root) == -1) {
    		return false;
    	}
    	return true;
    }

    // ��ȵļ򻯰�
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
