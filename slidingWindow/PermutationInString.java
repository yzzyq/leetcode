package slidingWindow;

//567. Permutation in String
//Given two strings s1 and s2, write a function to return true if s2 contains 
//the permutation of s1. In other words, one of the first string's permutations is the 
//substring of the second string.
//
//Example 1:
//
//Input: s1 = "ab" s2 = "eidbaooo"
//Output: True
//Explanation: s2 contains one permutation of s1 ("ba").
//Example 2:
//
//Input:s1= "ab" s2 = "eidboaoo"
//Output: False
// 
//
//Constraints:
//
//The input strings only contain lower case letters.
//The length of both given strings is in range [1, 10,000].


// 第二个字符串的排列是不是第一个字符串的子串

public class PermutationInString {
	
	// 这个使用滑动窗口，这个与之前的不同，之前那个窗口大小不固定，现在这个找的是子串，
	// 那么窗口大小就是固定的，区间长度固定。
    public boolean checkInclusion(String s1, String s2) {
    	if(s1 == "") return true;
    	if(s2 == "") return false;
    	int s1_len = s1.length();
    	if(s1_len > s2.length()) return false;
    	int[] s1_num = new int[32];
    	char[] s1_char = s1.toCharArray();
    	int size = 0;
    	for(char one_char:s1_char) {
    		if(s1_num[one_char - 'a'] == 0) size += 1;
    		s1_num[one_char - 'a'] += 1;
    	}
    	int[] s2_num = new int[32];
    	
    	int match = 0;
    	
    	int left = 0;
    	int right = 0;
    	while(right < s2.length()) {
            s2_num[s2.charAt(right) - 'a']++;
    		if(s2_num[s2.charAt(right) - 'a'] == s1_num[s2.charAt(right) - 'a']) match++;
    		while(match == size) {
    			if(right - left + 1 == s1_len) return true;
    			s2_num[s2.charAt(left) - 'a']--;
    			if(s2_num[s2.charAt(left) - 'a'] < s1_num[s2.charAt(left) - 'a']) match--;
    			left++;
    		}	
    		right++;
    	}
    	
    	return false;
    	
    }

	public static void main(String[] args) {
		PermutationInString pis = new PermutationInString();
		String s1 = "abcdxabcde";
		String s2 = "abcdeabcdx";
		boolean isClusion = pis.checkInclusion(s1, s2);
        System.out.println(isClusion);
	}

}
