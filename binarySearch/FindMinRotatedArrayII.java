package binarySearch;
import java.util.HashSet;
import java.util.Set;

//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
//Find the minimum element.
//
//The array may contain duplicates.
//
//Example 1:
//
//Input: [1,3,5]
//Output: 1
//Example 2:
//
//Input: [2,2,2,0,1]
//Output: 0
//Note:
//
//This is a follow up problem to Find Minimum in Rotated Sorted Array.
//Would allow duplicates affect the run-time complexity? How and why?

// ���ظ�Ԫ��

public class FindMinRotatedArrayII {

	// ��ŵ�˼·��֮ǰ��һ�����ظ�Ԫ�أ��ͼ򵥵İ취����ֱ��ȥ���ظ�Ԫ��
	// ����ʱ��̫�ã��ٶ�̫��������������˼�붼��ࡣ
    public int findMin(int[] nums) {
    	if(nums.length == 1) return nums[0]; 
        int start = 0;
        int end = nums.length - 1;
        int target = nums[end];
        while(start + 1 < end) {
        	while(start < end && nums[end] == nums[end - 1]) {
        		end--;
        	}
        	while(start < end && nums[start] == nums[start + 1]) {
        		start++;
        	}
        	int middle = start + (end - start)/2;
        	if(nums[middle] > target) {
        		start = middle;
        	}else if(nums[middle] < target){
        		end = middle;
        		target = nums[end];
        	}
        }
        return Math.min(nums[start], nums[end]);
    }
	
	public static void main(String[] args) {
		int[] input_nums = {1,1,1};
		FindMinRotatedArrayII fmrai = new FindMinRotatedArrayII();
        int min_nums = fmrai.findMin(input_nums);
        System.out.println(min_nums);
	}

}
