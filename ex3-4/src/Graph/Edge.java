package Graph;


/**
 * Edge is a class that represent an edge of a graph
 *
 * @param <V> is the type of the node
 * @param <L> is the type of the label
 */
public class Edge<V, L> implements AbstractEdge<V, L> {
    private final V start;
    private final V end;
    private final L label;

    /**
     * Edge is the constructor of the class
     *
     * @param a the start of the edge
     * @param b the end of the edge
     * @param l the label of the edge (it can be null for unlabelled graph)
     */
    public Edge(V a, V b, L l) {
        start = a;
        end = b;
        label = l;
    }

    @Override
    public V getStart() {
        if (start == null) System.out.println("the start of edge is null");
        return start;
    }

    @Override
    public V getEnd() {
        if (end == null) System.out.println("the end of edge is null");
        return end;
    }

    @Override
    public L getLabel() {
        if (label == null) System.out.println("the edge's label is null or the graph is unlabelled");
        return label;
    }


    public String toString() {
        return getStart() + "," + getEnd() + "," + getLabel();
    }

}
