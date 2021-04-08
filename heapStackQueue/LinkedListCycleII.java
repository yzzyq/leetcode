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

// ��Ҫ�ҵ����ĵ�һ���ڵ�

public class LinkedListCycleII {
	
	// ����ʹ�ù�ϣ��
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
    
    // ��ϣ�����ĵ�̫�࣬���⻹�ǿ���ʹ�ÿ���ָ��ģ�ֻ�����е������
    public ListNode detectCycle_1(ListNode head) {
        if(head == null || head.next == null) return null;
        
        ListNode low = head;
        ListNode fast = head;
        
        while(low != null && fast != null && fast.next != null) {
        	// ע������ƶ����õ�λ�ã���Ϊ��ʼ�㶼��һ���ģ����������󣬻��ڵ�һ�η�������
        	fast  = fast.next.next;
        	low = low.next;
        	if(fast == low) {
        		// ��ָ���·��Ϊa+b����ָ���·��Ϊa+n(b+c)+b
        		// ���ҿ�ָ������ָ���ٶȵ�2�������2(a+b)=a+n(b+c)+b
        		// a = (n-1)(b+c)+c
        		// ��ôa�ľ������C���ϻ��ĳ��ȣ�
        		// ���һ��������ߣ�һ�������������ĵط��ߣ���һ�����ڻ����������
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
