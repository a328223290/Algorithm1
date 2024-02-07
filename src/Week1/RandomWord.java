package Week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args){
        String champion = "";
        double count = 0;

        // Redirecting, if straight input from keyboard is wanted just comment this part.
//        try {
//            FileInputStream input = new FileInputStream("animals8.txt");
//            System.setIn(input);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            count++;
//            StdOut.println(1/count);
//            StdOut.println(StdRandom.bernoulli(1/count));
            if (StdRandom.bernoulli(1/count)){
                champion = s;
            }
//            StdOut.println(count);
        }
        StdOut.println(champion);
    }
}
