import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * A 2-dimensional, single-precision, floating-point vector with operator methods
 * Optimized for performance and extended capabilities.
 *
 * @author Jordan Scarrott
 */
public class MyVector {

    public float x, y;

    // Constructors
    public MyVector() {
        this(0, 0);
    }

    public MyVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the sum of two <code>MyVectors</code>
     */
    public static MyVector add(MyVector v1, MyVector v2) {
        return new MyVector(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * Adds two vectors and stores the result in a target vector.
     * Avoids allocation if target is provided.
     *
     * @param v1     the first vector
     * @param v2     the second vector
     * @param target the vector to store the result in (if null, a new vector is created)
     * @return the target vector containing the sum
     */
    public static MyVector add(MyVector v1, MyVector v2, MyVector target) {
        if (target == null) {
            return new MyVector(v1.x + v2.x, v1.y + v2.y);
        }
        target.x = v1.x + v2.x;
        target.y = v1.y + v2.y;
        return target;
    }

    /**
     * Returns the difference of two <code>MyVectors</code>
     */
    public static MyVector sub(MyVector v1, MyVector v2) {
        return new MyVector(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * Subtracts two vectors and stores the result in a target vector.
     * Avoids allocation if target is provided.
     *
     * @param v1     the first vector
     * @param v2     the second vector
     * @param target the vector to store the result in (if null, a new vector is created)
     * @return the target vector containing the difference
     */
    public static MyVector sub(MyVector v1, MyVector v2, MyVector target) {
        if (target == null) {
            return new MyVector(v1.x - v2.x, v1.y - v2.y);
        }
        target.x = v1.x - v2.x;
        target.y = v1.y - v2.y;
        return target;
    }

    /**
     * Returns a copy of the specified <code>MyVector</code> as a new <code>MyVector</code>
     *
     * @param v the <code>MyVector</code> to be copied
     * @return a copy of the specified <code>MyVector</code> as a new <code>MyVector</code>
     */
    public static MyVector copy(MyVector v) {
        return new MyVector(v.x, v.y);
    }

    /**
     * Returns the product of a specified <code>MyVector</code> and a specified scalar number
     * as a <code>MyVector</code>
     *
     * @param v the <code>MyVector</code> to be multiplied
     * @param m the scalar to be multiplied with the components of the specified
     *          <code>MyVector</code>
     * @return a new <code>MyVector</code> that is the product of the specified
     * <code>MyVector</code> and the specified scalar
     */
    public static MyVector mult(MyVector v, float m) {
        return new MyVector(v.x * m, v.y * m);
    }

    /**
     * Multiplies a vector by a scalar and stores the result in a target vector.
     * Avoids allocation if target is provided.
     *
     * @param v      the vector to multiply
     * @param m      the scalar
     * @param target the vector to store the result in
     * @return the target vector
     */
    public static MyVector mult(MyVector v, float m, MyVector target) {
        if (target == null) {
            return new MyVector(v.x * m, v.y * m);
        }
        target.x = v.x * m;
        target.y = v.y * m;
        return target;
    }

    /**
     * Returns the quotient of the specified <code>MyVector</code> and the
     * specified scalar number as a <b>new</b> <code>MyVector</code>
     *
     * @param v the <code>MyVector</code> to be divided
     * @param m the scalar by which the specified <code>MyVectors</code> components
     *          will be divided
     * @return a new <code>MyVector</code> that is the quotient of the specified
     * <code>MyVector</code> and the specified scalar
     */
    public static MyVector div(MyVector v, float m) {
        return new MyVector(v.x / m, v.y / m);
    }

    /**
     * Divides a vector by a scalar and stores the result in a target vector.
     * Avoids allocation if target is provided.
     *
     * @param v      the vector to divide
     * @param m      the scalar
     * @param target the vector to store the result in
     * @return the target vector
     */
    public static MyVector div(MyVector v, float m, MyVector target) {
        if (target == null) {
            return new MyVector(v.x / m, v.y / m);
        }
        target.x = v.x / m;
        target.y = v.y / m;
        return target;
    }

    /**
     * Computes the magnitude squared of the the MyVector
     * represented by this instance
     *
     * @return the square magnitude of the specified MyVector
     */
    public float magSq() {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * Sets the components, x and y, of this <code>MyVector</code>
     */
    public void set(MyVector v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Sets the components, x and y, of this <code>MyVector</code>
     *
     * @return the MyVector with the new values
     */
    public MyVector set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Returns a copy of this <code>MyVector</code> as a <b>new</b> <code>MyVector</code>
     *
     * @return a copy of this <code>MyVector</code> as a <b>new</b> <code>MyVector</code>
     */
    public MyVector copy() {
        return copy(this);
    }

    /**
     * Adds the components of the specified <code>MyVector</code> to
     * this <code>MyVector</code> and them returns this <code>MyVector</code>
     *
     * @param vx the X component to be added to this <code>MyVector</code>
     * @param vy the Y component to be added to this <code>MyVector</code>
     * @return this <code>MyVector</code> after the addition
     */
    public MyVector add(float vx, float vy) {
        this.x += vx;
        this.y += vy;
        return this;
    }

    /**
     * Adds the components of the specified <code>MyVector</code> to
     * this <code>MyVector</code> and them returns this <code>MyVector</code>
     *
     * @param v the <code>MyVector</code> to be added to this <code>MyVector</code>
     * @return this <code>MyVector</code> after the addition
     */
    public MyVector add(MyVector v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Subtracts the components of the specified <code>MyVector</code> from
     * this <code>MyVector</code> and them returns this <code>MyVector</code>
     *
     * @param vx the X component to be subtracted from this <code>MyVector</code>
     * @param vy the Y component to be subtracted from this <code>MyVector</code>
     * @return this <code>MyVector</code> after the subtraction
     */
    public MyVector sub(float vx, float vy) {
        this.x -= vx;
        this.y -= vy;
        return this;
    }

    /**
     * Subtracts the components of the specified <code>MyVector</code> from
     * this <code>MyVector</code> and them returns this <code>MyVector</code>
     *
     * @param v the <code>MyVector</code> to be subtracted from this <code>MyVector</code>
     * @return this <code>MyVector</code> after the subtraction
     */
    public MyVector sub(MyVector v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Multiplies this <code>MyVector</code> by the specified scalar, m
     *
     * @param m the scalar number by which this <code>MyVector</code>
     *          will be multiplied
     * @return this <code>MyVector</code> after the mulitplication
     */
    public MyVector mult(float m) {
        this.x *= m;
        this.y *= m;
        return this;
    }

    /**
     * Divides this <code>MyVector</code> by the specified scalar, m
     *
     * @param m the scalar number by which this <code>MyVector</code>
     *          will be divided
     * @return this <code>MyVector</code> after the division
     */
    public MyVector div(float m) {
        this.x /= m;
        this.y /= m;
        return this;
    }

    /**
     * Computes magnitude (length) of the relative magnitude vector represented
     * by this instance.
     *
     * @return magnitude of the vector
     */
    public float magnitude() {
        return (float) sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Normalizes the relative magnitude vector represented by this instance.
     * Returns a vector with the same direction and magnitude equal to 1.
     * If this is a zero vector, a zero vector is returned.
     *
     * @return the normalized {@code MyVector}
     */
    public MyVector normalize() {
        final float mag = magnitude();

        if (mag == 0.0f) {
            return this;
        }

        this.x /= mag;
        this.y /= mag;

        return this;
    }

    /**
     * Sets the magnitude of this vector.
     *
     * @param mag the new magnitude
     * @return this {@code MyVector}
     */
    public MyVector setMag(float mag) {
        this.normalize();
        this.mult(mag);
        return this;
    }

    /**
     * Limits the magnitude of this vector.
     *
     * @param max the maximum magnitude
     * @return this {@code MyVector}
     */
    public MyVector limit(float max) {
        if (magSq() > max * max) {
            this.setMag(max);
        }
        return this;
    }

    /**
     * Computes dot (scalar) product of the vector represented by this instance
     * and the specified vector.
     *
     * @param x the X magnitude of the other vector
     * @param y the Y magnitude of the other vector
     * @return the dot product of the two vectors
     */
    public float dotProduct(float x, float y) {
        return this.x * x + this.y * y;
    }

    /**
     * Computes dot (scalar) product of the myVector represented by this instance
     * and the specified myVector.
     *
     * @param myVector the other myVector
     * @return the dot product of the two vectors
     * @throws NullPointerException if the specified {@code myVector} is null
     */
    public float dotProduct(MyVector myVector) {
        return this.x * myVector.x + this.y * myVector.y;
    }

    /**
     * Computes cross product of the myVector represented by this instance
     * and the specified myVector.
     *
     * @param myVector the other myVector
     * @return the cross product of the two vectors
     * @throws NullPointerException if the specified {@code myVector} is null
     */
    public final float cross(MyVector myVector) {
        return this.x * myVector.y - this.y * myVector.x;
    }

    /**
     * Scales the MyVector represented by this instance
     * Multiplies the x and y components of the MyVector represented by
     * this instance by the specified x and y variables
     *
     * @param x the number by which to multiply the x component of MyVector
     *          represented by this instance
     * @param y the number by which to multiply the y component of MyVector
     *          represented by this instance
     */
    public void scale(float x, float y) {
        this.x *= x;
        this.y *= y;
    }

    /**
     * Returns the square of the distance between two points.
     *
     * @param x1 the X coordinate of the first specified point
     * @param y1 the Y coordinate of the first specified point
     * @param x2 the X coordinate of the second specified point
     * @param y2 the Y coordinate of the second specified point
     * @return the square of the distance between the two
     * sets of specified coordinates.
     */
    public static float distanceSq(float x1, float y1, float x2, float y2) {
        x1 -= x2;
        y1 -= y2;
        return (x1 * x1 + y1 * y1);
    }

    /**
     * Returns the distance between two points.
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the distance between the two vectors
     */
    public static float dist(MyVector v1, MyVector v2) {
        return (float) sqrt(distanceSq(v1.x, v1.y, v2.x, v2.y));
    }

    /**
     * Returns the distance from this vector to another.
     *
     * @param v the other vector
     * @return the distance
     */
    public float dist(MyVector v) {
        return dist(this, v);
    }

    /**
     * Computes the angle (in degrees) between two Vectors
     *
     * @param v1 the first MyVector
     * @param v2 the second MyVector
     * @return the angle between the two vectors measured in degrees
     */
    public static float angle(MyVector v1, MyVector v2) {
        final float delta = (v1.x * v2.x + v1.y * v2.y) / (float) sqrt(
                (v1.x * v1.x + v1.y * v1.y) * (v2.x * v2.x + v2.y * v2.y));

        if (delta > 1.0) {
            return 0.0f;
        }
        if (delta < -1.0) {
            return 180.0f;
        }

        return (float) Math.toDegrees(Math.acos(delta));
    }

    /**
     * Returns the angle between the positive Cartesian X axis and the
     * MyVector represented by this instance in radians
     *
     * @return the bearing of the MyVector represented by this instance in radians
     */
    public double bearing() {
        double ans = Math.acos(this.x / sqrt(this.x * this.x + this.y * this.y));
        // Account for fact that ans is just the shortest angle from x-axis which is always < 180deg
        return this.y < 0 ? ans + Math.PI : ans;
    }

    /**
     * Calculate the angle of rotation for this vector (only 2D) in radians.
     * @return the angle of rotation
     */
    public float heading() {
        return (float) Math.atan2(y, x);
    }

    /**
     * Computes the angle (in degrees) between this MyVector and
     * the specified MyVector.
     *
     * @param vx the X magnitude of the other vector
     * @param vy the Y magnitude of the other vector
     * @return the angle between the two vectors measured in degrees
     */
    public float angle(float vx, float vy) {
        return angle(this, new MyVector(vx, vy));
    }

    /**
     * Computes the angle (in degrees) between this MyVector and the
     * specified MyVector
     *
     * @param myVector the other myVector
     * @return the angle between the two vectors measured in degrees,
     * {@code NaN} if any of the two vectors is a zero myVector
     * @throws NullPointerException if the specified {@code myVector} is null
     */
    public float angle(MyVector myVector) {
        return angle(this, myVector);
    }

    /**
     * Returns myVector rotated by an angle of radAngle radians
     *
     * @param myVector the MyVector to rotate
     * @param radAngle angle in radians
     * @return a rotated MyVector
     */
    public static MyVector rotate(MyVector myVector, float radAngle) {
        double sine = sin(radAngle);
        double cosine = cos(radAngle);

        return new MyVector((float) (myVector.x * cosine - myVector.y * sine)
                , (float) (myVector.x * sine + myVector.y * cosine));
    }

    /**
     * Rotates a MyVector by 90 degrees
     *
     * @param myVector the MyVector to rotate
     * @return the specified MyVector rotated by 90deg
     */
    public static MyVector rotate90(MyVector myVector) {
        return new MyVector(-myVector.y, myVector.x);
    }

    /**
     * Rotates a MyVector by -90 degrees
     *
     * @param myVector the MyVector to rotate
     * @return the specified MyVector rotated by -90deg
     */
    public static MyVector rotateMin90(MyVector myVector) {
        return new MyVector(myVector.y, -myVector.x);
    }

    /**
     * Returns a random MyVector
     *
     * @return a random MyVector of with components ranging from 0 to 1
     */
    public static MyVector randomMyVector() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new MyVector(rand.nextFloat(), rand.nextFloat());
    }

    /**
     * Make a new 2D unit vector from an angle.
     * @param angle the angle in radians
     * @return the new unit vector
     */
    public static MyVector fromAngle(float angle) {
        return new MyVector((float)cos(angle), (float)sin(angle));
    }

    /**
     * Returns a random unit vector.
     * @return a random unit MyVector
     */
    public static MyVector random2D() {
        return fromAngle(ThreadLocalRandom.current().nextFloat() * (float)Math.PI * 2);
    }

    /**
     * Linear interpolate the vector to another vector
     *
     * @param v      the vector to interpolate to
     * @param amount the amount of interpolation; some value between 0.0 (old vector) and 1.0 (new vector). 0.1 is very near the old vector; 0.5 is halfway in between.
     * @return this
     */
    public MyVector lerp(MyVector v, float amount) {
        this.x += (v.x - this.x) * amount;
        this.y += (v.y - this.y) * amount;
        return this;
    }

    /**
     * Linear interpolate between two vectors.
     * @param v1 start vector
     * @param v2 end vector
     * @param amount interpolation factor
     * @return a new MyVector
     */
    public static MyVector lerp(MyVector v1, MyVector v2, float amount) {
        MyVector v = v1.copy();
        v.lerp(v2, amount);
        return v;
    }

    /**
     * Rotate the MyVector represented by this instance.
     * OPTIMIZED: Operates in-place without allocation.
     *
     * @param radAngle angle in radians
     */
    public void rotate(float radAngle) {
        float c = (float) cos(radAngle);
        float s = (float) sin(radAngle);
        float nx = this.x * c - this.y * s;
        float ny = this.x * s + this.y * c;
        this.x = nx;
        this.y = ny;
    }

    /**
     * Rotates the MyVector represented by this instance 90deg.
     * OPTIMIZED: Operates in-place.
     */
    public void rotate90() {
        float temp = this.x;
        this.x = -this.y;
        this.y = temp;
    }

    /**
     * Rotates the MyVector represented by this instance -90deg.
     * OPTIMIZED: Operates in-place.
     */
    public void rotateMin90() {
        float temp = this.y;
        this.y = -this.x;
        this.x = temp;
    }

    /**
     * Returns a <code>String</code> that represents the value
     * of this <code>Vec2d</code>.
     *
     * @return a string representation of this <code>Vec2d</code>.
     */
    @Override
    public String toString() {
        return "MyVector[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyVector)) {
            return false;
        }
        MyVector v = (MyVector) obj;
        return Float.compare(x, v.x) == 0 && Float.compare(y, v.y) == 0;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + Float.floatToIntBits(x);
        result = 31 * result + Float.floatToIntBits(y);
        return result;
    }
}
