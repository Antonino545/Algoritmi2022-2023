package Graph;

import java.util.*;

/**
 * The class Graph.Graph is an implementation of the graph interface
 *
 * @param <V> is the type of the node
 * @param <L> is the type of the label
 */
public class Graph<V, L> implements AbstractGraph<V, L> {
    private int sizeEdge;
    private int sizeNode;
    private final boolean directed;
    private final boolean labelled;
    private final Map<V, HashSet<Edge<V, L>>> neighbourList;
    final Map<V, Set<V>> adjacencyList;


    /**
     * Graph is the constructor of the class
     *
     * @param directed decides if the graph is oriented or not
     * @param labelled decides if the graph is labelled or not
     */
    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        neighbourList = new HashMap<>();
        adjacencyList = new HashMap<>();
        sizeEdge = 0;
        sizeNode = 0;
    }

    /**
     * checks if the graph is directed or not
     *
     * @return return true if the graph is directed, false otherwise
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * checks if the graph is labelled or not
     *
     * @return return true if the graph is labelled, false otherwise
     */
    @Override
    public boolean isLabelled() {
        return labelled;
    }

    /**
     * adds a node in the graph
     *
     * @param a it's the node to insert in the graph
     * @return return true if the node is inserted in the graph, false otherwise
     */
    @Override
    public boolean addNode(V a) {
        if (a == null) {
            System.err.println("the  element is null");
            return false;
        }
        if (neighbourList.containsKey(a)) {
            System.err.println("the element is already in the graph");
            return false;
        } else {
            neighbourList.put(a, new HashSet<>());
            adjacencyList.put(a, new HashSet<>());
            sizeNode++;
            return true;
        }
    }

    /**
     * adds an edge in the graph
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @param l it's the label of the edge
     * @return true if the edge is inserted in the graph, false otherwise
     */
    @Override
    public boolean addEdge(V a, V b, L l) {
        if (a == null || b == null) {
            System.err.println("the elements are null");
        }
        if (!containsNode(a)) {
            System.err.println("the first element is not in the graph");
            return false;
        } else if (!containsNode(b)) {
            System.err.println("the second element is not in the graph");
            return false;
        } else if (containsEdge(a, b)) {
            System.err.println("the edge is already in the graph");
            return false;
        }
        if (!labelled && l != null) {
            System.err.println("the graph is not labelled you can't add a label");
            return false;
        }
        if (labelled && l == null) {
            System.err.println("the graph is labelled you need to add a label");
            return false;
        }
        neighbourList.get(a).add(new Edge<>(a, b, l));
        adjacencyList.get(a).add(b);
        if (!directed) {
            neighbourList.get(b).add(new Edge<>(b, a, l));
            adjacencyList.get(b).add(a);
        }
        sizeEdge++;
        return true;
    }

    /**
     * adds an edge in the graph without a label
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @return true if the edge is inserted in the graph, false otherwise
     */
    public boolean addEdge(V a, V b) {
        if (!labelled) {
            return addEdge(a, b, null);
        } else {
            System.out.println("the graph is labelled you need to add a label");
            return false;
        }
    }

    /**
     * Checks if a node is in the graph
     *
     * @param a it's the node to check
     * @return return true if the node is in the graph, false otherwise
     */
    @Override
    public boolean containsNode(V a) {
        return neighbourList.containsKey(a);
    }

    /**
     * Checks if an edge is in the graph
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @return return true if the edge is in the graph, false otherwise
     */
    @Override
    public boolean containsEdge(V a, V b) {
        if (!containsNode(a) || !containsNode(b)) {
            System.err.println("The Element is not in the graph");
            return false;
        }
        return adjacencyList.get(a).contains(b);
    }

    /**
     * removeNode is a method that remove a node from the graph
     *
     * @param a it's the node to remove
     * @return return true if the node is removed from the graph, false otherwise
     */
    @Override
    public boolean removeNode(V a) {
        if (a == null) {
            return false;
        }
        if (!containsNode(a)) {
            return false;
        } else {
            Set<V> adjacentNodes = adjacencyList.get(a);
            for (V adjacentNode : adjacentNodes) {
                Set<Edge<V, L>> edges = neighbourList.get(adjacentNode);
                edges.removeIf(edge -> edge.getEnd().equals(a));
                sizeEdge--;
                if (!directed) {
                    Set<V> edgesToRemove = adjacencyList.get(adjacentNode);
                    edgesToRemove.remove(a);

                }
            }


            neighbourList.remove(a);
            adjacencyList.remove(a); // Remove the node entry from the adjacencyList
            sizeNode--;
            return true;
        }
    }

    /**
     * removes an edge from the graph
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @return return true if the edge is removed from the graph, false otherwise
     */
    @Override
    public boolean removeEdge(V a, V b) {
        if (a == null || b == null) {
            System.err.println("The Elements are null");
            return false;
        }
        if (!containsNode(a)) {
            System.err.println("the first element is not in the graph");
            return false;
        } else if (!containsNode(b)) {
            System.err.println("the second element is not in the graph");
            return false;
        } else if (!containsEdge(a, b)) {
            System.err.println("the edge is not in the graph");
            return false;
        } else {
            HashSet<Edge<V, L>> edges = neighbourList.get(a);
            if (edges.removeIf(edge -> edge.getEnd().equals(b))) {
                adjacencyList.get(a).remove(b);
                if (!directed) {
                    HashSet<Edge<V, L>> reverseEdges = neighbourList.get(b);
                    reverseEdges.removeIf(edge -> edge.getEnd().equals(a));
                    adjacencyList.get(b).remove(a);
                }
                sizeEdge--;
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * checks if the graph is empty
     *
     * @return return true if the graph is empty, false otherwise
     */
    public boolean isEmpty() {
        return numNodes() == 0 && numEdges() == 0;
    }

    /**
     * gets the number of nodes in the graph
     *
     * @return return the number of nodes in the graph
     */
    @Override
    public int numNodes() {
        return sizeNode;
    }

    /**
     * gets the number of edges in the graph
     *
     * @return return the number of edges in the graph
     */
    @Override
    public int numEdges() {
        return sizeEdge;
    }

    /**
     * gets the label of an edge
     *
     * @param a it's the first node of the edge
     * @param b it's the second node of the edge
     * @return return the label of the edge
     */
    @Override
    public L getLabel(V a, V b) {
        if (!labelled) {
            System.err.println("The Graph is not labelled");
            return null;
        }
        if (!this.containsEdge(a, b)) {
            System.err.println("The Edge is not in the graph");
        }
        HashSet<Edge<V, L>> edges = neighbourList.get(a);
        for (Edge<V, L> edge : edges) {
            if (edge.getEnd().equals(b)) {
                return edge.getLabel();
            }
        }
        return null;
    }

    /**
     * gets the nodes of the graph
     *
     * @return return the nodes of the graph
     */
    @Override
    public Collection<V> getNodes() {
        if (numNodes() == 0) {
            return Collections.emptySet();
        }
        return neighbourList.keySet();
    }

    @Override
    public Collection<? extends AbstractEdge<V, L>> getEdges() {
        Collection<Edge<V, L>> edges = new HashSet<>();
        if (numEdges() == 0) {
            return Collections.emptySet();
        }
        for (Set<Edge<V, L>> edgeSet : neighbourList.values()) {
            edges.addAll(edgeSet);
        }
        return edges;
    }

    /**
     * getNeighbours is a method that return the neighbours of a node
     *
     * @param a it's the node to get the neighbours
     * @return return the neighbours of the node
     */
    @Override
    public Collection<V> getNeighbours(V a) {
        if (!containsNode(a)) {
            System.err.println("the node is not in the graph");
            return Collections.emptySet();
        }
        if (numEdges() == 0) {
            return Collections.emptySet();
        }
        return adjacencyList.get(a);
    }

    /**
     * to string is a method that return a string that represent the graph
     *
     * @return return a string that represent the graph
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (V node : neighbourList.keySet()) {
            s.append(node.toString()).append("->");
            for (Edge<V, L> edge : neighbourList.get(node)) {
                s.append(edge.getEnd().toString()).append(" ");

            }
            s.append("\n");
        }
        return s.toString();
    }

}
