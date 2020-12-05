package uk.ac.ucl;

import java.util.Iterator;

public class LinkedListMySet<T extends Comparable<T>> extends AbstractMySet<T> {

    private Element<T> head;
    private int size = 0;

    private static class Element<E>
    {
        private E value;
        private Element<E> next;
        public Element(E value, Element<E> next)
        {
            this.value = value;
            this.next = next;
        }
    }

    public LinkedListMySet (){
        this.head = null;
    }


    @Override
    public void add(T value) throws MySetException {
        if(contains(value)){
            throw new MySetException("The value already exist.");
        }else {
            if (isEmpty()) {
                Element<T> new_node = new Element(value, null);
                this.head = new_node;
            } else {
                // Always add to the front because it's linked list's strength to
                // add things at O(1).
                head = new Element<>(value,head);
            }
            size++;
        }
    }

    @Override
    public boolean contains(T value) {
        Element<T> lastElement = new Element<>(null,head);
        while(lastElement.next != null){
            if(lastElement.value != null && lastElement.value.equals(value)){
                return true;
            }
            lastElement = lastElement.next;
        }
        if(lastElement.value != null && lastElement.value.equals(value)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(T value){
        size--;
        Element<T> currentNode = this.head;
        Element<T> prevNode = null;
        if(currentNode != null && currentNode.value == value){
            head = currentNode.next;
            return;
        }
        while(currentNode.next != null && currentNode.value != value){
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        if(currentNode == null){return;}

        prevNode.next = currentNode.next;
    }

    public class LinkedListMySetIterator implements Iterator<T>
    {
        Element<T> currentNode = new Element<>(null,head);

        int index = 0;
        public boolean hasNext()
        {
            return LinkedListMySet.this.size() > index ;
        }

        public T next()
        {
            index += 1;
            currentNode = currentNode.next;
            return currentNode.value;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListMySetIterator();
    }

    @Override
    public int compareTo(MySet<T> o) {
        throw new UnsupportedOperationException();
    }

}
