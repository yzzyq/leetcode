package dp;

import java.util.Scanner;

/**
 * 0-1��������
 * 
 * �� n ����Ʒ��һ����СΪ m �ı���. �������� A ��ʾÿ����Ʒ�Ĵ�С������ V ��ʾÿ����Ʒ�ļ�ֵ.

        �������װ�뱳�����ܼ�ֵ�Ƕ��?
 * 
 * ���� 1:

����: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
���: 9
����: װ�� A[1] �� A[3] ���Եõ�����ֵ, V[1] + V[3] = 9 
���� 2:

����: m = 10, A = [2, 3, 8], V = [2, 5, 8]
���: 10
����: װ�� A[0] �� A[2] ���Եõ�����ֵ, V[0] + V[2] = 10
 * 
 */


public class Backpack01 {
	
	public int backPack01(int m, int[] A, int[] V) {
		int[][] value = new int[A.length + 1][m + 1];
		
		for(int i = 1; i < A.length + 1; ++i) {
			for(int j = 1; j < m + 1; ++j) {
				// ��̬ת�Ʒ���
				value[i][j] = value[i-1][j];
				if(j >= A[i - 1]) {
					value[i][j] = Math.max(value[i][j], value[i - 1][j - A[i-1]] + V[i-1]);
				}
			}
		}
		
		return value[A.length][m];
		
	}
	
	
	
	// ��̬�滮
	public int backPack0(int m, int[] A, int[] V) {
        int[] value = new int[m+1];
        
        for(int a_index = 0; a_index < A.length; a_index++) {
        	for(int index = m; index >= 1 && index >= A[a_index]; index--) {
        		// ���������һ�µȼ۱任�ˣ����ǽ���ά����ѹ����һά����ʵ����֮ǰ�Ĺ���
        		value[index] = Math.max(value[index], value[index - A[a_index]] + V[a_index]); 
        	}
        }
        
        return value[m];
    }

	public static void main(String[] args) {
		// �༭��������
//		Scanner scan = new Scanner(System.in);
//	    int len = scan.nextInt();
//	    int m = scan.nextInt();
//	    int[] A = new int[len];
//	    int[] V = new int[len]; 
//		for(int index  = 0; index < len; index++){
//		    A[index] = scan.nextInt();
//		    V[index] = scan.nextInt();
//		}
		
		int[] A = {2,3,8};
		int[] V = {2,5,8};
		int m = 10;
		Backpack01 bp = new Backpack01();
        int max_v = bp.backPack01(m, A, V);
        System.out.println(max_v);
	}

}
