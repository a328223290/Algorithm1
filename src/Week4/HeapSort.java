package Week4;

import Week2.Selection;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class HeapSort {

    public static void sort(Comparable[] items){
        // First construct a MaxPQ
        int N = items.length;
        for (int i = N / 2; i >= 1; i--){
            sink(items, i, N);
        }

        // Repeatedly deleting the max and reconstruct the maxPQ
        while(N > 1){
            exch(items, 1, N);
            sink(items, 1, --N);
        }

    }

    private static void sink(Comparable[] items, int k, int N){
        while(2 * k <= N){
            int j = 2 * k;
            if (j < N && less(items, j, j + 1)){
                j = j + 1;
                if (less(items, k, j)){
                    exch(items, k, j);
                }
                else break;
            }
            else if (j < N && !less(items, j, j + 1)){
                if (less(items, k, j)){
                    exch(items, k, j);
                }
                else break;
            }
            else if (j == N){
                if (less(items, k, j)){
                    exch(items, k, j);
                }
                else break;
            }
            k = j;
        }
    }

    private static void exch(Comparable[] items, int i, int j){
        Comparable temp = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = temp;
    }

    private static boolean less(Comparable[] items, int i, int j){
        return items[i - 1].compareTo(items[j - 1]) < 0;
    }

    public static void main(String args[]){
        int n = StdIn.readInt();
        Integer[] arr = new Integer[n];
//        Integer[] arr = {16, 3, 21};

        System.out.print("Original array: ");
        for(int i = 0; i < arr.length; i++){
            arr[i] = StdRandom.uniformInt(n * 10);
            System.out.print(arr[i] + " ");
        }
        System.out.println("");

        Stopwatch stopwatch = new Stopwatch();
        HeapSort.sort(arr);
        double time = stopwatch.elapsedTime();

        System.out.print("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
        System.out.println("Sorting time: " + time);
    }
}
