package Week3;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import Week2.Insertion;


public class QuickSort {
    static int CUT_OFF = 3;

    // partitioning is the core of quick sort, which actually does the sorting.
    private static int partition(Comparable[] arr, int lo, int hi){
        Comparable pivot = arr[lo];
        int i = lo;
        int j = hi + 1;
        while(true) {
            while (less(arr[++i], pivot)) {
                if (i == hi) break;
            }

            while (less(pivot, arr[--j])) {
                if (j == lo) break;
            }

            // note that the pointer cross does not mean to exchange
            // the pivot and the j-th element as soon as i and j meet,
            // the j pointer will continue to move until it stops.
            if (j <= i){
                exch(arr, lo, j);
                break;
            }
            else {
                exch(arr, i, j);
            }
            }
        return j;
    }

    // One tricky stuff is how to treat equal keys.
    // An interesting thing is that when stop on keys equal to pivot, a stackoverflow error would occur...
    // reason unknown...
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) <= 0;
    }

    public static void exch(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void sort(Comparable[] arr, int lo, int hi){
        if (hi - lo + 1 <= CUT_OFF){
            Insertion.sort(arr);
        }
        if (hi <= lo) return;
        int mid = partition(arr, lo, hi);
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
    }

    public static void sort(Comparable[] arr){
        StdRandom.shuffle(arr);
        sort(arr, 0,  arr.length - 1);
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
        QuickSort.sort(arr);
        double time = stopwatch.elapsedTime();

        System.out.print("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("Sorting time: " + time);
//        Integer[] arr = {1, 2, 3, 4, 5};
//        QuickSort.exch(arr, 0 , 1);
//        System.out.println(arr[0]);

    }

}
