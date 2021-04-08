package recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 95. Unique Binary Search Trees II
 * 
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store 
 * values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 

Constraints:

0 <= n <= 8
 * 
 * @author yzzyq
 *
 */

// ����һ���������ɶ�����������������Ҫѭ�򽥽��Ŀ�

public class UniqueBinarySearchTreesII {
	
	// �Եݹ��˼��һֱ������������˼��
	
	// 1. ֻ�ǹ���һ������������
	public TreeNode createTree(int n) {
		return hasTree(1,n);
	}
	
	public TreeNode hasTree(int start, int end) {
		// �ݹ�ֹͣ����
		if(start > end) {
			return null;
		}
		
		// �ֽ��������
		int middle = (start + end) / 2;
		TreeNode left = hasTree(start, middle - 1);
		TreeNode right = hasTree(middle + 1, end);
		
		TreeNode middle_node = new TreeNode(middle);
		middle_node.left = left;
		middle_node.right = right;
		
		return middle_node;
	}
	
	
	private void printTree(TreeNode avl_tree) {
		// ʹ�õĶ���
		Queue<TreeNode> print_queue = new LinkedList<TreeNode>();
		print_queue.offer(avl_tree);
		
		while(!print_queue.isEmpty()) {
			TreeNode temp_node = print_queue.poll();
			System.out.print(temp_node.val + ", ");
			if(temp_node.left != null) print_queue.offer(temp_node.left);
			if(temp_node.right != null) print_queue.offer(temp_node.right);
		}
		System.out.println();
	}
	
	// ���ɶ����,�ݹ��˼��
    public List<TreeNode> generateTrees(int n) {
        if(n < 1) {
        	return new ArrayList<TreeNode>();
        }
    	
    	return generateManyTree(1,n);
    }
	
	public List<TreeNode> generateManyTree(int start, int end){
		List<TreeNode> manyTree = new ArrayList<TreeNode>();
		if(start > end) {
			manyTree.add(null);
			return manyTree;
		}
		
		// �ֽ��������
		for(int middle = start; middle <= end; middle++) {
			List<TreeNode> left = generateManyTree(start, middle - 1);
			List<TreeNode> right = generateManyTree(middle + 1, end);
			
			// ��Ϊ��Ҫ��˼����Ƕ��������ô������������Ҳ�Ƕ�������ұ߳�����Ҳ�Ƕ����
			// ��ô���Ǿ���Ҫ��֮�����������
			for(int index = 0; index < left.size(); index++) {
				for(int com_index = 0; com_index < right.size(); com_index++) {
					TreeNode root = new TreeNode(middle);
					root.left = left.get(index);
					root.right = right.get(com_index);
					manyTree.add(root);
				}
			}
			
		}
		
		return manyTree;
	}
	
	// �ݹ�һ�㶼�ǿ��Ա�ɶ�̬�滮�ģ�������ǿ��Խ�����ĵݹ��ɶ�̬�滮
	// ��Ϊ����������������С�������ĸ����ģ���˴洢���ǴӸ���0��1��2�Ľ��������3������ʱ������õ�ǰ��Ľ��
	public List<TreeNode> generateTrees_1(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n+1];
        // ���ﴦ�����0���������������Ϊ0�Ļ����ͷ���[]���������Ϊ0����ô����null
        dp[0] = new ArrayList<TreeNode>();
        if(n == 0) {
        	return dp[0];
        }
        dp[0].add(null);
        
        for(int index = 1; index < n + 1; index++) {
        	dp[index] = new ArrayList<TreeNode>();
        	// ������Ǵ��������������ȷʵ�ڵ�
        	for(int root_index = 1; root_index <= index; root_index++) {
        		int left_num = root_index - 1;
        		int right_num = index - root_index;		
        		for(TreeNode left_tree:dp[left_num]) {
        			for(TreeNode right_tree:dp[right_num]) {
        				TreeNode root_node = new TreeNode(root_index);
        				root_node.left = left_tree;
        				// ��Ϊ�����������ֽϴ�֮ǰ���ֱ���õĻ������־ͻ���ִ���
        				// ���Ҫ����
        				root_node.right = getRightClone(right_tree, root_index);
        				dp[index].add(root_node);
        			}
        		}
        		
        	}
        	
        }
        
        return dp[n];
    }
	
    // ��������������
	private TreeNode getRightClone(TreeNode right_tree, int root_index) {
		if(right_tree == null) {
			return right_tree;
		}
		
		TreeNode new_node = new TreeNode(right_tree.val + root_index);
		new_node.left = getRightClone(right_tree.left, root_index);
		new_node.right = getRightClone(right_tree.right, root_index);
		return new_node;
	}

	// ���Ƕ�̬�滮���������Ǹı�ݹ飬����һ��ȫ�µ�˼��
	// ��Ҫ���ǽ��µ������뵽֮ǰ�����оͿ��ԣ���Ϊ�µ���һ��������
	// ���Բ����λ��һ�㶼�Ǹ��ڵ㣬�����������������������ȵȡ�
	public List<TreeNode> generateTrees_2(int n) {
		List<TreeNode> pre_tree = new ArrayList();
		if(n < 1) {
			return pre_tree;
		}
		pre_tree.add(null);
		
		// ��ʼ����
		for(int index = 1; index <=n; index++) {
			List<TreeNode> curr_tree = new ArrayList();
			for(int pre_index = 0; pre_index < pre_tree.size(); pre_index++) {
				// ֱ�ӷ��뵽���ڵ���
				TreeNode right_root = new TreeNode(index);
				right_root.left = pre_tree.get(pre_index);
				curr_tree.add(right_root);
				
				// ���뵽�������ĸ����ڵ���
				for(int right_index = 0; right_index < n; right_index ++) {
					// ��Ҫ���Ƶ�ǰ����
					TreeNode temp_tree = treeCopy(pre_tree.get(pre_index));
					// ���ҵ�Ҫ�������������λ��
					TreeNode right_pos = temp_tree;
					for(int count = 0; count < right_index; count++) {
						if(right_pos == null) {
							break;
						}
						
						right_pos = right_pos.right;
					}
					
					if(right_pos == null) {
						break;
					}
					// ��ʼ���룬���ǽ��ҵ�λ�õ�������,��Ϊ�������������
					right_root = new TreeNode(index);
					right_root.left = right_pos.right;
					right_pos.right = right_root;
					curr_tree.add(temp_tree);
				}
				
			}
			pre_tree = curr_tree;
			
		}
		
		return pre_tree;
	}
	
	private TreeNode treeCopy(TreeNode treeNode) {
		if(treeNode == null) {
			return null;
		}
		
		TreeNode new_node = new TreeNode(treeNode.val);
		new_node.left = treeCopy(treeNode.left);
		new_node.right = treeCopy(treeNode.right);
		return new_node;
	}

	public static void main(String[] args) {
		int n = 3;
		var ubst = new UniqueBinarySearchTreesII();
		
//		TreeNode root = ubst.createTree(n);
//		ubst.printTree(root);
		
		List<TreeNode> node = ubst.generateTrees(n);
		
	}

}
