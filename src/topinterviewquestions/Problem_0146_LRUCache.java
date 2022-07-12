package topinterviewquestions;

import java.util.HashMap;

/*
 * 在leetcode上提交时，把文字替换成下面的代码
 * 然后把类名、构造方法名从Problem_0146_LRUCache改为LRUCache即可
 */

public class Problem_0146_LRUCache {

    private MyCache<Integer, Integer> cache;

    public Problem_0146_LRUCache(int capacity) {
        cache = new MyCache<>(capacity);
    }

    public int get(int key) {
        Integer ans = cache.get(key);
        return ans == null ? -1 : ans;
    }

    public void put(int key, int value) {
        cache.set(key, value);
    }

    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeDoubleLinkedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }

            // 分离node节点
            // node 不是尾巴
            if (head == node) {
                head = node.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }

            // 把node节点放在链表的最后
            node.last = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        /**
         * 移除头结点
         */
        public Node<K, V> removeHead() {
            // 当前链表内无元素
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            // 链表中只有一个节点的时候
            if (head == tail) {
                head = null;
                tail = null;
            }
            // 链表内节点不止一个
            else {
                head = res.next;
                res.next = null;
                head.last = null;
            }
            return res;
        }

    }

    public static class MyCache<K, V> {
        private HashMap<K, Node<K, V>> keyNodeMap;
        private NodeDoubleLinkedList<K, V> nodeList;
        private final int capacity;

        /**
         * 初始化的时候需要指定容量
         */
        public MyCache(int cap) {
            if (cap < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            keyNodeMap = new HashMap<K, Node<K, V>>();
            nodeList = new NodeDoubleLinkedList<K, V>();
            capacity = cap;
        }

        public V get(K key) {
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        /**
         * 分为更新操作和新增操作
         */
        public void set(K key, V value) {
            // 更新操作
            if (keyNodeMap.containsKey(key)) {
                Node<K, V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            }
            // 新增操作
            else {
                if (keyNodeMap.size() == capacity) {
                    removeMostUnusedCache();
                }
                Node<K, V> newNode = new Node<K, V>(key, value);
                keyNodeMap.put(key, newNode);
                nodeList.addNode(newNode);
            }
        }

        private void removeMostUnusedCache() {
            Node<K, V> removeNode = nodeList.removeHead();
            keyNodeMap.remove(removeNode.key);
        }

    }

}
