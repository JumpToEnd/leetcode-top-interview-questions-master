package topinterviewquestions;

/**
 * 删除给定节点
 *
 * 借尸还魂
 */
public class Problem_0237_DeleteNodeInLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
