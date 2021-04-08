package unionFind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 130. Surrounded Regions
 * 
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board 
are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the 
border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected 
horizontally or vertically.
 * 
 * 
 * @author yzzyq
 *
 */

// 将所有被X包围的O填充为X

// 思路应该是从边界出发，来做深度广度搜索
// 并查集也是从边界出发

public class SurroundedRegions {
	// 自己写的错误版本，虽然思想也是深度和广度，但是及其的复杂，还不对。
    public void solve_1(char[][] board) {
    	if(board.length <= 2) return;
    	if(board[0].length <= 2) return;
    	int row = board.length;
    	int col = board[0].length;
    	boolean[][] isVisited = new boolean[row][col];
    	for(int row_index = 0; row_index < board.length; ++row_index) {
    		for(int col_index = 0; col_index < board[0].length; ++col_index) {
    			if(board[row_index][col_index] == 'O' && !isVisited[row_index][col_index]) {
    				boolean isSlove = true;
    				List<int[]> path_list = new ArrayList<int[]>();
    				Queue<int []> border = new LinkedList<int[]>();
    				border.add(new int[] {row_index,col_index});
    				isVisited[row_index][col_index] = true;
    				while(!border.isEmpty()) {
    					int[] pos = border.poll();
    					if(pos[0] <=0 || pos[0] >= row - 1 || pos[1] <= 0 || pos[1] >= col - 1) {
    						isSlove = false;
    						break;					
    					}
    					
    					if(pos[0] - 1 >= 0 && board[pos[0] - 1][pos[1]] == 'O' && !isVisited[pos[0] - 1][pos[1]]) {
    						border.add(new int[] {pos[0] - 1,pos[1]});
    						isVisited[pos[0] - 1][pos[1]] = true;
    					}
    					
    					if(pos[0] + 1 < row && board[pos[0] + 1][pos[1]] == 'O' && !isVisited[pos[0] + 1][pos[1]]) {
    						border.add(new int[] {pos[0] + 1,pos[1]});
    						isVisited[pos[0] + 1][pos[1]] = true;
    					}
    					
    					if(pos[1] - 1 >= 0 && board[pos[0]][pos[1] - 1] == 'O' && !isVisited[pos[0]][pos[1] - 1]) {
    						border.add(new int[] {pos[0],pos[1] - 1});
    						isVisited[pos[0]][pos[1] - 1] = true;
    					}
    					
    					if(pos[1] + 1 < col && board[pos[0]][pos[1] + 1] == 'O' && !isVisited[pos[0]][pos[1] + 1]) {
    						border.add(new int[] {pos[0],pos[1] + 1});
    						isVisited[pos[0]][pos[1] + 1] = true;
    					}
    					
    					path_list.add(new int[] {pos[0],pos[1]});		
    				}
    				
    				if(isSlove) {
    					for(int index = 0; index < path_list.size(); ++index) {
    						int[] pos = path_list.get(index);
    						board[pos[0]][pos[1]] = 'X';
    					}
    					   					
    				}
    			   				
    			}
    			isVisited[row_index][col_index] = true;
    		}
    	}
        
    }
    
    int row;
    int col;
    
    // 从边界来看，之后从边界上的O连接到内部的O，使用深度搜索
    public void solve(char[][] board) {
    	if(board.length == 0) return;
    	
    	row = board.length;
        col = board[0].length;
    	
    	for(int index = 0; index < row; ++index) {
    		dfs(board, index, 0);
    		dfs(board, index, col - 1);
    	}
    	
    	for(int index = 0; index < col; ++index) {
    		dfs(board, 0, index);
    		dfs(board, row - 1, index);
    	}
    	
    	for(int row_index = 0; row_index < row; ++row_index) {
    		for(int col_index = 0; col_index < col; ++col_index) {
    			if(board[row_index][col_index] == 'A') {
    				board[row_index][col_index] = 'O';
    			}else if(board[row_index][col_index] == 'O') {
    				board[row_index][col_index] = 'X';
    			}
    		}
    	}
    	
    }
    
