package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/** 727. minimum window subsequence
 * 
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T 
 * is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty 
string "". If there are multiple such minimum-length windows, return the one with the 
left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must 
occur in order.

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
 * 
 * 
 * @author yzzyq
 *
 */

// 找出最靠近的子串

public class MinWindowSubsequence {
    
	// 双指针法，使用的使往返指针
	public String minWindow(String S, String T) {
		if(S.length() < T.length()) return "";
		
		int s_pos = 0;
		int t_pos = 0;
		int start = -1;
		int max_len = Integer.MAX_VALUE;
		
		// 先找出包含全部字符的
		while(s_pos < S.length()) {
			if(S.charAt(s_pos) == T.charAt(t_pos)) {
				if(++t_pos == T.length()) {
					int end = s_pos + 1;
					t_pos--;
					// 之后向右检查
					while(t_pos >= 0) {
						if(S.charAt(s_pos) == T.charAt(t_pos)) {
							s_pos--;
							t_pos--;
						}else {
							s_pos--;
						}
					}
					s_pos++;
					t_pos++;
					// 找出的是最小的
					if(end - s_pos < max_len) {
						max_len = end - s_pos;
						start = s_pos;
					}
				}	
			}
			s_pos++;
		}
		
		return start != -1?S.substring(start, start + max_len):"";
	}
	
	public static void main(String[] args) {
		var mws = new MinWindowSubsequence();
		String s = "aaaabcdebdde";
		String t = "bde";
		String win_sequence = mws.minWindow(s, t);
        System.out.println(win_sequence);  
	}

}
