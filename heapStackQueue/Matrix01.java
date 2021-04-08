package heapStackQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 542. 01 Matrix
 * 
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:

Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]
Example 2:

Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 

Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.
 * 
 * @author yzzyq
 *
 */

// ���������ÿ��1�������0�ľ���

public class Matrix01 {
	
    public int[][] updateMatrix(int[][] matrix) {
    	int row = matrix.length;
    	int col = matrix[0].length;
        int[][] all_min_distance = new int[row][col];
        
        for(int row_index = 0; row_index < row; row_index++) {
        	for(int col_index = 0; col_index < col; col_index++) {
        		if(matrix[row_index][col_index] == 0) {
        			all_min_distance[row_index][col_index] = 0;
        		}else {
        			// �����������
        			int min_dis = Integer.MAX_VALUE;
        			// �Ƚ��в鿴��ǰ��
        			if(row_index > 0) {
        				min_dis = Math.min(min_dis, all_min_distance[row_index - 1][col_index] + 1);
        			}
        			
        			if(col_index > 0) {
        				min_dis = Math.min(min_dis, all_min_distance[row_index][col_index - 1] + 1);
        			}
        			all_min_distance[row_index][col_index] = min_dis;
        			int dis = 0;
        			Deque<Integer> queue = new LinkedList<Integer>();
        			queue.offer(row_index*col + col_index);
        			int curr_num = 1;
        			int next_num = 0;
        			while(!queue.isEmpty()) {
        				int index = queue.poll();
        				int one_row = index / col;
        				int one_col = index % col;
        				if(matrix[one_row][one_col] == 0) {
        					all_min_distance[row_index][col_index] = Math.min(min_dis, dis);
        					break;
        				}else {
        					if(one_row + 1 < row) {
        						queue.offer((one_row + 1)*col + one_col);
        						++next_num;
        					}
        					
        					if(one_col + 1 < col) {
        						queue.offer(one_row*col + one_col + 1);
        						++next_num;
        					}
        					
        				}
        				if(--curr_num <= 0) {
        					curr_num = next_num;
        					next_num = 0;
        					++dis;
        				}
        			}
        			
        		}
        	}
        }
        
        return all_min_distance;
    }
    
    
    // Ҳ����ֱ�Ӵ�0��λ�ÿ�ʼ�룬���й������������
    public int[][] updateMatrix_1(int[][] matrix) {
    	int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};
    	// ���ҳ�0��λ��
    	int row = matrix.length;
    	int col = matrix[0].length;
    	Deque<int []> queue= new LinkedList<int []>();
    	boolean[][] isVisited = new boolean[row][col];
    	int[][] dis = new int[row][col];
    	for(int row_index = 0; row_index < row; ++row_index) {
    		for(int col_index = 0; col_index < col; ++col_index) {
    			if(matrix[row_index][col_index] == 0) {
    				isVisited[row_index][col_index] = true;
    				queue.offer(new int[] {row_index,col_index});
    			}
    		}
    	}
    	
    	// ���й����������
    	while(!queue.isEmpty()) {
    		int[] temp = queue.poll();
    		int one_row = temp[0];
    		int one_col = temp[1];
    		for(int index = 0; index < pos.length; index++) {
    			int temp_row = one_row + pos[index][0];
    			int temp_col = one_col + pos[index][1];
    			
    			if(temp_row >= 0 && temp_row < row && temp_col >= 0 && 
    					temp_col < col && !isVisited[temp_row][temp_col]) {
    				dis[temp_row][temp_col] = dis[one_row][one_col] + 1;
    				isVisited[temp_row][temp_col] = true;
    				queue.offer(new int[] {temp_row,temp_col});
    			}
    		}
    	}
    	
