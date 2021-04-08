package binarySearch;
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
//(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
//You are given a target value to search. If found in the array return its index, 
//otherwise return -1.
//
//You may assume no duplicate exists in the array.
//
//Your algorithm's runtime complexity must be in the order of O(log n).
//
//Example 1:
//
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
//Example 2:
//
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1

// ���ظ�Ԫ�أ�����

public class SearchInRotatedSorted {
    // ��֮����������У��ֱ�ʹ��˳�����,�ٶ��е���
//	public int search(int[] nums, int target) {
//		if(nums.length == 0) return -1;
//		int one_point = 0;
//		int two_point = nums.length - 1;
//		boolean is_one_search = true, is_two_search = true;
//		while(one_point < nums.length && two_point >= 0 && (is_one_search || is_two_search)) {
//			if(target == nums[one_point]) {
//				return one_point;
//			}else if(target > nums[one_point]) {
//				one_point++;
//			}else if(target < nums[one_point]) {
//				is_one_search = false;
//			}
//			
//			if(target == nums[two_point]) {
//				return two_point;
//			}else if(target < nums[two_point]) {
//				two_point--;
//			}else if(target > nums[two_point]) {
//				is_two_search = false;
//			}
//			
//		}
//		return -1;
//    }
	
	// ʹ�ö��ַ�������������������ۣ��ٶȿ��ԣ��ڴ����Ĵ�
	public int search(int[] nums, int target) {
		if(nums.length == 0) return -1;
		int start = 0;
		int end = nums.length - 1;
		
		// ����start = middleд���У��ж�����������start < end�������ᵼ��������
		while(start + 1 < end) {
			int middle = start + (end - start)/2;
			if(target == nums[middle]) return middle;
			if(nums[start] < nums[middle]) {
				if(target <= nums[middle] && target >= nums[start]) {
					end = middle;
				}else {
					start = middle;
				}
				
			}else if(nums[end] > nums[middle]){
				if(target >= nums[middle] && target <= nums[end]) {
					start = middle;
				}else {
					end = middle;
				}
			}
		}
		
		// �����ж���ã������
		if(nums[start] == target) return start;
		if(nums[end] == target) return end;
		return -1;
	}
	

	public static void main(String[] args) {
		int[] input_nums = {1,3,5};
        int target = 1;
        SearchInRotatedSorted sirs = new SearchInRotatedSorted();
        int index = sirs.search(input_nums, target);
        System.out.println(index);
	}

}
