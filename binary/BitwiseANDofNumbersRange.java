package binary;

/**
 * 201. Bitwise AND of Numbers Range
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers 
 * in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 * 
 * @author yzzyq
 *
 */

// 将范围内全部数字都进行按位与运算

// 每个数字依次的话，就会超时

public class BitwiseANDofNumbersRange {
	
	// 找公共子串
    public int rangeBitwiseAnd(int m, int n) {
    	int shift = 0;
    	
    	while(m < n) {
    		m >>>= 1;
    		n >>>= 1;
    		shift++;
    	}
    	
    	return m << shift;
    }
    
    // Brian Kernighan算法
    public int rangeBitwiseAnd_1(int m, int n) {
    	while(m < n) {
    		n = n & (n-1);
    	}
    	return n;
    }

	public static void main(String[] args) {
		int m = 0, n = 2147483647;
		var banr = new BitwiseANDofNumbersRange();
        int num = banr.rangeBitwiseAnd_1(m, n);
        System.out.println(num);
	}

}
