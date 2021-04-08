package unionFind;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 547. Friend Circles
 * 
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:

Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
 

Example 2:

Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

 

Constraints:

1 <= N <= 200
M[i][i] == 1
M[i][j] == M[j][i]
 * 
 * @author yzzyq
 *
 */

// 查询朋友圈的数量

public class FriendCircles {
	
	// 使用并查集
	class UnionFind{
		
		int[] parent;
		int[] ranks;
		int num;
		
		public UnionFind(int num) {
			this.num = num;
			this.parent = new int[num];
			this.ranks = new int[num];
			
			for(int index = 0; index < num; ++index) {
				parent[index] = index;
				ranks[index] = 1;
			}
		}
		
		public void union(int node1, int node2) {
			int parent_node1 = find(node1);
			int parent_node2 = find(node2);
			
			if(parent_node1 != parent_node2) {
				if(ranks[parent_node1] > ranks[parent_node2]) {
					parent[parent_node2] = parent_node1;
				}else if(ranks[parent_node1] < ranks[parent_node2]) {
					parent[parent_node1] = parent_node2;
				}else {
					parent[parent_node1] = parent_node2;
					++ranks[parent_node2];
				}
				--this.num;
			}	
		}
		
		public int find(int node) {
			if(parent[node]!=node) parent[node] = find(parent[node]);
			return parent[node];
		}
		
	}	
	
	public int findCircleNum(int[][] M) {
		if(M.length <= 1) return M.length;

		int people_num = M.length;
		UnionFind uf = new UnionFind(people_num);
		
		for(int row_index = 0; row_index < people_num; ++row_index) {
			for(int col = row_index + 1; col < people_num; ++col) {
				if(M[row_index][col] == 1) {
					uf.union(row_index,col);
				}
			}
		}
		
		return uf.num;
    }
	
	// 深度优先
	public int findCircleNum_1(int[][] M) {
		if(M.length <= 1) return M.length;
		int people_num = M.length;
		
		boolean[] isVisited = new boolean[people_num];
		int num = 0;
		
		for(int row_index = 0; row_index < people_num; ++row_index) {
			if(!isVisited[row_index]) {
				dfs(M, isVisited, row_index);
				++num;
			}
		}
		
		return num;
	}

	private void dfs(int[][] m, boolean[] isVisited, int row_index) {
		// 这里是必须从0开始遍历，不然一些是通过不了的，比如1和4，4和3
		for(int index = 0; index < isVisited.length; ++index) {
			if(m[row_index][index] == 1 && !isVisited[index]) {
				isVisited[index] = true;
				dfs(m,isVisited,index);
			}
		}
		
	}
	
	// 广度优先
	public int findCircleNum_2(int[][] M) {
		if(M.length <= 1) return M.length;
		int people_num = M.length;
		
		boolean[] isVisited = new boolean[people_num];
		int num = 0;
		
		for(int index = 0; index < people_num; ++index) {
			if(!isVisited[index]) {
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.add(index);
				while(!queue.isEmpty()) {
					int re_index = queue.remove();
					isVisited[re_index] = true;
					for(int temp = 0; temp < people_num; ++temp) {
						if(M[re_index][temp] == 1&&!isVisited[temp]) queue.add(temp);
					}
				}
	
				++num;
			}
		}
		
		return num;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int[][] M = {{1,1,0},
                     {1,1,0},
                     {0,0,1}};
        FriendCircles fc = new FriendCircles();
        int num = fc.findCircleNum_2(M);
        System.out.println(num);		
	}

}
