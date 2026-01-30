
import java.util.Arrays;

public class MyVectorBatchTest {
    public static void main(String[] args) {
        testBatchAdd();
        testBatchRotate();
        testBatchDot();

        System.out.println("All MyVectorBatch tests passed!");
    }

    private static void assertEquals(float expected, float actual, String message) {
        if (Math.abs(expected - actual) > 0.0001f) {
            throw new RuntimeException("Assertion failed: " + message + " Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void testBatchAdd() {
        int size = 100;
        MyVectorBatch b1 = new MyVectorBatch(size);
        MyVectorBatch b2 = new MyVectorBatch(size);

        for (int i = 0; i < size; i++) {
            b1.set(i, i, i);
            b2.set(i, 1, 1);
        }

        b1.add(b2);

        MyVector tmp = new MyVector();
        for (int i = 0; i < size; i++) {
            b1.get(i, tmp);
            assertEquals(i + 1, tmp.x, "Batch Add x at " + i);
            assertEquals(i + 1, tmp.y, "Batch Add y at " + i);
        }
    }

    private static void testBatchRotate() {
        int size = 10;
        MyVectorBatch b1 = new MyVectorBatch(size);
        for (int i = 0; i < size; i++) {
            b1.set(i, 1, 0);
        }

        b1.rotate((float)Math.PI / 2);

        MyVector tmp = new MyVector();
        for (int i = 0; i < size; i++) {
            b1.get(i, tmp);
            assertEquals(0, tmp.x, "Batch Rotate x at " + i);
            assertEquals(1, tmp.y, "Batch Rotate y at " + i);
        }
    }

    private static void testBatchDot() {
        int size = 5;
        MyVectorBatch b1 = new MyVectorBatch(size);
        MyVectorBatch b2 = new MyVectorBatch(size);
        float[] res = new float[size];

        for (int i = 0; i < size; i++) {
            b1.set(i, 1, 0);
            b2.set(i, 0, 1);
        }

        b1.dot(b2, res);

        for (int i = 0; i < size; i++) {
            assertEquals(0, res[i], "Batch Dot orthogonal");
        }
    }
}
