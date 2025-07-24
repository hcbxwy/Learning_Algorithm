package tree;

/**
 * 微软折纸面试题
 * <p>
 *     请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。
 *     此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 *     如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 *      给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 *      例如:N=1时，打印: down N=2时，打印: down down up
 * </p>
 *
 * @author hcb
 * @since 2025/7/24 10:44
 */
public class Issue83_paperFolding {

    public static void main(String[] args) {
        print(2);
    }

    public static void print(int n) {
        // 第1层肯定是down
        print(1, n, true);
    }

    // 其实就是中序遍历，想象成二叉树；i为当前层，N为最大层
    public static void print(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        print(i + 1, N, true);
        System.out.print((down ? "down" : "up") + " ");
        print(i + 1, N, false);
    }
}
