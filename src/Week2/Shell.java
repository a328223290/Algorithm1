package Week2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Class for shell sort with increment sequence 3x + 1.
 * O(n^(3/2)) with increment sequence 3x + 1
 */
public class Shell {

    public static void sort(Comparable[] items){
        int h = 1;
        int N = items.length;
        // This step is to increment h.
        while(h < N / 3) h = 3 * h + 1;

        // Note here used insertion sort
        while(h > 0){
            for(int i = h; i < N; i++){
                for (int j = i; j >= h; j -= h){
                    if(less(items[j], items[j - h])) swap(j, j - h, items);
                    else break;
                }
            }
            h = h / 3;
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
        Shell.sort(arr);
        double time = stopwatch.elapsedTime();

        System.out.print("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("Sorting time: " + time);
    }
}
