package dp;

import java.util.Arrays;

/**
 * 背包问题，这题是lintCode 92
 * 描述
在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i]
 * 
 * 样例
样例 1:
	输入:  [3,4,8,5], backpack size=10
	输出:  9

样例 2:
	输入:  [2,3,5,7], backpack size=12
	输出:  12
	
挑战
O(n x m) 的时间复杂度 and O(m) 空间复杂度
如果不知道如何优化空间O(n x m) 的空间复杂度也可以通过.
 * 
 * @author yzzyq
 *
 */

// 这是简化版的0-1背包问题

public class Backpack {
	
	// 动态规划，原始版本
	public int backPack(int m, int[] A) {
        if(m == 0 || A.length == 0) return 0;
		int[][] A_num = new int[m + 1][A.length + 1];
		for(int index = 1; index <= m; index++) {
			for(int A_index = 1; A_index <= A.length; A_index++) {
				// 要特别注意到这里，这行代码如果放在了if里面就会出错。
				A_num[index][A_index] = A_num[index][A_index - 1];
				if(index >= A[A_index - 1]) {
					A_num[index][A_index] = Math.max(A_num[index][A_index],
							A_num[index - A[A_index - 1]][A_index - 1] + A[A_index - 1]);
				}
			}
		}
		
		return A_num[m][A.length];
    }
	
	//动态规划，进行优化，这里的二维数组可以转换成一维数组
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
