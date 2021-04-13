package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

示例 1：

输入: n = 5, m = 3
输出: 3
示例 2：

输入: n = 10, m = 17
输出: 2
 
限制：

1 <= n <= 10^5
1 <= m <= 10^6
 * 
 * @author yzzyq
 *
 */

public class LastRemaing {
	
	// 递归操作
	public int lastRemaining(int n, int m) {
		if(n == 1) {
			return 0;
		}

		return (lastRemaining(n-1, m) + m)%n;
    }
	
	// 迭代操作
	public int lastRemaining_1(int n, int m) {
		int live = 0;

		for(int index = 2; index < n; ++index) {
			live = (live + m)%index;
		}
		
		return live;
    }
	
	// 链表操作
	public int lastRemaining_2(int n, int m) {
		List<Integer> last_list = new ArrayList<Integer>();
		for(int temp = 0; temp < n; ++temp) {
			last_list.add(temp);
		}

		while(last_list.size() > 1) {
			
			for(int index = 0; index < m; ++index) {
				if(index != m-1) {
					last_list.add(last_list.get(0));
				}
				last_list.remove(0);
			}
			
		}
		
		return last_list.get(0);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
