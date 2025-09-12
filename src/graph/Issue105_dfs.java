package graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 图的深度优先遍历
 *
 * @author hcb
 * @since 2025/9/12 17:31
 */
public class Issue105_dfs {

    // 入栈就打印
    void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.val);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }
}
