package binarySearch;
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//
//Find the minimum element.
//
//You may assume no duplicate exists in the array.
//
//Example 1:
//
//Input: [3,4,5,1,2] 
//Output: 1
//Example 2:
//
//Input: [4,5,6,7,0,1,2]
//Output: 0

// �������ظ�Ԫ��

public class FindMinRotatedArray {
	// ʹ�ö��ֵ�˼�룬������ȫ������һ��
	// �ٶȺܿ�
    public int findMin(int[] nums) {
    	if(nums.length == 1) return nums[0]; 
        int start = 0;
        int end = nums.length - 1;
        int target = nums[end];
        while(start + 1 < end) {
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
		int[] input_num = {1,2,3};
		FindMinRotatedArray fmra = new FindMinRotatedArray();
        int min_num = fmra.findMin(input_num);
        System.out.println(min_num);
	}

}
