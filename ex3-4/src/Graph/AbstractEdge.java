package Graph;

/**
 * AbstractEdge is an interface that represent an edge of a graph
 *
 * @param <V> is the type of the node
 * @param <L> is the type of the label
 */
public interface AbstractEdge<V, L> {

    /**
     * get the edge's start
     *
     * @return return the edge's start
     */
    public V getStart(); // il nodo di partenza dell'arco

    /**
     * get tha edge's end
     *
     * @return return the edge's end
     */
    public V getEnd(); // il nodo di arrivo dell'arco

    /**
     * get the edge's label
     *
     * @return return the edge's label
     */
    public L getLabel(); // l'etichetta dell'arco
}

