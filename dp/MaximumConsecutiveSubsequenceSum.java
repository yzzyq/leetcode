package dp;

/**
 * ������Ŀ
 * �����������������к�
 * [-2,1,-3,4,-1,2,1,-5,4]
 * ����������о���4,-1,2,1���;���6
 * 
 * @author yzzyq
 *
 */


public class MaximumConsecutiveSubsequenceSum {

	// δ���飬û�����ݼ����м���
	// ��̬�滮��Ŀ
	public int getMax(int[] nums) {
		int len = nums.length;
		if(len == 0) return 0;
		int[] dp = new int[len];
		dp[0] = nums[0];
		int max = dp[0];
		
		// �����ת�Ʒ����У���ǰԪ���Ǳ���Ҫѡ�ģ�������Ϊ�����������ã�����������ܼ�������
		// ��ǰ��ֻ�ܶ�ǰ��������б�����ѡ���߲�ѡ�ÿ���
		for(int index = 1; index < len; index++) {
			dp[index] = Math.max(nums[index], nums[index] + dp[index - 1]);
			if(dp[index] > max) max = dp[index]; 
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        var maxCon = new MaximumConsecutiveSubsequenceSum();
        int max_num = maxCon.getMax(nums);
        System.out.println(max_num);
	}

}
