package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**30 Substring with Concatenation of All Words
 * 
 * You are given a string s and an array of strings words of the same length. 
 * Return all starting indices of substring(s) in s that is a concatenation of each word 
 * in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.

Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []
Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
 

Constraints:

1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.
 *  
 */

//所有字符串的拼接

public class SubstringWithConcatenationOfAllWords {
	
	// 能过，效率很低，空间很大，10%，这种暴力破解不可取，直接依次比较字符串的map是否相同即可
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> subString_num = new ArrayList<Integer>();
        int all_length = words.length*words[0].length();
        int one_length = words[0].length();
        if(s.length() == 0 || words.length == 0) return subString_num;
        if(s.length() < all_length) return subString_num;
    	
        Map<String, Integer> string_num = new HashMap<String, Integer>();
        
        for(String one_word:words) {
        	if(!string_num.containsKey(one_word)) string_num.put(one_word, 0);
        	string_num.put(one_word, string_num.get(one_word)+1);
        }
        
        for(int index = 0; index + all_length - 1 < s.length(); index++) {
        	String sub_string = s.substring(index,index+all_length);
        	Map<String, Integer> string_num_temp = new HashMap<String, Integer>();
        	for(int one_index = 0; one_index + one_length - 1 < sub_string.length(); one_index=one_index+one_length) {
        		String one_string = sub_string.substring(one_index, one_index + one_length);
        		if(!string_num_temp.containsKey(one_string)) string_num_temp.put(one_string, 0);
        		string_num_temp.put(one_string, string_num_temp.get(one_string)+1);
        	}
        	if(string_num.equals(string_num_temp)) subString_num.add(index);
        }
        
        return subString_num;
    }
    
    // 使用滑动窗口
    public List<Integer> findSubstring_1(String s, String[] words) {
    	List<Integer> subString_num = new ArrayList<Integer>();
        int all_length = words.length*words[0].length();
        int one_length = words[0].length();
        if(s.length() == 0 || words.length == 0) return subString_num;
        if(s.length() < all_length) return subString_num;
    	
        HashMap<String, Integer> string_num = new HashMap<String, Integer>();
        
        for(String one_word:words) {
        	string_num.put(one_word, string_num.getOrDefault(one_word, 0)+1);
        }
        
        // 使用滑动窗口，最主要面临的是起点选择，因为匹配的字符串起点可以在任意位置，
        // 暴力破解选择起点，就是依次看，
        // 窗口就不需要依次看，因为字符串都是一样长度的，那么，只要一个子串依次遍历，那么如果有匹配的，肯定会有匹配上的
        for(int index = 0; index < words[0].length(); index++) {
        	int left = index, right = index, count = 0;
            HashMap<String, Integer> string_num_temp = new HashMap<String, Integer>();
            
            while(right + one_length - 1 < s.length()) {
            	String sub_string = s.substring(right, right+one_length);
            	right += one_length;
            	if(!string_num.containsKey(sub_string)) {
            		count = 0;
            		left = right;
            		string_num_temp.clear();
            	}else {
            		count++;
            		string_num_temp.put(sub_string, string_num_temp.getOrDefault(sub_string, 0)+1);
            		
            		while(string_num_temp.get(sub_string) > string_num.get(sub_string)) {
                		String left_string = s.substring(left,left+one_length);
                		string_num_temp.put(left_string, string_num_temp.get(left_string) - 1);
                		count--;
                		left += one_length;
                	}
                	
                	if(count == words.length) subString_num.add(left);
            		
            	}
            	
            }
        }
        
        return subString_num;
    }
    

	public static void main(String[] args) {
		var swcoa = new SubstringWithConcatenationOfAllWords();
		String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
		String[] words = {"fooo","barr","wing","ding","wing"};
        List<Integer> subString_num = swcoa.findSubstring_1(s, words);
        for(int i = 0; i < subString_num.size(); i++) {
        	System.out.print(subString_num.get(i) + ",");
        }
	}

}
