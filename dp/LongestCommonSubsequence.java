package dp;

import java.util.Arrays;

/**
 * 1143. Longest Common Subsequence
 * Given two strings text1 and text2, return the length of their longest common 
 * subsequence.

A subsequence of a string is a new string generated from the original string 
with some characters(can be none) deleted without changing the relative order of 
the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). 
A common subsequence of two strings is a subsequence that is common to both strings.

 

If there is no common subsequence, return 0.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length <= 1000
1 <= text2.length <= 1000
The input strings consist of lowercase English characters only.
 * 
 * 
 * @author yzzyq
 *
 */

// 只要涉及到子序列问题，一般都是动态规划解决
// 这是因为子序列是需要穷举出所有的结果的。这是典型的二维动态规划问题。

public class LongestCommonSubsequence {
	
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length() == 0 || text2.length() == 0) return 0;
        
        int[][] dp = new int[text1.length() + 1][text2.length()+1];
        
        for(int index = 1; index <= text1.length(); index++) {
        	for(int com_index = 1; com_index <= text2.length(); com_index++) {
        		if(text1.charAt(index-1) == text2.charAt(com_index-1)) {
        			dp[index][com_index] = dp[index - 1][com_index - 1] + 1;
        		}else {
        			dp[index][com_index] = Math.max(dp[index][com_index - 1], dp[index - 1][com_index]);
        		}
        	}
        	
        }
        
        return dp[text1.length()][text2.length()];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text1 = "abcde", text2 = "ace";
		var lcss = new LongestCommonSubsequence();
		int longest_sub = lcss.longestCommonSubsequence(text1, text2);
        System.out.println(longest_sub);
	}

}
