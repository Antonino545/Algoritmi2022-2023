package Graph;

import java.util.Collection;

/**
 * Graph is an interface that represent a graph
 *
 * @param <V> is the type of the node
 * @param <L> is the type of the label
 */
public interface AbstractGraph<V, L> {

    /**
     * get true if the graph is directed, false otherwise
     *
     * @return if the graph is directed or not
     */
    boolean isDirected(); // dice se il grafo è diretto o meno -- O(1)

    /**
     * get true if the graph is labelled, false otherwise
     *
     * @return if the graph is labelled or not
     */
    boolean isLabelled(); // dice se il grafo è etichettato o meno -- O(1)

    /**
     * insert a node in the graph
     *
     * @param a it's the node to insert in the graph
     * @return true if the node is inserted, false if the node is already in the graph
     */
    boolean addNode(V a); // aggiunge un nodo -- O(1)

    /**
     * insert an edge in the graph
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @param l it's the label of the edge
     * @return true if the edge is inserted, false if the edge is already in the graph
     */
    boolean addEdge(V a, V b, L l); // aggiunge un arco dati estremi ed etichetta -- O(1)

    /**
     * check if a node is in the graph
     *
     * @param a it's the node to check
     * @return true if the node is in the graph, false otherwise
     */
    boolean containsNode(V a); // controlla se un nodo è nel grafo -- O(1)

    /**
     * check if an edge is in the graph
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @return true if the edge is in the graph, false if the edge is not in the graph
     */
    boolean containsEdge(V a, V b); // controlla se un arco è nel grafo -- O(1) (*)

    /**
     * removes node from the graph if it exists
     *
     * @param a node to remove
     * @return true if the node is removed successfully
     */
    boolean removeNode(V a); // rimuove un nodo dal grafo -- O(N)

    /**
     * removes edge from the graph if it exists if it's not oriented it removes two edges (a,b) and (b,a)
     *
     * @param a starting node
     * @param b destination node
     * @return true if the edge is removed successfully
     */
    boolean removeEdge(V a, V b); // rimuove un arco dal grafo -- O(1) (*)

    /**
     * counts the number of nodes in the graph
     *
     * @return number of nodes in the graph
     */
    int numNodes(); // numero di nodi -- O(1)

    /**
     * counts the number of edges in the graph
     *
     * @return number of edges in the graph
     */
    int numEdges(); // numero di archi -- O(N)

    /**
     * gets all the nodes in the graph and returns them as a collection
     *
     * @return a collection of nodes in the graph
     */
    Collection<V> getNodes(); // recupero dei nodi del grafo -- O(N)

    /**
     * gets all the edges in the graph and returns them as a collection
     *
     * @return a collection of edges in the graph
     */
    Collection<? extends AbstractEdge<V, L>> getEdges(); // recupero degli archi del grafo -- O(N)

    /**
     * gets all the nodes adjacent to node a and returns them as a collection
     *
     * @param a node for which the method retrieves the adjacent nodes
     * @return returns a collection of nodes in the graph adjacent to a
     */
    Collection<V> getNeighbours(V a); // recupero dei nodi adiacenti ad un dato nodo -- O(1) (*)

    /**
     * gets the label of the edge between nodes a and b
     *
     * @param a starting node
     * @param b destination node
     * @return returns the label of an edge connecting nodes a and b
     */
    L getLabel(V a, V b); // recupero dell'etichetta di un arco -- O(1) (*)

}

