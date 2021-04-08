package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 47. Permutations II
 * 
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]

 * @author yzzyq
 *
 */

// 不能有重复的

public class PermutationsII {
	
    public List<List<Integer>> permuteUnique(int[] nums) {
        
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> temp_list = new ArrayList<Integer>();
    	Arrays.sort(nums);
    	for(int one:nums) {
    		temp_list.add(one);
    	}
        if(nums.length == 0) return result;
        // 需要用一个变量来记录交换的位置
        getResult(result, 0, temp_list, nums);
        return result;
    	
    }

	private void getResult(List<List<Integer>> result, int first, List<Integer> temp_list, int[] nums) {
		if(first == nums.length - 1 && !result.contains(temp_list)) {
			result.add(new ArrayList<>(temp_list));
			return ;
		}
		
		for(int index = first; index < nums.length; index++) {
			Collections.swap(temp_list, first, index);
			getResult(result, first + 1, temp_list, nums);
			// 撤销操作就是交换回来
			Collections.swap(temp_list, index, first);
		}
		
	}
	
	
	// 还有一种回溯的思想，但是根本上都是一样的
	public List<List<Integer>> permuteUnique_1(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> temp_list = new ArrayList<Integer>();
        if(nums.length == 0) return result;
        Arrays.sort(nums);
        boolean[] isVisited = new boolean[nums.length];
        Arrays.fill(isVisited, false);
        getResult_1(result, isVisited, temp_list, nums);
        return result;
        
    }

	private void getResult_1(List<List<Integer>> result, boolean[] isVisited, List<Integer> temp_list, int[] nums) {
		if(temp_list.size() == nums.length) {
			result.add(new ArrayList<>(temp_list));
			return ;
		}
		
		for(int index = 0; index < nums.length; index++) {
			if(isVisited[index] || (index > 0 && nums[index] == nums[index - 1] && isVisited[index - 1])) continue;
			temp_list.add(nums[index]);
			isVisited[index] = true;
			getResult_1(result, isVisited, temp_list, nums);
			isVisited[index] = false;
			temp_list.remove(temp_list.size() - 1);
		}
		
	}
	

	public static void main(String[] args) {
		int[] nums = {1,1,2};
        var perm = new PermutationsII();
        List<List<Integer>> result = perm.permuteUnique_1(nums);
        System.out.println(result);
	}

}