    public void dfs(char[][] board, int start_row, int start_col) {
    	if(start_row < 0 || start_row >= row || start_col < 0 || 
    			start_col >= col || board[start_row][start_col] != 'O') {
    		return;
    	}
    	board[start_row][start_col] = 'A';
    	dfs(board, start_row - 1, start_col);
    	dfs(board, start_row + 1, start_col);
    	dfs(board, start_row, start_col - 1);
    	dfs(board, start_row, start_col + 1);
    }
    
    class UnoinFind{
    	int[] parents;
    	int[] ranks;
    	public UnoinFind(int totalCount) {
    		parents = new int[totalCount];
    		ranks = new int[totalCount];
    		for(int index = 0; index < totalCount; ++index) {
    			parents[index] = index;
    			ranks[index] = 0;
    		}
    	}
    	
    	public void union(int node1, int node2) {
    		int parent_node1 = getParent(node1);
    		int parent_node2 = getParent(node2);
    		
    		if(parent_node1 != parent_node2) {
    			if(ranks[parent_node1] > ranks[parent_node2]) {
    				parents[parent_node2] = parent_node1;
    				
    			}else if(ranks[parent_node1] < ranks[parent_node2]){
    				parents[parent_node1] = parent_node2;
    				
    			}else {
    				parents[parent_node1] = parent_node2;
    				++ranks[parent_node2];
    			}
    		}
    	}
    	
    	public int getParent(int node) {
    		if(parents[node] != node) parents[node] = getParent(parents[node]);
    		return parents[node];
    	}
    	
    	public boolean isConnect(int node1, int node2) {
    		return getParent(node1) == getParent(node2);
    	}
    }
    
    // 并查集操作
    public void solve_2(char[][] board) {
    	if(board.length == 0) return; 
    	int row = board.length;
    	int col = board[0].length;
    	
    	UnoinFind uf = new UnoinFind(row*col + 1);
    	int dummyNode = row*col;
    	
    	for(int row_index = 0; row_index < row; ++row_index) {
    		for(int col_index = 0; col_index < col; ++col_index) {
    			if(board[row_index][col_index] == 'O') {
    				// 边界的O与一个特定的dummyNode连接
    				if(row_index == 0 || row_index == row - 1 || col_index == 0 || col_index == col-1) {
    					uf.union(row_index*col + col_index, dummyNode);
    				}else {
    					// 内部的话需要看上下左右的
    					if(row_index > 0 && board[row_index - 1][col_index] == 'O') {
    						uf.union(row_index*col + col_index, (row_index - 1)*col + col_index);
    					}
    					if(col_index > 0 && board[row_index][col_index - 1] == 'O') {
    						uf.union(row_index*col + col_index, row_index*col + col_index - 1);
    					}
    					if(row_index < row - 1 && board[row_index + 1][col_index] == 'O') {
    						uf.union(row_index*col + col_index, (row_index + 1)*col + col_index);
    					}
    					if(col_index < col - 1 && board[row_index][col_index + 1] == 'O') {
    						uf.union(row_index*col + col_index, row_index*col + col_index + 1);
    					}
    					
    				}
    			}
    		}
    	}
    	
    	for(int row_index = 0; row_index < row; ++row_index) {
    		for(int col_index = 0; col_index < col; ++col_index) {
    			if(board[row_index][col_index] == 'O' && !uf.isConnect(dummyNode, row_index*col + col_index)) {
    				board[row_index][col_index] = 'X';
    			}
    		}
    	}
    	
    }
    
    

	public static void main(String[] args) {
		char[][] board = {{'X', 'X', 'X', 'X'},{'X', 'O', 'O', 'X'},{'X', 'X', 'O', 'X'},{'X', 'O', 'X', 'X'}};
//		char[][] board = {};
		SurroundedRegions sr = new SurroundedRegions();
		sr.solve_2(board);
		for(int index = 0; index < board.length; ++index) {
			for(int col = 0; col < board[index].length; ++col) {
				System.out.print(board[index][col] + ",");
			}
			System.out.println();
		}
	}

}
