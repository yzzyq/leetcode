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

// ʢȤ�ı����⣬��ʱ���ӳ��ˣ�һֱ�붯̬�滮����

public class MaximumProductofThreeNumbers {
	
	// ֱ�ӾͿ�ǰ����������������Ĵ�С������Ѿ����������е����
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
