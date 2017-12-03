package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A double linked list collection object that stores data
 * as a node.
 * 
 * @author Jeffrey
 * @version 2017-12-02
 * 
 * @param <E>
 *          Generic paramaterized type.
 */
public class DoubleLinkedList<E> implements Iterable<E>, Serializable {
    
    private static final long serialVersionUID = -2260912150597434272L;
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    /**
     * Instantiates a DoubleLinkedList object.
     */
    public DoubleLinkedList() {
        
    }
    
    /**
     * An single collection unit that contains the data stored
     * in the list and two referencest to the next and previous
     * node in the list.
     * 
     * @author Jeffrey
     * @version 2017-12-02
     * 
     * @param <E>
     *          generic paramaterized type.
     */
    public static class Node<E> implements Serializable {
        
        private static final long serialVersionUID = 3024491391320805720L;
       
        /**
         * The data stored in this node.
         */
        private E data;
        
        /**
         * Reference to the next node in the linked list.
         */
        private Node<E> next; 
        
        /**
         * Reference to the previous node in the linked list.
         */
        private Node<E> prev;
        
        
        /**
         * Instantiates a new Node object with no references to 
         * a next or previous node.
         * 
         * @param data
         *          data to be stored in this Node.
         */
        Node(E data) {
            this(data, null, null);
            
        }
        
        /**
         * Three paramater constructor to instantiate a Node object
         * with references to a next Node and a previous Node.
         * 
         * @param data
         *              data to be stored in the Node
         * @param next
         *              next Node in the LinkedList
         * @param prev
         *              previous Node in the LinkedList
         */
        Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        
        /**
         * Returns the data stored in a Node.
         * 
         * @return
         *          the data stored in the Node.
         */
        E getData() {
            return data;
        }
        
        /**
         * Get the next Node in the list.
         * 
         * @return
         *      the next Node in the LinkedList
         */
        Node<E> getNext() {
            return next;
        }
        
        /**
         * Get the previous Node in the list.
         * 
         * @return
         *      the previous Node in the LinkedList
         */
        Node<E> getPrev() {
            return prev;
        }   
    }

    /**
     * Stores data in a Node and adds it to the front
     * of the Linked List.
     * 
     * @param data
     *              data to be added.
     * @throws CouldNotAddException
     *              if data null.
     */
    public void addToFront(E data) throws CouldNotAddException {
        
        if (data == null) {
            throw new CouldNotAddException("Cannot add null object");
        }
        if (head == null) {
            tail = new Node<E>(data);
            head = tail;
        } else {
            head = new Node<E>(data, head, null);
        }
        
        size++;
    }
    
    /**
     * Removes data from the front of the list.
     * 
     * @return
     *          the data stored in the Node at the front of the list.
     * @throws CouldNotRemoveException
     *          if List is empty.
     */
    public E removeFromFront() throws CouldNotRemoveException {
       
        //Check if list empty
        if (isEmpty()) {
            throw new CouldNotRemoveException("List is empty.");
        }
        
        // Store data of Node to be removed
        E data = head.data;
        
        // Check if List is size 1, if so empty list.
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            //else assign new Node as new head.
            head = head.next;
        }
        
        size--;
        return data;
    }
    
    /**
     * Stores data in a Node and adds it to the end of the LinkedList.
     * 
     * @param data
     *          the data to add.
     * @throws CouldNotAddException
     *          if data is null.
     */
    public void addToEnd(E data) throws CouldNotAddException {
        
        // Check if data is null
        if (data == null) {
            throw new CouldNotAddException("Cannot add null object");
        }
        
        // Check if list is empty
        if (isEmpty()) {
            head = new Node<E>(data);
            tail = head;
        } else {
            Node<E> temp = new Node<E>(data);
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        
        size++;
    }
    
    /**
     * Removes data from the end of the LinkedList.
     * 
     * @return
     *          data stored in the removed Node.
     * @throws CouldNotRemoveException
     *          if list is empty.
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        E removed;
        if (isEmpty()) {
            throw new CouldNotRemoveException("List is empty");
        } else if (head == tail) {
            removed = tail.data;
            head = null;
            tail = null; 
        } else {
            removed = tail.data;
            tail = tail.prev;
            tail.next = null;
         
        }
        size--;
        return removed;
        
    }
    
    /**
     * Returns the element at the beginning of the List.
     * 
     * @return
     *          the data stored in the Node at the head of the list.
     */
    public E getFirst() {
        
        if (head != null) {
            return head.data;
        } else {
            return null;
        }
        
    }
    
    /**
     * Returns the element at the end of the List.
     * 
     * @return
     *          the data stored in the Node at the tail of the list.
     */
    public E getLast() {
        if (tail != null) {
            return tail.data;
        } else {
            return null;
        }
       
    }
    
    /**
     * Gets the number of elements in the LinkedList.
     * @return
     *          current size of the LinkedList.
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks whether the List is empty of Nodes.
     * 
     * @return
     *      true if no Nodes currently in the list.
     */
    public boolean isEmpty() {
        return head != null && tail != null;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator <E>() {
            
            private Node<E> next = head;       
            
            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public E next() throws NoSuchElementException {
                
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = next.data;
                next = next.next;
                return data;
            }
            
            
        };
    } 
}
