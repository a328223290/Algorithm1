package Week2;

import edu.princeton.cs.algs4.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;
    private Item[] arr;
    private int N;
    // The first item in the queue.
    private int first;
    // The last item in the queue plus 1.
    private int last;

    // construct an empty randomized queue
    public RandomizedQueue(){
        arr = (Item[])new Object[INIT_CAPACITY];
        N = 0;
        first = 0;
        last = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return N;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (N == arr.length) resize(2 * arr.length);
        if (last == arr.length) last = 0;
        arr[last++] = item;
        N++;
        // 循环队列
        swapItem();
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = arr[first];
        // Avoid loitering
        arr[first++] = null;
        if (first == arr.length) first = 0;
        N--;
        if (N == arr.length / 4) resize(arr.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniformInt(N);
        return arr[(first + random) % arr.length];
    }

    private void resize(int capacity){
        assert capacity > N;
        Item[] newArr = (Item[]) new Object[capacity];

        for (int i = 0; i <= N; i++){
                newArr[i] = arr[(first + i) % arr.length];
            }
        arr = newArr;
        first = 0;
        last = N;
    }

    //这个函数让队列first的元素与前面的任一元素交换（包括它自己）
    //这样一来dequeue中输出的就可以是任意一个元素，完全随机
    //还可以避免队列中出现null的情况，非常巧妙
    private void swapItem(){
        int index = StdRandom.uniformInt(N);
        Item temp = arr[first];
        arr[first] = arr[(first + index) % arr.length];
        arr[(first + index) % arr.length] = temp;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private Item[] randomizedArr;
        int current;

        public RandomIterator() {
            current = 0;
            randomizedArr = (Item[]) new Object[N];
            for (int i = 0; i < N; i++){
                randomizedArr[i] = arr[(first + i) % arr.length];
            }
            // 洗牌创造随机性
            StdRandom.shuffle(randomizedArr);
        }

        public boolean hasNext(){
            return current < N;
        }

        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            return randomizedArr[current++];
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                queue.enqueue(item);
                System.out.print("Current queue: ");
                for (String s : queue) {
                    System.out.print(s + " ");
                }
                System.out.println("");
                System.out.println("Current size: " + queue.size());
                System.out.println("########################################");
            }
            else if (!queue.isEmpty()) {
                System.out.println("Dequeued item: " + queue.dequeue());
                System.out.println("Current size: " + queue.size());
            }
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}


