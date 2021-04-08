package binary;

/**
 * 190. Reverse Bits
 * 
 * Reverse bits of a given 32 bits unsigned integer.

Note:

Note that in some languages such as Java, there is no unsigned integer type. In this case, 
both input and output will be given as a signed integer type. They should not affect your 
implementation, as the integer's internal binary representation is the same, whether it is 
signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, 
in Example 2 above, the input represents the signed integer -3 and the output represents the 
signed integer -1073741825.
Follow up:

If this function is called many times, how would you optimize it?

 

Example 1:

Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned 
integer 43261596, so return 964176192 which its binary representation is 
00111001011110000010100101000000.
Example 2:

Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned 
integer 4294967293, so return 3221225471 which its binary representation is 
10111111111111111111111111111111.
 
Constraints:

The input must be a binary string of length 32
 * 
 * @author yzzyq
 *
 */

// 颠倒二进制数

public class ReverseBits {
	
	// 逐位进行颠倒即可,
    public int reverseBits(int n) {
        int result = 0;
        for(int pos = 0; pos < 32; pos++) {
        	result += (n&1) << (31 - pos);
        	n >>= 1;
        }
    	
        return result;
    }
    
    // 每一位都直接放到颠倒的位置上
    public int reverseBits_1(int n) {
        int result = 0;
        for(int pos = 0; pos < 32; pos++) {
        	result ^= ((n >> pos) & 1) == 1 ? (1 << (31 - pos)):0;
        }
    	
        return result;
    }
    
    // 分治移位合并，因为是固定的32位，那么前16位和后16位颠倒，之后分别颠倒
    public int reverseBits_2(int n) {
    	n = (n >>> 16) | (n << 16);
    	n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
    	n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);    	
    	n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    // 与上面的思想一样，但是写法优化了，这个也是java中Integer.reverse(int i)按位取反的源代码
    // 
    public int reverseBits_3(int n) {
    	// 整个代码分成了四步
    	n = ((n & 0x55555555) << 1) | ((n >>> 1) & 0x55555555);
    	n = ((n & 0x33333333) << 2) | ((n >>> 2) & 0x33333333);
    	n = ((n & 0x0f0f0f0f) << 4) | ((n >>> 4) & 0x0f0f0f0f);
    	n = (n << 24) | ((n & 0xff00) << 8) | ((n >>> 8) & 0xff00) | (n >>> 24);
    	return n;
    }
    
    
	public static void main(String[] args) {
		int n = 43261596;
		var rb = new ReverseBits();
		int rever_num = rb.reverseBits_3(n);
        System.out.println(rever_num);
	}

}
