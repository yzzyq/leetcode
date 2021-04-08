package dp;

import java.util.Arrays;

/** 
 *  64. Minimum Path Sum
 *  Given a m x n grid filled with non-negative numbers, find a path from top left 
 *  to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 * 
 * @author yzzyq
 *
 */

// 找最小路径，很经典的最值，动态规划题目

public class MinimumPathSum {
	
	// 动态规划
    public int minPathSum(int[][] grid) {
    	int line = grid.length;
    	int col = grid[0].length;
    	// 分成的子状态，最主要的就是能在后面用上，这就是计算顺序
        int[][] min_path = new int[line][col];
        // 初始问题和边界
        min_path[0][0] = grid[0][0];
        for(int one_line = 0; one_line < line; one_line++) {
        	
        	for(int one_col = 0; one_col < col; one_col++) {
        		if(one_line == 0 || one_col == 0) {
        			if(one_line == 0 && one_col == 0) {
        				min_path[0][0] = grid[0][0];
        			}else if(one_line == 0) {
        				min_path[one_line][one_col] = grid[one_line][one_col]
        						+ min_path[one_line][one_col - 1];
        			}else {
        				min_path[one_line][one_col] = grid[one_line][one_col]
        						+ min_path[one_line - 1][one_col];
        			}
        				
        		}else {
        			// 转移方程
        			min_path[one_line][one_col] = Math.min(min_path[one_line -1][one_col],
            				min_path[one_line][one_col - 1]) + grid[one_line][one_col];
            		
        		}
   
        	}
        	
        }
        
        return min_path[line - 1][col - 1];
    }
    
    // 优化版
    public int minPathSum_1(int[][] grid) {
    	int line = grid.length;
    	int col = grid[0].length;
        int[][] min_path = new int[line + 1][col + 1];
        min_path[0][0] = grid[0][0];
        for(int i = 1; i < line; i++) {
        	min_path[i][0] = min_path[i - 1][0] + grid[i][0];
        }
        
        for(int j = 1; j < col; j++) {
        	min_path[0][j] = min_path[0][j - 1] + grid[0][j];
        }
        
        for(int one_line = 1; one_line < line; one_line++) {
        	
        	for(int one_col = 1; one_col < col; one_col++) {
        			min_path[one_line][one_col] = Math.min(min_path[one_line -1][one_col],
            				min_path[one_line][one_col - 1]) + grid[one_line][one_col];
   
        	}
        	
        }
        
        return min_path[line-1][col-1];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        var mps = new MinimumPathSum();
		int min_path = mps.minPathSum_1(grid);
		System.out.println(min_path);
	}

}
