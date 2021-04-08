package binary;
import java.util.Arrays;

/**
 * 136. Single Number
 * 
 * Given a non-empty array of integers nums, every element appears twice except for one. 
 * Find that single one.

Follow up: Could you implement a solution with a linear runtime complexity and without 
using extra memory?

Example 1:

Input: nums = [2,2,1]
Output: 1
Example 2:

Input: nums = [4,1,2,1,2]
Output: 4
Example 3:

Input: nums = [1]
Output: 1
 

Constraints:

1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
 * 
 * @author yzzyq
 *
 */

// 找出只出现一次的数字

public class SingleNumber {
	
	// 先排序后遍历，这种方法的空间复杂度可以，在90以上
    public int singleNumber(int[] nums) {
    	int len = nums.length;
        if(len == 1) return nums[0];
        int one_num = Integer.MAX_VALUE;
        Arrays.sort(nums);
        
        for(int index = 0; index < len - 1; index += 2) {
        	if(nums[index] != nums[index + 1]) {
        		one_num = nums[index];
        		break;
        	}
        }
        
        if(one_num == Integer.MAX_VALUE) return nums[len - 1];
        
        return one_num;
    }
    
    // 可以使用异或骚操作
    public int singleNumber_1(int[] nums) {
        if(nums.length == 1) return nums[0];
        for(int index = 1; index < nums.length; index++) {
        	nums[0] ^= nums[index];
        }
        return nums[0];
    }

	public static void main(String[] args) {
		int[] nums = {2,2,1};
        var sn = new SingleNumber();
        int num = sn.singleNumber_1(nums);
        System.out.println(num);
	}

}
