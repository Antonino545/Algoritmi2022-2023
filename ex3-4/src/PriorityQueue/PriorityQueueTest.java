package PriorityQueue;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * PriorityQueueTest is a class that test the methods of PriorityQueue
 */
public class PriorityQueueTest {
    private PriorityQueue<Integer> queue;

    /*
     * Test the method empty
     */
    @Test
    public void testIsEmpty() {
        queue = new PriorityQueue<>(new ComparInt());
        assertTrue(queue.empty());
    }

    /*
     * Test push one element in the queue
     */
    @Test
    public void testPushOneint() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(1);
        assertFalse(queue.empty());
        assertEquals("1 ", queue.toString());
    }

    /**
     * Test push  elements in the queue
     */
    @Test
    public void testPushInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(1);
        assertEquals("1 2 8 5 ", queue.toString());
    }

    /**
     * Test pop one element in the queue
     */
    @Test
    public void testpopOneInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(1);
        queue.pop();
        assertTrue(queue.empty());
    }

    /**
     * Test remove elements in the queue
     */
    @Test
    public void testRemoveTwoInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.remove(5);
        assertEquals("2 ", queue.toString());
    }

    /**
     * test pop integer elements in the queue
     */
    @Test
    public void testPopInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(1);
        queue.pop();
        assertEquals("2 5 8 ", queue.toString());
    }

    /**
     * test remove integer elements in the queue
     */
    @Test
    public void testremoveInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(10);
        queue.push(2);
        queue.push(8);
        queue.push(1);
        queue.remove(1);
        queue.remove(2);
        assertEquals("8 10 ", queue.toString());
    }

    /**
     * test contains integer elements in the queue
     */
    @Test
    public void testContainsInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(1);
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(8));
    }

    /**
     * test push,remove and top integer elements in the queue
     */
    @Test
    public void testPushRemoveTop() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(1);
        queue.push(0);
        queue.remove(1);
        assertEquals(queue.top().intValue(), 0);
        queue.remove(2);
        assertEquals(queue.top().intValue(), 0);
        queue.remove(5);
        assertEquals(queue.top().intValue(), 0);
    }

    /**
     * tests that the method top works correctly returning the element with highest priority
     */
    @Test
    public void testtopInt() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(10);
        assertEquals(2, queue.top().intValue());
    }

    /**
     * tests that the method changePriority works correctly
     */
    @Test
    public void testchangePriority() {
        queue = new PriorityQueue<>(new ComparInt());
        queue.push(5);
        queue.push(2);
        queue.push(8);
        queue.push(10);
        queue.substitutionPriority(2, 1);
        assertEquals("1 5 8 10 ", queue.toString());
        queue.substitutionPriority(10, 2);
        assertEquals("1 2 8 5 ", queue.toString());
        queue.substitutionPriority(5, 10);
        assertEquals("1 2 8 10 ", queue.toString());
    }

    /**
     * ComparInt is a class that implements Comparator<Integer>
     */
    static class ComparInt implements Comparator<Integer> {

        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }


}