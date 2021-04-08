package tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
102. Binary Tree Level Order Traversal

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, 
level by level).

就是层次遍历

*/


public class TreeLevelOrderTraversal {
	
	// 层次遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> tree_list = new ArrayList<List<Integer>>();
    	if(root == null) return tree_list;
        Queue<TreeNode> tree_queue = new LinkedList<TreeNode>();
        tree_queue.offer(root);
        while(!tree_queue.isEmpty()) {
        	List<Integer> one_list = new ArrayList<Integer>();
        	int queue_size = tree_queue.size();
        	int index = 0;
        	while(index < queue_size) {
        		TreeNode one_node = tree_queue.poll();
        		one_list.add(one_node.val);
        		if(one_node.left != null) tree_queue.offer(one_node.left);
        		if(one_node.right != null) tree_queue.offer(one_node.right);
        		index++;
        	}
        	tree_list.add(one_list);
        }
        return tree_list;
    }
	
}
