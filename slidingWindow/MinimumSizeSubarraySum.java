package slidingWindow;
/**
 * 
 * 209. Minimum Size Subarray Sum.
 * 
 * Given an array of n positive integers and a positive integer s, find the minimal length of 
 * a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * @author yzzyq
 *
 */


// 找出大于等于s的最小长度

public class MinimumSizeSubarraySum {
	
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;
    	int min_len = nums.length + 1;
    	int left = 0;
    	int match_s = 0;
    	
    	for(int right = 0; right < nums.length; right++) {
    		match_s += nums[right];
    		
    		while(match_s >= s) {
    			min_len = Math.min(min_len, right - left + 1);
    			match_s -= nums[left];
    			left++;
    		}
    	}
        
    	return min_len == nums.length + 1?0:min_len;
    }

	public static void main(String[] args) {
		var minSum = new MinimumSizeSubarraySum();
		int s = 15;
		int[] nums = {5,1,3,5,10,7,4,9,2,8};
        int min_len = minSum.minSubArrayLen(s, nums);
        System.out.println(min_len);
	}

}
