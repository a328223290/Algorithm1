package Week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;

public class Solver {
    private Board initial;
    private Stack<Board> stack = new Stack<Board>();
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException("Null input.");
        }
        MinPQ<SearchNode> pq =new MinPQ<SearchNode>();
        this.initial = initial;

        if (isSolvable()){
            SearchNode initialNode = new SearchNode(initial, null, 0);
            pq.insert(initialNode);
            SearchNode min = null;
            while (min == null || !min.board.isGoal()) {
                min = pq.delMin();
//                System.out.println("--------------------------------------");
//                System.out.println("Current min: " + min.board);
//                System.out.println("distance: " + min.distance + "   moves: " + min.moves + "    priority: " + min.priority);
//                System.out.println("Countdown: " + moveCount);
//                System.out.println("Neighbors: ");

                for (Board neighbor : min.board.neighbors()) {
                        if (min.preNode == null || !neighbor.equals(min.preNode.board)) {
                            SearchNode newNode = new SearchNode(neighbor, min, min.moves + 1);
                            pq.insert(newNode);
//                            System.out.println(newNode.board);
//                            System.out.println("distance: " + newNode.distance + "   moves: " + newNode.moves + "    priority: " + newNode.priority);
                        }
                }
            }
            SearchNode goalNode = min;

            // Put the path into the stack to reverse the path order after finding the correct path.
            while (goalNode != null){
                stack.push(goalNode.board);
                goalNode = goalNode.preNode;
            }
            moves = stack.size() - 1;
        }


    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        int moveCount = 0;
        Board twin = initial.twin();
        MinPQ<SearchNode> pq1 = new MinPQ<>();
        MinPQ<SearchNode> pq2 = new MinPQ<>();
        SearchNode initialNode = new SearchNode(initial, null, 0);
        SearchNode twinNode = new SearchNode(twin, null, 0);
        pq1.insert(initialNode);
        pq2.insert(twinNode);
        SearchNode min1 = null;
        SearchNode min2 = null;

        while((min1 == null && min2 == null) || (!min1.board.isGoal() && !min2.board.isGoal())){
            min1 = pq1.delMin();
            min2 = pq2.delMin();
//            System.out.println("Detecting round " + moveCount);

            for (Board neighbor : min1.board.neighbors()) {
//                    System.out.println("pq1 neighbor: " + min1.board.toString());
                    if (min1.preNode == null || !neighbor.equals(min1.preNode.board)) {
                        SearchNode newNode = new SearchNode(neighbor, min1, min1.moves + 1);
                        pq1.insert(newNode);
                    }
            }
            for (Board neighbor : min2.board.neighbors()) {
//                    System.out.println("pq2 neighbor: " + min2.board.toString());
                    if (min2.preNode == null || !neighbor.equals(min2.preNode.board)) {
                        SearchNode newNode = new SearchNode(neighbor, min2, min2.moves + 1);
                        pq2.insert(newNode);
                    }
            }

        }
        if (min1.board.isGoal()) return true;
        else return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if (!isSolvable()) return -1;
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if (!isSolvable()) return null;
        else {
            return stack;
        }
    }


    private class SearchNode implements Comparable<SearchNode>{
        int distance;
        int priority;
        int moves;
        Board board;
        SearchNode preNode;

        public SearchNode(Board board,SearchNode preNode, int moves){
            this.distance = board.manhattan();
            this.board = board;
            this.moves = moves;
            this.preNode = preNode;
            this.priority = this.distance + this.moves;
        }

        @Override
        public int compareTo(SearchNode o) {
            if (this.priority < o.priority) return -1;
            else if (this.priority > o.priority) return 1;
            else {
                if (this.distance < o.distance) return -1;
                else if (this.distance < o.distance) return 1;
                else return 0;
            }
        }
    }

    // test client (see below)
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
