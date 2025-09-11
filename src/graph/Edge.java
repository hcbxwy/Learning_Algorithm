package graph;

/**
 * 图的边
 *
 * @author hcb
 * @since 2025/9/11 17:24
 */
class Edge {
    int weight;
    Node from;
    Node to;

    Edge(int w, Node from, Node to) {
        this.weight = w;
        this.from = from;
        this.to = to;
    }
}
