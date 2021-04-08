package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 438.
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p 
will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

// 输出s中是p的异位词的子串的索引位置。异位词就是字母相同，但是顺序不同

public class FindAllAnagrams {
	
	// 使用滑动窗口来做，
    public List<Integer> findAnagrams(String s, String p) {
    	List<Integer> angagrams_list = new ArrayList<Integer>();
        if(s.length() < p.length()) return angagrams_list;
        if(s.length() == 0 || p.length() == 0) return angagrams_list;
        int[] p_num = new int[26];
        int[] s_num = new int[26];
        
        int size = 0;
        char[] p_char = p.toCharArray();
        for(char one_char:p_char) {
        	if(p_num[one_char - 'a'] == 0) size++;
        	p_num[one_char - 'a']++;
        }
        
        int match = 0;
        int left = 0;
        int right = 0;
        while(right < s.length()) {
        	char one = s.charAt(right);
        	s_num[one - 'a']++;
        	if(s_num[one - 'a'] == p_num[one - 'a']) match++;
        	while(match == size) {
        		if(right - left + 1 == p.length()) {
        			angagrams_list.add(left);		
        		}
        		s_num[s.charAt(left) - 'a']--;
        		if(s_num[s.charAt(left) - 'a'] < p_num[s.charAt(left) - 'a']) match--;
        		left++;
        	}		
        	right++;	
        }
        
        return angagrams_list;
    }

	public static void main(String[] args) {
		FindAllAnagrams faa = new FindAllAnagrams();
		String s = "abab";
		String p = "ab";		
        List<Integer> list_anagram = faa.findAnagrams(s, p);
        System.out.println(list_anagram);
	}

}
