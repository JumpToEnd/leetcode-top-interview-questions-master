package followup;

/**
 * 前缀树   树状数组
 * <p>
 * 每个数的管辖范围是 最后的1 拆掉 然后 末尾+1  .... 自己
 * 比如：1100 管辖的范围就是  1001 ... 1100
 */
public class IndexTreeTest {

    public static class IndexTree {

        private int[] tree;
        private int N;

        public IndexTree(int size) {
            N = size;
            tree = new int[N + 1];
        }

        /**
         * 求累加和
         * index & -index => 提取出最右侧的1
         *
         * @param index
         * @return
         */
        public int sum(int index) {
            int ret = 0;
            while (index > 0) {
                ret += tree[index];
                // 每次抹掉最右侧的1
                index -= index & -index;
            }
            return ret;
        }

        /**
         * 在index位置 加上 d
         * <p>
         * 需要操作的位置是 每次最后一个1 加1
         *
         * @param index 索引位置
         * @param d     操作数
         */
        public void add(int index, int d) {

            while (index <= N) {
                tree[index] += d;
                // 每次最右侧的1 + 1
                index += index & -index;
            }
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
