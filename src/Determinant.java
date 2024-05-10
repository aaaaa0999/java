import parcs.*;

public class Determinant implements AM {
    public void run(AMInfo info) {
        try {
            System.out.println("Gaussian elimination started.");
            Matrix matrix = (Matrix) info.parent.readObject();
            System.out.println("Received matrix for processing.");
            double result = gaussianDeterminant(matrix.getData());
            System.out.println("Calculated determinant using Gaussian elimination: " + result);
            info.parent.write(result);
            System.out.println("Result sent back to parent.");
        } catch (Exception e) {
            System.err.println("Error during Gaussian elimination: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private double gaussianDeterminant(double[][] matrix) {
        int size = matrix.length;
        double det = 1;
        for (int i = 0; i < size; i++) {
            int maxRow = i;
            for (int j = i + 1; j < size; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = j;
                }
            }
            if (i != maxRow) {
                double[] temp = matrix[i];
                matrix[i] = matrix[maxRow];
                matrix[maxRow] = temp;
                det *= -1;
            }

            if (matrix[i][i] == 0) {
                return 0;
            }

            det *= matrix[i][i];
            for (int j = i + 1; j < size; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < size; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }
        return det;
    }
}
