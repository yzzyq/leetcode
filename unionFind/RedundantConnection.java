package unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 684. Redundant Connection
 * 
 * In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), 
with one additional edge added. The added edge has two different vertices chosen from 1 to N, and 
was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with 
u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are 
multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] 
should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input 
array.

Update (2017-09-26):
We have overhauled the problem description + test cases and specified clearly the graph is an 
undirected graph. For the directed graph follow up please see Redundant Connection II). 
We apologize for any inconvenience caused.
 * 
 * 
 * @author yzzyq
 *
 */

// 去除一条冗余边

public class RedundantConnection {
	// 并查集
	Map<Integer, Integer> parent = new HashMap<Integer, Integer>();
	Map<Integer, Integer> ranks = new HashMap<Integer, Integer>();
	
	public boolean union(int node1, int node2) {
		if(!parent.containsKey(node1)) {
			parent.put(node1, node1);
			ranks.put(node1, 1);
		}
		if(!parent.containsKey(node2)) {
			parent.put(node2, node2);
			ranks.put(node2, 1);
		}
		
		int node1_parent = getParent(node1);
		int node2_parent = getParent(node2);
		
		if(node1_parent != node2_parent) {
			if(ranks.get(node1_parent) > ranks.get(node2_parent)) {
				parent.put(node2_parent, node1_parent);
			}else if(ranks.get(node1_parent) < ranks.get(node2_parent)) {
				parent.put(node1_parent, node2_parent);
			}else {
				parent.put(node2_parent, node1_parent);
				ranks.put(node1_parent, ranks.get(node1_parent) + 1);
			}
			return false;
		}
		
		return true;
	}
	
	public int getParent(int node) {
		if(parent.get(node) != node) {
			int temp = getParent(parent.get(node));
			parent.put(node, temp);
		}
		
		return parent.get(node);
	}
	
    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
    	
        for(int index = 0; index < len; ++index) {
        	boolean is_redundant = union(edges[index][0], edges[index][1]);
        	if(is_redundant) {
        		return edges[index];
        	}
        }
		return null;
    }
    
    // DFS,使用map
    List<List<Integer>> connect;
    
    public int[] findRedundantConnection_1(int[][] edges) {
    	connect = new ArrayList<List<Integer>>();
    	
        int len = edges.length;
    	
        for(int index = 0; index <= len; ++index) {
        	connect.add(new ArrayList<Integer>());       	
        }
        
        for(int index = 0; index < len; ++index) {
        	boolean is_connect = dfs(edges[index][0],0,edges[index][1]);
        	
        	if(is_connect) {
        		return edges[index];
        	}else {
        		connect.get(edges[index][0]).add(edges[index][1]);
        		connect.get(edges[index][1]).add(edges[index][0]);
        	}
        	
        }
        
    	return null;
    	
    }
    
    public boolean dfs(int start,int father, int target) {
    	if(start == target) {
    		return true;
    	}
    	
    	List<Integer> temp = connect.get(start);
    	boolean is_conn = false;
    	
    	for(int one:temp) {
    		if(one == father) continue;
    		is_conn = is_conn || dfs(one,start,target);
    	}
    	
    	return is_conn;
    }
    
    

	public static void main(String[] args) {
//		int[][] edges = {{1,2}, {1,3}, {2,3}};
		int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
		var rc = new RedundantConnection();
		int[] result = rc.findRedundantConnection_1(edges);
		if(result != null) System.out.println(result[0] + "," + result[1]);

	}

}
