package sort;

import java.util.Arrays;

/**
 * 905. Sort Array By Parity
 * 
 * Given an array A of non-negative integers, return an array consisting of all the even 
 * elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 

Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000
 * 
 * 
 * @author yzzyq
 *
 */

// 将所有的偶数放在奇数之前

public class SortArrayByParity {
	
	// 排序,就是太慢了
	public int[] sortArrayByParity(int[] A) {
		Integer[] B = new Integer[A.length];
		for(int index = 0; index < A.length; ++index) {
			B[index] = A[index];
		}
		
		Arrays.sort(B, (a,b) -> Integer.compare(a%2,b%2));
		
		for(int index = 0; index < A.length; ++index) {
			A[index] = B[index];
		}
		return A;
	}
	
	// 还可以俩边扫描，第一遍得到偶数，第二遍得到奇数，直接放入
	
	
	// 双指针
    public int[] sortArrayByParity_1(int[] A) {
        if(A.length == 1) return A;
        
        int one = 0;
        int two = 0;
        while(one < A.length && two < A.length) {
        	if((A[one] & 1) != 0) {
        		if(two < one) two = one;
        		while(two < A.length && (A[two] & 1) != 0) ++two;
        		if(two == A.length) break;
        		int temp = A[one];
        		A[one] = A[two];
        		A[two] = temp;
        	}
        	++one;
        }
        
        return A;
    }

	public static void main(String[] args) {
//		int[] A = {3,1,2,4};
		int[] A = {0,1};
		SortArrayByParity sabp = new SortArrayByParity();
		int[] result = sabp.sortArrayByParity(A);
		for(int one:result) {
			System.out.println(one);
		}

	}

}
