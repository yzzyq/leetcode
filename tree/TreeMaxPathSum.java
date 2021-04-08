package tree;
/*
124. Binary Tree Maximum Path Sum
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree 
along the parent-child connections. The path must contain at least one node and does not need to go through 
the root.

�������·��

*/

public class TreeMaxPathSum {
	int max_path_sum = Integer.MIN_VALUE;
	
    public int maxPathSum(TreeNode root) {
    	searchMaxPathSum(root);
        return max_path_sum;
    }
	
    // ʹ�õݹ��˼�����������ﷵ����·���Ļ�����ֻ��ѡ������һ��·��
    // �����������·����ʱ�򣬾Ϳ���������������
    // ������ʡȥ�ˣ���ô���������Ͳ����Ǹ�����
	private int searchMaxPathSum(TreeNode root) {
		if(root == null) return 0;
		int left_sum = Math.max(searchMaxPathSum(root.left),0);
		int right_sum = Math.max(searchMaxPathSum(root.right),0);
		// �������������ӽڵ�������������Ͻڵ�����������������ģ��������������ӽڵ�������
		max_path_sum = Math.max(left_sum + right_sum + root.val,max_path_sum);
		return Math.max(left_sum, right_sum) + root.val;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
