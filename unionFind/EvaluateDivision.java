package unionFind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 399. Evaluate Division
 * 
 * You are given an array of variable pairs equations and an array of real numbers values, where 
 * equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or 
 * Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must 
find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in 
division by zero and that there is no contradiction.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],
["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],
["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 * 
 * 
 * @author yzzyq
 *
 */

// 除法求值
// 此题没有思考出来，看了题解，有好几种解法，利用的图

public class EvaluateDivision {
	
	// DFS，利用图，先将之变成图，之后对图进行DFS
	Map<String,Map<String,Double>> map_result;
	
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		map_result = new HashMap<String, Map<String,Double>>();
		
		// 首先将式子变成图
		for(int index = 0; index < equations.size(); ++index) {
			List<String> one_equation = equations.get(index);
			System.out.println(one_equation.size());
			map_result.computeIfAbsent(one_equation.get(0), k -> new HashMap<>()).put(one_equation.get(1), values[index]);
			map_result.computeIfAbsent(one_equation.get(1), k -> new HashMap<>()).put(one_equation.get(0), 1/values[index]);
		}
		
		double[] results = new double[queries.size()];
		
		for(int index = 0; index < queries.size(); ++index) {
			List<String> one_q = queries.get(index);
			
			// 进行深度优先搜索
			double result = dfs(one_q.get(0),one_q.get(1),new HashSet<String>(),1.0);
			results[index] = result;
		}
		
		return results;
    }

	private double dfs(String start, String end, HashSet<String> visited,double value) {
		if(!map_result.containsKey(start) || !map_result.containsKey(end) || visited.contains(start)) {
			return -1.0;
		}
		
		if(start.equals(end)) return value;
		
			
		Map<String,Double> middle = map_result.get(start);
		
		visited.add(start);
		
		for(String key:middle.keySet()) {
			double res = dfs(key,end,visited,value*middle.get(key));
			if(res != -1) return res;
		}
		
		return -1;
	}
	
	
	// 并查集，不过并查集有点复杂，
	Map<String, String> parent = new HashMap<String, String>();
	Map<String, Double> val = new HashMap<String, Double>();

	// 对结点进行初始化
	public void init(String key) {
		if(!parent.containsKey(key)) {
			parent.put(key, key);
			val.put(key, 1.0);
		}
	}
	
	// 查找父亲
	public String getParent(String key) {
		// 路径压缩
		if(!parent.get(key).equals(key)) {
			String father = parent.get(key);
			parent.put(key, getParent(father));
			val.put(key, val.get(key)*val.get(father));
		}
		return parent.get(key);
	}
	
	// 进行合并
	public void union(String node1,String node2,double value) {
		init(node1);
		init(node2);
	    String parent_node1 = getParent(node1);
	    String parent_node2 = getParent(node2);
	    
	    if(!parent_node1.equals(parent_node2)) {
	    	parent.put(parent_node1,parent_node2);
	    	val.put(parent_node1, value*val.get(node2)/val.get(node1));
	    }
	   
	}
	
	public double[] calcEquation_1(List<List<String>> equations, double[] values, List<List<String>> queries) {
		for(int index = 0; index < equations.size(); ++index) {
			List<String> one = equations.get(index);
			union(one.get(0),one.get(1),values[index]);
		}
		
		double[] results = new double[queries.size()];
		
		// 从并查集中查找
		for(int index = 0; index < queries.size(); ++index) {
			String a = queries.get(index).get(0);
			String b = queries.get(index).get(1);
			
			if(!parent.containsKey(a) || !parent.containsKey(b) || !getParent(a).equals(getParent(b))) {
				results[index] = -1;
			}else {
				results[index] = val.get(a) / val.get(b);
			}
			
		}
		
		return results;
	}
	
	

	public static void main(String[] args) {
		List<List<String>> equations = new ArrayList<List<String>>();
		List<String> one = new ArrayList<String>();
		one.add("a");
		one.add("b");
		equations.add(one);
		List<String> one1 = new ArrayList<String>();
		one1.add("b");
		one1.add("c");
		equations.add(one1);
	    double[] values = {2.0,3.0};
	    List<List<String>> queries = new ArrayList<List<String>>();
	    List<String> one_q = new ArrayList<String>();
	    one_q.add("a");
		one_q.add("c");
		queries.add(one_q);
		List<String> one_q1 = new ArrayList<String>();
		one_q1.add("b");
		one_q1.add("a");
		queries.add(one_q1);
		List<String> one_q2 = new ArrayList<String>();
		one_q2.add("a");
		one_q2.add("e");
		queries.add(one_q2);
		List<String> one_q3 = new ArrayList<String>();
		one_q3.add("a");
		one_q3.add("a");
		queries.add(one_q3);
		List<String> one_q4 = new ArrayList<String>();
		one_q4.add("x");
		one_q4.add("x");
		queries.add(one_q4);
		EvaluateDivision ed = new EvaluateDivision();
	    double[] results = ed.calcEquation_1(equations, values, queries);
        for(double result:results) {
        	System.out.print(result + ",");
        }
	}

}
