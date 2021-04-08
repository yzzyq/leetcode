package slidingWindow;

import java.util.HashMap;
import java.util.Map;

// 992.
//Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
//
//(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
//
//Return the number of good subarrays of A.
//
// 
//
//Example 1:
//
//Input: A = [1,2,1,2,3], K = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
//Example 2:
//
//Input: A = [1,2,1,3,4], K = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
// 
//
//Note:
//
//1 <= A.length <= 20000
//1 <= A[i] <= A.length
//1 <= K <= A.length


public class SubarraysWithKDifferentIntegers {
	
	// 滑动窗口，固定长度的
    public int subarraysWithKDistinct(int[] A, int K) {
    	if(A.length == 0 || K == 0) return 0;
        
        int left = 0;
        int right = 0;
        int match = 0;
        int num = 0;
        Map<Integer, Integer> intger_num = new HashMap<Integer, Integer>();
        
        for(;right < A.length; right++) {
        	if(!intger_num.containsKey(A[right]) || intger_num.get(A[right]) == 0) {
        		intger_num.put(A[right], 0);
        		match++;
        	}
        	intger_num.put(A[right], intger_num.get(A[right])+1);
        	
        	while(match == K) {
        		num++;
        		
        		int temp_right = right + 1;
        		while(temp_right < A.length && intger_num.containsKey(A[temp_right]) && intger_num.get(A[temp_right]) > 0) {
        			num++;
        			temp_right++;
        			
        		}
        		
        		intger_num.put(A[left], intger_num.get(A[left])-1);
        		if(intger_num.get(A[left]) == 0) match--;
        		left++;
        		
        	}
        }
        
        return num;
    }
    
    
    // 优化版
//    public int subarraysWithKDistinct(int[] arr, int k) {
//        short[] count = new short[arr.length+1];
//        short unique=0;
//        int i=0, total=0, good=0;
//        for(int j=0; j<arr.length; j++){
//          if(count[arr[j]]++==0) unique++;
//          if(unique>k){
//            count[arr[i++]]--;
//            unique--;
//            good=0;
//          }
//          while(count[arr[i]]>1){
//            count[arr[i++]]--;
//            good++;
//          }
//          if(unique==k) total += good+1;
//        }
//        return total;
//      }

	public static void main(String[] args) {
		SubarraysWithKDifferentIntegers swdi = new SubarraysWithKDifferentIntegers();
		int[] A = {1,1,1,1,2};
		int[] B = A;
		A[0] = 1;
		for(int value:B) {
			System.out.println(value);
		}
		System.out.println(B);
//		int K = 1;
//		int sub_num = swdi.subarraysWithKDistinct(A, K);
//		System.out.println(sub_num);
		String[] C = {"ab","cd"};
		String[] D = C;
		C[0] = "a";
		for(String value:D) {
			System.out.println(value);
		}
//		System.out.println(B);
	}

}
