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

// ģ��������ʽ��ƥ��

// .ƥ�����ⵥ���ַ�
// *ƥ���������ǰ���ַ�

public class RegularExpressionMatch {
	
	// ʹ�õݹ�
	// �ȿ���û��*����������û��*�û�����ôֻ��Ҫһ��һ���ַ�����ƥ�伴��
    public boolean isMatchNotStar(String s, String p) {
    	// ��������ַ���Ϊ�գ���ô���ǶԵ�
    	if(s.isEmpty()) return p.isEmpty();
    	
    	boolean first_match = (!s.isEmpty()&&s.charAt(0) == p.charAt(0)||p.charAt(0) == '.');
    	
    	return first_match && isMatch(s.substring(1), p.substring(1));
    }
    
    // ��������*������Ҫ�����ַ�*�͡�*�����ｫ֮��������������һ����ֱ�����������ַ���˵��*ǰ�ߵ��ַ�û�г��ֹ�
 	// �ڶ���������ʹ��*ǰ����ַ���S�е��ַ�����ƥ�䣬ֱ��P�г�����������
    public boolean isMatch(String s, String p) {
    	// ��������ַ���Ϊ�գ���ô���ǶԵ�
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
    // �����ݹ�÷���������������Ϊ�����������оٳ�ȫ�������������ʹ�õĻ��ǵݹ�������⵼����˫���� 
    // �ŵ���Ǽ򵥲������������٣�����˼��Ҳ�ܼ򵥡�  
 
    // ��̬�滮��ͬ�����оٳ�ȫ�����������    
    public boolean isMatch_1(String s, String p) {
    	// ��������ַ���Ϊ�գ���ô���ǶԵ�
    	int s_len = s.length();
    	int p_len = p.length();
    	// ���߽�����
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
    				// ����*��ʱ�򣬿��Էֳ����֣���һ�־���*ǰ���������ȣ�ƥ��1��
    				// �ڶ��־���*ǰ����������ȣ�ƥ��0��
    				// �������������ƥ����
    				// ����ƥ��һ����ƥ�����ǿ��Ժϲ���
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
