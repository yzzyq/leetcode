package unionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 200. Number of Islands
 * 
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 * 
 * 
 * @author yzzyq
 *
 */



public class NumberofIslands {
	
	// ʹ�ù����������
    public int numIslands(char[][] grid) {
    	int height = grid.length;
    	int width = grid[0].length;
        boolean[][] isVisited = new boolean[height][width];
        
        int num = 0;
        
        for(int h_index = 0; h_index < height; h_index++) {
        	for(int w_index = 0; w_index < width; w_index++) {
        		if(!isVisited[h_index][w_index] && grid[h_index][w_index] == '1') {
        			// ʹ�ö���
        			Queue<ArrayList<Integer>> pos_queue = new LinkedList<ArrayList<Integer>>(); 
        			ArrayList<Integer> temp_list = new ArrayList<Integer>();
        			isVisited[h_index][w_index] = true;
        			temp_list.add(h_index);
        			temp_list.add(w_index);
        			pos_queue.offer(temp_list);
        			while(!pos_queue.isEmpty()) {
        				ArrayList<Integer> temp = pos_queue.poll();
        				int h = temp.get(0);
        				int w = temp.get(1);
//        				isVisited[h][w] = true;   д������Ļ�����Ȼ�������ˣ����ǻᳬʱ
        				if(h - 1 >= 0 && !isVisited[h-1][w] && grid[h-1][w] == '1') {
        					ArrayList<Integer> tep = new ArrayList<Integer>();
        					tep.add(h-1);
        					tep.add(w);
        					pos_queue.offer(tep);
        					// ����Ҫ��һʱ�����߹�����Ϊ���ڶ����е�ʱ��û�б���߹��Ļ��������Խ��Խ��
        					isVisited[h-1][w] = true;
        				}
        				
        				if(w - 1 >= 0 && !isVisited[h][w-1] && grid[h][w-1] == '1') {
        					ArrayList<Integer> tep = new ArrayList<Integer>();
        					tep.add(h);
        					tep.add(w-1);
        					pos_queue.offer(tep);
        					isVisited[h][w-1] = true;
        				}
        				
        				if(h + 1 < height && !isVisited[h+1][w] && grid[h+1][w] == '1') {
        					ArrayList<Integer> tep = new ArrayList<Integer>();
        					tep.add(h+1);
        					tep.add(w);
        					pos_queue.offer(tep);
        					isVisited[h+1][w] = true;
        				}
        				
        				if(w + 1 < width && !isVisited[h][w+1] && grid[h][w+1] == '1') {
        					ArrayList<Integer> tep = new ArrayList<Integer>();
        					tep.add(h);
        					tep.add(w+1);
        					pos_queue.offer(tep);
        					isVisited[h][w+1] = true;
        				}
        			}
        			num++;
        		}
        	}
        }    
        return num;
    }

    
    // ������BFS�����Ż�������ĺܶ����ÿ���ȥ��
    public int numIslands_1(char[][] grid) {
    	int height = grid.length;
    	int width = grid[0].length;
        
        int num = 0;
        
        for(int h_index = 0; h_index < height; h_index++) {
        	for(int w_index = 0; w_index < width; w_index++) {
        		if(grid[h_index][w_index] == '1') {
        			++num;
        			grid[h_index][w_index] = '0';
        			
        			// ʹ�ö���,�������ֱ��ͨ������õ�
        			Queue<Integer> pos_queue = new LinkedList<Integer>(); 
        			pos_queue.offer(h_index*width + w_index);
        			while(!pos_queue.isEmpty()) {
        				int temp = pos_queue.poll();
        				int h = temp/width;
        				int w = temp%width;
        				if(h - 1 >= 0 && grid[h-1][w] == '1') {
        					pos_queue.offer((h-1)*width + w);
        					grid[h - 1][w] = '0';
        				}
        				
        				if(w - 1 >= 0 && grid[h][w-1] == '1') {
        					pos_queue.offer(h*width + w - 1);
        					grid[h][w - 1] = '0';
        				}
        				
        				if(h + 1 < height && grid[h+1][w] == '1') {
        					pos_queue.offer((h+1)*width + w);
        					grid[h+1][w] = '0';
        				}
        				
        				if(w + 1 < width && grid[h][w+1] == '1') {
        					pos_queue.offer(h*width + w + 1);
        					grid[h][w+1] = '0';
        				}
        			}
        		}
        	}
        }    
        return num;
    }
    
