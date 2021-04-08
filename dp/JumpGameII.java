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

// ��Ծ���⣬�����ٵĴ���

public class JumpGameII {
	
	// ��̬�滮,ʱ�临�ӶȺͿռ临�Ӷȶ���
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
    
    // ̰���㷨�����Ƿ�����ң��ҳ������յ���Զ���Ǹ���,�������Ǹ���һ���
    // ���ߵ�ʱ�临�ӶȲ��
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
    
    // ������ң�Ҳ��̰�ģ�����������ʱ�临�Ӷȣ������ܴ�
    public int jump_2(int[] nums) {
    	int len_nums = nums.length;
    	int end = 0;
    	int max_pos = 0;
    	int step = 0;
    	
    	// �����ס�����ǲ���Ҫ�������һ��Ԫ�أ��߽�һ�����ڵ������ֵ��
    	for(int index = 0; index < len_nums - 1; index++) {
    		// ֻҪ��¼�õ�ǰ�ɴﵽλ���е���󼴿�
    		max_pos = Math.max(max_pos, index + nums[index]);
    		// ������˱߽磬��ô����Ҫ����1
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
