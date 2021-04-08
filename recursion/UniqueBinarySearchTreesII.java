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

// 给定一个数，生成二叉搜索树，此题需要循序渐进的看

public class UniqueBinarySearchTreesII {
	
	// 对递归的思想一直不熟练，慢慢思考
	
	// 1. 只是构建一个二叉搜索树
	public TreeNode createTree(int n) {
		return hasTree(1,n);
	}
	
	public TreeNode hasTree(int start, int end) {
		// 递归停止条件
		if(start > end) {
			return null;
		}
		
		// 分解成子问题
		int middle = (start + end) / 2;
		TreeNode left = hasTree(start, middle - 1);
		TreeNode right = hasTree(middle + 1, end);
		
		TreeNode middle_node = new TreeNode(middle);
		middle_node.left = left;
		middle_node.right = right;
		
		return middle_node;
	}
	
	
	private void printTree(TreeNode avl_tree) {
		// 使用的队列
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
	
	// 生成多棵树,递归的思想
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
		
		// 分解成子问题
		for(int middle = start; middle <= end; middle++) {
			List<TreeNode> left = generateManyTree(start, middle - 1);
			List<TreeNode> right = generateManyTree(middle + 1, end);
			
			// 因为主要的思想就是多棵树，那么左子树出来的也是多棵树，右边出来的也是多棵树
			// 那么我们就需要对之进行自由组合
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
	
	// 递归一般都是可以变成动态规划的，因此我们可以将上面的递归变成动态规划
	// 因为上面左右子树都是小于整个的个数的，因此存储就是从个数0，1，2的结果，后面3创建的时候可以用到前面的结果
	public List<TreeNode> generateTrees_1(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n+1];
        // 这里处理好了0的俩种情况，本身为0的话，就返回[]，如果本身不为0，那么都是null
        dp[0] = new ArrayList<TreeNode>();
        if(n == 0) {
        	return dp[0];
        }
        dp[0].add(null);
        
        for(int index = 1; index < n + 1; index++) {
        	dp[index] = new ArrayList<TreeNode>();
        	// 下面就是创建多棵树，先是确实节点
        	for(int root_index = 1; root_index <= index; root_index++) {
        		int left_num = root_index - 1;
        		int right_num = index - root_index;		
        		for(TreeNode left_tree:dp[left_num]) {
        			for(TreeNode right_tree:dp[right_num]) {
        				TreeNode root_node = new TreeNode(root_index);
        				root_node.left = left_tree;
        				// 因为右子树的数字较大，之前如果直接用的话，数字就会出现错误
        				// 这边要主义
        				root_node.right = getRightClone(right_tree, root_index);
        				dp[index].add(root_node);
        			}
        		}
        		
        	}
        	
        }
        
        return dp[n];
    }
	
    // 用于生成右子树
	private TreeNode getRightClone(TreeNode right_tree, int root_index) {
		if(right_tree == null) {
			return right_tree;
		}
		
		TreeNode new_node = new TreeNode(right_tree.val + root_index);
		new_node.left = getRightClone(right_tree.left, root_index);
		new_node.right = getRightClone(right_tree.right, root_index);
		return new_node;
	}

	// 还是动态规划，不过不是改编递归，而是一种全新的思想
	// 主要就是将新的数插入到之前的树中就可以，因为新的数一定是最大的
	// 所以插入的位置一般都是根节点，右子树，右子树的右子树等等。
	public List<TreeNode> generateTrees_2(int n) {
		List<TreeNode> pre_tree = new ArrayList();
		if(n < 1) {
			return pre_tree;
		}
		pre_tree.add(null);
		
		// 开始插入
		for(int index = 1; index <=n; index++) {
			List<TreeNode> curr_tree = new ArrayList();
			for(int pre_index = 0; pre_index < pre_tree.size(); pre_index++) {
				// 直接放入到根节点中
				TreeNode right_root = new TreeNode(index);
				right_root.left = pre_tree.get(pre_index);
				curr_tree.add(right_root);
				
				// 放入到右子树的各个节点中
				for(int right_index = 0; right_index < n; right_index ++) {
					// 先要复制当前的树
					TreeNode temp_tree = treeCopy(pre_tree.get(pre_index));
					// 查找到要插入的右子树的位置
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
					// 开始插入，就是将找到位置的右子树,作为插入结点的左子树
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
