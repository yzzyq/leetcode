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

// ���������ͬ�ַ�����ִ�

public class LongestSubstringWithAtMostTwoChar {
	
	// �������ڣ�ά��һ��map���ж��ַ�����
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
	
	// ��ʵ������Ҫ����μ�¼�ַ����ִ��������ַ�����Ϊ0�Ļ�������ȥһ���ַ���Ȼ��Ϳ�������ѭ��
	// ��ô��Ȼ��Ҫ�ж��ַ��Ƿ���֣���ôҲ���Լ�¼���һ���ַ����ֵ�λ�ã�ͨ��λ�����ж��Ƿ����ظ�������ظ��ľͿ���ɾ��
	
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
