package slidingWindow;

/**
 * 
 * 978. Longest Turbulent Subarray
 * 
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent 
pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

 

Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
Example 2:

Input: [4,8,12,16]
Output: 2
Example 3:

Input: [100]
Output: 1
 

Note:

1 <= A.length <= 40000
0 <= A[i] <= 10^9
 * 
 * @author yzzyq
 *
 */

// �����������


public class LongestTurbulentSubarray {
	
	// ʹ�û������������Ҳ����ʹ�ö�̬�滮����
    public int maxTurbulenceSize(int[] A) {
    	if(A.length == 0) return 0;
        if(A.length == 1 || (A.length == 2 && A[0] == A[1])) return 1;
        
        int left = 0;
        int max_len = 1;
        
        for(int right = 1; right < A.length; right++) {
        	int temp = Integer.compare(A[right], A[right - 1]);
        	
        	// �ҳ�ֹͣ������ֹͣ��ʱ�����жϳ���
        	if(right == A.length - 1 || temp*Integer.compare(A[right + 1], A[right]) != -1) {
        		// �ر�ע�⣬���ܵ���0������0�������ѭ������
        		if(temp != 0) max_len = Math.max(max_len, right - left + 1);
        		left = right;
        	}

        }
        return max_len;
    }

	public static void main(String[] args) {
		var longestTurbulent = new LongestTurbulentSubarray();
        int[] A = {9,4,2,10,7,8,8,1,9};
        int long_subarrays = longestTurbulent.maxTurbulenceSize(A);
        System.out.println(long_subarrays);
	}

}
