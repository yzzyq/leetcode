package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 * 
 * Given a string, find the length of the longest substring T that contains at most k 
 * distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
 * 
 * 
 * @author yzzyq
 *
 */

// 最多包含K个字符的最长子串

public class LongestSubstringWithAtMostKDistinctChar {
    
	// 不定长窗口大小
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if(k == 0 || s.length() == 0) return 0; 
		
		int max_num = 0;
		Map<Character, Integer> char_num = new HashMap<Character, Integer>();
		
		for(int left = 0, right = 0; right < s.length(); right++) {
			char right_char = s.charAt(right);
			if(!char_num.containsKey(right_char) || char_num.get(right_char) == 0) {
				k--;
				char_num.put(right_char, 0);
			}
			char_num.put(right_char, char_num.get(right_char) + 1);
			
			while(k < 0) {
				char left_char = s.charAt(left);
				char_num.put(left_char, char_num.get(left_char) - 1);
				if(char_num.containsKey(left_char) && char_num.get(left_char) == 0) {
					k++;
				}
				left++;
			}
			max_num = max_num > right-left+1?max_num:right-left+1;
		}
		
		return max_num;
	}
	
	
	public static void main(String[] args) {
		String s = "aacdaa";
		int k = 3;
		var distinctChar = new LongestSubstringWithAtMostKDistinctChar();
		int length_num = distinctChar.lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(length_num);
	}

}
