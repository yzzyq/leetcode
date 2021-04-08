package recursion;


/**
 * 92. Reverse Linked List II
 * Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 �� m �� n �� length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
 * 
 * 
 * @author yzzyq
 *
 */

// ָ��λ��֮��ķ�ת

public class ReverseLinkedListII {
	
	// ��ͦ��ģ�
	// ��ת������֮ǰһ��������Ҫ��¼ǰ��ָ��
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m == n) return head;
        
        ListNode rever_first_node = head;
        ListNode pre_rever_first_node = new ListNode();
        ListNode pre = null;
        ListNode current = head;
        
        for(int index = 1; index <=n; index++) {
        	if(index + 1 == m) {
        		rever_first_node = current.next;
        		if(m > 1) pre_rever_first_node = current;
        	}
        	
        	if(index >= m) {
        		ListNode temp = current.next;
        		current.next = pre;
        		pre = current;
        		
        		if(index == n) {
        			rever_first_node.next = temp;
        			if(m > 1) pre_rever_first_node.next = current;
        			break;
        		}
        		
        		current = temp;
        	}
        	if(index < m) current = current.next;
        }
        
        if(m > 1) return head;
        return current;
    	
    }
    
    
    // �ݹ�ʵ��
    // �����Ѷȣ�����ֻ�Ƿ�תǰn��Ԫ��
    ListNode next_node = null;
    public ListNode reverseN(ListNode head, int n) {
    	if(n == 1) {
    		next_node = head.next;
    		return head;
    	}
    	
    	ListNode p = reverseN(head.next, n -1);
    	head.next.next = head;
    	// ����ǶԵģ�ֻ������һ���ڵ�������
    	head.next = next_node;
    	return p;
    }
    
    // ֮����չ����Χ��
    public ListNode reverseBetween_1(ListNode head, int m, int n) {
    	if(m == 1) {
    		return reverseN(head,n);
    	}
    	head.next = reverseBetween_1(head.next,m-1,n-1);
    	return head;
    }

	public static void main(String[] args) {
        var rll = new ReverseLinkedListII();
		
		ListNode five = new ListNode(5,null);
		ListNode four = new ListNode(4,five);
		ListNode three = new ListNode(3,four);
		ListNode second = new ListNode(2,three);
		ListNode first = new ListNode(1,second);

		int m = 1;
		int n = 5;
        ListNode result = rll.reverseBetween_1(first,m,n);
		
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}

	}

}
