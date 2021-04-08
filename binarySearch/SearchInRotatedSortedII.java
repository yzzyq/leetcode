package binarySearch;
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
//
//You are given a target value to search. If found in the array return true, otherwise return false.
//
//Example 1:
//
//Input: nums = [2,5,6,0,0,1,2], target = 0
//Output: true
//Example 2:
//
//Input: nums = [2,5,6,0,0,1,2], target = 3
//Output: false
//Follow up:
//
//This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
//Would this affect the run-time complexity? How and why?

// ���ظ�Ԫ��

public class SearchInRotatedSortedII {
    
    public boolean search(int[] nums, int target) {
    	if(nums.length == 0) return false;
		int start = 0;
		int end = nums.length - 1;
		
		// ����start = middleд���У��ж�����������start < end�������ᵼ��������
		while(start + 1 < end) {
			if(start < end && nums[start] == nums[start + 1]) start++;
			if(start < end && nums[end] == nums[end - 1]) end--;
			int middle = start + (end - start)/2;
			if(target == nums[middle]) return true;
			
			
			if(nums[start] < nums[middle]) {
				if(target <= nums[middle] && target >= nums[start]) {
					end = middle;
				}else {
					start = middle;
				}
			// һ��Ҫ������仰������ֱ��дelse,��Ȼ����{3��1��1}�����޷��ж�
			}else if(nums[end] > nums[middle]){
				if(target >= nums[middle] && target <= nums[end]) {
					start = middle;
				}else {
					end = middle;
				}
			}
		}
		
		if(nums[start] == target || nums[end] == target) return true;
		return false;
    }
	
	public static void main(String[] args) {
		int[] input_nums = {3,1,1};
        int target = 1;
        SearchInRotatedSortedII sirs = new SearchInRotatedSortedII();
        boolean is_exist = sirs.search(input_nums, target);
        System.out.println(is_exist);

	}

}
