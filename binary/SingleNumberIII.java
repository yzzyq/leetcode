package binary;
import java.util.Arrays;

/**260. Single Number III
 * 
 * Given an integer array nums, in which exactly two elements appear only once and all 
 * the other elements appear exactly twice. Find the two elements that appear only once. 
 * You can return the answer in any order.

Follow up: Your algorithm should run in linear runtime complexity. Could you implement it 
using only constant space complexity?

Example 1:

Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:

Input: nums = [-1,0]
Output: [-1,0]
Example 3:

Input: nums = [0,1]
Output: [1,0]
 

Constraints:

1 <= nums.length <= 30000
 Each integer in nums will appear twice, only two integers will appear once.
 * 
 * 
 * @author yzzyq
 *
 */


public class SingleNumberIII {
	
	// 感觉还行
	public int[] singleNumber(int[] nums) {
		if(nums.length == 1 || (nums.length == 2 && nums[0] != nums[1])) return nums;
		
		Arrays.sort(nums);
		
		int[] results = new int[2];
		int index = 0;
		int num_index = 0;
		for(; index < nums.length - 1; ) {
			if(nums[index] == nums[index + 1]) {
				index += 2;
			}else {
				results[num_index] = nums[index];
				index++;
				num_index++;
			}
		}
        
		if(index == nums.length - 1) results[num_index] = nums[index];
		
		return results;
    }
	
	// 当然最简单最快的还是位运算
	// 思路还是和以前的一样，但是这出现了俩个，不能直接异或运算，如果我们直接异或的话，能得到什么
	// 能得到这俩个数在哪位上不同，那么根据这个位数的不同，我就可以将之分组，那么就转化为一个
	// 绝妙的思路
	public int[] singleNumber_1(int[] nums) {
		if(nums.length == 1 || (nums.length == 2 && nums[0] != nums[1])) return nums;
		
		// 先要标记出第几位不同
		
		// 这边标记第几位不同，实现思路还是异或运算，初始值为1，化为二进制就是第一位不同
		int differ_pos = 1;
		int sum_all = 0;
		int[] results = new int[2];
		for(int one:nums) {
			sum_all ^= one;
		}
		
		while(true) {
			if((sum_all & 1) == 1) {
				break;
			}
			differ_pos = differ_pos << 1;
			sum_all = sum_all >> 1;
		}
		
		for(int one:nums) {
			if((one & differ_pos) == 0) {
				results[0] ^= one;
			}else {
				results[1] ^= one;
			}
		}
		
		return results;
	}

	public static void main(String[] args) {
		int[] nums = {1,2,1,3,2,5};
        var sn = new SingleNumberIII();
        int[] result = sn.singleNumber_1(nums);
        for(int one:result) {
        	System.out.print(one + ",");
        }
	}

}
