package Week3;

import Week2.Selection;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class MergeSort{

    /**
     * Function for merging two sorted arrays, the sorting process is done in this function.
     * O(N) = NlogN
     */
    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi){
        // Make sure the two halves are already sorted.
        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid + 1, hi);

        // copy elements of arr to aux, aux is an auxiliary array for merging two halves of arr.
        for (int i = lo; i <= hi; i++){
            aux[i] = arr[i];
        }

        // merge the two halves
        int i = lo, j = mid + 1;
        for(int index = lo; index <= hi; index++){
            if (i <= mid && j <= hi) {
                if (aux[i].compareTo(aux[j]) <= 0) arr[index] = aux[i++];
                else arr[index] = aux[j++];
            }
            else {
                if (i > mid) arr[index] = aux[j++];
                else if (j > hi) arr[index] = aux[i++];
            }
        }

    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi){
        if (hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void sort(Comparable[] arr){
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    public static boolean isSorted(Comparable[] arr, int lo, int hi){
        for(int i = lo; i < hi; i++){
            if (arr[i].compareTo(arr[i + 1]) > 0) return false;
        }
        return true;
    }

    public static void main(String[] args){
        int n = StdIn.readInt();
        Integer[] arr = new Integer[n];

        System.out.print("Original array: ");
        for(int i = 0; i < arr.length; i++){
            arr[i] = StdRandom.uniformInt(n * 10);
            System.out.print(arr[i] + " ");
        }
        System.out.println("");

        Stopwatch stopwatch = new Stopwatch();
        MergeSort.sort(arr);
        double time = stopwatch.elapsedTime();

        System.out.print("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("Sorting time: " + time);
    }


}
