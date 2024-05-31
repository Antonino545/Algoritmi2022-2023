package Graph;

import java.io.*;
import java.util.*;

import PriorityQueue.PriorityQueue;

/**
 * Prim is a class to implement the Prim's algorithm to find the minimum spanning forest of a graph
 */
public class Prim {


    private static final Graph<String, Float> graph = new Graph<String, Float>(false, true);
    private static int numNode = 0;

    /**
     * Minimum spanning Forest is a method that find the minium spanning forest of a graph
     *
     * @param graph is the graph where we want to find the minimum spanning tree
     * @param <V>   is the type of the node
     * @param <L>   is the type of the label
     * @return return the minimum spanning forest of the graph
     */
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
        Comparator<Edge<V, L>> edgeComparator = (o1, o2) -> Double.compare(o1.getLabel().doubleValue(), o2.getLabel().doubleValue());
        Set<V> visited = new HashSet<>();
        Collection<Edge<V, L>> forest = new ArrayList<>();
        PriorityQueue<Edge<V, L>> queue = new PriorityQueue<>(edgeComparator);
        for (V node : graph.getNodes()) {
            if (!visited.contains(node)) {
                visited.add(node);
                for (V next : graph.getNeighbours(node)) {
                    queue.push(new Edge<>(node, next, graph.getLabel(node, next)));
                }
                while (!queue.empty()) {
                    Edge<V, L> edge = queue.top();
                    queue.pop();
                    V currentNode = edge.getEnd();
                    if (visited.contains(currentNode)) {
                        continue;
                    }
                    forest.add(edge);
                    visited.add(currentNode);
                    for (V nextNode : graph.getNeighbours(currentNode)) {
                        if (!visited.contains(nextNode)) {
                            queue.push(new Edge<>(currentNode, nextNode, graph.getLabel(currentNode, nextNode)));
                        }
                    }
                }
            }
        }
        numNode = visited.size();
        return forest;
    }


    /**
     * ReadCsv is a method that read the csv file and create the graph
     *
     * @param path is the path of the csv file
     */
    private static void readCsv(String path) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                graph.addNode(split[0]);
                graph.addNode(split[1]);
                graph.addEdge(split[0], split[1], Float.valueOf(split[2]));
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * printMsf is a method that print the minimum spanning forest
     */
    private static void printMsf() {
        double weight = 0;
        Collection<? extends AbstractEdge<String, Float>> forest = minimumSpanningForest(graph);
        for (AbstractEdge<String, Float> edge : forest) {
            System.out.println(edge);
            weight += edge.getLabel().doubleValue();
        }
        System.err.println("Summary:");
        System.err.println("Number of edges in Forest: " + forest.size());
        System.err.println("Number of nodes in Forest: " + numNode);
        weight= weight / 1000;
        System.err.printf("Total weight of Forest: %.3f km%n",weight);

    }

    /**
     * Main is a method that read the csv file and print the minimum spanning forest
     *
     * @param args is the path of the csv file
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java Prim <file>");
            System.exit(1);
        }
        readCsv(args[0]);
        printMsf();
    }
}
