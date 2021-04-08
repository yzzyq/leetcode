package dp;

/**
 * 647. Palindromic Substrings
 * 
 * Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different 
substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
 * 
 * @author yzzyq
 *
 */

// 查看字符串中有多少个回文子串

public class PalindromicSubstrings {
	
	// 动态规划问题，不过运行时间较长，并且运行空间较大
    public int countSubstrings(String s) {
    	if(s.length() == 0) return 0;
        if(s.length() == 1) return 1;
        
        int s_len = s.length();
        
        boolean[][] is_palind = new boolean[s_len][s_len];
        int max_num = 0;
    	
        for(int index = s_len - 1; index >= 0; index--) {
        	
        	for(int com_index = s_len - 1; com_index >= index; com_index--) {
        		// 一句话即可进行判断关系
        		is_palind[index][com_index] = (s.charAt(index) == s.charAt(com_index) && 
        				(com_index - index <= 2 || is_palind[index + 1][com_index - 1]));
        		if(is_palind[index][com_index]) max_num++;
        		
//        		if(com_index == index) {
//        			is_palind[index][com_index] = true;
//        			max_num++;
//        		}else {
//        			if(s.charAt(index) == s.charAt(com_index)) {
//        				is_palind[index][com_index] = true;
//        				if(com_index - 1 >= index + 1) is_palind[index][com_index] = is_palind[index + 1][com_index - 1];
//        			    if(is_palind[index][com_index]) max_num++;
//        			}else {
//        				is_palind[index][com_index] = false;
//        			}
//        		}
        	}
        }
        return max_num;     
    }
    
    // 最基本的判断回文串的方式，就是暴力遍历，由中心字符进行中心拓展
    public int countSubstrings_1(String s) {
    	if(s.length() == 0) return 0;
        if(s.length() == 1) return 1;
        
        char[] s_char = s.toCharArray();
        
        int palindrom_num = 0;
        
        for(int start = 0; start < s.length(); start++) {
        	palindrom_num += getNum(start, start, s_char);
        	palindrom_num += getNum(start, start + 1, s_char);
        }
        
    	return palindrom_num;
    }
    
    public int getNum(int left, int right, char[] s_char) {
    	int num = 0;
    	while(left >= 0 && right < s_char.length && s_char[left] == s_char[right]) {
    		left--;
    		right++;
    		num++;
    	}
    	return num;
    }
    
    
    // 经典马拉车算法
    public int countSubstrings_2(String s) {
    	if(s.length() == 0) return 0;
        if(s.length() == 1) return 1;
        
        // 对字符串进行重新组织
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("^#");
    	for(int i = 0; i < s.length(); i++) {
    		sb.append(s.charAt(i));
    		sb.append("#");
    	}
    	sb.append("$");
    	
    	int sb_len = sb.length();
    	int[] par_len = new int[sb_len];
    	
    	int r_pos = 0, m_pos = 0, num = 0;
    	for(int index = 1; index < sb_len - 1; index++) {
    		// 先判断它的镜像是否超过了边界距离
    		par_len[index] = index < r_pos?Math.min(r_pos - index, par_len[2*m_pos - index]):1; 
            // 进行中心拓展
    		while(sb.charAt(index+par_len[index]) == sb.charAt(index-par_len[index])) {
    			par_len[index]++;
    		}
    		
    		// 维护
    		if(index + par_len[index] > r_pos) {
    			m_pos = index;
    			r_pos = par_len[index] + index;
    		}
    		
    		// 统计
    		num += par_len[index]/2;
    	}
    	
    	return num;
    }
    

	public static void main(String[] args) {
		String s = "aaa";
        var ps = new PalindromicSubstrings();
        int num = ps.countSubstrings_2(s);
        System.out.println(num);
	}

}
