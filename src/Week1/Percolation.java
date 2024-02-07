package Week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int[] grid;
    private int n;
    private int count = 0;

    // creates n-by-n grid, with all sites initially blocked
    // 1 represents open, 0 represents blocked
    // Consider i-th row and j-th column element as the i*j-th element in uf.
    // Extra 2 nodes as virtual nodes.
    public Percolation (int n){
        if(n <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new int[n * n + 2];
        grid[0] = 1;
        grid[n * n + 1] = 1;
        for(int i = 1; i < grid.length - 1; i++){
            if(i >= 1 && i <= n){
                uf.union(0, i);
            }
            else if(i <= (n * n) && i >= (n * n - n + 1)){
                uf.union(n * n + 1, i);
            }
            grid[i] = 0;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(!validate(row, col)){
            throw new IllegalArgumentException();
        }

        if(!isOpen(row, col)){
            grid[getIndex(row, col)] = 1;
            count++;

            // at most 4 calls to union().
            if(row - 1 > 0 && isOpen(row - 1, col)){
                if (uf.find(getIndex(row, col)) != uf.find(getIndex(row - 1, col)))
                    uf.union(getIndex(row - 1, col), getIndex(row, col));
            }
            if(row + 1 <= n && isOpen(row + 1, col)){
                if (uf.find(getIndex(row, col)) != uf.find(getIndex(row + 1, col)))
                    uf.union(getIndex(row + 1, col), getIndex(row, col));
            }
            if(col - 1 > 0 && isOpen(row, col - 1)){
                if (uf.find(getIndex(row, col)) != uf.find(getIndex(row, col - 1)))
                    uf.union(getIndex(row, col - 1), getIndex(row, col));
            }
            if(col+ 1 <= n && isOpen(row, col + 1)){
                if (uf.find(getIndex(row, col)) != uf.find(getIndex(row, col + 1)))
                    uf.union(getIndex(row, col + 1), getIndex(row, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(!validate(row, col)){
            throw new IllegalArgumentException();
        }
        return grid[getIndex(row, col)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(!validate(row, col)){
            throw new IllegalArgumentException();
        }
        return (grid[getIndex(row, col)] == 1 && uf.connected(getIndex(row, col), 0));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        if(n == 1){
            return numberOfOpenSites() == 1;
        }
        else {
            return uf.find(0) == uf.find(n * n + 1);
        }
    }

    private boolean validate(int p, int q){
        return (p > 0 && q >0 && p <= n && q <= n);
    }

    private int getIndex(int row, int col){
        return (row - 1) * n + col;
    }

    // test client (optional)
    public static void main(String[] args){
        System.out.println("Enter n: ");
        int n = StdIn.readInt();

        Percolation p = new Percolation(n);
        System.out.println("Enter 1 to open site, 2 to check whether percolates, 3 to obtain the number of open sites ");
        while(!StdIn.isEmpty()){
            int in = StdIn.readInt();
            switch (in){
                case 1:
                    System.out.println("Please enter row: ");
                    int row = StdIn.readInt();
                    System.out.println("Please enter col: ");
                    int col = StdIn.readInt();
                    if(p.isOpen(row, col)){
                        System.out.println("The node is already open.");
                    }
                    p.open(row, col);
                    System.out.println("Operation completed.");
                    System.out.println("--------------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("Percolates? " + p.percolates());
                    System.out.println("--------------------------------------------------------------------");
                    break;
                case 3:
                    System.out.println("Number of open sites: " + p.numberOfOpenSites());
                    System.out.println("--------------------------------------------------------------------");
                    break;
            }
        }
    }
}