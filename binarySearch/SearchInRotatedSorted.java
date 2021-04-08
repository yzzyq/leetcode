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

// 无重复元素，查找

public class SearchInRotatedSorted {
    // 将之变成俩个序列，分别使用顺序查找,速度有点慢
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
	
	// 使用二分法，分四种情况进行讨论，速度可以，内存消耗大
	public int search(int[] nums, int target) {
		if(nums.length == 0) return -1;
		int start = 0;
		int end = nums.length - 1;
		
		// 这种start = middle写法中，判断条件不能是start < end，这样会导致它不变
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
		
		// 这样判断最好，分清楚
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
