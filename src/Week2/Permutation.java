package Week2;

//import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }

//        In in = new In("C:\\Users\\32822\\IdeaProjects\\Algorithm1\\src\\Week2\\assignments\\permutation10.txt");
//        while(!in.isEmpty()){
//            String s = in.readString();
//            queue.enqueue(s);
//        }

        for (int i = 0; i < k; i++){
            StdOut.println(queue.dequeue());
        }

    }
}
