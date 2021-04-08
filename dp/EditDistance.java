package dp;

/**
 * 72. Edit Distance
 * Share
Given two words word1 and word2, find the minimum number of operations required 
to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 * 
 * @author yzzyq
 *
 */

// 俩个字符串变成一种的步骤，这种一般都是动态规划
// 最主要的就是这三种删除，替换，插入操作对应着矩阵上的哪些

public class EditDistance {
	
	// 动态规划
    public int minDistance(String word1, String word2) {
        int word1_len = word1.length();
        int word2_len = word2.length();
        
        if(word1_len*word2_len == 0) {
        	return word1_len+word2_len;
        }
        
    	int[][] min_nums = new int[word2_len+1][word1_len+1];
    	
    	// 得先初始化
    	for(int com_index = 0; com_index <= word2_len; com_index++) {
    		min_nums[com_index][0] = com_index;
    	}
    	
    	for(int index = 0; index <= word1_len; index++) {
    		min_nums[0][index] = index;
    	}
    	
        
        for(int com_index = 1; com_index <= word2_len; com_index++) {
        	for(int index = 1; index <= word1_len; index++) {
        		if(word1.charAt(index - 1) == word2.charAt(com_index - 1)) {
        			min_nums[com_index][index] = min_nums[com_index - 1][index - 1];
        		}else {
        			// 判断这三种操作中，哪一种是最小操作数
        			// 删除，替换谁更小
        			int temp = Math.min(min_nums[com_index][index-1], 
        					min_nums[com_index -1][index-1]);
        			min_nums[com_index][index] = Math.min(min_nums[com_index - 1][index], temp) + 1;
        		    
        		}
        	}
        }
        return min_nums[word2_len][word1_len];
    }
    
    // 可以使用递归方法，其实能用递归，就能用动态规划
    public int minDistance_1(String word1, String word2) {
    	if(word1.length() == 0 || word2.length() == 0) {
    		return Math.max(word1.length(), word2.length());
    	}
    	
    	// 当它相等得时候，
    	if(word1.charAt(word1.length() - 1) == word2.charAt(word2.length() - 1)) {
    		return minDistance_1(word1.substring(0, word1.length() - 1), 
    				word2.substring(0, word2.length() - 1));
    	}
    	
    	// 不相等得时候就需要进行操作,删除，替换，插入
    	return 1 + Math.min(minDistance_1(word1.substring(0, word1.length() - 1), 
				word2.substring(0, word2.length())), 
    			Math.min(minDistance_1(word1.substring(0, word1.length() - 1), 
	    				word2.substring(0, word2.length() - 1)),
	    				minDistance_1(word1.substring(0, word1.length()), 
	    	    				word2.substring(0, word2.length() - 1))));
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word1 = "horse";
		String word2 = "ros";
		EditDistance ed = new EditDistance();
		int min_oper = ed.minDistance(word1, word2);
        System.out.println(min_oper); 
	}

}
