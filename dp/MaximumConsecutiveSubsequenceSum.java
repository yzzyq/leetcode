package dp;

/**
 * 笔试题目
 * 这个是最大连续子序列和
 * [-2,1,-3,4,-1,2,1,-5,4]
 * 最大连续序列就是4,-1,2,1，和就是6
 * 
 * @author yzzyq
 *
 */


public class MaximumConsecutiveSubsequenceSum {

	// 未检验，没有数据集进行检验
	// 动态规划题目
	public int getMax(int[] nums) {
		int len = nums.length;
		if(len == 0) return 0;
		int[] dp = new int[len];
		dp[0] = nums[0];
		int max = dp[0];
		
		// 这里的转移方程中，当前元素是必须要选的，这是因为它得是连续得，这样后面才能继续运行
		// 以前你只能对前面得子序列保持着选或者不选得考虑
		for(int index = 1; index < len; index++) {
			dp[index] = Math.max(nums[index], nums[index] + dp[index - 1]);
			if(dp[index] > max) max = dp[index]; 
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        var maxCon = new MaximumConsecutiveSubsequenceSum();
        int max_num = maxCon.getMax(nums);
        System.out.println(max_num);
	}

}
