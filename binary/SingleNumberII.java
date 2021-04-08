package binary;

/**
 * 137. Single Number II
 * 
 * Given a non-empty array of integers, every element appears three times except for one, 
 * which appears exactly once. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without 
using extra memory?

Example 1:

Input: [2,2,3,2]
Output: 3
Example 2:

Input: [0,1,0,1,0,1,99]
Output: 99
 * @author yzzyq
 *
 */

// 其他元素出现了三次，找出出现一次的

public class SingleNumberII {
	// 思路可以是，创造一个32位的数，将之也变成二进制数，
	// 将它们相加，最终对之进行3取余，不是0的，就是出现的1
    public int singleNumber(int[] nums) {
        int res = 0;
        
        for(int index = 0; index < 32; index++) {
        	int sum = 0;
        	for(int num:nums) {
        		sum += (num >> index) & 1;
        	}
        	res |= (sum%3) << index;
        	
        }
        
        return res;
    }
    
    // 还有另一种思路，设置三个数，分别看第一位，第二位和第三位
    public int singleNumber_1(int[] nums) {
        int first = 0, second = 0, third = 0;
        
        for(int num:nums) {
        	// 先更新第二个数
        	second |= first&num;
        	first ^= num;
        	
        	third = first&second;
        	// 之后纠正第一个数和第二个数
        	first &= ~third;
        	second &= ~third;
        }
        
        return first;
    }
    
    // 还有一种思路，整体的想法和上面的想法一样，不过是变成了俩位来求
    public int singleNumber_2(int[] nums) {
        int first = 0, second = 0;
        
        for(int num:nums) {
        	// 这种写法非常简洁
        	// 大概意思就是第一位如果相同了，那么看第二位是否存在，存在就变成0
        	// 特别要理解~取反位运算符的意思，当second为0的时候，
        	// first ^num不管是什么，都是全部通过
        	// 当second与first ^num相同了，那么就是全部都不通过。
        	first = ~second & (first ^num);
        	second = ~first &(second ^num);
        }
        
        return first;
    }

	public static void main(String[] args) {
		int[] nums = {0,1,0,1,0,1,99};
		var snii = new SingleNumberII();
		int num = snii.singleNumber_2(nums);
		System.out.println(num);
		int three = 1;
		System.out.println(1 & -1);
		System.out.println(1&~1);
	}

}
