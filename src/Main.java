import java.io.File;
import java.util.Scanner;
import parcs.*;

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
