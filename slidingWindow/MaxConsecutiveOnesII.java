package slidingWindow;

// 1004.
//Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
//
//Return the length of the longest (contiguous) subarray that contains only 1s. 
//
// 
//
//Example 1:
//
//Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//Output: 6
//Explanation: 
//[1,1,1,0,0,1,1,1,1,1,1]
//Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
//Example 2:
//
//Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//Output: 10
//Explanation: 
//[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
// 
//
//Note:
//
//1 <= A.length <= 20000
//0 <= K <= A.length
//A[i] is 0 or 1

// �����ܽ�K��0���1�����1���ַ�������

public class MaxConsecutiveOnesII {
	
	// ���1���ַ���,����ֱ��ʹ�û������ڵ�˼�뼴��
    public int longestOnes(int[] A, int K) {
    	// ���ڵ���ߺ��ұ�
    	int max_length = 0;
    	for(int left=0,right=0;right < A.length;right++) {
    		if(A[right] == 0) {
    			if(K > 0) {
        			K--;
        		}else {
        			while(A[left++] == 1);
        		}
    		}
    		
    		max_length = Math.max(max_length, right-left+1);
    	}
        return max_length;
    }

	public static void main(String[] args) {
		MaxConsecutiveOnesII acs = new MaxConsecutiveOnesII();
		int[] A = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
		int K = 3;
        int max_length = acs.longestOnes(A, K);
        System.out.println(max_length);
	}

}
