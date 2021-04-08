package tree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

还是层次遍历，但是遍历是锯齿状的，就是先左边到右边，下一层就是右边到左边

*/

public class TreeLevelOrderTraveralZigzag {
	
	// 遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> tree_list = new ArrayList<List<Integer>>();
    	if(root == null) return tree_list;
        Queue<TreeNode> tree_queue = new LinkedList<TreeNode>();
        tree_queue.offer(root);
        boolean leftToRight = true;
        while(!tree_queue.isEmpty()) {
        	List<Integer> one_list = new ArrayList<Integer>();
        	int queue_size = tree_queue.size();
        	int index = 0;
        	while(index < queue_size) {
        		TreeNode one_node = tree_queue.poll();
        		// 就是层次遍历上加一个判断条件
        		if(leftToRight) {
        			one_list.add(one_node.val);
        		}else {
        			one_list.add(0, one_node.val);
        		}
        		if(one_node.left != null) tree_queue.offer(one_node.left);
        		if(one_node.right != null) tree_queue.offer(one_node.right);
        		index++;
        	}
        	leftToRight = !leftToRight;
        	tree_list.add(one_list);
        }
        return tree_list;
    }

}
