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

// ���������е����ڽڵ�

// ϸ���ϵ��ѵ㣬��Ҫ�ǽ����ڵ��ʱ�򣬿�ʼ��ͷ���Ͳ�����ͷ����ˣ����Һ��潻������ǰ�ں���ľͻ���ʧ��
// ��������򵥵ķ�����������һ��ͷָ��

public class SwapNodesinPairs {
	
	// �ݹ�ⷨ
    public ListNode swapPairs(ListNode head) {
    	// �ݹ������������ֻ��һ������û�нڵ��ʱ��ֹͣ����
        if(head == null || head.next == null) return head;
        
        // �����ڵ�
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        
        // ����
        return next;
    }
    
    // �����ⷨ
    public ListNode swapPairs_1(ListNode head) {
    	if(head == null || head.next == null) return head;
    	// ����һ��ͷָ��
    	ListNode pre_node = new ListNode(-1,head);
    	ListNode dummey = pre_node;
    	
    	while(head != null && head.next != null) {
    		// ����
    		ListNode right = head.next;
    		
    		dummey.next = right;
    		head.next = right.next;
    		right.next = head;
    		
    		// ����
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
