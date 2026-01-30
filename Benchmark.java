
public class Benchmark {
    public static void main(String[] args) {
        int iterations = 10_000_000;
        MyVector v1 = new MyVector(10, 20);
        MyVector v2 = new MyVector(30, 40);
        MyVector target = new MyVector();
        float accum = 0;

        long start, end;

        // Warmup
        for (int i = 0; i < 10000; i++) {
            MyVector.add(v1, v2);
            MyVector.add(v1, v2, target);
            v1.add(v2);
            v1.rotate(0.1f);
        }

        System.out.println("--- Single Vector Benchmarks ---");

        // Test 1: Static Add (Allocation)
        start = System.nanoTime();
        MyVector last = new MyVector();
        for (int i = 0; i < iterations; i++) {
            last = MyVector.add(v1, v2);
        }
        accum += last.x;
        end = System.nanoTime();
        System.out.printf("Static Add (Allocation): %.4f ms%n", (end - start) / 1_000_000.0);

        // Test 2: Static Add (Reuse Target)
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            MyVector.add(v1, v2, target);
        }
        accum += target.x;
        end = System.nanoTime();
        System.out.printf("Static Add (Reuse Target): %.4f ms%n", (end - start) / 1_000_000.0);

        // Test 3: Instance Add (In-place)
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            v1.add(v2);
        }
        accum += v1.x;
        end = System.nanoTime();
        System.out.printf("Instance Add (In-place):   %.4f ms%n", (end - start) / 1_000_000.0);

        // Test 4: Rotate (Optimized In-place)
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            v1.rotate(0.001f);
        }
        accum += v1.x;
        end = System.nanoTime();
        System.out.printf("Rotate (Optimized):      %.4f ms%n", (end - start) / 1_000_000.0);

        System.out.println("\n--- Batch Benchmarks (size=10,000, 1000 iters) ---");

        int batchSize = 10_000;
        int batchIters = 1000;

        // Setup Array of Objects
        MyVector[] vectors = new MyVector[batchSize];
        for(int i=0; i<batchSize; i++) vectors[i] = new MyVector(i, i);

        // Setup Batch (SoA)
        MyVectorBatch batch = new MyVectorBatch(batchSize);
        for(int i=0; i<batchSize; i++) batch.set(i, i, i);

        // Warmup Batch
         for(int i=0; i<100; i++) batch.rotate(0.1f);

        // Test AoO Rotate
        start = System.nanoTime();
        for (int j = 0; j < batchIters; j++) {
            for (int i = 0; i < batchSize; i++) {
                vectors[i].rotate(0.001f);
            }
        }
        accum += vectors[0].x;
        end = System.nanoTime();
        double aooTime = (end - start) / 1_000_000.0;
        System.out.printf("Array of Objects Rotate: %.4f ms%n", aooTime);

        // Test SoA Rotate
        start = System.nanoTime();
        for (int j = 0; j < batchIters; j++) {
            batch.rotate(0.001f);
        }
        accum += batch.x[0];
        end = System.nanoTime();
        double soaTime = (end - start) / 1_000_000.0;
        System.out.printf("MyVectorBatch SoA Rotate: %.4f ms%n", soaTime);
        System.out.printf("Speedup: %.2fx%n", aooTime / soaTime);

        System.out.println("Accum: " + accum);
    }
}
