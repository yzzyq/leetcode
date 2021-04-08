package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *  120. Triangle
 * 
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move 
 * to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the 
total number of rows in the triangle.
 * 
 * @author yzzyq
 *
 */

// 求最顶向下的最小路径

public class Triangle {
	
	// 自顶向下，动态规划
    public int minimumTotal_1(List<List<Integer>> triangle) {
    	if(triangle.size() == 1) return triangle.get(0).get(0);
        int sum_num = (1+triangle.size())*triangle.size()/2;
    	int[] is_min = new int[sum_num];
    	
    	is_min[0] = triangle.get(0).get(0);
    	int num = 1;
    	int min_value = Integer.MAX_VALUE;
    	
    	for(int floor = 1; floor < triangle.size(); floor++) {
    		List<Integer> one_list = triangle.get(floor);
    		is_min[num] = one_list.get(0) + is_min[num - one_list.size() + 1];
    		is_min[num + one_list.size() - 1] = one_list.get(one_list.size() - 1) + is_min[num-1];
    		if(floor == triangle.size() - 1) min_value = Math.min(is_min[num], is_min[num + one_list.size() - 1]); 
    		for(int index = 1; index < one_list.size() - 1; index++) {
    			int new_index = num + index;
    			int one_route = is_min[new_index - one_list.size()];
    			int two_route = is_min[new_index - one_list.size() + 1];
    			is_min[new_index] = Math.min(one_route, two_route) + one_list.get(index);
    			if(floor == triangle.size() - 1 && min_value > is_min[new_index]) {
    				min_value = is_min[new_index];
        		}
    		}
    		num += one_list.size();
    	}
    	
    	return min_value;
    }
    
    // 优化版，自底向上，比上面的那个好点
    public int minimumTotal(List<List<Integer>> triangle) {
    	int line = triangle.size();
    	// 空间优化，只使用一组数组,对于边界情况，我们使用多出的一个来运算。
    	int[] min_dis = new int[line+1];
    	
    	for(int i = line - 1; i >= 0; i--) {
    		for(int j = 0; j < triangle.get(i).size(); j++) {
    			min_dis[j] = Math.min(min_dis[j], min_dis[j+1]) + triangle.get(i).get(j);    		}
    	}
    	return min_dis[0];
    }

	public static void main(String[] args) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		List<Integer> one_triangele = Arrays.asList(-10);
		List<Integer> two_triangele = Arrays.asList(3,4);
		List<Integer> three_triangele = Arrays.asList(6,5,7);
//		List<Integer> four_triangele = Arrays.asList(4,1,8,3);
		triangle.add(one_triangele);
		triangle.add(two_triangele);
		triangle.add(three_triangele);
//		triangle.add(four_triangele);
        
		Triangle t = new Triangle();
		int min = t.minimumTotal(triangle);
		System.out.println(min);
		
	}

}
