package dp;

import java.util.Arrays;

/**
 * 
 * 978. Longest Turbulent Subarray
 * 
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent 
pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

 

Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
Example 2:

Input: [4,8,12,16]
Output: 2
Example 3:

Input: [100]
Output: 1
 

Note:

1 <= A.length <= 40000
0 <= A[i] <= 10^9
 * 
 * @author yzzyq
 *
 */

// 最长湍流子数组

public class LongestTurbulentSubarray {
	
	// 此题目可以使用滑动窗口，也可以使用动态规划来解答
	// 大致思路就是使用二维数组来存储，左边一列就是A[i] > A[i - 1],右边一列就是 A[i] < A[i - 1]
    public int maxTurbulenceSize(int[] A) {
    	int a_len = A.length;
    	if(a_len == 1) return 1; 

    	int[][] dp = new int[a_len][2];
    	for(int index = 0; index < a_len; index++) {
    		Arrays.fill(dp[index], 1);
    	}
    	
    	int num = 0;
    	
    	for(int index = 1; index < a_len; ++index) {
    		if(A[index] > A[index - 1]) {
    			dp[index][0] = dp[index-1][1] + 1;
    		}
    		
    		if(A[index] < A[index-1]) {
    			dp[index][1] = dp[index-1][0] + 1;
    		}
    		
    		num = Math.max(num, Math.max(dp[index][0],dp[index][1]));
    	}
    	
    	return num;
    }

	public static void main(String[] args) {
		var longestTurbulent = new LongestTurbulentSubarray();
        int[] A = {9,4,2,10,7,8,8,1,9};
        int long_subarrays = longestTurbulent.maxTurbulenceSize(A);
        System.out.println(long_subarrays);
	}

}
