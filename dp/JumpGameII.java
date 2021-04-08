package dp;

import java.util.Arrays;

/**
 * 45. Jump Game II
 * 
 * Given an array of non-negative integers, you are initially positioned at the 
 * first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

Note:

You can assume that you can always reach the last index.
 * 
 * @author yzzyq
 *
 */

// 跳跃问题，用最少的次数

public class JumpGameII {
	
	// 动态规划,时间复杂度和空间复杂度都高
    public int jump(int[] nums) {
    	int nums_len = nums.length;
    
    	if(nums_len == 1) return 0;
    	int[] min_step = new int[nums_len];
    	Arrays.fill(min_step, Integer.MAX_VALUE);
    	min_step[0] = 0;
    	for(int index = 0; index < nums_len; index++) {
    		int max_len = index + nums[index];
    		max_len = Math.min(max_len, nums_len - 1);
    		
    		for(int update_index = index + 1; update_index <= max_len; update_index++) {
    			min_step[update_index] = Math.min(min_step[update_index], min_step[index] + 1);
    		}
    		
    	}
    	return min_step[nums_len - 1];
    }
    
    // 贪心算法，就是反向查找，找出距离终点最远的那个数,比上面那个好一点点
    // 二者的时间复杂度差不多
    public int jump_1(int[] nums) {
    	int dis = nums.length - 1;
    	int jump_num = 0;
    	
    	while(dis > 0) {
    		for(int index = 0; index < dis; index++) {
    			if(index + nums[index] >= dis) {
    				dis = index;
    				jump_num++;
    				break;
    			}
    		}
    	}
    	
    	return jump_num;
    }
    
    // 正向查找，也是贪心，不过降低了时间复杂度，提升很大
    public int jump_2(int[] nums) {
    	int len_nums = nums.length;
    	int end = 0;
    	int max_pos = 0;
    	int step = 0;
    	
    	// 这里记住，我们不需要访问最后一个元素，边界一定大于等于这个值，
    	for(int index = 0; index < len_nums - 1; index++) {
    		// 只要记录好当前可达到位置中的最大即可
    		max_pos = Math.max(max_pos, index + nums[index]);
    		// 如果过了边界，那么就需要加上1
    		if(index == end) {
    			end = max_pos;
    			step++;
    		}
    		
    	}
    	
    	return step;
    }

	public static void main(String[] args) {
		int[] nums = {2,3,1,1,4};
        JumpGameII jgii = new JumpGameII();
        int min_num = jgii.jump_2(nums);
        System.out.println(min_num);
	}

}
