//package Week2;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    private Node oldLast;

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        N = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return N == 0;
    }

    // return the number of items on the deque
    public int size(){
        return N;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (isEmpty()){
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            // 这一段非常重要，因为数据结构变成了双向链表，所以旧节点也得修改。
            oldFirst.previous = first;
            first.previous = null;
        }
        N++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (isEmpty()){
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = null;
            first = last;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = oldLast;
            // 这一段非常重要，因为数据结构变成了双向链表，所以旧节点也得修改。
            oldLast.next = last;
        }
        N++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        if (first != null) first.previous = null;
        if (first == last){
            last = first;
        }
        N--;
        //注意这个corner case，由于是双向链表，当仅剩一个元素时，last的previous会还没更新。
        if (N == 1){
            last = first;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        if (last != null) last.next = null;
        if (last == first){
            first = last;
        }
        N--;
        //注意这个corner case，由于是双向链表，当仅剩一个元素时，first的next会还没更新。
        if (N == 1){
            first = last;
        }
        return item;
    }

    private class Node{
        Item item;
        Node next;
        Node previous;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<String> deque = new Deque<String>();
        System.out.println("Is current deque empty? " + deque.isEmpty());
        System.out.println("Enter '1' for addFirst, '2' for addLast, '-' for removeFirst, '--' for removeLast.");

        while (true){
            System.out.println("Please specify a mode: ");
            String s = StdIn.readString();
            if (s.equals("1")){
                System.out.println("AddFirst mode: (Enter 'e' for exit)");
                while (true) {
                    String in = StdIn.readString();
                    if (in.equals("e"))  {
                        System.out.println("Exit curren mode.");
                        break;
                    }
                    else {
                        deque.addFirst(in);
                        System.out.print("Current deque: ");
                        for (String item : deque) {
                            System.out.print(item + " ");
                        }
                        System.out.println("");
                        System.out.println("########################################");
                        System.out.println("Is current deque empty? " + deque.isEmpty());
                        System.out.println("Size:  " + deque.size());
                    }
                }
            }
            else if (s.equals("2")){
                    System.out.println("AddLast mode: (Enter 'e' for exit)");
                while (true) {
                    String in = StdIn.readString();
                    if (in.equals("e")) {
                        System.out.println("Exit curren mode.");
                        break;
                    }
                    else {
                        deque.addLast(in);
                        System.out.print("Current deque: ");
                        for (String item : deque) {
                            System.out.print(item + " ");
                        }
                        System.out.println("");
                        System.out.println("########################################");
                        System.out.println("Is current deque empty? " + deque.isEmpty());
                        System.out.println("Size:  " + deque.size());
                    }
                }
            }
            else if (s.equals("-")){
                System.out.println("RemoveFirst mode");
                System.out.println("Removed element: " + deque.removeFirst());
                System.out.print("Current deque: ");
                for (String item : deque){
                    System.out.print(item + " ");
                }
                System.out.println("");
                System.out.println("########################################");
                System.out.println("Is current deque empty? " + deque.isEmpty());
                System.out.println("Size:  " + deque.size());
            }
            else if (s.equals("--")){
                System.out.println("RemoveLast mode: ");
                System.out.println("Removed element: " + deque.removeLast());
                System.out.print("Current deque: ");
                for (String item : deque){
                    System.out.print(item + " ");
                }
                System.out.println("");
                System.out.println("########################################");
                System.out.println("Is current deque empty? " + deque.isEmpty());
                System.out.println("Size:  " + deque.size());
            }
        }
    }
}
