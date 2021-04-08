package dp;

import java.util.Arrays;

/**
 * �������⣬������lintCode 92
 * ����
��n����Ʒ����ѡ������Ʒװ�뱳���������װ���������豳���Ĵ�СΪm��ÿ����Ʒ�Ĵ�СΪA[i]
 * 
 * ����
���� 1:
	����:  [3,4,8,5], backpack size=10
	���:  9

���� 2:
	����:  [2,3,5,7], backpack size=12
	���:  12
	
��ս
O(n x m) ��ʱ�临�Ӷ� and O(m) �ռ临�Ӷ�
�����֪������Ż��ռ�O(n x m) �Ŀռ临�Ӷ�Ҳ����ͨ��.
 * 
 * @author yzzyq
 *
 */

// ���Ǽ򻯰��0-1��������

public class Backpack {
	
	// ��̬�滮��ԭʼ�汾
	public int backPack(int m, int[] A) {
        if(m == 0 || A.length == 0) return 0;
		int[][] A_num = new int[m + 1][A.length + 1];
		for(int index = 1; index <= m; index++) {
			for(int A_index = 1; A_index <= A.length; A_index++) {
				// Ҫ�ر�ע�⵽������д������������if����ͻ����
				A_num[index][A_index] = A_num[index][A_index - 1];
				if(index >= A[A_index - 1]) {
					A_num[index][A_index] = Math.max(A_num[index][A_index],
							A_num[index - A[A_index - 1]][A_index - 1] + A[A_index - 1]);
				}
			}
		}
		
		return A_num[m][A.length];
    }
	
	//��̬�滮�������Ż�������Ķ�ά�������ת����һά����
	public int backPack_1(int m, int[] A) {
        if(m == 0 || A.length == 0) return 0;
		int[] nums = new int[m + 1];
	
		for(int A_index = 0; A_index < A.length; A_index++) {
			for(int index = m; index >=1 && index >= A[A_index]; index--) {
				nums[index] = Math.max(nums[index], nums[index - A[A_index]] + A[A_index]);
			}
		}
		
		
		return nums[m]; 
    }

	public static void main(String[] args) {
		int[] A = {3,4,8,5};
		int size = 10;
		Backpack bp = new Backpack();
        int max_num = bp.backPack_1(size, A);
        System.out.println(max_num);
	}

}
