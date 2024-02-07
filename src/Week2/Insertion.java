package Week2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Class for insertion sort.
 * O(n^2) for the worst case, but O(n) for partially sorted array.
 */
public class Insertion {

    public static void sort(Comparable[] items){
        for(int i = 0; i < items.length; i++){
            for(int j = i; j > 0; j--){
                if(less(items[j], items[j - 1])) swap(j, j - 1, items);
                else break;
            }
        }
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) == -1;
    }

    private static void swap(int i, int j, Comparable[] items){
        Comparable temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public static void main(String args[]){
        int n = StdIn.readInt();
        Integer[] arr = new Integer[n];

        System.out.print("Original array: ");
        for(int i = 0; i < arr.length; i++){
            arr[i] = StdRandom.uniformInt(n * 10);
            System.out.print(arr[i] + " ");
        }
        System.out.println("");

        Stopwatch stopwatch = new Stopwatch();
        Insertion.sort(arr);
        double time = stopwatch.elapsedTime();

        System.out.print("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("Sorting time: " + time);
    }

}
