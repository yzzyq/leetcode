package binary;

/**
 * 338. Counting Bits
 * 
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
 * calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do 
it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in 
c++ or in any other language.
 * 
 * @author yzzyq
 *
 */

// 计算连续数字中1的个数

public class CountingBits {
	
	// 动态规划，直接设置每一次位运算的结果推导
	public int[] countBits(int num) {
		int[] results = new int[num+1];
        if(num == 0) return results;
        
        for(int index = 1; index <= num; index++) {
        	results[index] = 1 + results[index&(index - 1)];
        }
        
        
		return results;
    }
	
	// 动态规划，思想和上面的不同，这个就是看最高有效位
	public int[] countBits_1(int num) {
		int[] results = new int[num+1];
        if(num == 0) return results;
        results[1] = 1;
        int start = 0, b = 1;
        
        while(b <= num) {
        	
        	while(start < b && start + b <= num) {
        		results[start+b] = 1 + results[start];
        		start++;
        	}
        	
        	start = 0;
        	b <<= 1;
        	
        }
        
        
		return results;
    }
	
	
	// 动态规划，最低有效位，就是利用对2取余和除数来计算，这是因为二者只差一位
	public int[] countBits_2(int num) {
		int[] results = new int[num+1];
        if(num == 0) return results;
        for(int index = 1; index <= num; index++) {
        	results[index] = results[index / 2] + index % 2; 
        }
		return results;
    }

	public static void main(String[] args) {
		int num = 5;
		var cb = new CountingBits();
        int[] temp = cb.countBits_2(num);
        for(int one:temp) {
        	System.out.print(one + ",");
        }
	}

}
