package dp;


/**
 * 55. Jump Game
 * Given an array of non-negative integers, you are initially positioned at the 
 * first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length 
is 0, which makes it impossible to reach the last index.
 * 
 * 
 * @author yzzyq
 *
 */

// 跳跃，存在性问题，看能够到达

public class JumpGame {
	
	// 动态规划
    public boolean canJump(int[] nums) {
    	int len_nums = nums.length;
        boolean[] isArrive = new boolean[len_nums];
        isArrive[0] = true;
    	
    	for(int index = 1; index < len_nums; index++) {
    		isArrive[index] = false;
    		for(int compared_index = index - 1; compared_index >= 0; compared_index--) {
    			if(isArrive[compared_index] && nums[compared_index] + compared_index >= index) {
    				isArrive[index] = true;
    				break;
    			}
    		}
    	}
    	return isArrive[len_nums - 1];
    }
    
    // 贪心算法,维护一个最大距离
    public boolean canJump_1(int[] nums) {
    	int max_dis = nums[0];
    	int nums_len = nums.length;
    	if(nums_len == 1) return true;
    	
    	for(int index = 1; index < nums_len; index++) {
    		if(max_dis >= index) {
    			max_dis = Math.max(max_dis, nums[index] + index);
    			if(max_dis >= nums_len - 1) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

	public static void main(String[] args) {
		int[] nums = {2,0,0};
		JumpGame jg = new JumpGame();
        boolean isArrive = jg.canJump_1(nums);
        System.out.println(isArrive);
	}

}
