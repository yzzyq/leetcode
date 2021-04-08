package recursion;

/**
 * 24. Swap Nodes in Pairs
 * 
 * Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes. Only nodes itself may be changed.


Example 1:


Input: head = [1,2,3,4]
Output: [2,1,4,3]
Example 2:

Input: head = []
Output: []
Example 3:

Input: head = [1]
Output: [1]
 

Constraints:

The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 * 
 * @author yzzyq
 *
 */

// 交换链表中的相邻节点

// 细节上的难点，主要是交换节点的时候，开始的头结点就不再是头结点了，并且后面交换后，以前在后面的就会消失了
// 迭代中最简单的方法就是设置一个头指针

public class SwapNodesinPairs {
	
	// 递归解法
    public ListNode swapPairs(ListNode head) {
    	// 递归结束条件就是只有一个或者没有节点的时候，停止交换
        if(head == null || head.next == null) return head;
        
        // 交换节点
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        
        // 交错
        return next;
    }
    
    // 迭代解法
    public ListNode swapPairs_1(ListNode head) {
    	if(head == null || head.next == null) return head;
    	// 设置一个头指针
    	ListNode pre_node = new ListNode(-1,head);
    	ListNode dummey = pre_node;
    	
    	while(head != null && head.next != null) {
    		// 交换
    		ListNode right = head.next;
    		
    		dummey.next = right;
    		head.next = right.next;
    		right.next = head;
    		
    		// 迭代
    		dummey = head;
    		head = head.next;
    		
    	}
    	
    	return pre_node.next;
    }

	public static void main(String[] args) {
		var snip = new SwapNodesinPairs();
		ListNode end_node = new ListNode(4,null);
		ListNode third_node = new ListNode(3,end_node);
	    ListNode second_node = new ListNode(2,third_node);
	    ListNode first_node = new ListNode(1,second_node);
		ListNode head = snip.swapPairs_1(first_node);
		while(head != null) {
			System.out.println(head.val + " --> ");
			head = head.next;
		}

	}

}
