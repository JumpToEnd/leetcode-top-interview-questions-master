package topinterviewquestions;

import java.util.PriorityQueue;

/**
 * 堆
 * <p>
 * 两个堆：大根堆、小根堆
 * <p>
 * 第一个数一定进大根堆
 * 剩余的数遵循以下原则：
 * 1. num 小于等于 大根堆的堆顶 进大根堆，否则进小根堆
 * 2. 大根堆 size - 小根堆 size == 2，大根堆堆顶弹出进小根堆
 * 3. 小根堆 size - 大根堆 size == 2，小根堆堆顶弹出进大根堆
 */
public class Problem_0295_FindMedianFromDataStream {

    class MedianFinder {

        private PriorityQueue<Integer> maxh;
        private PriorityQueue<Integer> minh;

        public MedianFinder() {
            maxh = new PriorityQueue<>((a, b) -> b - a);
            minh = new PriorityQueue<>((a, b) -> a - b);
        }

        public void addNum(int num) {
            // 第一个数
            if (maxh.isEmpty()) {
                maxh.add(num);
            }
            // 剩余的数
            else {
                if (maxh.peek() >= num) {
                    maxh.add(num);
                } else {
                    minh.add(num);
                }
            }
            // 平衡两个堆
            balance();
        }

        public double findMedian() {
            // 如果两个堆大小相等
            if (maxh.size() == minh.size()) {
                return (double) (maxh.peek() + minh.peek()) / 2;
            }
            // 两个堆大小不同，那么返回数量多的那个
            else {
                return maxh.size() > minh.size() ? maxh.peek() : minh.peek();
            }
        }

        private void balance() {
            if (maxh.size() == minh.size() + 2) {
                minh.add(maxh.poll());
            }
            if (maxh.size() == minh.size() - 2) {
                maxh.add(minh.poll());
            }
        }

    }

}