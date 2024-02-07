package Week2;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item>{
    private Node first = null;
    private Node last = null;

    public void enqueue(Item item){
        if (isEmpty()){
            last = new Node();
            last.item = item;
            last.next = null;
            first = last;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            oldLast.next = last;
        }
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    private class Node{
        Item item;
        Node next;
    }

    public Iterator<Item> iterator(){
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item>{
        Node current = first;

        public boolean hasNext(){
            return current != null;
        }

        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args){
        LinkedQueue<String> queue = new LinkedQueue<String>();
        System.out.println("Is current stack empty? " + queue.isEmpty());
        System.out.println("Enter any string or '-' for pop: ");

        while(true){
            String str = StdIn.readString();
            if (str.equals("-")){
                if (queue.isEmpty()) throw new NoSuchElementException("The stack is empty and cannot pop anything.");
                else {
                    System.out.println("Popped element: " + queue.dequeue());
                    System.out.println("########################################");
                    System.out.print("Current stack: ");
                    for (String s : queue){
                        System.out.print(s + " ");
                    }
                    System.out.println("");
                    System.out.println("########################################");
                    System.out.println("Is current stack empty? " + queue.isEmpty());
                }
            }
            else {
                queue.enqueue(str);
                System.out.println("########################################");
                System.out.print("Current stack: ");
                for (String s : queue){
                    System.out.print(s + " ");
                }
                System.out.println("");
                System.out.println("########################################");
                System.out.println("Is current stack empty? " + queue.isEmpty());
            }

        }
    }
    }


