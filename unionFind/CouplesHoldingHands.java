package unionFind;

import javax.print.attribute.standard.Copies;

/**
 * 765. Couples Holding Hands
 * 
 * N couples sit in 2N seats arranged in a row and want to hold hands. We want to know the minimum 
 * number of swaps so that every couple is sitting side by side. A swap consists of choosing any 
 * two people, then they stand up and switch seats.

The people and seats are represented by an integer from 0 to 2N-1, the couples are numbered in 
order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple 
being (2N-2, 2N-1).

The couples' initial seating is given by row[i] being the value of the person who is initially 
sitting in the i-th seat.

Example 1:

Input: row = [0, 2, 1, 3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
Example 2:

Input: row = [3, 2, 0, 1]
Output: 0
Explanation: All couples are already seated side by side.
Note:

len(row) is even and in the range of [4, 60].
row is guaranteed to be a permutation of 0...len(row)-1.
 * 
 * @author yzzyq
 *
 */

// 交换数字

// 群组问题

public class CouplesHoldingHands {
	
	// 贪心
	public int minSwapsCouples(int[] row) {
		
		int num = 0;
		for(int index = 0; index < row.length; index+=2) {
			int temp = row[index];
			if(row[index+1] == (temp^1)) continue;
			++num;
			
			for(int two_index = index+1; two_index < row.length; ++two_index) {
				if(row[two_index] == (temp^1)) {
					row[two_index] = row[index+1];
					row[index+1] = (temp^1);
					break;
				}
			}
			
		}
		
		return num;

    }
	
	// 对于群组问题，使用并查集，如何使用并查集是个很大的问题，单从题目来看，一般不会和并查集联系起来
	// 我看题目的时候，完全没有想到并查，只认为贪心了，
	// 这里最大的把俩个情侣看成一组，组之间可以合并起来的这种思想，主要是组可以合并来代替贪心算法中的交换位置
	class UnionFind{
		int[] parents;
		int[] weights;
		
		public UnionFind(int num) {
			parents = new int[num];
			weights = new int[num];
			for(int index = 0; index < num; ++index) {
				parents[index] = index;
				weights[index] = 1;
			}
		}
		
		public void union(int node1, int node2) {
			int parent_node1 = find(node1);
			int parent_node2 = find(node2);
			
			if(parent_node1 == parent_node2) return;
			
			if(weights[parent_node1] > weights[parent_node2]) {
				parents[parent_node2] = parent_node1;
			}else if(weights[parent_node1] < weights[parent_node2]) {
				parents[parent_node1] = parent_node2;
			}else {
				parents[parent_node1] = parent_node2;
				++weights[parent_node2];
			}
			
		}

		public int find(int node1) {
			if(parents[node1] != node1) parents[node1] = find(parents[node1]); 
			return parents[node1];
		}
		
		public int getRes() {
			int num = 0;
			
			for(int index = 0; index < parents.length; ++index) {
				if(parents[index] != index) ++num;
			}
			
			return num;
		}
	
	}
	
	public int minSwapsCouples_1(int[] row) {
		int num = row.length / 2;
		UnionFind uf = new UnionFind(num);
		for(int index = 0; index < row.length; index+=2) {
			uf.union(row[index]/2, row[index+1]/2);
		}
		
		return uf.getRes();
	}
	

	public static void main(String[] args) {
		int[] rows = {0,2,1,3};
		var ch = new CouplesHoldingHands();
        int result = ch.minSwapsCouples_1(rows);
        System.out.println(result);
	}

}
