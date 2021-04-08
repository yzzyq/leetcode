package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 159. 
 * 
 * Given a string s , find the length of the longest substring t  that contains at most 2 
 * distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: tis "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: tis "aabbb" which its length is 5.
 * 
 * 
 * 
 * @author yzzyq
 *
 */

// 最多俩个不同字符的最长字串

public class LongestSubstringWithAtMostTwoChar {
	
	// 滑动窗口，维护一个map来判断字符个数
	public int lengthOfLongestSubstringTwoDistinct_1(String s) {
		if(s.length() == 0) return 0;
        
		int left = 0;
		int max_num = 0;
		Map<Character, Integer> char_num = new HashMap<Character, Integer>();
		
		for(int right = 0; right < s.length(); right++) {
			char right_char = s.charAt(right);
			if(!char_num.containsKey(right_char)) {
				char_num.put(right_char, 0);
			}
			char_num.put(right_char, char_num.get(right_char)+1);
			
			while(char_num.size() > 2) {
				char left_char = s.charAt(left);
				char_num.put(left_char, char_num.get(left_char)-1);
                if(char_num.get(left_char) == 0) char_num.remove(left_char);				
				
				left++;
			}
			
			max_num = Math.max(max_num, right - left + 1);
			
		}
		
		return max_num;
		
	}
	
	// 其实上面主要是如何记录字符出现次数，在字符出现为0的话，就消去一个字符，然后就可以跳出循环
	// 那么既然是要判断字符是否出现，那么也可以记录最后一次字符出现的位置，通过位置来判断是否是重复，如果重复的就可以删掉
	
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if(s.length() == 0) return 0;
		
		int max_num = 0;
		int left = 0;
		Map<Character, Integer> char_num = new HashMap<Character, Integer>();
		
		for(int right = 0; right < s.length(); right++) {
			char right_char = s.charAt(right);
			if(!char_num.containsKey(right_char)) char_num.put(right_char, 0);
			char_num.put(right_char, right);
			
			while(char_num.size() > 2) {
				char left_char = s.charAt(left);
				if(char_num.get(left_char) == left) char_num.remove(left_char);
				left++;
			}
			max_num = Math.max(max_num, right - left + 1);
		}
		
		return max_num;
	}
	
	
	

	public static void main(String[] args) {
		var long_char = new LongestSubstringWithAtMostTwoChar();
		String s = "eceba";
		int long_num = long_char.lengthOfLongestSubstringTwoDistinct(s);
		System.out.println(long_num);

	}

}
