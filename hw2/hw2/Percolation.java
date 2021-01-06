package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF PerUnion;
    private WeightedQuickUnionUF PerUnionFull;
    private int r;
    private boolean[][] twoDArray;
    private int openSites;
    private int virTop;
    private int virBot;

    private boolean checkBounds(int row, int col) {
        if (row >= r || col >= r || row < 0 || col < 0) {
            return false;
        }
        return true;
    }

    private int twoDToOneD(int row, int col) {
        return (row * r) + col;
    }

    private boolean possibleFill(int row, int col) {
        if (checkBounds(row, col)) {
            return twoDArray[row][col];
        }
        return false;
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        PerUnion = new WeightedQuickUnionUF(N * N + 2);
        PerUnionFull = new WeightedQuickUnionUF(N * N + 1);
        r = N;
        twoDArray = new boolean[N][N];
        openSites = 0;
        virTop = N * N;
        virBot = N * N + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!checkBounds(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid arguments");
        }
        twoDArray[row][col] = true;
        openSites += 1;
        int index = twoDToOneD(row, col);

        boolean up = possibleFill(row + 1, col);
        boolean down = possibleFill(row - 1, col);
        boolean right = possibleFill(row, col + 1);
        boolean left = possibleFill(row, col - 1);

        if (row == 0) {
            PerUnion.union(index, virTop);
            PerUnionFull.union(index, virTop);
        } else if (row == r - 1) {
            PerUnion.union(index, virBot);
        }

        if (up) {
            PerUnion.union(index, twoDToOneD(row + 1, col));
            PerUnionFull.union(index, twoDToOneD(row + 1, col));
        }
        if (down) {
            PerUnion.union(index, twoDToOneD(row - 1, col));
            PerUnionFull.union(index, twoDToOneD(row - 1, col));
        }
        if (right) {
            PerUnion.union(index, twoDToOneD(row, col + 1));
            PerUnionFull.union(index, twoDToOneD(row , col + 1));
        }
        if (left) {
            PerUnion.union(index, twoDToOneD(row, col - 1));
            PerUnionFull.union(index, twoDToOneD(row, col - 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!checkBounds(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid arguments");
        }
        return twoDArray[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!checkBounds(row, col)) {
            throw new java.lang.IndexOutOfBoundsException("Invalid arguments");
        }
        int index = twoDToOneD(row, col);
        return PerUnionFull.connected(index, virTop);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return PerUnion.connected(virTop, virBot);
    }

    // use for unit testing (not required, but keep this here for the autograder
    public static void main(String[] args) {
        Percolation p = new Percolation(10);
        p.open(3, 4);
        p.open(2, 4);
        p.open(2, 2);
        p.open(2, 3);
        p.open(0, 2);
        p.open(4, 4);
        p.open(1, 2);
        p.open(4, 0);
        System.out.println(p.isOpen(3, 4));
        System.out.println(p.isOpen(2, 4));
        System.out.println(p.isOpen(2, 2));
        System.out.println(p.openSites);
        System.out.println(p.isFull(0, 2));
        System.out.println(p.isFull(4, 4));
        System.out.println(p.percolates());
        System.out.println(p.isFull(4, 0));
        System.out.println(p.PerUnion.count());
    }
}
