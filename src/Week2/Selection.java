package Week2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Class for selection sort.
 * O(n^2)
 */
public class Selection{

    public static void sort(Comparable[] items){
        for(int i = 0; i < items.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < items.length; j++){
                if(less(items[j], items[min])) min = j;
            }
            swap(i, min, items);
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
        Selection.sort(arr);
        double time = stopwatch.elapsedTime();

        System.out.print("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("Sorting time: " + time);
    }
}
