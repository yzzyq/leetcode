package heapStackQueue;

import java.util.HashSet;
import java.util.Set;

/**142. Linked List Cycle II
 * 
 *
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by 
continuously following the next pointer. Internally, pos is used to denote the index of the node 
that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Notice that you should not modify the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 
Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.
 * 
 * @author yzzyq
 *
 */

// 需要找到环的第一个节点

public class LinkedListCycleII {
	
	// 可以使用哈希表
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        
        Set<ListNode> node_set = new HashSet<ListNode>();
        
        while(head != null) {
        	if(node_set.contains(head)) {
        		return head;
        	}
        	node_set.add(head);
        	head = head.next;
        }
        
        return null;
    }
    
    // 哈希表消耗的太多，这题还是可以使用快慢指针的，只不过有点难理解
    public ListNode detectCycle_1(ListNode head) {
        if(head == null || head.next == null) return null;
        
        ListNode low = head;
        ListNode fast = head;
        
        while(low != null && fast != null && fast.next != null) {
        	// 注意这个移动放置的位置，因为起始点都是一样的，如果放在最后，会在第一次发生错误
        	fast  = fast.next.next;
        	low = low.next;
        	if(fast == low) {
        		// 慢指针的路径为a+b，快指针的路径为a+n(b+c)+b
        		// 并且快指针是慢指针速度的2倍，因此2(a+b)=a+n(b+c)+b
        		// a = (n-1)(b+c)+c
        		// 那么a的距离就是C加上环的长度，
        		// 如果一个从起点走，一个继续从相遇的地方走，就一定能在环的入口相遇
        		fast = head;
        		while(fast != low) {
        			low = low.next;
        			fast = fast.next;
        		}
        		return fast;
        	}
        }    
        return null;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode five = new ListNode(5);
		ListNode four = new ListNode(4,five);
		ListNode three = new ListNode(3,four);
		ListNode second = new ListNode(1,three);
		ListNode first = new ListNode(2,second);
		five.next = three;
		
		LinkedListCycleII llcycle = new LinkedListCycleII();
		ListNode firstNode = llcycle.detectCycle_1(first);
        System.out.println(firstNode.val);

	}

}
