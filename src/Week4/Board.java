package Week4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Board {
    private int n;
    private int[][] tiles;
    private Board[] neighbors;
    private int hammingDist = 0;
    private int manhattanDist = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        // 检验n是否在0-128之间以及tiles是否为方阵？
        n = tiles.length;
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                this.tiles[i][j] = tiles[i][j];
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i == n - 1 && j == n - 1){
                    if (tiles[i][j] != 0){
                        continue;
                    }
                }
                else {
                    if (tiles[i][j] != (i * n + j + 1)) {
                        hammingDist++;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (tiles[i][j] == 0){
                    continue;
                }
                else{
                    int iGoal = tiles[i][j] / n;
                    int jGoal = tiles[i][j] - iGoal * n - 1;
                    if (jGoal < 0){
                        iGoal--;
                        jGoal = n - 1;
                    }
                    manhattanDist = manhattanDist + Math.abs(iGoal - i) + Math.abs(jGoal - j);
                }
            }
        }
    }

    // string representation of this board
    public String toString(){
        String str = "";
        str = str + n;
        for (int i = 0; i < n; i++){
            str = str + "\n ";
            for (int j = 0; j < n; j++){
                str = str + tiles[i][j] + " ";
            }
        }
        return str;
    }

    // board dimension n
    public int dimension(){
        return n;
    }

    // number of tiles out of place
    public int hamming(){
        return hammingDist;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        return manhattanDist;
    }

    // is this board the goal board?
    public boolean isGoal(){
//        return hamming() == 0 && manhattan() == 0;
        return manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if (y == null) return false;
        if (y == this) return true;
        if (y.getClass() != this.getClass()) return false;

        return toString().equals(((Board) y).toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        int iBlock = 0;
        int jBlock = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (tiles[i][j] == 0){
                    iBlock = i;
                    jBlock = j;
                }
            }
        }
        if (iBlock == 0 && jBlock == 0){
            neighbors = new Board[2];
            exch(iBlock, jBlock, iBlock + 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock + 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock + 1);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock, jBlock + 1);
        }
        else if (iBlock == 0 && jBlock == n - 1){
            neighbors = new Board[2];
            exch(iBlock, jBlock, iBlock + 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock + 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock - 1);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock, jBlock - 1);
        }
        else if (iBlock == n - 1 && jBlock == 0){
            neighbors = new Board[2];
            exch(iBlock, jBlock, iBlock - 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock - 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock + 1);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock, jBlock + 1);
        }
        else if (iBlock == n - 1 && jBlock == n - 1){
            neighbors = new Board[2];
            exch(iBlock, jBlock, iBlock - 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock - 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock - 1);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock, jBlock - 1);
        }
        else if (iBlock == 0){
            neighbors = new Board[3];
            exch(iBlock, jBlock, iBlock + 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock + 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock - 1);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock, jBlock - 1);

            exch(iBlock, jBlock, iBlock, jBlock + 1);
            Board neighbor3 = new Board(tiles);
            neighbors[2] = neighbor3;
            exch(iBlock, jBlock, iBlock, jBlock + 1);
        }
        else if (iBlock == n - 1){
            neighbors = new Board[3];
            exch(iBlock, jBlock, iBlock - 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock - 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock - 1);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock, jBlock - 1);

            exch(iBlock, jBlock, iBlock, jBlock + 1);
            Board neighbor3 = new Board(tiles);
            neighbors[2] = neighbor3;
            exch(iBlock, jBlock, iBlock, jBlock + 1);
        }
        else if (jBlock == 0){
            neighbors = new Board[3];
            exch(iBlock, jBlock, iBlock + 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock + 1, jBlock);

            exch(iBlock, jBlock, iBlock - 1, jBlock);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock - 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock + 1);
            Board neighbor3 = new Board(tiles);
            neighbors[2] = neighbor3;
            exch(iBlock, jBlock, iBlock, jBlock + 1);
        }
        else if (jBlock == n - 1){
            neighbors = new Board[3];
            exch(iBlock, jBlock, iBlock + 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock + 1, jBlock);

            exch(iBlock, jBlock, iBlock - 1, jBlock);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock - 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock - 1);
            Board neighbor3 = new Board(tiles);
            neighbors[2] = neighbor3;
            exch(iBlock, jBlock, iBlock, jBlock - 1);
        }
        else{
            neighbors = new Board[4];
            exch(iBlock, jBlock, iBlock + 1, jBlock);
            Board neighbor1 = new Board(tiles);
            neighbors[0] = neighbor1;
            exch(iBlock, jBlock, iBlock + 1, jBlock);

            exch(iBlock, jBlock, iBlock - 1, jBlock);
            Board neighbor2 = new Board(tiles);
            neighbors[1] = neighbor2;
            exch(iBlock, jBlock, iBlock - 1, jBlock);

            exch(iBlock, jBlock, iBlock, jBlock + 1);
            Board neighbor3 = new Board(tiles);
            neighbors[2] = neighbor3;
            exch(iBlock, jBlock, iBlock, jBlock + 1);

            exch(iBlock, jBlock, iBlock, jBlock - 1);
            Board neighbor4 = new Board(tiles);
            neighbors[3] = neighbor4;
            exch(iBlock, jBlock, iBlock, jBlock - 1);
        }
        return new BoardIterable();
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        Board twin;
        int i = 0;
        int j = 0;
        int k = 1;
        int l = 0;
        if (tiles[i][j] == 0){
            i = 1;
            j = 1;
        }
        else if (tiles[k][l] == 0){
            k = 0;
            l = 1;
        }
        exch(i, j, k, l);
        twin = new Board(tiles);
        exch(i, j, k, l);
        return twin;
    }

    private void exch(int i, int j, int k, int l){
        int temp = tiles[i][j];
        tiles[i][j] = tiles[k][l];
        tiles[k][l] = temp;

    }


    private class BoardIterable implements Iterable<Board>{

        @Override
        public Iterator<Board> iterator() {
            return new BoardIterator();
        }
    }

    private class BoardIterator implements Iterator<Board>{
        int i = 0;

        @Override
        public boolean hasNext() {
            return i < neighbors.length;
        }

        @Override
        public Board next() {
            return neighbors[i++];
        }
    }

    // unit testing (not graded)
    public static void main(String[] args){
        int[][] temp = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
//        int[][] temp = {{1, 0}, {2, 3}};
        Board board1 = new Board(temp);
        Board board2 = new Board(temp);
        Board board3 = new Board(new int[][]{{4, 7, 1}, {6, 0, 5},{3, 8, 2}});
        System.out.println("board1: " + board1.toString());
        System.out.println("board3: " + board3.toString());
        System.out.println("is board1 and board2 the same: " + board1.equals(board2));
        System.out.println("is board1 and board3 the same: " + board1.equals(board3));
        System.out.println("hamming distance of board1: " + board1.hamming());
        System.out.println("manhattan distance of board1: " + board1.manhattan());
        System.out.println("twin of board1: " + board1.twin().toString());
        System.out.println("neighbors of board1: ");
        for (Board neighbor : board1.neighbors()){
            System.out.println(neighbor.toString());
        }
        System.out.println("neighbors of board3: ");
        for (Board neighbor : board3.neighbors()){
            System.out.println(neighbor.toString());
        }
    }

}