    // ʹ�������������
    public int numIslands_2(char[][] grid) {
    	int height = grid.length;
    	int width = grid[0].length;
        
        int num = 0;
        
        for(int h_index = 0; h_index < height; h_index++) {
        	for(int w_index = 0; w_index < width; w_index++) {
        		if(grid[h_index][w_index] == '1') {
        			++num;
        			dfs(grid, h_index, w_index);
        		}
        	}
        }	
    	
        return num;
    }
    
    
	private void dfs(char[][] grid, int h_index, int w_index) {
		grid[h_index][w_index] = '0';
		int height = grid.length;
    	int width = grid[0].length;
		
		if(h_index - 1 >= 0 && grid[h_index-1][w_index] == '1') dfs(grid, h_index - 1, w_index);
		
		if(w_index - 1 >= 0 && grid[h_index][w_index - 1] == '1') dfs(grid, h_index, w_index-1);
		
		if(h_index + 1 < height && grid[h_index+1][w_index] == '1') dfs(grid, h_index + 1, w_index);
		
		if(w_index + 1 < width && grid[h_index][w_index+1] == '1') dfs(grid, h_index, w_index+1);	
		
	}
	
	// ������ͨ�����⣬����ʹ�ò��鼯
	class UnionFind{
		int count;
		int[] parent;
		int[] rank;
		
		public UnionFind(char[][] grid) {
			count = 0;
			int height = grid.length;
	    	int width = grid[0].length;
			parent = new int[height*width];
			rank = new int[height*width]; // ����������Ż����鼯��
			
			for(int h_index = 0; h_index < height; h_index++) {
	        	for(int w_index = 0; w_index < width; w_index++) {
	        		if(grid[h_index][w_index] == '1') {
	        			parent[h_index*width + w_index] = h_index*width + w_index;
	        			++count;
	        		}
	        		rank[h_index*width + w_index] = 0;
	        	}
			}
		}
		
		public void Union(int index, int pos) {
			int parent_index = find(index);
			int parent_pos = find(pos);
			
			if(parent_index != parent_pos) {
				if (rank[parent_index] < rank[parent_pos]) {
					parent[parent_index] = parent_pos;
				} else if (rank[parent_index] > rank[parent_pos]) {
					parent[parent_pos] = parent_index;
				} else {
					parent[parent_pos] = parent_index;
					rank[parent_index] += 1;
				}
				--count;
			}
		}
		
		public int find(int index) {
			if(parent[index] != index) parent[index] = find(parent[index]);
			return parent[index];
		}
		
		
	}
	
	public int numIslands_3(char[][] grid) {
		int height = grid.length;
    	int width = grid[0].length;
    	
    	// ��Ҫ�����������е�������Ԥ����
    	UnionFind uf = new UnionFind(grid);
    	for(int h_index = 0; h_index < height; h_index++) {
        	for(int w_index = 0; w_index < width; w_index++) {
        		if(grid[h_index][w_index] == '1') {
        			grid[h_index][w_index] = '0';
        			if(h_index - 1 >= 0 && grid[h_index-1][w_index] == '1') 
        				uf.Union(h_index*width + w_index, (h_index-1)*width + w_index);
        			
        			if(w_index - 1 >= 0 && grid[h_index][w_index - 1] == '1') 
        				uf.Union(h_index*width + w_index, h_index*width + w_index-1);
        			
        			if(h_index + 1 < height && grid[h_index+1][w_index] == '1') 
        				uf.Union(h_index*width + w_index, (h_index+1)*width + w_index);
        			
        			if(w_index + 1 < width && grid[h_index][w_index+1] == '1') 
        				uf.Union(h_index*width + w_index, h_index*width + w_index+1);
        		}
        	}
		}
    	
    	return uf.count;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid = {
				      {'1','1','1','1','1','1','1'},
                      {'0','0','0','0','0','0','1'},
                      {'1','1','1','1','1','0','1'},
                      {'1','0','0','0','1','0','1'},
                      {'1','0','1','0','1','0','1'},
                      {'1','0','1','1','1','0','1'},
                      {'1','1','1','1','1','1','1'}
	                   };
		NumberofIslands nil = new NumberofIslands();
		int num = nil.numIslands_3(grid);
        System.out.println(num);
	}

}
