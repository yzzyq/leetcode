package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 139. Word Break
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty 
 * words, determine if s can be segmented into a space-separated sequence of one or 
 * more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 * 
 * @author yzzyq
 *
 */

// 单词能够被拆分成字典中的字符串

public class WordBreak {
	
	// 存在性动态规划问题
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<String>(wordDict);
        
        boolean[] is_break = new boolean[s.length() + 1]; 
        
        is_break[0] = true;
        
        for(int index = 1; index <= s.length(); index++) {
        	
        	for(int middle = 0; middle < index; middle++) {
        		if(is_break[middle] && wordDict.contains(s.substring(middle, index))) {
        			is_break[index] = true;
        			break;
        		}
        	}
        	
        }
        
        return is_break[s.length()];
    }
    
    // 回溯法，需要优化，对于特殊的字符会超时
    public boolean wordBreak_1(String s, List<String> wordDict) {
    	return getAllSoultion(s,wordDict,"");
    }
    
    public boolean getAllSoultion(String s, List<String> wordDict, String sb) {
    	
    	if(sb.length() == s.length()) {
    		if(sb.equals(s)) {
    			return true;
    		}else {
    			return false;
    		}
    	}
    	
    	if(sb.length() > s.length()) {
    		return false;
    	}
        
    	for(String one_word:wordDict) {
    		if(getAllSoultion(s,wordDict,sb + one_word)) {
    			return true;
    		}
    	}
    
    	return false;
    }
    
    // 递归,需要加入memorization技术
    public boolean wordBreak_2(String s, List<String> wordDict) {
    	HashSet<String> wordSet = new HashSet<String>();
    	for(String one:wordDict) {
    		wordSet.add(one);
    	}
    	Map<Integer, Boolean> mem_map = new HashMap<Integer, Boolean>();
    	return getAllSoultion_1(s,wordSet,0, mem_map);
    }
    
    public boolean getAllSoultion_1(String s, HashSet<String> wordSet, int start, Map<Integer, Boolean> mem_map) {
    	if(start == s.length()) {
    		return true;
    	}
    	
        if(mem_map.containsKey(start)) {
        	return mem_map.get(start);
        }
    		
    	
    	for(int index = start; index < s.length(); index++) {
    		if(wordSet.contains(s.substring(start, index + 1)) && getAllSoultion_1(s,wordSet,index+1,mem_map)) {
    			mem_map.put(start, true);
    			return true;
    		}
    	}
    	mem_map.put(start, false);
    	return false;
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aaaaaa";
		List<String> wordDict = List.of("aa","a");
        WordBreak wb = new WordBreak();
        boolean is_word = wb.wordBreak_2(s, wordDict);
		System.out.println(is_word); 
	}

}
