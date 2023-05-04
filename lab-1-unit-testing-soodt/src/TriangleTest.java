
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest {

  static TriangleType equilateral = TriangleType.EQUILATERAL;
  static TriangleType scalene = TriangleType.SCALENE;
  static TriangleType isosceles = TriangleType.ISOSCELES;
  static TriangleType invalid = TriangleType.INVALID;

  @Test
  public void test1() {
    final TriangleType type = Triangle.classify(1, 2, 3);
    assertEquals(scalene, type);
  }

  @Test
  public void testInvalid1() {
    final TriangleType type = Triangle.classify(1, 2, 4);
    assertEquals(invalid, type);
  }

  @Test
  public void testInvalid2() {
    final TriangleType type = Triangle.classify(1, 4, 2);
    assertEquals(invalid, type);
  }

  @Test
  public void testInvalid3() {
    final TriangleType type = Triangle.classify(4, 1, 2);
    assertEquals(invalid, type);

  }

  @Test
  public void testInvalidNeg1() {
    final TriangleType type = Triangle.classify(-1, 1, 1);
    assertEquals(invalid, type);
  }

  @Test
  public void testInvalidNeg2() {
    final TriangleType type = Triangle.classify(1, -1, 1);
    assertEquals(invalid, type);
  }

  @Test
  public void testInvalidNeg3() {
    final TriangleType type = Triangle.classify(1, 1, -1);
    assertEquals(invalid, type);
  }

  @Test
  public void testEquilateral() {
    final TriangleType type = Triangle.classify(1, 1, 1);
    assertEquals(equilateral, type);
  }

  @Test
  public void testIsoceles1() {
    final TriangleType type = Triangle.classify(2, 2, 3);
    assertEquals(isosceles, type);
  }

  @Test
  public void testIsoceles2() {
    final TriangleType type = Triangle.classify(2, 3, 2);
    assertEquals(isosceles, type);
  }

  @Test
  public void testIsoceles3() {
    final TriangleType type = Triangle.classify(3, 2, 2);
    assertEquals(isosceles, type);
  }

  @Test
  public void testInvalid() {
    final TriangleType type = Triangle.classify(3, 1, 1);
    assertEquals(invalid, type);
  }

}
