package noClassification;
import java.util.Arrays;

/**
 * 628. Maximum Product of Three Numbers
 * Given an integer array, find three numbers whose product is maximum and 
 * output the maximum product.

Example 1:

Input: [1,2,3]
Output: 6
 

Example 2:

Input: [1,2,3,4]
Output: 24
 * 
 * 
 * @author yzzyq
 *
 */

// 盛趣的笔试题，当时脑子抽了，一直想动态规划来解

public class MaximumProductofThreeNumbers {
	
	// 直接就看前面三个和最后俩个的大小，这个已经包含了所有的情况
	public int maximumProduct(int[] nums) {
        int nums_len = nums.length;
		Arrays.sort(nums);
		return Math.max(nums[nums_len - 1]*nums[0]*nums[1], nums[nums_len - 1]*nums[nums_len - 2]*nums[nums_len - 3]);
    }

	public static void main(String[] args) {
		int[] nums = {1,2,3,4};
		var mptn = new MaximumProductofThreeNumbers();
        int max_value = mptn.maximumProduct(nums);
		System.out.println(max_value);
	}

}
