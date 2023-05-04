
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Assert;

public class InsertionSortTest {

  @Test
  public void testEmptyList() {
    final InsertionSort ob = new InsertionSort();
    Assert.assertEquals(ob.sort(new ArrayList<Integer>(0)), new ArrayList<Integer>(0));
  }

  @Test
  public void testSortingSortedList() {
    final InsertionSort ob = new InsertionSort();
    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int i = 1; i < 10; i++) {
      list.add(i);
    }
    Assert.assertEquals(ob.sort(list), list);
  }

  @Test
  public void testSortingReversedList() {
    final InsertionSort ob = new InsertionSort();
    ArrayList<Integer> list1 = new ArrayList<Integer>();
    ArrayList<Integer> list2 = new ArrayList<Integer>();

    for (int i = 1; i < 10; i++) {
      list1.add(i);
    }
    for (int i = 9; i > 0; i--) {
      list2.add(i);
    }
    System.out.println(list2);
    Assert.assertEquals(ob.sort(list2), list1);
  }

}