package noClassification;
import java.util.LinkedList;
import java.util.List;

//There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
//You may assume nums1 and nums2 cannot be both empty.
//
//Example 1:
//
//nums1 = [1, 3]
//nums2 = [2]
//
//The median is 2.0
//Example 2:
//
//nums1 = [1, 2]
//nums2 = [3, 4]
//
//The median is (2 + 3)/2 = 2.5


public class MediumArrays {
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if(1 == nums1.length && 1 == nums2.length) return (double)(nums1[0] + nums2[0])/2;
        double median_num = 0;
        int len_arrays = nums1.length + nums2.length;
        int[] max_nums = new int[len_arrays];
    	int nums1_pos = 0;
    	int nums2_pos = 0;
        float min_value;
    	while((nums1_pos < nums1.length) || (nums2_pos < nums2.length)) {
    		min_value = Float.POSITIVE_INFINITY;
    		if(nums1_pos < nums1.length) min_value = nums1[nums1_pos];
    		
    		if(nums2_pos < nums2.length && min_value > nums2[nums2_pos]) {
    			min_value = nums2[nums2_pos];
    			nums2_pos++;
    		}else {
    			nums1_pos++;
    		}
    		max_nums[nums1_pos + nums2_pos - 1] = (int)min_value;
    	}
    	
    	if(len_arrays % 2 == 1) median_num = max_nums[(int)(len_arrays + 1)/2 - 1];
    	if(len_arrays % 2 != 1) median_num = 
    			(double)(max_nums[(int)((len_arrays + 1)/2) - 1] + max_nums[(int)((len_arrays + 1)/2)])/2;
    	return median_num;
    }

	public static void main(String[] args) {
		int[] nums1 = {100000};
		int[] nums2 = {100001};
		
		MediumArrays ma = new MediumArrays();
		double median_num = ma.findMedianSortedArrays(nums1,nums2);
		System.out.println(median_num);

	}

}
