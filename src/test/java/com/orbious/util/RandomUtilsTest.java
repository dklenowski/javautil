package com.orbious.util;

import junit.framework.Assert;
import org.junit.Test;

public class RandomUtilsTest {
  
  @Test
  public void shuffle() {
    int[] a;
    int equalct;
    
    a = RandomUtils.shuffle(10);
    Assert.assertEquals(a.length, 10);
    equalct = 0;
    for ( int i = 0; i < a.length; i++ ) {
     if ( a[i] == i ) equalct++;
    }
    
    if ( equalct > 5 ) 
      Assert.fail("too close to original array (" + equalct + "): " + Strings.cvtIntArray(a));
    
    a = RandomUtils.shuffle(100);
    Assert.assertEquals(a.length, 100);
    equalct = 0;
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] == i ) equalct++;
    }

    if ( equalct > 5 ) 
      Assert.fail("too close to original array (" + equalct + "): " + Strings.cvtIntArray(a));
  }
  
  @Test
  public void shuffleone() {
    int[] a = RandomUtils.shuffle(1);
    Assert.assertEquals(a[0], 0);
    Assert.assertEquals(a.length, 1);
  }
  
  
}
