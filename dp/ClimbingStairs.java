package dp;

/**
 * 70. Climbing Stairs
 * 
 * You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you 
climb to the top?

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 * 
 * @author yzzyq
 *
 */

// 走楼梯，就是动态规划求和问题

public class ClimbingStairs {
	
	// 动态规划
    public int climbStairs(int n) {
    	if(n < 3) return n; 
        int[] n_step = new int[n + 1];
        n_step[0] = 0;
        n_step[1] = 1;
        n_step[2] = 2;
        for(int index = 3; index < n + 1; index++) {
        	n_step[index] = n_step[index - 1] + n_step[index - 2];	
        }
        
        return n_step[n];
    }
    
    // 动态规划，空间优化
    public int climbStairs_1(int n) {
        int one_num = 0, two_num = 0, three_num = 1;
        for(int index = 1; index <= n; index++) {
        	one_num = two_num;
        	two_num = three_num;
        	three_num = one_num + two_num;
        }
        
        return three_num;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int n = 3;
        var cs = new ClimbingStairs();
        int sum_route = cs.climbStairs_1(n);
        System.out.println(sum_route);
	}

}
