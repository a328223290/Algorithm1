package Week2;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Class for stack using resizing array.
 *  The size of the array is initialized as 1 and doubled when full, halved when 1/4 full.
 *  The amortized cost is O（1） but linear time in worst case for both push and pop operation.
 * @param <Item> Generics
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] arr = (Item[])new Object[1];
    // N represents the index of the last element.
    private int N = -1;

    public void push(Item item){
        N++;
        if(N == arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[N] = item;
    }

    public Item pop(){
        Item item;
        item = arr[N];
        arr[N--] = null;
        if (N == arr.length / 4 - 1){
            resize(arr.length / 2);
        }
        // Cannot simply use 'return arr[N--]' as we have to take loitering problem into account.
        return item;
    }

    private void resize(int capacity){
        Item[] newArr = (Item[])new Object[capacity];
        for (int i = 0; i < arr.length / 2; i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public boolean isEmpty(){
        return N < 0;
    }

    public int size(){
        return arr.length;
    }

    public int getN(){
        return N;
    }

    public Iterator<Item> iterator(){
        return new ResizingArrayStackIterator();
    }

    private class ResizingArrayStackIterator implements Iterator<Item>{
        private int index = N;

        public boolean hasNext(){
            return index >= 0;
        }

        public Item next(){
            return arr[index--];
        }
    }

    public static void main(String[] args){
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
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
                    System.out.println("Size of the array: " + stack.size());
                    System.out.println("N: " + stack.getN());
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
                System.out.println("Size of the array: " + stack.size());
                System.out.println("N: " + stack.getN());
            }

        }
    }

}
