package Week1;
import edu.princeton.cs.algs4.StdIn;

/**
 * Class for weighted union-find.
 * O(lg N) for both connected and union operation.
 * The core idea is to make sure the root of a smaller tree is connected to that of a larger tree to prevent tall tree.
 */
public class WeightedUF {

    private int[] id;
    // For keeping the size of the tree
    private int[] size;

    public WeightedUF(int N){
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < id.length; i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    public int root(int p) {
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }



    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    //Consider size of the tree
    public void union(int p, int q){
        if (!connected(p, q)) {
            if (size[root(p)] <= size[root(q)]) {
                // Must change the size first.
                size[root(q)] = size[root(q)] + size[root(p)];
                id[root(p)] = root(q);
            }
            else{
                size[root(p)] = size[root(q)] + size[root(p)];
                id[root(q)] = root(p);
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

        System.out.print("size: ");
        for(int i = 0; i < size.length; i++){
            System.out.print(size[i]);
            System.out.print("    ");
        }

        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("Enter N:");
        int N = StdIn.readInt();
        WeightedUF uf = new WeightedUF(N);
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



