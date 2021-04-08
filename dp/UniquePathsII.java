package dp;

/**
 * 63. Unique Paths II
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in 
 * the diagram below).

The robot can only move either down or right at any point in time. The robot is 
trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths 
would there be?
 * 
 * Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
 * 
 * @author yzzyq
 *
 */

// 和上一题差不多，就是多了障碍

public class UniquePathsII {
	
	// 动态规划
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	// 要注意开头是否被石头挡住了
    	if(obstacleGrid[0][0] == 1) return 0;
        int line = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        
        int[][] path = new int[line][col];
        path[0][0] = 1;
        for(int i = 1; i < line; i++) {
        	path[i][0] = 1;
        	if(obstacleGrid[i][0] == 1 || path[i - 1][0] == 0) {
        		path[i][0] = 0;
        	}
        }
        
        for(int j = 1; j < col; j++) {
        	path[0][j] = 1;
        	if(obstacleGrid[0][j] == 1 || path[0][j - 1] == 0) {
        		path[0][j] = 0;
        	}
        }
        
        
        for(int a_line = 1; a_line < line; a_line++) {
        	for(int a_col = 1; a_col < col; a_col++) {
        		if(obstacleGrid[a_line][a_col] == 1) {
        			path[a_line][a_col] = 0;
        		}else {
        			if(path[a_line - 1][a_col] == 0 && path[a_line][a_col - 1] == 0) {
        				path[a_line][a_col] = 0;
        			}else {
        				path[a_line][a_col] = path[a_line][a_col - 1] + path[a_line - 1][a_col];
        			}
        		}
        	}
        }
        
        return path[line - 1][col - 1];
    }
    
    // 空间优化，其实和上题基本是一致的
    public int uniquePathsWithObstacles_1(int[][] obstacleGrid) {
    	int line = obstacleGrid.length;
    	int col = obstacleGrid[0].length;
    	
    	int[] path = new int[col];
    	
    	path[0] = obstacleGrid[0][0] == 0?1:0;
    	
    	for(int a_line = 0; a_line < line; a_line++) {
    		for(int a_col = 0; a_col < col; a_col++) {
    			if(obstacleGrid[a_line][a_col] == 1) {
    				path[a_col] = 0;
    				continue;
    			}
    			
    			if(a_col - 1 >= 0 && obstacleGrid[a_line][a_col] == 0) {
    				path[a_col] += path[a_col - 1];
    			}
    			
    		}
    	}
    	
    	return path[col - 1];
    }

	public static void main(String[] args) {
		int[][] room_pos = {{0,0,0},{0,1,0},{0,0,0}};
        var upt = new UniquePathsII();
        int min_path = upt.uniquePathsWithObstacles_1(room_pos);
        System.out.println(min_path);
	}

}
