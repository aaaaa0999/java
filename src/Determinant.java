import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import parcs.*;

class Matrix implements Serializable {
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

public class Determinant implements AM {
    public void run(AMInfo info) {
        Matrix matrix = (Matrix) info.parent.readObject();
        double result = calculateDeterminant(matrix.getData());
        info.parent.write(result);
    }

    private double calculateDeterminant(double[][] matrix) {
        int size = matrix.length;
        if (size == 1) {
            return matrix[0][0];
        }
        double det = 0;
        for (int i = 0; i < size; i++) {
            double[][] subMatrix = new double[size - 1][size - 1];
            for (int j = 1; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (k < i) {
                        subMatrix[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        subMatrix[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            det += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(subMatrix);
        }
        return det;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("Determinant.jar");
        Matrix matrix = fromFile(curtask.findFile("input"));

        AMInfo info = new AMInfo(curtask, null);
        point p = info.createPoint();
        channel c = p.createChannel();
        p.execute("Determinant");
        c.write(matrix);

        System.out.println("Waiting for result...");
        System.out.println("Result: " + c.readDouble());
        curtask.end();
    }

    public static Matrix fromFile(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        int size = sc.nextInt();
        Matrix matrix = new Matrix(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.setData(i, j, sc.nextDouble());
            }
        }
        return matrix;
    }
}
