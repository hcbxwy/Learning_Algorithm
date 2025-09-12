package graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 *
 * @author hcb
 * @since 2025/9/11 17:36
 */
public class Issue104_bfs {

    static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.val);
            for (Node next : node.nexts) {
                if (!visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
    }
}
