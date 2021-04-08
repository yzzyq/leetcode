package dp;

/**
 * 132. Palindrome Partitioning II
 * Given a string s, partition s such that every substring of the partition is a 
 * palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1
 

Constraints:

1 <= s.length <= 2000
s consists of lower-case English letters only.
 * 
 * @author yzzyq
 *
 */

// 分割字符串

public class PalindromePartitioningII {
	
	// 动态规划
    public int minCut(String s) {
    	if(s.length() == 1) return 0;
        
    	int[] min_cuts = new int[s.length()];
    	boolean[][] dp = new boolean[s.length()][s.length()];
    	min_cuts[0] = 0;
    	
    	for(int index = 1; index < min_cuts.length; index++) {
    		min_cuts[index] = index;
    		for(int com_index = 0; com_index <= index; com_index++) {
    			if(s.charAt(com_index) == s.charAt(index) && (index - com_index <2 || dp[com_index+1][index-1])) {
    				dp[com_index][index] = true;
    				min_cuts[index] = (com_index == 0)?0:Math.min(min_cuts[com_index-1] + 1, min_cuts[index]); 
    			}
    			
    		}
    	}
    	
    	return min_cuts[s.length() - 1];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aab";
        var pp = new PalindromePartitioningII();
        int min_cut_num = pp.minCut(s);
        System.out.println(min_cut_num);
	}

}
