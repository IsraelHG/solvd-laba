package com.solvd.collections;

import java.util.NoSuchElementException;

/**
 * LinkedList - A custom implementation Java class that provides
 * methods to add, remove, and manipulate nodes in the list.
 */
public class LinkedList<T> {
    /**
     * Node object that will contain the generic element,
     * plus two pointers that link the next node/previous node.
     *
     * @param <T>
     */
    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;

        public Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.item = element;
            this.next = next;
        }
    }

    /**
     * Points to the first node of the list.
     */
    private Node<T> head;
    /**
     * Points to the last node of the list.
     */
    private Node<T> tail;
    /**
     * Holds the number of elements in the list.
     */
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Links the element e as the front element in the list.
     *
     * @param e element to add in list
     */
    private void linkFront(T e) {
        final Node<T> newNode = new Node<>(null, e, null);

        // Adds a new node to this list if it is empty.
        if (this.head == null && this.tail == null && this.size == 0) {
            this.head = this.tail = newNode;
        }
        // Adds a new node to the front of
        // the list if it is NOT empty.
        else {
            newNode.next = head;
            this.head.prev = newNode;
            this.head = newNode;
        }
        this.size++;
    }

    /**
     * Links the element e as the back element in the list.
     *
     * @param e element to add in list
     */
    private void linkBack(T e) {
        final Node<T> newNode = new Node<>(null, e, null);

        // Adds a new node to this list if it is empty.
        if (this.head == null && this.tail == null && this.size == 0) {
            this.head = this.tail = newNode;
        }
        // Adds a new node to the end of
        // the list if it is NOT empty.
        else {
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    /**
     * Unlinks non-null front node.
     *
     * @param head head node used to
     *             remove the front element in the list.
     */
    private T unlinkFront(Node<T> head) {
        final T tempItem = head.item;

        if (head == this.tail && this.size == 1) {
            this.head.prev = this.head.next = null;
            this.head = this.tail = null;
            this.size--;
            return tempItem;
        }
        this.head = this.head.next;
        this.head.prev = null;
        this.size--;
        return tempItem;
    }

    /**
     * Unlinks non-null back node.
     *
     * @param tail tail node used to
     *             remove the back element in the list.
     */
    private T unlinkBack(Node<T> tail) {
        final T tempItem = tail.item;

        if (tail == this.head && this.size == 1) {
            this.tail.next = this.tail.prev = null;
            this.tail = this.head = null;
            this.size--;
            return tempItem;
        }
        this.tail = this.tail.prev;
        this.tail.next = null;
        this.size--;
        return tempItem;
    }

    /**
     * Unlinks non-null node x.
     *
     * @param x node used to
     *          remove the specified element in the list.
     */
    private T unlink(Node<T> x) {
        // If only one node in the list
        if (this.head == this.tail && this.size == 1) {
            final T tempItem = head.item;
            this.head.next = this.head.prev = null;
            this.tail = this.head = null;
            this.size--;
            return tempItem;
        }
        // If the node is at the front of the list
        else if (x == this.head) {
            return unlinkFront(x);
        }
        // If the node is at the back of the list
        else if (x == this.tail) {
            return unlinkBack(x);
        }
        // If the node is in-between the list
        else {
            Node<T> prev = x.prev;
            Node<T> next = x.next;
            T tempItem = x.item;

            // Linking process...
            prev.next = next;
            x.prev = null;

            next.prev = prev;
            x.next = null;

            x.item = null;
            this.size--;
            return tempItem;
        }
    }

    /**
     * Adds the element to the end of this list.
     * <p>This method is equivalent to {@link #addBack(Object)}.
     *
     * @param e element to be added to this list.
     * @return true if element was added to this list.
     */
    public boolean add(T e) {
        linkBack(e);
        return true;
    }

    /**
     * Adds the element at the front of this list.
     *
     * @param e element to be added to this list.
     */
    public void addFront(T e) {
        linkFront(e);
    }

    /**
     * Adds the element to the back of this list.
     * <p>This method is equivalent to {@link #add(Object)}.
     *
     * @param e element to be added to this list.
     */
    public void addBack(T e) {
        linkBack(e);
    }

    /**
     * Removes and returns the front element from the list.
     *
     * @return the front element from the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFront() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        return unlinkFront(this.head);
    }

    /**
     * Removes and returns the back element from the list.
     *
     * @return the back element from the list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeBack() {
        if (this.tail == null) {
            throw new NoSuchElementException();
        }
        return unlinkBack(this.tail);
    }

    /**
     * Removes the first present specified of the element from this list,
     * if it is present. If this list does not contain the element, it does nothing.
     * Returns {@code true} if this list
     * contained the specified element.
     *
     * @param x element to be removed from this list.
     * @return {@code boolean} Depending if this list contained the specified element
     */
    public boolean remove(Object x) {
        // Looks in the list if null is present as en element
        if (x == null) {
            for (Node<T> curr = this.head; curr != tail.next; curr = curr.next) {
                if (curr.item == null) {
                    unlink(curr);
                    return true;
                }
            }
        }
        // Looks in the list if a non-null
        // object is present as an element.
        else {
            for (Node<T> curr = this.head; curr != tail.next; curr = curr.next) {
                if (x.equals(curr.item)) {
                    unlink(curr);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the element at the index position in the list.
     *
     * @param index index of the element to return
     * @return the element in this list
     * @throws IndexOutOfBoundsException Checks if the index is not within the list.
     */
    public T get(int index) {
        if (!(index >= 0 && index < this.size)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        return node(index).item;
    }

    /**
     * Returns the Node at the index position.
     */
    private Node<T> node(int index) {
        Node<T> curr;
        // Starts the search from the front of the list.
        if (index < (this.size / 2)) {
            curr = this.head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        }
        // Starts the search from the back of the list.
        else {
            curr = this.tail;
            for (int i = this.size - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        return curr;
    }

    /**
     * Prints the list from front to back.
     */
    public void printList() {
        Node<T> curr = this.head;
        while (curr != null) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
