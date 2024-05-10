import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import parcs.*;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("Determinant.jar");
        Matrix matrix = fromFile(curtask.findFile("input-11-0"));

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        for (int i = 0; i < matrix.getSize(); i++) {
            point p = new point(curtask, 0);  // Используем конструктор point(task curtask, int parentNumber)
            channel c = p.createChannel();
            p.execute("Determinant");
            Matrix minor = getMinor(matrix, 0, i);
            c.write(minor);
            points.add(p);
            channels.add(c);
        }

        double result = 0;
        for (int i = 0; i < channels.size(); i++) {
            double subDet = channels.get(i).readDouble();
            result += Math.pow(-1, i) * matrix.getData()[0][i] * subDet; // Суммирование результатов с учётом знака
        }

        System.out.println("Result: " + result);
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

    private static Matrix getMinor(Matrix matrix, int excludedRow, int excludedCol) {
        int size = matrix.getSize();
        Matrix minor = new Matrix(size - 1);
        int minorRow = 0;

        for (int i = 0; i < size; i++) {
            if (i == excludedRow) continue;

            int minorCol = 0;
            for (int j = 0; j < size; j++) {
                if (j == excludedCol) continue;

                minor.setData(minorRow, minorCol, matrix.getData()[i][j]);
                minorCol++;
            }
            minorRow++;
        }
        return minor;
    }
}
