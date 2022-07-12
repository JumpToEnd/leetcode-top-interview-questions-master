package topinterviewquestions;

/**
 * 期望值
 */
public class Problem_0268_MissingNumber {

    public static int missingNumber(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l) {
                l++;
            }
            // 无用值的情况，进垃圾区
            // 1. [l] < l
            // 2. [l] >= r
            // 3. arr[arr[l]] == arr[l]
            else if (arr[l] < l || arr[l] >= r || arr[arr[l]] == arr[l]) {
                swap(arr, l, --r);
            } else {
                swap(arr, l, arr[l]);
            }
        }
        return l;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
