package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 1100. 
 * 
 * 给你一个字符串 S，找出所有长度为 K 且不含重复字符的子串，请你返回全部满足要求的子串的 数目。

示例 1：

输入：S = "havefunonleetcode", K = 5
输出：6
解释：
这里有 6 个满足题意的子串，分别是：'havef','avefu','vefun','efuno','etcod','tcode'。
1
2
3
4
示例 2：

输入：S = "home", K = 5
输出：0
解释：
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。
1
2
3
4
提示：

1 <= S.length <= 10^4
S 中的所有字符均为小写英文字母
1 <= K <= 10^4
 * 
 * 
 * @author yzzyq
 *
 */

// 还是与之前的题目相似，具体使用滑动窗口

public class FindKLengthSubstringWithNoRepeatedchar {

	public int numKLenSubstrNoRepeats(String s, int k) {
		if(s.length() == 0 || k == 0 || s.length() < k) return 0;
		
		int sum_num = 0;
		int left = 0;
		Map<Character, Integer> char_num = new HashMap<Character, Integer>();
		
		for(int right = 0; right < s.length(); right++) {
			char right_char = s.charAt(right);
			if(!char_num.containsKey(right_char)) char_num.put(right_char, 0); 
			char_num.put(right_char, char_num.get(right_char) + 1);
			
			while(char_num.size() == k) {
				char left_char = s.charAt(left);
				if(right - left + 1 == char_num.size()) sum_num++; 
				char_num.put(left_char, char_num.get(left_char) - 1);
				
				if(char_num.get(left_char) == 0) char_num.remove(left_char);
				
				left++;
			}
		}
		return sum_num;
	}
	
	
	public static void main(String[] args) {
		var leng_str = new FindKLengthSubstringWithNoRepeatedchar();
		String s = "homee";
		int k = 5;
        int sum_num = leng_str.numKLenSubstrNoRepeats(s, k);
		System.out.println(sum_num);
	}

}
