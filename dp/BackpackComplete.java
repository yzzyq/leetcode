package dp;

import java.util.Scanner;

/**
 * ��ȫ��������
 * 
 * ��0-1�������ⲻͬ�ľ��ǣ�ÿ����Ʒ�������޼����á�
 * 
 * �� N ����Ʒ��һ�������� V �ı�����ÿ����Ʒ�������޼����á�

�� i ����Ʒ������� vi����ֵ�� wi��

��⽫��Щ��Ʒװ�뱳������ʹ��Щ��Ʒ��������������������������ܼ�ֵ���
�������ֵ��

�����ʽ
��һ������������N��V���ÿո�������ֱ��ʾ��Ʒ�����ͱ����ݻ���

�������� N �У�ÿ���������� vi,wi���ÿո�������ֱ��ʾ�� i ����Ʒ������ͼ�ֵ��

�����ʽ
���һ����������ʾ����ֵ��

���ݷ�Χ
0<N,V��1000
0<vi,wi��1000
��������
4 5
1 2
2 4
3 4
4 5
���������
10
 * 
 * @author yzzyq
 *
 */

public class BackpackComplete {
	
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
		
		int[] A = {1,2,3,4};
		int[] V = {2,4,4,5};
		int m = 5;
		
//		int[] A = {5,32,17,7,6,29,2,14,6,1};
//		int[] V = {8,47,43,9,4,40,6,31,17,3};
//		int m = 100;
		
		BackpackComplete bp = new BackpackComplete();
        int two_v = bp.backPackCom(m, A, V);
        System.out.println(two_v);
	}
	
	
	
	public int backPackCom(int m, int[] A, int[] V) {
		int[] value = new int[m+1];
		
		value[0] = 0;
		
		for(int com_index = 0; com_index < A.length; com_index++) {
			for(int index = A[com_index]; index <= m; index++) {
				value[index] = Math.max(value[index], 
						value[index - A[com_index]] + V[com_index]);
			}
		}
		
		for(int one:value) {
			System.out.print(one + ",");
		}
		System.out.println();
		
		return value[m];
	}

}
