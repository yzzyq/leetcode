package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 1100. 
 * 
 * ����һ���ַ��� S���ҳ����г���Ϊ K �Ҳ����ظ��ַ����Ӵ������㷵��ȫ������Ҫ����Ӵ��� ��Ŀ��

ʾ�� 1��

���룺S = "havefunonleetcode", K = 5
�����6
���ͣ�
������ 6 ������������Ӵ����ֱ��ǣ�'havef','avefu','vefun','efuno','etcod','tcode'��
1
2
3
4
ʾ�� 2��

���룺S = "home", K = 5
�����0
���ͣ�
ע�⣺K ���ܻ���� S �ĳ��ȡ�����������£����޷��ҵ��κγ���Ϊ K ���Ӵ���
1
2
3
4
��ʾ��

1 <= S.length <= 10^4
S �е������ַ���ΪСдӢ����ĸ
1 <= K <= 10^4
 * 
 * 
 * @author yzzyq
 *
 */

// ������֮ǰ����Ŀ���ƣ�����ʹ�û�������

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
