package noClassification;
//Given a string s, find the longest palindromic substring in s. 
//You may assume that the maximum length of s is 1000.
//
//Example 1:
//
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
//Example 2:
//
//Input: "cbbd"
//Output: "bb"



// 回文字
// 这里找到的回文字只要在字符串中就可以,只要是子串就可以了，一开始理解错误，导致这道题写了很久

public class Palindromic {
	
	// 回文字的方法
    public String longestPalindrome(String s) {
    	if(s.length() < 2) return s;
    	char[] s_chars = s.toCharArray();
    	int max_len = 0, max_left = 0, max_right = 0;
    	for(int start = 0; start < s_chars.length - 1; start++) {
    		int left = start,right = start,len = 0;
    		while(left >= 0 && right < s_chars.length && s_chars[left] == s_chars[right]) {
    			left--;
    			right++;
    			len = right - left + 1;
    		}
    		if(max_len < len) {
    			max_left = left + 1;
    			max_right = right;
    			max_len = len;
    		}
    		
    		left = start;
    		right = start + 1;
    		len = 0;
    		while(left >= 0 && right < s_chars.length && s_chars[left] == s_chars[right]) {
    			left--;
    			right++;
    			len = right - left + 1;
    		}
    		if(max_len < len) {
    			max_left = left + 1;
    			max_right = right;
    			max_len = len;
    		}
    		
    	}
    	
    	return s.substring(max_left,max_right);
    }

	public static void main(String[] args) {
		String p_string = "ac";
//		String p_string = "babad";
//		String p_string = "cbbd";
		Palindromic p = new Palindromic();
		String longPalin = p.longestPalindrome(p_string);
		System.out.println(longPalin);		
	}

}
