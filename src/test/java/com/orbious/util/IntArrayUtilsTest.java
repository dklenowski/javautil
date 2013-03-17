package com.orbious.util;

import org.junit.Assert;
import org.junit.Test;

public class IntArrayUtilsTest {

  @Test
  public void cardinality() {
    int[] a;
    int[] b;

    a = new int[] { 1, 5, 3, 3, 5, 6 };
    b = IntArrayUtils.cardinality(a);
    Assert.assertArrayEquals(b, new int[] { 1, 3, 5, 6 });

    a = new int[] { 1, 2, 3, 4 };
    b = IntArrayUtils.cardinality(a);
    Assert.assertArrayEquals(b, new int[] { 1, 2, 3, 4 });

    a = new int[] { 1, 2, 2, 3, 4 };
    b = IntArrayUtils.cardinality(a);
    Assert.assertArrayEquals(b, new int[] { 1, 2, 3, 4  });

    a = new int[] { 100, 65, 95, 95, 46, 3, 1 };
    b = IntArrayUtils.cardinality(a);
    Assert.assertArrayEquals(b, new int[] { 1, 3, 46, 65, 95, 100 });
  }
  
  @Test
  public void unpad() {
    int[] a;

    a = IntArrayUtils.unpad(new int[] { -1, 5, 6, 7 });
    Assert.assertArrayEquals(a, new int[] { 5, 6, 7 });
    
    a = IntArrayUtils.unpad(new int[] { 0, 1, 5, -1, 6, 7, -1 });
    Assert.assertArrayEquals(a, new int[] { 0, 1, 5, 6, 7 });
  }
  
  @Test
  public void contains() {
    int[] a;
    
    a = new int[] { 1, 2, 3, 4, 5 };
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 3, 5 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(new int[] { 1, 3, 5 }, a, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 2, 3, 4, 5 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(new int[] { 1, 2, 3, 4, 5  }, a, -1, false));
    Assert.assertFalse(IntArrayUtils.contains(a, new int[] { 1, 9 }, -1, false));
    Assert.assertFalse(IntArrayUtils.contains(new int[] { 1, 9  }, a, -1, false));    
    
    
    a = new int[] { 1, 2, 3, 4, 5 };
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 2, 3, 4, 5 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(new int[] { 1, 2, 3, 4, 5 }, a, -1, false));
    Assert.assertFalse(IntArrayUtils.contains(a, new int[] { 1, 2, 3, 4, 6 }, -1, false));
    Assert.assertFalse(IntArrayUtils.contains(new int[] { 1, 2, 3, 4, 6 }, a, -1, false));  
    
    a = new int[] { 1, 1, 3, 4, 5 };
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 1, 4, 5 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 1, 4 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 1, 4, -1 }, -1, true));
    Assert.assertFalse(IntArrayUtils.contains(a, new int[] { 1, 1, 1, 4, 5 }, -1, false));
    
    a = new int[] { 1, 3, 3, 6, 5 };
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 5 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 3, 5 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 3, 5, -1 }, -1, true));
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 1, 3, 3, 5 }, -1, false));
  }
  
  @Test
  public void contains2() {
    int[] a;
    
    a = new int[] { 5, 3, 2, 9 };
    Assert.assertTrue(IntArrayUtils.contains(a, new int[] { 5, 3, 2, 9, -1 }, -1, false));
    Assert.assertTrue(IntArrayUtils.contains(new int[] { 5, 3, 2, 9, -1 }, a, -1, false));
  }
  
  @Test
  public void contains3() {
    Assert.assertTrue(
        IntArrayUtils.contains(
            new int[] { 8, 9, 3, 4, 5 },
            new int[] { 2, 3, 4, 5}, 3, false));
  }
  
  @Test
  public void allpad() {
    Assert.assertFalse(IntArrayUtils.allpad(new int[] { 1, 2, 3, -1, -1 }));
    Assert.assertTrue(IntArrayUtils.allpad(new int[] { -1, -1, -1, -1 }));
    Assert.assertFalse(IntArrayUtils.allpad(new int[] { -1, -1, -1, 6 }));
  }
  
  @Test
  public void minunpad() {
    Assert.assertTrue(IntArrayUtils.minunpad(new int[] { 1, 2, 3, -1, -1 }, 2));
    Assert.assertFalse(IntArrayUtils.minunpad(new int[] { 1, 2, 3, -1, -1 }, 4));
    Assert.assertFalse(IntArrayUtils.minunpad(new int[] { -1, -1, -1, -1}, 1));
    Assert.assertTrue(IntArrayUtils.minunpad(new int[] { -1, -1, -1, 6 }, 1));
    Assert.assertFalse(IntArrayUtils.minunpad(new int[] { -1, -1, -1, 6 }, 2));
  }
  
}
