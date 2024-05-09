import parcs.*;

public class Determinant implements AM {
    public void run(AMInfo info) {
        try {
            System.out.println("Determinant calculation started.");
            Matrix matrix = (Matrix) info.parent.readObject();
            System.out.println("Received matrix for processing.");
            double result = calculateDeterminant(matrix.getData());
            System.out.println("Calculated determinant: " + result);
            info.parent.write(result);
            System.out.println("Result sent back to parent.");
        } catch (Exception e) {
            System.err.println("Error during determinant calculation: " + e.getMessage());
            e.printStackTrace();
        }
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
