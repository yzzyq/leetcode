package binarySearch;
//Given a sorted array and a target value, return the index if the target is found. 
//If not, return the index where it would be if it were inserted in order.
//
//You may assume no duplicates in the array.
//
//Example 1:
//
//Input: [1,3,5,6], 5
//Output: 2
//Example 2:
//
//Input: [1,3,5,6], 2
//Output: 1
//Example 3:
//
//Input: [1,3,5,6], 7
//Output: 4
//Example 4:
//
//Input: [1,3,5,6], 0
//Output: 0



public class SearchInsertPos {
	
	// 二分查找位置
    public int searchInsert(int[] nums, int target) {
    	if(nums.length == 0) return 0;
    	int start = 0;
    	int end = nums.length - 1;
    	while(start <= end) {
    		int middle = (start + end) / 2;
    		if(target == nums[middle]) {
    			return middle;
    		}else if(target > nums[middle]) {
    			start = middle + 1;
    		}else if(target < nums[middle]) {
    			end = middle - 1;
    		}
    	}
        // 要注意返回的位置，因为它可能会比插入位置的值要小
    	return start;
    }

	public static void main(String[] args) {
//		int a = 1/2;
//		System.out.println();
		int[] input_num = {1,3,5,6};
		int insert_value = 0;
		SearchInsertPos sip = new SearchInsertPos();
		int index = sip.searchInsert(input_num, insert_value);
        System.out.println(index);
	}

}
