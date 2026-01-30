
public class MyVectorTest {

    public static void main(String[] args) {
        testConstructors();
        testAdd();
        testSub();
        testMult();
        testDiv();
        testRotate();
        testMagnitude();
        testNormalize();
        testDotProduct();
        testAngle();
        testScale();
        testDistance();
        testTargetMethods();
        testNewCapabilities();

        System.out.println("All tests passed!");
    }

    private static void testTargetMethods() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector target = new MyVector();

        MyVector res = MyVector.add(v1, v2, target);
        assertEquals(target, res, "Add target return");
        assertEquals(4, target.x, "Add target x");
        assertEquals(6, target.y, "Add target y");

        MyVector.sub(v1, v2, target);
        assertEquals(-2, target.x, "Sub target x");
        assertEquals(-2, target.y, "Sub target y");

        MyVector.mult(v1, 2, target);
        assertEquals(2, target.x, "Mult target x");
        assertEquals(4, target.y, "Mult target y");

        MyVector.div(v1, 2, target);
        assertEquals(0.5f, target.x, "Div target x");
        assertEquals(1.0f, target.y, "Div target y");
    }

    private static void testNewCapabilities() {
        // Lerp
        MyVector v1 = new MyVector(0, 0);
        MyVector v2 = new MyVector(10, 10);
        v1.lerp(v2, 0.5f);
        assertEquals(5, v1.x, "Lerp x");
        assertEquals(5, v1.y, "Lerp y");

        // Limit
        v1.set(10, 0);
        v1.limit(5);
        assertEquals(5, v1.magnitude(), "Limit mag");
        assertEquals(5, v1.x, "Limit x");

        // SetMag
        v1.set(1, 1);
        v1.setMag(10);
        assertEquals(10, v1.magnitude(), "SetMag");

        // Heading
        v1.set(1, 0);
        assertEquals(0, v1.heading(), "Heading 0");
        v1.set(0, 1);
        assertEquals((float)Math.PI/2, v1.heading(), "Heading PI/2");

        // Dist
        v1.set(0, 0);
        v2.set(3, 4);
        assertEquals(5, v1.dist(v2), "Dist");

        // FromAngle
        MyVector v3 = MyVector.fromAngle(0);
        assertEquals(1, v3.x, "FromAngle x");
        assertEquals(0, v3.y, "FromAngle y");

        // Random2D
        MyVector vRand = MyVector.random2D();
        assertEquals(1, vRand.magnitude(), "Random2D mag");

        // Equals/HashCode
        MyVector eq1 = new MyVector(1.5f, 2.5f);
        MyVector eq2 = new MyVector(1.5f, 2.5f);
        MyVector neq = new MyVector(1.5f, 2.6f);
        if (!eq1.equals(eq2)) throw new RuntimeException("Equals failed");
        if (eq1.equals(neq)) throw new RuntimeException("Not Equals failed");
        if (eq1.hashCode() != eq2.hashCode()) throw new RuntimeException("HashCode failed");
    }

    private static void assertEquals(float expected, float actual, String message) {
        if (Math.abs(expected - actual) > 0.0001f) {
            throw new RuntimeException("Assertion failed: " + message + " Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void assertEquals(MyVector expected, MyVector actual, String message) {
        if (Math.abs(expected.x - actual.x) > 0.0001f || Math.abs(expected.y - actual.y) > 0.0001f) {
            throw new RuntimeException("Assertion failed: " + message + " Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void testConstructors() {
        MyVector v1 = new MyVector();
        assertEquals(0, v1.x, "Default constructor x");
        assertEquals(0, v1.y, "Default constructor y");

        MyVector v2 = new MyVector(1, 2);
        assertEquals(1, v2.x, "Constructor x");
        assertEquals(2, v2.y, "Constructor y");
    }

    private static void testAdd() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);

        // Static add
        MyVector v3 = MyVector.add(v1, v2);
        assertEquals(4, v3.x, "Static add x");
        assertEquals(6, v3.y, "Static add y");

        // Instance add
        v1.add(v2);
        assertEquals(4, v1.x, "Instance add x");
        assertEquals(6, v1.y, "Instance add y");
    }

    private static void testSub() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);

        // Static sub
        MyVector v3 = MyVector.sub(v1, v2);
        assertEquals(-2, v3.x, "Static sub x");
        assertEquals(-2, v3.y, "Static sub y");

        // Instance sub
        v1.sub(v2);
        assertEquals(-2, v1.x, "Instance sub x");
        assertEquals(-2, v1.y, "Instance sub y");
    }

    private static void testMult() {
        MyVector v1 = new MyVector(1, 2);

        // Static mult
        MyVector v2 = MyVector.mult(v1, 2);
        assertEquals(2, v2.x, "Static mult x");
        assertEquals(4, v2.y, "Static mult y");

        // Instance mult
        v1.mult(2);
        assertEquals(2, v1.x, "Instance mult x");
        assertEquals(4, v1.y, "Instance mult y");
    }

    private static void testDiv() {
        MyVector v1 = new MyVector(2, 4);

        // Static div (Wait, current implementation is broken, let's see what happens)
        // MyVector v2 = MyVector.div(v1, 2); // This method does not exist as static

        // Instance div(MyVector, float) - Fixed implementation
        MyVector vFixed = v1.div(new MyVector(10, 10), 2);
        // Should divide the passed vector (10, 10) by 2 -> (5, 5)
        assertEquals(5, vFixed.x, "Fixed instance div x");
        assertEquals(5, vFixed.y, "Fixed instance div y");

        // Instance div(float) - In-place
        v1.div(2);
        assertEquals(1, v1.x, "Instance div x");
        assertEquals(2, v1.y, "Instance div y");
    }

    private static void testRotate() {
        MyVector v1 = new MyVector(1, 0);

        // Static rotate
        MyVector v2 = MyVector.rotate(v1, (float)Math.PI / 2);
        assertEquals(0, v2.x, "Static rotate x");
        assertEquals(1, v2.y, "Static rotate y");

        // Instance rotate
        v1.rotate((float)Math.PI / 2);
        assertEquals(0, v1.x, "Instance rotate x");
        assertEquals(1, v1.y, "Instance rotate y");

        // Rotate 90
        v1.set(1, 0);
        v1.rotate90();
        assertEquals(0, v1.x, "Rotate90 x");
        assertEquals(1, v1.y, "Rotate90 y");
    }

    private static void testMagnitude() {
        MyVector v1 = new MyVector(3, 4);
        assertEquals(5, v1.magnitude(), "Magnitude");
        assertEquals(25, v1.magSq(), "MagSq");
    }

    private static void testNormalize() {
        MyVector v1 = new MyVector(3, 4);
        v1.normalize();
        assertEquals(0.6f, v1.x, "Normalize x");
        assertEquals(0.8f, v1.y, "Normalize y");
        assertEquals(1, v1.magnitude(), "Normalized Magnitude");
    }

    private static void testDotProduct() {
        MyVector v1 = new MyVector(1, 0);
        MyVector v2 = new MyVector(0, 1);
        assertEquals(0, v1.dotProduct(v2), "Dot product orthogonal");

        v2.set(1, 0);
        assertEquals(1, v1.dotProduct(v2), "Dot product parallel");
    }

    private static void testAngle() {
        MyVector v1 = new MyVector(1, 0);
        MyVector v2 = new MyVector(0, 1);
        assertEquals(90, MyVector.angle(v1, v2), "Angle 90");
    }

    private static void testScale() {
        MyVector v1 = new MyVector(1, 2);
        v1.scale(2, 3);
        assertEquals(2, v1.x, "Scale x");
        assertEquals(6, v1.y, "Scale y");
    }

    private static void testDistance() {
        float d2 = MyVector.distanceSq(0, 0, 3, 4);
        assertEquals(25, d2, "DistanceSq");
    }
}
