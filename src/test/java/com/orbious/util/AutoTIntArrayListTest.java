package com.orbious.util;

import org.junit.Assert;
import org.junit.Test;

public class AutoTIntArrayListTest {

  @Test
  public void add() throws Exception {
    AutoTIntArrayList list = new AutoTIntArrayList();
    list.add(4, 4);
    
    Assert.assertArrayEquals(
        new int[] { -1, -1, -1, -1, 4 },
        list.toArray() );
    
    list.add(5, 5);
    list.add(11, 11);
    
    Assert.assertArrayEquals(
        new int[] { -1, -1, -1, -1, 4, 5, -1, -1, -1, -1, -1, 11 },
        list.toArray() );
    
    list.add(11, 12);

    Assert.assertArrayEquals(
        new int[] { -1, -1, -1, -1, 4, 5, -1, -1, -1, -1, -1, 12 },
        list.toArray() );
    
    list.add(10, 10);
    list.add(11, 11);
   
    Assert.assertArrayEquals(
        new int[] { -1, -1, -1, -1, 4, 5, -1, -1, -1, -1, 10, 11 },
        list.toArray() );
  }
}
