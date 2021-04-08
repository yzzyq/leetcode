package binary;

/**
 * 191. Number of 1 Bits
 * 
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has 
 * (also known as the Hamming weight).

Note:

Note that in some languages such as Java, there is no unsigned integer type. In this case, 
the input will be given as a signed integer type. It should not affect your implementation, 
as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, 
in Example 3 above, the input represents the signed integer. -3.
Follow up: If this function is called many times, how would you optimize it?

 

Example 1:

Input: n = 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
Example 2:

Input: n = 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
Example 3:

Input: n = 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 

Constraints:

The input must be a binary string of length 32
 * 
 * @author yzzyq
 *
 */

// 输入一个32位二进制字符串

public class Numberof1Bits {

	// 直接使用位运算即可
    public int hammingWeight(int n) {
        if(n == 0 || n == 1) return n;
        
        int num = 0;
        
        for(int index = 0; index < 32; index++) {
        	if((n & 1) == 1) {
        		++num;
        	}
        	
        	n >>= 1;
        }
        
        
    	return num;
    }
    
    // 位操作技巧，二进制的技巧，4&3，就会将4最后一个1变成0，那么我们看要经过多少次，就有多少个1
    public int hammingWeight_1(int n) {
    	int num = 0;
    	while(n != 0) {
    		num++;
    		n = n&(n-1);
    	}
    	return num;
    }
	
	public static void main(String[] args) {
		int n = 11;
        var nob = new Numberof1Bits();
        int num = nob.hammingWeight_1(n);
        System.out.println(num);
	}

}
