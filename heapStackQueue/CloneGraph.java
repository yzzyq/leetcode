package heapStackQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 133. Clone Graph
 * 
 * Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, 
the first node with val = 1, the second node with val = 2, and so on. The graph is represented in 
the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph. Each list 
describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given 
node as a reference to the cloned graph.

 

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node 
with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
Example 4:


Input: adjList = [[2],[1]]
Output: [[2],[1]]
 

Constraints:

1 <= Node.val <= 100
Node.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 * 
 * @author yzzyq
 *
 */

// 依旧是递归复制

public class CloneGraph {
	
	// 递归
	HashMap<Node, Node> isVisited = new HashMap<Node, Node>();
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        
        if(isVisited.containsKey(node)) {
        	return isVisited.get(node);
        }
        
        Node temp_node = new Node(node.val, new ArrayList<Node>());
        
        isVisited.put(node, temp_node);
        
        for(Node neighbor:node.neighbors) {
        	temp_node.neighbors.add(cloneGraph(neighbor));
        }
        
        return temp_node;
    }
    
    // 广度搜索，使用队列,这个思路的细节，还是要注意
    public Node cloneGraph_1(Node node) {
    	if(node == null) return node;
    	
    	// 它也是输出结点的，主要就是判断是否访问过，是原生结点和复制结点的键值对
    	HashMap<Node, Node> isVisited = new HashMap<Node, Node>();
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.offer(node);
    	// 创建新的结点
    	isVisited.put(node, new Node(node.val, new ArrayList<Node>()));
    	
    	while(!queue.isEmpty()) {
    		Node one = queue.poll();
    		for(Node neg_one:one.neighbors) {
    			if(!isVisited.containsKey(neg_one)) {
    				isVisited.put(neg_one, new Node(neg_one.val, new ArrayList<Node>()));
    				queue.offer(neg_one);
    			}
    			// 这个复制结点的方式比较特殊
    			isVisited.get(one).neighbors.add(isVisited.get(neg_one));
    		}
    		
    	}
    	
    	return isVisited.get(node);
    }
    

	public static void main(String[] args) {
		CloneGraph cg = new CloneGraph();
        
		
		Node first = new Node(1);
		Node second = new Node(2);
		Node third = new Node(3);
		Node fourth = new Node(4);
		
		first.neighbors.add(second);
		first.neighbors.add(fourth);
		
		second.neighbors.add(first);
		second.neighbors.add(third);
		
		third.neighbors.add(second);
		third.neighbors.add(fourth);
		
		fourth.neighbors.add(first);
		fourth.neighbors.add(third);
		
		Node n = cg.cloneGraph_1(first);
		System.out.println(n);
	}

}
