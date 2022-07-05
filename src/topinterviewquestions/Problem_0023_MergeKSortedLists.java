package topinterviewquestions;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 小根堆
 */
public class Problem_0023_MergeKSortedLists {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        // 开始连接
        ListNode head = heap.poll();
        ListNode pre = head;
        // 如果下一个节点不是空，把下一个节点放进小根堆
        if (pre.next != null) {
            heap.add(pre.next);
        }
        // 循环此过程，把堆中最小的结点取出，然后这个结点的下一个结点如果不为空，把下一个节点放进堆中
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
        }
        return head;
    }

}
