package PriorityQueue;

public interface AbstractQueue<E> {
    /**
     * check if the queue is empty
     *
     * @return true if the queue is empty, false otherwise
     */
    boolean empty(); // controlla se la coda è vuota -- O(1)

    /**
     * insert an element into the queue according to the comparator
     *
     * @return true if push was successful, false otherwise
     */
    boolean push(E e); // aggiunge un elemento alla coda -- O(logN)

    /**
     * check if the queue contains an element using the hashset
     *
     * @param e the element to check
     * @return true if the element is in the queue, false otherwise
     */
    boolean contains(E e); // controlla se un elemento è in coda -- O(1)

    /**
     * get the element with the highest priority
     *
     * @return The element with the highest priority
     */
    E top(); // accede all'elemento in cima alla coda -- O(1)

    /**
     * remove the element with the highest priority
     */
    void pop(); // rimuove l'elemento in cima alla coda -- O(logN)

    /**
     * remove an element from the queue
     *
     * @param e the element to remove
     * @return true if the element was removed, false otherwise
     */
    boolean remove(E e); // rimuove un elemento se presente in coda -- O(logN)
}

