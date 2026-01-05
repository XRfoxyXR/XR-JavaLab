package ru.xrfoxyxr.lab2;

public class Table {
    private final int rows;
    private final int cols;
    private final int[][] data;

    public Table(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public int getValue(int row, int col) {
        return data[row][col];
    }

    public void setValue(int row, int col, int value) {
        data[row][col] = value;
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append(data[r][c]);
                if (c < cols - 1) sb.append(" ");
            }
            if (r < rows - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public double average() {
        long sum = 0;
        for (int[] row : data) {
            for (int v : row) {
                sum += v;
            }
        }
        return (rows * cols == 0) ? 0.0 : (double) sum / (rows * cols);
    }
}

