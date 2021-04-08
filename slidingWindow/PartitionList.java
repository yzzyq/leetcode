package slidingWindow;


/*
 * 86. Partition List
 * 
 * Given a linked list and a value x, partition it such that all nodes less than x come before 
 * nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
 * 
 * 
 */

// �ָ���������

public class PartitionList {
	
	// ˫ָ��
	public ListNode partition(ListNode head, int x) {
		// ͷ��㲻ȷ����ʱ��ʹ���ưͽڵ�
		ListNode before_node = new ListNode(0);
		ListNode after_node = new ListNode(0);
		
		ListNode before_current = before_node;
		ListNode after_current = after_node;
		
		while(head != null) {
			if(head.val < x) {
				before_current.next = new ListNode(head.val);
				before_current = before_current.next;
			}else {
				after_current.next = new ListNode(head.val);
				after_current = after_current.next;
			}
			head = head.next;
		}
		
		before_current.next = after_node.next;

		return before_node.next;
    }

	public static void main(String[] args) {
		ListNode five = new ListNode(5,null);
		ListNode second = new ListNode(2,five);
		ListNode first = new ListNode(1,second);
		var pl = new PartitionList();
        ListNode result = pl.partition(first, 3);
		
		while(result != null) {
			System.out.print(result.val + "->");
			result = result.next;
		}
	}

}
