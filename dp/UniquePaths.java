package dp;

import java.util.Arrays;

/**
 * 62. Unique Paths
 * 
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * Input: m = 3, n = 7 Output: 28 Example 2:
 * 
 * Input: m = 3, n = 2 Output: 3 Explanation: From the top-left corner, there
 * are a total of 3 ways to reach the bottom-right corner: 1. Right -> Down ->
 * Down 2. Down -> Down -> Right 3. Down -> Right -> Down Example 3:
 * 
 * Input: m = 7, n = 3 Output: 28 Example 4:
 * 
 * Input: m = 3, n = 3 Output: 6
 * 
 * 
 * Constraints:
 * 
 * 1 <= m, n <= 100 It's guaranteed that the answer will be less than or equal
 * to 2 * 109.
 * 
 * @author yzzyq
 *
 */

// 这个也是路径问题，不过这个是总共的路径，不过也可以使用动态规划，化为子问题

public class UniquePaths {

	// 动态规划
	public int uniquePaths(int m, int n) {
		int[][] path = new int[m][n];

		for (int line = 0; line < m; line++) {
			for (int col = 0; col < n; col++) {
				if (line == 0 || col == 0) {
					path[line][col] = 1;
				} else {
					path[line][col] = path[line - 1][col] + path[line][col - 1];
				}
			}

		}
		return path[m - 1][n - 1];
	}

	// 进行空间优化
	public int uniquePaths_1(int m, int n) {
		int[] path = new int[n];
		Arrays.fill(path, 1);
		for (int line = 1; line < m; line++) {
			for (int col = 1; col < n; col++) {
				path[col] += path[col - 1];
			}

		}
		return path[n - 1];
	}

	public static void main(String[] args) {
		int m = 3;
		int n = 2;
		UniquePaths up = new UniquePaths();
		int path_sum = up.uniquePaths_1(m, n);
		System.out.println(path_sum);
	}

}
