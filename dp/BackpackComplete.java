package dp;

import java.util.Scanner;

/**
 * 完全背包问题
 * 
 * 与0-1背包问题不同的就是，每种物品都有无限件可用。
 * 
 * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。

第 i 种物品的体积是 vi，价值是 wi。

求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
输出最大价值。

输入格式
第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。

接下来有 N 行，每行两个整数 vi,wi，用空格隔开，分别表示第 i 种物品的体积和价值。

输出格式
输出一个整数，表示最大价值。

数据范围
0<N,V≤1000
0<vi,wi≤1000
输入样例
4 5
1 2
2 4
3 4
4 5
输出样例：
10
 * 
 * @author yzzyq
 *
 */

public class BackpackComplete {
	
	public static void main(String[] args) {
		// 编辑器的输入
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