    	return dis;
    }
    
    // ��һ�ַ�����ʱ�򣬳�ʼ�Ƕ�̬�滮������0�ں�����޷�����ֻ����0�ں����ʱ�����BFS��
    // 0����ں���Ļ����ٴӺ�����һ�ζ�̬�滮����ôҲ�ǿ��Եģ��ܶ����˵�����ϣ����£����ϣ������ĸ�������
    // ��ʵû�б�Ҫ��ֻ��Ҫ�����ϣ����£�������������������ܰ���ȫ���ˣ�����ֻ�ӶԽ��������ǱȽ�������
    
    // �ĸ�����
    public int[][] updateMatrix_2(int[][] matrix) {
    	int row = matrix.length;
    	int col = matrix[0].length;
    	// ��ʼ��
    	int[][] results = new int[row][col];
    	
    	for(int index = 0; index < row; index++) {
    		// �ǵ�/2��int�����ֵ+1�������ɸ���
    		Arrays.fill(results[index], Integer.MAX_VALUE/2);
    	}
    	
    	for(int row_index = 0; row_index < row; row_index++) {
    		for(int col_index = 0; col_index < col; col_index++) {
    			if(matrix[row_index][col_index] == 0) {
    				results[row_index][col_index] = 0;
    			}
    		}
    	}
    	
    	// ��ʼ���ĸ��Ǹ���
    	// ����
    	for(int row_index = 0; row_index < row; row_index++) {
    		for(int col_index = 0; col_index < col; col_index++) {
    			if(row_index - 1 >= 0) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index-1][col_index] + 1);
    			}
    			
    			if(col_index - 1 >= 0) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index][col_index-1] + 1);
    			}
    		}
    	}
    	
    	// ����
    	for(int row_index = row - 1; row_index >= 0; row_index--) {
    		for(int col_index = 0; col_index < col; col_index++) {
    			if(row_index + 1 < row) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index+1][col_index] + 1);
    			}
    			
    			if(col_index - 1 >= 0) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index][col_index-1] + 1);
    			}
    		}
    	}
    	
    	// ����
    	for(int row_index = 0; row_index < row; row_index++) {
    		for(int col_index = col - 1; col_index >= 0; col_index--) {
    			if(row_index - 1 >= 0) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index-1][col_index] + 1);
    			}
    			
    			if(col_index + 1 < col) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index][col_index+1] + 1);
    			}
    		}
    	}
    	
    	// ����
    	for(int row_index = row - 1; row_index >= 0; row_index--) {
    		for(int col_index = col - 1; col_index >= 0; col_index--) {
    			if(row_index + 1 < row) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index+1][col_index] + 1);
    			}
    			
    			if(col_index + 1 < col) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index][col_index+1] + 1);
    			}
    		}
    	}
    	
    	return results;
    }
    
    // �ԽǷ���
    public int[][] updateMatrix_3(int[][] matrix) {
    	int row = matrix.length;
    	int col = matrix[0].length;
    	// ��ʼ��
    	int[][] results = new int[row][col];
    	
    	for(int index = 0; index < row; index++) {
    		// �ǵ�/2��int�����ֵ+1�������ɸ���
    		Arrays.fill(results[index], Integer.MAX_VALUE/2);
    	}
    	
    	for(int row_index = 0; row_index < row; row_index++) {
    		for(int col_index = 0; col_index < col; col_index++) {
    			if(matrix[row_index][col_index] == 0) {
    				results[row_index][col_index] = 0;
    			}
    		}
    	}
    	
    	// ����
    	for(int row_index = 0; row_index < row; row_index++) {
    		for(int col_index = 0; col_index < col; col_index++) {
    			if(row_index - 1 >= 0) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index-1][col_index] + 1);
    			}
    			
    			if(col_index - 1 >= 0) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index][col_index-1] + 1);
    			}
    		}
    	}
    	
    	// ����
    	for(int row_index = row - 1; row_index >= 0; row_index--) {
    		for(int col_index = col - 1; col_index >= 0; col_index--) {
    			if(row_index + 1 < row) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index+1][col_index] + 1);
    			}
    			
    			if(col_index + 1 < col) {
    				results[row_index][col_index] = Math.min(results[row_index][col_index], 
    						results[row_index][col_index+1] + 1);
    			}
    		}
    	}
    	
    	return results;
    }
    
    
    

	public static void main(String[] args) {
		int[][] matrix = {{1,1,0,0,1,0,0,1,1,0},
		                  {1,0,0,1,0,1,1,1,1,1},
		                  {1,1,1,0,0,1,1,1,1,0},
		                  {0,1,1,1,0,1,1,1,1,1},
		                  {0,0,1,1,1,1,1,1,1,0}};
		Matrix01 m = new Matrix01();
		int[][] results = m.updateMatrix_3(matrix); 
		
		for(int i = 0; i < results.length; ++i) {
			for(int j = 0; j < results[i].length; ++j) {
				System.out.print(results[i][j] + ",");
			}
			System.out.println();
		}
	}
}
