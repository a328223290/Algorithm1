package Week2;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Class for stack using linked list.
 *  Push and pop operation takes constant time.
 *  But it uses extra time and space to deal with the list.
 * @param <Item> Generics
 */
public class LinkedStack<Item> implements Iterable<Item> {

    private Node first = null;

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    private class Node{
        Item item;
        Node next;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListedStackIterator();
    }

    private class ListedStackIterator implements Iterator<Item>{

        Node current = first;

        @Override
        // The condition is not current.next != null. remind this!
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args){
        LinkedStack<String> stack = new LinkedStack<String>();
        System.out.println("Is current stack empty? " + stack.isEmpty());
        System.out.println("Enter any string or '-' for pop: ");

        while(true){
            String str = StdIn.readString();
            if (str.equals("-")){
                if (stack.isEmpty()) throw new NoSuchElementException("The stack is empty and cannot pop anything.");
                else {
                    System.out.println("Popped element: " + stack.pop());
                    System.out.println("########################################");
                    System.out.print("Current stack: ");
                    for (String s : stack){
                        System.out.print(s + " ");
                    }
                    System.out.println("");
                    System.out.println("########################################");
                    System.out.println("Is current stack empty? " + stack.isEmpty());
                }
            }
            else {
                stack.push(str);
                System.out.println("########################################");
                System.out.print("Current stack: ");
                for (String s : stack){
                    System.out.print(s + " ");
                }
                System.out.println("");
                System.out.println("########################################");
                System.out.println("Is current stack empty? " + stack.isEmpty());
            }

        }
    }

}
