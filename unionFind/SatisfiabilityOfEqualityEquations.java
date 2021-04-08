package unionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 990. Satisfiability of Equality Equations
 * 
 * Given an array equations of strings that represent relationships between variables, each string 
 * equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a 
 * and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy 
all the given equations.

Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, 
but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true
 
Note:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] and equations[i][3] are lowercase letters
equations[i][1] is either '=' or '!'
equations[i][2] is '='
 * 
 * @author yzzyq
 *
 */

// 判断几个等式在一起的时候，是不是成立

public class SatisfiabilityOfEqualityEquations {
	
	// 并查集
	class UnionFind{
		
		Map<Character, Character> parent = new HashMap<Character, Character>();
		Map<Character, Integer> weights = new HashMap<Character, Integer>();
		
		public UnionFind(String[] equations) {
			for(String equation:equations) {
				char temp_a = equation.charAt(0);
				char temp_b = equation.charAt(3);
				if(!parent.containsKey(temp_a)) {
					parent.put(temp_a, temp_a);
					weights.put(temp_a, 1);
				}
				
				if(!parent.containsKey(temp_b)) {
					parent.put(temp_b, temp_b);
					weights.put(temp_b, 1);
				}
				
			}
		}
		
		public void union(char node1, char node2) {
			char parent_node1 = find(node1);
			char parent_node2 = find(node2);
			
			if(parent_node1 == parent_node2) return;
			
			if(weights.get(parent_node1) > weights.get(parent_node2)) {
				parent.put(parent_node2, parent_node1);
			}else if(weights.get(parent_node1) < weights.get(parent_node2)) {
				parent.put(parent_node1, parent_node2);
			}else {
				parent.put(parent_node1, parent_node2);
				weights.put(parent_node2, weights.get(parent_node2) + 1);
			}
			
		}
		
		public char find(char node) {
			if(parent.get(node) != node) {
				char p = find(parent.get(node));
				parent.put(node, p);
			}
			return parent.get(node);
		}
		
	}
	
	// 我在这按犯的错误就是。不知道何时处理冲突。第一次我是把最后一个字符串作为冲突
	// 第二次我是记录每次判断的字符，只要之前比较过相同的字符，那么就认为有冲突，但是有错误，
	// 在"a==b","b!=c","c==a"情况下，就会发生错误
	
	
	// 其实就是先遍历等式，之后再看不等式是否成立
	public boolean equationsPossible(String[] equations) {
		// 这个方法很多都是没有优化好的
		UnionFind uf = new UnionFind(equations);
		// 等式合并
		for(String equation:equations) {
			if(equation.charAt(1) == '=') {
				uf.union(equation.charAt(0), equation.charAt(3));
			}
			
		}
		
		// 不等式判断
		for(String equation:equations) {
			if(equation.charAt(1) == '!') {
				if(uf.find(equation.charAt(0)) == uf.find(equation.charAt(3))) {
					return false;
				}
			}
		}
			
		
        return true;
    }
	
	
	public boolean equationsPossible_1(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

	

	

	public static void main(String[] args) {
//		String[] equations = {"a==b","b==c","a==c"};
//		String[] equations = {"c==c","b==d","x!=z"};
		String[] equations = {"c==c","f!=a","f==b","b==c"};
//		String[] equations = {"a==b","b!=c","c==a"};
		var see = new SatisfiabilityOfEqualityEquations();
		boolean isPossible = see.equationsPossible(equations);
        System.out.println(isPossible);
	}

}
