package unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 使用map实现一个并查集
 *
 * @author hcb
 * @since 2025/8/26 11:24
 */
public class Issue97_UnionFindOfMap {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        UnionFind<String> uf = new UnionFind<>(list);
        System.out.println(uf.find("a", "b"));
        uf.union("a", "c");
        System.out.println(uf.find("a", "b"));
        uf.union("c", "b");
        System.out.println(uf.find("b", "c"));
    }


    static class UnionFind<V> {
        HashMap<V, V> parentMap;
        HashMap<V, Integer> sizeMap;

        private UnionFind(List<V> values) {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();

            for (V v : values) {
                parentMap.put(v, v);
                sizeMap.put(v, 1);
            }
        }

        // 找到顶级父亲（祖先），这里有个优化点：路径扁平化，即访问过的路径的父亲改为祖先
        private V findTopParent(V v) {
            Stack<V> stack = new Stack<>();
            while (v != parentMap.get(v)) {
                stack.push(v);
                v = parentMap.get(v);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), v);
            }
            return v;
        }

        // 查询两个元素是否在同一个集合中
        private boolean find(V a, V b) {
            return findTopParent(a) == findTopParent(b);
        }

        // 合并两个元素到一个集合中，这里有个优化点：合并集合时，小挂大
        private void union(V a, V b) {
            V aFather = findTopParent(a);
            V bFather = findTopParent(b);

            if (aFather != bFather) {
                int aSize = sizeMap.get(aFather);
                int bSize = sizeMap.get(bFather);
                V bigger = aSize >= bSize ? aFather : bFather;
                V small = bigger == aFather ? bFather : aFather;
                parentMap.put(small, bigger);
                sizeMap.remove(small);
                sizeMap.put(bigger, aSize + bSize);
            }
        }
    }
}
