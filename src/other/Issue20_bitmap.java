package other;

/**
 * 实现一个位图
 * 具备add、remove、contains方法
 */
public class Issue20_bitmap {

    public static void main(String[] args) {
        Bitmap bitmap = new Bitmap(10);
        bitmap.add(1);
        bitmap.add(2);
        bitmap.delete(1);
        System.out.println(bitmap.contains(1));
    }

    public static class Bitmap {
        private final long[] bits;

        public Bitmap(int max) {
            // 创建多大的位图，计算需要多少位，比如1~63需要1位就行，64则需要2位才能满足，用long来存，每1位可以存64个数
            bits = new long[(max + 64) >> 6];
        }

        public void add(int num) {
            // // 确定在第几位
            // int index = num >> 6;
            // // 确定在第几位上的第几个位置
            // long pos = 1L << (num & 63);
            // // 在指定位指定位置上从0改为1
            // bits[index] |= pos;

            // num >> 6是为了确定在第几位
            // 63全是1，num & 63 保留地位中1的位置
            // 1L << (num & 63)  确定在64位中哪个位置
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            // 删除就是指定位置变为0
            // 找到位置后，先取反再进行与运算，就可以得到结果
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }
    }
}
