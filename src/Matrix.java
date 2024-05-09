import java.io.Serializable;

public class Matrix implements Serializable {
    private double[][] data;

    public Matrix(int size) {
        data = new double[size][size];
    }

    public void setData(int row, int col, double value) {
        data[row][col] = value;
    }

    public double[][] getData() {
        return data;
    }

    public int getSize() {
        return data.length;
    }
}
