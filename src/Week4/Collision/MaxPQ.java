package Week4.Collision;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    private Key[] pq;
    private int N;

    public MaxPQ(){
        pq = (Key[]) new Comparable[2];
        N = 0;
    }

    public MaxPQ(Key[] keys){
        int capacity = 1;
        while (capacity * 2 <= keys.length + 1){
            capacity = capacity * 2;
        }
        capacity = capacity * 2;
        pq = (Key[]) new Comparable[capacity];
        N = keys.length;
        for (int i = 1; i <= keys.length; i++){
            pq[i] = keys[i - 1];
        }
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public Key delMax(){
         if (isEmpty()) throw new NoSuchElementException("The pq is empty.");
         Key max = pq[1];
         pq[1] = pq[N];
         pq[N] = null;
         N--;
         sink(1);
         if (N == pq.length / 4){
             resize(pq.length / 2);
         }
         return max;
    }

    public Key max(){
        if (isEmpty()) throw new NoSuchElementException("The pq is empty.");
        return pq[1];
    }

    public void insert(Key key){
        if (N + 1 == pq.length){
            resize(pq.length * 2);
        }
        pq[++N] = key;
        swim(N);
    }

    public int size(){
        return N;
    }

    private void resize(int capacity){
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; i++){
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void sink(int k){
        while (2 * k <= N){
            int j = 2 * k;
            if (j < N && !less(j, j + 1)){
                if (less(k, j)){
                    exch(k, j);
                }
                else break;
            }
            else if (j < N && less(j, j + 1)){
                j = j + 1;
                if (less(k, j)){
                    exch(k, j);
                }
                else break;
            }
            else if (j == N)
                if (less(k, j)){
                    exch(k, j);
                }
                else break;
            else break;
            k = j;
        }

    }

    private void swim(int k){
        while (k / 2 >= 1){
            if (less(k / 2, k)){
                exch(k / 2, k);
            }
            else break; //没必要一直循环下去
            k = k / 2;
        }
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    @Override
    public Iterator<Key> iterator() {
        return new PQIterator();
    }

    private class PQIterator<Key> implements Iterator<Key>{
        int index = 1;

        @Override
        public boolean hasNext() {
            return index <= N;
        }

        @Override
        public Key next() {
            return (Key) pq[index++];
        }
    }

    public static void main(String[] args){
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        Integer[] ints = {1, 2, 3, 4, 5};
        MaxPQ<Integer> pq2 = new  MaxPQ<Integer>(ints);

        System.out.println("Current max pq: ");
        for (Integer key : pq){
            System.out.print(key + " ");
        }
        System.out.println("");

        System.out.println("Current max pq2: ");
        for (Integer key : pq2){
            System.out.print(key + " ");
        }
        System.out.println("");
        System.out.println("Size of pq2: " + pq2.size());
        System.out.println("");
        System.out.println("");

        System.out.println("Enter any integer to insert number: ");
        while(!StdIn.isEmpty()){
            String command = StdIn.readString();
            if (command.equals("delete")){
                System.out.println("Delete max: " + pq.delMax());
                System.out.print("Current max pq: ");
                for (Integer key : pq){
                    System.out.print(key + " ");
                }
                System.out.println("");
                System.out.println("Size of pq: " + pq.size());
                System.out.println("");
            }
            else if (command.equals("max")){
                System.out.println("Max: " + pq.max());
                System.out.print("Current max pq: ");
                for (Integer key : pq){
                    System.out.print(key + " ");
                }
                System.out.println("");
                System.out.println("Size of pq: " + pq.size());
                System.out.println("");
            }
            else {
                System.out.println("insert mode: ");
                while (!StdIn.isEmpty()) {
                    Integer num = StdIn.readInt();
                    if (num == -1) break;
                    System.out.println("Entering " + num + "......");
                    pq.insert(num);
                    System.out.print("Current max pq: ");
                    for (Integer key : pq) {
                        System.out.print(key + " ");
                    }
                    System.out.println("");
                    System.out.println("Size of pq: " + pq.size());
                    System.out.println("");
                }
            }
        }
    }
}
