package Week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Class for quick-find of union-find problem.
 * For quick-find, the structure of the union component is flat as, for a certain component, we simply set the root by
 * changing the id of the child to the id of the root. This makes connected operation quite simple, which takes only
 * O(N) complexity for N inputs.
 * But it makes union operation to be extremely costly as when connecting a node x to another node y, we have to make
 * sure the nodes who were connected with node x is also connected to node y, which means all ids of the nodes
 * connected to node x must be reset to that of node y. As a result, the union operation takes O(N^2) complexity with
 * N inputs.
 */
public class QuickFindUF {
    int[] id;

    public QuickFindUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[q] == id[p];
    }


    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++){
            if(id[i] == pid){
                id[i] = qid;
            }
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
        QuickFindUF uf = new QuickFindUF(N);
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
        }

    }
}


