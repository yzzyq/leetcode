package slidingWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/***
 * 424. Longest Repeating Character Replacement
 * 
 * Given a string s that consists of only uppercase English letters, you can perform at most k 
 * operations on that string.

In one operation, you can choose any character of the string and change it to any other 
uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get 
after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
 

Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 * 
 * @author yzzyq
 *
 */


// 替换字符串的字符，查找出最长的


public class LongRepeatingCharacterReplace {
	
	public int characterReplacement(String s, int k) {
		if(s.length() == 0) return 0;
		
		int[] char_num = new int[26];
		int most = 0;
		int max_num = 0;
		
		for(int left = 0, right = 0; right < s.length(); right++) {
			
			most = Math.max(most, ++char_num[s.charAt(right) - 'A']);
			
			if(most + k < right - left + 1) {
				char_num[s.charAt(left++) - 'A']--;
			}
			
			max_num = Math.max(max_num, right - left + 1);
			
		}
		
		return max_num;
		
	}
	
	
	
//    public int characterReplacement(String s, int k) {
//        if(s.length() == 0) return 0;
//    	
//        
//        int max_len = 0;
//        int left = 0;
//        int right = 0;
//        char left_char = s.charAt(left);
//        int no_match = 0;
//        Map<Character, Integer> char_num = new HashMap<Character, Integer>();
//        
//        while(right < s.length()) {
//        	if(s.charAt(right) != left_char) {
//        		no_match++;
//        	}
//        	
//        	if(k >= no_match) max_len = max_len > right - left + 1?max_len:right - left + 1;
//        	
//        	if(!char_num.containsKey(s.charAt(right))) char_num.put(s.charAt(right), 0);
//        	char_num.put(s.charAt(right), char_num.get(s.charAt(right))+ 1);
//        	
//        	while(k < no_match) {
//        		char_num.put(left_char, char_num.get(left_char) - 1);
//        		left++;
//        		
//        		left_char = s.charAt(left);
//        		
//        		no_match = 0;
//        		
//        		for(char temp:char_num.keySet()) {
//        			if(temp != left_char) no_match += char_num.get(temp);
//        		}       		
//        	}
//        	right++;
//        }
//        
//        return max_len;
//        
//    }
    
	
    public int characterReplacement_1(String s, int k) {
    	if(s.length() == 0) return 0;
        int[] letter = new int[26];
        
        int l = 0;
        
        int most = ++letter[s.charAt(0) - 'A'];
        int res = 1;
        
        for(int r = 1; r < s.length();r++)
        {
            most = Math.max(most,++letter[s.charAt(r) - 'A']);
            
            if(most + k < r - l + 1)
            {
                letter[s.charAt(l++)-'A']--;

            }
            res = Math.max(res,r-l+1);
        }
        
        
        return res;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "BBBB";
		int k = 0;
		var lpcr = new LongRepeatingCharacterReplace();
        int repeat_num = lpcr.characterReplacement(s, k);
		System.out.println(repeat_num);
	}

}
