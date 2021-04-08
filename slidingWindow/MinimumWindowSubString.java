package slidingWindow;

import java.util.HashMap;
import java.util.Map;

//76.
//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//
//Example:
//
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
//Note:
//
//If there is no such window in S that covers all characters in T, return the empty string "".
//If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

// 求最小子串

public class MinimumWindowSubString {
	
	// 使用的是滑动窗口思想
    public String minWindow(String s, String t) {
        int t_len = t.length();
        Map<Character, Integer> t_num = new HashMap<Character, Integer>();
        for(char one:t.toCharArray()) {
        	if(!t_num.containsKey(one)) {
        		t_num.put(one, 0);
        	}
        	t_num.put(one, t_num.get(one) + 1);
        }
        int min_left = 0;
        int min_right = s.length()-1;
        boolean is_exist = false;
        
        for(int left = 0, right = 0; right < s.length(); right++) {
        	char one_char = s.charAt(right);
    		if(t_num.containsKey(one_char)) {
    			if(t_num.get(one_char) > 0) {
    				t_len--;
    			}
    			t_num.put(one_char, t_num.get(one_char)-1);
    		}
        	if(t_len <= 0) {
        		while(!t_num.containsKey(s.charAt(left)) || t_num.get(s.charAt(left)) < 0) {
        			
        			if(t_num.containsKey(s.charAt(left)) && t_num.get(s.charAt(left)) < 0) {
        				t_num.put(s.charAt(left),t_num.get(s.charAt(left))+1);
        			}
        			left++;
        		}
        		
        		if(min_right - min_left >= right - left) {
        			min_right = right;
        			min_left = left;
        			is_exist = true;
        		}
        		
        		t_num.put(s.charAt(left),t_num.get(s.charAt(left))+1);
        		left++;
        		t_len++;
        	}
                	
        }	
    	return is_exist?s.substring(min_left, min_right + 1):"";
    }

	public static void main(String[] args) {
		MinimumWindowSubString mws = new MinimumWindowSubString();
        String s = "a";
		String t = "a";
		String min_str = mws.minWindow(s,t);
		System.out.println("输出:" + min_str);
	}

}
