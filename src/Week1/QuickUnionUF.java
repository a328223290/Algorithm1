package Week1;

import edu.princeton.cs.algs4.StdIn;

/**
 * Class for quick-union of union-find problem.
 * O(n) for both connected and union operation (root operation considered).
 */
public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int root(int p) {
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }

    public void union(int p, int q){
        if(!connected(p, q)){
            id[root(p)] = root(q);
        }
    }

    public void traverse(){
        System.out.print("index: ");
        for(int i = 0; i < id.length; i++){
            System.out.print(i);
            System.out.print("    ");
        }

        System.out.println("");

        System.out.print("id: ");
        for(int i = 0; i < id.length; i++){
            System.out.print(id[i]);
            System.out.print("    ");
        }

        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("Enter N:");
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        System.out.println("1 for connected operation, 2 for union operation, 3 for traverse:");

        while(!StdIn.isEmpty()){
            int command = StdIn.readInt();
            if (command == 1){
                System.out.println("Enter p and q:");
                int p = StdIn.readInt();
                int q = StdIn.readInt();
                if(uf.connected(p, q)) {
                    System.out.println(p + " and " + q + " is connected.");
                }
                else System.out.println(p + " and " + q + " is not connected.");
                System.out.println("-------------------------------------------");
            }

            else if (command == 2){
                System.out.println("Enter p and q:");
                int p = StdIn.readInt();
                int q = StdIn.readInt();
                if(!uf.connected(p, q)){
                    uf.union(p, q);
                    System.out.println("Union completed.");
                }
                else System.out.println("Already connected.");
                System.out.println("-------------------------------------------");
            }

            else if (command == 3){
                uf.traverse();
                System.out.println("-------------------------------------------");
            }

//            else if (command == 4){
//                System.out.println("Enter p");
//                int p = StdIn.readInt();
//                uf.root(p);
//                System.out.println("-------------------------------------------");
//            }
        }

    }

}
