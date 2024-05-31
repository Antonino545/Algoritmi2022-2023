package Graph;

import java.util.Collection;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Graph
 */
public class GraphTest {
    Graph<Integer, String> graph = null;

    /**
     * Test for check if the graph is directed
     */
    @Test
    public void TestIsDirected() {
        graph = new Graph<Integer, String>(true, false);
        assertTrue(graph.isDirected());
    }

    /**
     * Test for check if the graph is not directed
     */
    @Test
    public void TestIsLabelled() {
        graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.isLabelled());
    }

    /**
     * Test for check if the graph is empty or not
     */
    @Test
    public void TestNodeEmpty() {
        graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.isEmpty());
    }

    /**
     * Test for check if add node works  with one node correctly
     */
    @Test
    public void TestNodeAdded() {
        graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.addNode(5));
        assertTrue(graph.containsNode(5));
        assertEquals(1, graph.numNodes());
        assertFalse(graph.isEmpty());
    }

    /**
     * Test for check if add Edge in a not directed graph works correctly and use containsEdge
     */
    @Test
    public void TestEdgeAddedNotDirected() {
        graph = new Graph<Integer, String>(false, false);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6));
        assertTrue(graph.containsEdge(5, 6));
        assertTrue(graph.containsEdge(6, 5));
        assertEquals(1, graph.numEdges());
        assertEquals(2, graph.numNodes());
        assertFalse(graph.isEmpty());
    }

    /**
     * Test for check if add Edge in a directed graph works correctly and use containsEdge
     */
    @Test
    public void TestEdgeDirected() {
        graph = new Graph<Integer, String>(true, false);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertFalse(graph.isEmpty());
    }

    /**
     * Test for check if remove node works correctly  in a not directed graph and use containsEdge
     */
    @Test
    public void TestRemoveNode() {
        graph = new Graph<Integer, String>(true, false);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertFalse(graph.removeNode(null));
        assertFalse(graph.removeNode(8));
        assertTrue(graph.addEdge(5, 6));
        assertTrue(graph.addEdge(5, 7));
        assertTrue(graph.removeNode(5));
        assertFalse(graph.containsNode(5));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertFalse(graph.containsEdge(5, 7));
        assertTrue(graph.containsNode(6));
        assertFalse(graph.containsNode(5));
        assertEquals(2, graph.numNodes());
        assertEquals(0, graph.numEdges());
    }

    /**
     * Test for check if remove node works correctly  in a not directed graph and use containsEdge
     */
    @Test
    public void TestRemoveNode_notdirected() {
        graph = new Graph<Integer, String>(false, false);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertTrue(graph.addNode(8));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.addEdge(5, 7, null));
        assertTrue(graph.addEdge(5, 8, null));
        assertTrue(graph.removeNode(5));
        assertFalse(graph.containsNode(5));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertEquals(3, graph.numNodes());
        assertEquals(0, graph.numEdges());
        assertTrue(graph.containsNode(6));
        assertFalse(graph.containsNode(5));
    }

    /**
     * Test for check if remove edge works correctly in a directed graph and use containsEdge
     */
    @Test
    public void TestRemoveEdgeDirected() {
        graph = new Graph<Integer, String>(true, false);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.removeEdge(5, 6));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertTrue(graph.containsNode(6));
        assertTrue(graph.containsNode(5));
    }

    /**
     * Test for check if remove edge works correctly in a not directed graph and use containsEdge
     */
    @Test
    public void TestRemoveEdgeNotDirected() {
        graph = new Graph<Integer, String>(false, false);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6));
        assertTrue(graph.removeEdge(5, 6));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertTrue(graph.containsNode(6));
        assertTrue(graph.containsNode(5));
    }

    /**
     * Test for check if getLabel works correctly
     */
    @Test
    public void TestGetLabel() {
        graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6, "ciao"));
        assertEquals(graph.getLabel(5, 6), "ciao");
    }

    /**
     * Test for check if getNeighbours works correctly
     */
    @Test
    public void TestGetneighbourList() {
        graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertTrue(graph.addNode(8));
        assertTrue(graph.addEdge(5, 6, "ciao"));
        assertTrue(graph.addEdge(5, 7, "ciao"));
        assertTrue(graph.addEdge(6, 7, "ciao"));
        assertTrue(graph.addEdge(7, 8, "ciao"));
        assertTrue(graph.addEdge(8, 5, "ciao"));
        Collection<Integer> test = graph.getNeighbours(5);
        assertTrue(test.contains(6));
        assertTrue(test.contains(7));

    }

    /**
     * Test for check if getNodes works correctly and use containsNode
     */
    @Test
    public void TestGetNode() {
        graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        Collection<Integer> test = graph.getNodes();
        assertTrue(test.contains(5));
        assertTrue(test.contains(6));
        assertTrue(test.contains(7));
    }


    @Test
    public void testGetEdges() {
        Graph<Integer, String> graph = new Graph<Integer, String>(false, true);
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertTrue(graph.addEdge(5, 6, "ciao"));
        assertTrue(graph.addEdge(5, 7, "ciao"));
        assertTrue(graph.addEdge(6, 7, "ciao"));
        Collection<? extends AbstractEdge<Integer, String>> test = graph.getEdges();
        assertEquals(3, test.size() / 2);
        assertEquals( "[5,7,ciao, 7,6,ciao, 7,5,ciao, 5,6,ciao, 6,5,ciao, 6,7,ciao]", test.toString());
    }

    @Test
    public void testRemoveNode() {
        Graph<Integer, String> graph = new Graph<Integer, String>(true, false);
        assertTrue(graph.addNode(1));
        assertTrue(graph.addNode(2));
        assertTrue(graph.addNode(3));
        assertTrue(graph.addNode(4));
        assertTrue(graph.addNode(5));
        assertTrue(graph.addEdge(1, 2));
        assertTrue(graph.addEdge(1, 3));
        assertTrue(graph.addEdge(1, 4));
        assertTrue(graph.addEdge(1, 5));
        assertTrue(graph.removeNode(1));
        assertEquals(0, graph.numEdges());
        assertEquals(4, graph.numNodes());
    }
}


