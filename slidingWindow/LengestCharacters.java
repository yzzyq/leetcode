package slidingWindow;
import java.util.HashMap;
import java.util.Map;

/*
 * 
 * 3. 
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

*/

public class LengestCharacters {
	
	// 方法的时间和空间复杂度极其差，运行时间长，并且空间使用多
    public int lengthOfLongestSubstring(String s) {
        String concat_str = "";
        int max_str_len = 0;
        for(int index = 0; index < s.length(); index++) {
			char one_char = s.charAt(index);
			int char_pos = concat_str.indexOf(one_char);
			if (char_pos == -1) {
				concat_str += one_char;
			} else {
				max_str_len = max_str_len > concat_str.length() ? max_str_len : concat_str.length();
				concat_str = concat_str.substring(char_pos + 1, concat_str.length()) + one_char;
			}
        }       		
        max_str_len = max_str_len > concat_str.length() ? max_str_len : concat_str.length();		
        return max_str_len;
    }
    
    // 双指针法，这种方法就是维护一个滑动窗口，并且使用字典来维护字符出现的位置，当然这里是维护最后一次出现的位置
    // 时间降低了10S，空间降低了10M
    public int lengthOfLongestSubstring_perfect(String s) {
    	// 简单的字符串直接判断即可
    	if(0 == s.length()) return 0;
    	if(1 == s.length()) return 1;
    	int max_str_len = 1;
        // 保存字符位置的字典
    	Map<Character, Integer> pos_map = new HashMap();

    	for(int left = -1, right = 0; right < s.length(); right++) {
    		char one_char = s.charAt(right);
    		if(pos_map.containsKey(one_char)) {
    			// 必须要有max，比如abba，在判断a的时候，如果没有max，那么left就会移到0
    			left = Math.max(left, pos_map.get(one_char));
    		}
    		pos_map.put(one_char, right);
    		max_str_len = Math.max(max_str_len, right - left);
    	}
    	return max_str_len;
    } 
	
    // 单指针方法，只需要左边的指针移动，并且使用布尔数组来判断
    // 速度很快，只需要2S，只需要39M
    public int lengthOfLongestSubstring_2(String s) {
        if(s.length()==0){
            return 0;
        }
        int left=0;
        boolean []arr=new boolean[128];
        int max=1,i=0;
        for(i=0;i<s.length();i++){
            if(!arr[s.charAt(i)]){
                arr[s.charAt(i)]=true;
            }
            else{
                max=Math.max(max,(i-left));
                while(left<i&&s.charAt(left)!=s.charAt(i)){
                    arr[s.charAt(left++)]=false;    //reset the chaaracters that occured in between to false.
                }
                left++;
            }
        }
        max=Math.max(max,(i-left));
        return max;
    }
	

	public static void main(String[] args) {
		String input_str = "abba";
		LengestCharacters lc = new LengestCharacters();
		int len = lc.lengthOfLongestSubstring_2(input_str);
        System.out.println(len);
	}

}
