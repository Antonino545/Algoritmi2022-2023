package PriorityQueue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 * PriorityQueue.PriorityQueue is a queue that orders its elements according to a comparator
 *
 * @param <E> the type of elements in this queue
 */
public class PriorityQueue<E> implements AbstractQueue<E> {

    private final Comparator<E> compar;
    private final ArrayList<E> heap;
    private final Set<E> elements;
    private int size;

    /**
     * constructor of the class
     *
     * @param comparator the comparator that will be used to order this priority queue
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.compar = comparator;
        this.size = 0;
        this.heap = new ArrayList<>();
        this.elements = new HashSet<>();
        heap.addFirst(null);//add null to index 0
    }

    /**
     * gets the index of the left child of the element at index i
     *
     * @param i index of node
     * @return the index of the left child of the node at index i
     */
    private int getLeftChild(int i) {
        return 2 * i;
    }

    /**
     * gets the index of the right child of the element at index i
     *
     * @param i index of node
     * @return the index of the right child of the node at index i
     */
    private int getRightChild(int i) {
        return 2 * i + 1;
    }

    /**
     * gets the index of the parent of the element at index i
     *
     * @param i index of node
     * @return the index of the parent of the node at index i
     */
    private int getParent(int i) {
        return i / 2;
    }

    /**
     * Swaps two elements in the heap
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * fixHeap is used to fix the heap after a push and remove operation
     *
     * @param i index of node
     */
    private void fixHeap(int i) {
        while (i != 1 && compar.compare(heap.get(i), heap.get(getParent(i))) < 0) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    /**
     * minHeapify is used to fix the heap after a pop or remove operation
     *
     * @param i index of node
     */
    private void minHeapify(int i) {
        int smallest = i;
        int left = getLeftChild(i);
        int right = getRightChild(i);

        if (left <= size && compar.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }
        if (right <= size && compar.compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }


    /**
     * checks if the priority queue is empty
     *
     * @return true if size is zero, false otherwise
     */
    @Override
    public boolean empty() {
        return this.size == 0;
    }

    /**
     * adds an element to the priority queue
     *
     * @param e element to insert in the queue
     * @return true if the element is added, false if the element is null or if it's already in the heap
     */
    @Override
    public boolean push(E e) {
        if (e == null || this.contains(e)) {
            System.err.println("The element is null or the element is already in the heap");
            return false;
        }

        size++;
        heap.add(size, e);
        elements.add(e);
        fixHeap(size);
        return true;
    }

    /**
     * checks if the priority queue cointains an element
     *
     * @param e element to find in the queue
     * @return true if the element is in the queue false otherwise
     */
    @Override
    public boolean contains(E e) {
        return elements.contains(e);
    }

    /**
     * gets the element with the highest priority(the root)
     *
     * @return the element with the highest priority
     */
    @Override
    public E top() {
        if (empty()) {
            System.err.println("Heap is empty");
            System.err.println("Insert elements first");
            return null;
        }
        return heap.get(1);
    }

    /**
     * get the number of elements in the queue
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return size;
    }

    /**
     * removes the element with the highest priority (the root of the heap)
     */
    @Override
    public void pop() {
        if (empty()) {
            System.err.println("Heap is empty");
            System.err.println("Insert elements first");
        } else {
            E removedElement = heap.get(1);
            swap(1, size);
            heap.remove(size);
            size--;
            minHeapify(1);
            elements.remove(removedElement);
        }
    }

    /**
     * removes an element from the heap if it's present in the heap and if it's not null and fix the heap after the remove with
     * minHeapify or fixHeap depending on the element that is removed and the element that is in the root of the heap
     * after the remove operation
     *
     * @param e element to remove
     * @return true if the element is removed from the heap false otherwise
     */
    @Override
    public boolean remove(E e) {
        if (!this.contains(e) || empty() || e == null) {
            System.err.println("Element not in the heap or the element is null");
            return false;
        }
        int IndexRemove = heap.indexOf(e);
        E lastElement = heap.get(size);
        heap.set(IndexRemove, lastElement);
        heap.remove(size);
        elements.remove(e);
        size--;
        if (compar.compare(lastElement, e) < 0) {
            fixHeap(IndexRemove);
        } else {
            minHeapify(IndexRemove);
        }
        return true;
    }

    /**
     * replace an element in the queue with another element
     *
     * @param a the element to be replaced
     * @param b the element to replace a
     */
    public void substitutionPriority(E a, E b) {
        if (contains(a)) {
            remove(a);
            push(b);
        } else {
            System.out.println("Element not in the heap");
        }
    }

    /**
     * get a string representation of the heap
     *
     * @return a string representation of the heap
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            s.append(heap.get(i)).append(" ");
        }
        return s.toString();
    }


}