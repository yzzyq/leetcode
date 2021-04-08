package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 300. Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing 
 * subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length 
is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return 
the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 * 
 * 
 * @author yzzyq
 *
 */

// 最长上升子串

public class LongestIncreasingSubsequence {
	
	// 动态规划，就是找出更长的子串
    public int lengthOfLIS(int[] nums) {
        if(nums.length < 2) return nums.length;
        
        int[] long_sub = new int[nums.length];
        Arrays.fill(long_sub, 1);
        int max_len = 0;
        for(int index = 1; index < nums.length; index++) {
        	
        	for(int com_index = 0; com_index < index; com_index++) {
        		if(nums[index] > nums[com_index]) {
        			long_sub[index] = Math.max(long_sub[index], long_sub[com_index]+1);
        		}
        	}
        	
        	max_len = Math.max(max_len, long_sub[index]);
        }
        
    	return max_len;
    }
    
    // 贪心+二分查找
    public int lengthOfLIS_1(int[] nums) {
    	if(nums.length < 2) return nums.length;
    	
    	List<Integer> LIS = new ArrayList<>();
    	LIS.add(nums[0]);
    	
    	for(int index = 1; index < nums.length; index++) {
    		if(nums[index] > LIS.get(LIS.size() - 1)) {
    			LIS.add(nums[index]);
    		}else if(nums[index] <= LIS.get(0)){
    			LIS.set(0,nums[index]);
    		}else {
    			int left = 0, right = LIS.size() - 1;
    			
    			while(left < right) {
    				int middle = left + (right - left)/2;
    				// 特别注意这个条件。我们需要判断相等的时候，是需要将之替换的
    				if(nums[index] > LIS.get(middle)) {
    					left = middle + 1;
    					
    				}else {
    					right = middle;	
    				}
    			}
    			LIS.set(right, nums[index]);
    		}
    	}
    	
    	return LIS.size();
    }
    
    

	public static void main(String[] args) {
		int[] nums = {3,5,6,2,5,4,19,5,6,7,12};
		var lcs = new LongestIncreasingSubsequence();
		int longest_sub = lcs.lengthOfLIS_1(nums);
        System.out.println(longest_sub);
	}

}
