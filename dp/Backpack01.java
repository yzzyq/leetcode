package dp;

import java.util.Scanner;

/**
 * 0-1背包问题
 * 
 * 有 n 个物品和一个大小为 m 的背包. 给定数组 A 表示每个物品的大小和数组 V 表示每个物品的价值.

        问最多能装入背包的总价值是多大?
 * 
 * 样例 1:

输入: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
输出: 9
解释: 装入 A[1] 和 A[3] 可以得到最大价值, V[1] + V[3] = 9 
样例 2:

输入: m = 10, A = [2, 3, 8], V = [2, 5, 8]
输出: 10
解释: 装入 A[0] 和 A[2] 可以得到最大价值, V[0] + V[2] = 10
 * 
 */


public class Backpack01 {
	
	public int backPack01(int m, int[] A, int[] V) {
		int[][] value = new int[A.length + 1][m + 1];
		
		for(int i = 1; i < A.length + 1; ++i) {
			for(int j = 1; j < m + 1; ++j) {
				// 动态转移方程
				value[i][j] = value[i-1][j];
				if(j >= A[i - 1]) {
					value[i][j] = Math.max(value[i][j], value[i - 1][j - A[i-1]] + V[i-1]);
				}
			}
		}
		
		return value[A.length][m];
		
	}
	
	
	
	// 动态规划
	public int backPack0(int m, int[] A, int[] V) {
        int[] value = new int[m+1];
        
        for(int a_index = 0; a_index < A.length; a_index++) {
        	for(int index = m; index >= 1 && index >= A[a_index]; index--) {
        		// 对上面的做一下等价变换了，就是将二维数组压缩成一维，其实还是之前的过程
        		value[index] = Math.max(value[index], value[index - A[a_index]] + V[a_index]); 
        	}
        }
        
        return value[m];
    }

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
		
		int[] A = {2,3,8};
		int[] V = {2,5,8};
		int m = 10;
		Backpack01 bp = new Backpack01();
        int max_v = bp.backPack01(m, A, V);
        System.out.println(max_v);
	}

}
