package ca.bcit.comp2526.a2c;

import java.io.Serializable;
import java.util.Iterator;


public class DoubleLinkedList<E> implements Iterable<E>, Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    public class Node<E> implements Serializable {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        E data;
        Node<E> next, prev;
        
        Node (E data) {
            this(data, null, null);
            
        }
        
        Node (E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        
        E getLifeform() {
            return data;
        }
        
        Node<E> getNext() {
            return next;
        }
        
        Node<E> getPrev() {
            return prev;
        }
        
        
    }

    
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
    
    public E removeFromFront() throws CouldNotRemoveException {
        
        if (head == null) {
            throw new CouldNotRemoveException("List is empty.");
        }
        
        E data = head.data;
        
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        
        size--;
        return data;
    }
    
    public void addToEnd(E data) throws CouldNotAddException {
        
        if (data == null) {
            throw new CouldNotAddException("Cannot add null object");
        }
        
        if (head == null) {
            head = new Node<E>(data);
            tail = head;
        } else {
            Node<E> temp = new Node<E>(data);
            tail.next = temp;
            tail = temp;
        }
        
        size++;
    }
    
    public E removeFromEnd() throws CouldNotRemoveException {
        
        if (head == null) {
            throw new CouldNotRemoveException("List is empty");
        } else {
            E removed = tail.data;
            tail = tail.prev;
            tail.next = null;
            size--;
            return removed;
        }
        
    }
    
    public E getFirst() {
        
        if (head !=null) {
            return head.data;
        } else {
            return null;
        }
        
    }
    
    public E getLast() {
        if (tail != null) {
            return tail.data;
        } else {
            return null;
        }
       
    }
    
    public int size() {
        return size;
    }
    
    

    @Override
    public Iterator<E> iterator() {
        return new Iterator <E>() {
            
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                
                return current != tail;
            }

            @Override
            public E next() {
                current = current.next;
                
                return current.data;
            }
            
        };
    }


    
    
    
    

    
}
