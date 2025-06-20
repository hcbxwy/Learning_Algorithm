package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 求最大线段重合数
 * 给定很多线段，每个线段都有两个数[start,end],表示线段开始位置和结束位置，左右都是闭区间。规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须：>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class Issue61_heapSort2 {

    public static void main(String[] args) {
        List<Line> lines = new ArrayList<>();
        lines.add(new Line(2, 3));
        lines.add(new Line(1, 5));
        lines.add(new Line(4, 8));
        lines.add(new Line(4, 7));
        System.out.println(getMax(lines));
    }

    public static int getMax(List<Line> lines) {
        if (lines == null || lines.isEmpty()) {
            return 0;
        }
        // 根据线段起点升序排序
        lines.sort(Comparator.comparingInt(a -> a.start));
        // 小根堆优先队列存线段终点，每次存进去之前先把队列中小于该线段起点的终点都弹出
        int max = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Line line : lines) {
            while (!queue.isEmpty() && queue.peek() < line.start) {
                queue.poll();
            }
            queue.add(line.end);
            max = Math.max(max, queue.size());
        }
        return max;
    }

    public static class Line {
        private final int start;
        private final int end;

        private Line(int s, int e) {
            start = s;
            end = e;
        }
    }
}
