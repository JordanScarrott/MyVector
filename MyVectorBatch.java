
import java.util.concurrent.ThreadLocalRandom;

/**
 * A batch of 2D vectors implemented using Structure of Arrays (SoA) layout.
 * This provides superior performance for bulk operations due to CPU cache locality
 * and effective SIMD utilization by the JIT compiler.
 */
public class MyVectorBatch {
    public final float[] x;
    public final float[] y;
    public final int size;

    /**
     * Creates a new batch of vectors initialized to zero.
     * @param size the number of vectors in the batch
     */
    public MyVectorBatch(int size) {
        this.size = size;
        this.x = new float[size];
        this.y = new float[size];
    }

    /**
     * Sets the components of the vector at the specified index.
     * @param index the index of the vector to set
     * @param x the x component
     * @param y the y component
     */
    public void set(int index, float x, float y) {
        this.x[index] = x;
        this.y[index] = y;
    }

    /**
     * Gets the vector at the specified index into a target MyVector.
     * @param index the index to retrieve
     * @param target the target MyVector to store the result
     * @return the target MyVector
     */
    public MyVector get(int index, MyVector target) {
        if (target == null) target = new MyVector();
        target.x = this.x[index];
        target.y = this.y[index];
        return target;
    }

    /**
     * Adds another batch of vectors to this batch in-place.
     * @param other the other batch to add
     */
    public void add(MyVectorBatch other) {
        if (other.size != size) throw new IllegalArgumentException("Batch sizes must match");
        for (int i = 0; i < size; i++) {
            this.x[i] += other.x[i];
            this.y[i] += other.y[i];
        }
    }

    /**
     * Subtracts another batch of vectors from this batch in-place.
     * @param other the other batch to subtract
     */
    public void sub(MyVectorBatch other) {
        if (other.size != size) throw new IllegalArgumentException("Batch sizes must match");
        for (int i = 0; i < size; i++) {
            this.x[i] -= other.x[i];
            this.y[i] -= other.y[i];
        }
    }

    /**
     * Multiplies all vectors in this batch by a scalar in-place.
     * @param m the scalar
     */
    public void mult(float m) {
        for (int i = 0; i < size; i++) {
            this.x[i] *= m;
            this.y[i] *= m;
        }
    }

    /**
     * Divides all vectors in this batch by a scalar in-place.
     * @param m the scalar
     */
    public void div(float m) {
        for (int i = 0; i < size; i++) {
            this.x[i] /= m;
            this.y[i] /= m;
        }
    }

    /**
     * Rotates all vectors in this batch by an angle in-place.
     * @param angle the angle in radians
     */
    public void rotate(float angle) {
        float c = (float) Math.cos(angle);
        float s = (float) Math.sin(angle);
        for (int i = 0; i < size; i++) {
            float oldX = this.x[i];
            float oldY = this.y[i];
            this.x[i] = oldX * c - oldY * s;
            this.y[i] = oldX * s + oldY * c;
        }
    }

    /**
     * Computes the magnitude squared of all vectors.
     * @param result array to store results (must be same size)
     */
    public void magSq(float[] result) {
        if (result.length != size) throw new IllegalArgumentException("Result array size mismatch");
        for (int i = 0; i < size; i++) {
            result[i] = x[i] * x[i] + y[i] * y[i];
        }
    }

    /**
     * Computes the dot product with another batch.
     * @param other the other batch
     * @param result array to store results
     */
    public void dot(MyVectorBatch other, float[] result) {
        if (other.size != size || result.length != size) throw new IllegalArgumentException("Size mismatch");
        for (int i = 0; i < size; i++) {
            result[i] = this.x[i] * other.x[i] + this.y[i] * other.y[i];
        }
    }

    /**
     * Fills the batch with random vectors (0-1).
     */
    public void randomFill() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            x[i] = rand.nextFloat();
            y[i] = rand.nextFloat();
        }
    }
}
