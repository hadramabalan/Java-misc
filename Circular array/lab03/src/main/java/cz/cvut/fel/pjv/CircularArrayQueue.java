package cz.cvut.fel.pjv;

/**
 * Implementation of the {@link Queue} backed by fixed size array.
 */
public class CircularArrayQueue implements Queue {
    int size, head, tail, count;
    String[] elements;
    /**
     * Creates the queue with capacity set to the value of 5.
     */
    public CircularArrayQueue() {
        this.count = 0;
        this.head = 0;
        this.size = 5;
        this.tail = -1;
        this.elements = new String[this.size];
    }


    /**
     * Creates the queue with given {@code capacity}. The capacity represents maximal number of elements that the
     * queue is able to store.
     * @param capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
        this.count = 0;
        this.head = 0;
        this.tail = -1;
        this.size = capacity;
        this.elements = new String[this.size];
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public boolean isFull() {
        return this.size == this.count;
    }

    @Override
    public boolean enqueue(String obj) {
        if (obj == null || isFull()){
            return false;
        }
        else {
            //if(++this.tail == this.size) {this.tail = 0;}
            this.tail = (++this.tail == this.size) ? 0 : this.tail;
            this.elements[this.tail] = obj;
            ++this.count;
            return true;
        }
    }

    @Override
    public String dequeue() {
        String ret = this.elements[this.head++];
        --this.count;
        if(this.head == this.size) this.head = 0;
        return ret;
    }

    @Override
    public void printAllElements() {
        for(int i = 0; i < this.count; i++){
            if(i+this.head >= this.size) System.out.println(this.elements[i - this.size + this.head]);
            else System.out.println(this.elements[i+this.head]);
        }
    }
}