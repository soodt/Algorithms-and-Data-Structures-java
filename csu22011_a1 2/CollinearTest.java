package csu22011_a1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Arrays;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 03/10/22 22:33:19
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */

    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear with 3 empty arrays should return zero",     expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast with 3 empty arrays should return zero", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // TODO: write more tests here to cover 100% of the instructions and the branches of Collinear.java
    @Test
    public void test1() {
    	
      int[] test1 = new int[3];
  	  test1[0]=1;
  	  test1[1]=2;
  	  test1[2]=3;
  	  
  	  int[] test2 = new int[3];
  	  test2[0]=-3;
  	  test2[1]=2;
  	  test2[2]=-1;
  	  
  	  int[] test3 = new int[3];
  	  test3[0]=0;
  	  test3[1]=2;
  	  test3[2]=3;
  	  
  	  int expectedResult = 2;
  	  
  	  assertEquals(expectedResult,Collinear.countCollinear(test1,test2,test3));
  	  assertEquals(expectedResult,Collinear.countCollinearFast(test1,test2,test3));
    }
    
    @Test
    public void test2() {
    	
    	  
    	int[] test1 = new int[7];
      	int[] test2 = new int[7];
      	int[] test3 = new int[7];
      	
      	for (int i=0; i<test1.length;i++) {
      		test1[i] = -2+i;
      	}
      	
      	for (int i=0; i<test1.length;i++) {
      		test2[i] = -5+i;
      	}
      	for (int i=0; i<test1.length;i++) {
      		test3[i] = i;
      	}
      	test3[2]=4;
      	test3[4]=2;
      	assertEquals(24,Collinear.countCollinearFast(test2,test1,test3));
    	
    	
    }

}
