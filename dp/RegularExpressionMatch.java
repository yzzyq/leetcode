package dp;
// 10.Regular Expression Matching
// Given an input string (s) and a pattern (p), implement regular expression matching with 
// support for '.' and '*'.
//
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
// The matching should cover the entire input string (not partial).
//
// Note:
//
// s could be empty and contains only lowercase letters a-z.
// p could be empty and contains only lowercase letters a-z, and characters like . or *.
// Example 1:
//
// Input:
// s = "aa"
// p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".
// Example 2:
//
// Input:
//s = "aa"
//p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
//by repeating 'a' once, it becomes "aa".
//Example 3:
//
//Input:
//s = "ab"
//p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
//Example 4:
//
//Input:
//s = "aab"
//p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
//Example 5:
//
//Input:
//s = "mississippi"
//p = "mis*is*p*."
//Output: false

// 模拟正则表达式得匹配

// .匹配任意单个字符
// *匹配零个或多个前置字符

public class RegularExpressionMatch {
	
	// 使用递归
	// 先考虑没有*得情况，如果没有*得话，那么只需要一个一个字符进行匹配即可
    public boolean isMatchNotStar(String s, String p) {
    	// 如果俩个字符都为空，那么就是对的
    	if(s.isEmpty()) return p.isEmpty();
    	
    	boolean first_match = (!s.isEmpty()&&s.charAt(0) == p.charAt(0)||p.charAt(0) == '.');
    	
    	return first_match && isMatch(s.substring(1), p.substring(1));
    }
    
    // 但是有了*，就需要考虑字符*和。*，这里将之变成俩种情况，第一就是直接跳过俩个字符，说明*前边得字符没有出现过
 	// 第二就是依次使用*前面得字符和S中得字符进行匹配，直到P中出现上面的情况
    public boolean isMatch(String s, String p) {
    	// 如果俩个字符都为空，那么就是对的
    	if(p.isEmpty()) return s.isEmpty();
    	
    	boolean first_match = (!s.isEmpty()&&
    			               (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
    	
    	if(p.length() >= 2 && p.charAt(1) == '*') {
    		return (isMatch(s, p.substring(2))||
    				(first_match && isMatch(s.substring(1), p)));
    	}else {
    		return first_match && isMatch(s.substring(1), p.substring(1));
    	}
    	
    }
    // 上述递归得方法很慢很慢，因为它不仅仅是列举出全部得情况，并且使用的还是递归操作，这导致了双重慢 
    // 优点就是简单操作，代码量少，并且思想也很简单。  
 
    // 动态规划，同样是列举出全部的情况来看    
    public boolean isMatch_1(String s, String p) {
    	// 如果俩个字符都为空，那么就是对的
    	int s_len = s.length();
    	int p_len = p.length();
    	// 求解边界条件
    	boolean[][] dp = new boolean[s_len + 1][p_len + 1];
    	dp[0][0] = true;
    	
    	for(int index = 1; index < p_len; ++index) {
    		if(p.charAt(index) == '*' && dp[0][index - 1]) {
    			dp[0][index + 1] = true;
    		}
    	}
    	
    	for(int s_index = 0; s_index < s_len; ++s_index) {
    		for(int p_index = 0; p_index < p_len; ++p_index) {
    			if(p.charAt(p_index) == s.charAt(s_index) || p.charAt(p_index) == '.') {
    				dp[s_index + 1][p_index + 1] = dp[s_index][p_index];
    			}else if(p.charAt(p_index) == '*') {
    				// 当是*的时候，可以分成三种，第一种就是*前面的与它相等，匹配1个
    				// 第二种就是*前面与它不相等，匹配0个
    				// 第三种情况就是匹配多个
    				// 其中匹配一个与匹配多个是可以合并的
    				if(p.charAt(p_index - 1) != s.charAt(s_index) && p.charAt(p_index - 1) != '.') {
    					dp[s_index + 1][p_index + 1] = dp[s_index + 1][p_index - 1];
    				}else {
    					dp[s_index + 1][p_index + 1] = (dp[s_index + 1][p_index] || 
        						dp[s_index + 1][p_index - 1] || dp[s_index][p_index + 1]);
    				}
    			}	
    		}
    	}
    	
    	return dp[s_len][p_len];
    }
    
    
	public static void main(String[] args) {
		String s = "aa";
		String p = "a*";
		RegularExpressionMatch rem = new RegularExpressionMatch();
//		System.out.println(s.substring(1));
		boolean is_match = rem.isMatch_1(s, p);
        System.out.println(is_match);
	}

}
