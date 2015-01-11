package com.orbious.util;

import org.junit.Test;
import org.junit.Assert;

public class StatisticsTest {
  
  @Test
  public void sum() throws Exception {
    Statistics stats = new Statistics(new int[] { 1, 2, 3, 4, 5});
    Assert.assertEquals(stats.sum(), 15.0, 0.1);
    
    stats.set(new int[] { 1, 2, 3, 4, 5, 6 });
    Assert.assertEquals(stats.sum(), 21.0, 0.1);
  }

  @Test
  public void mean() throws Exception {
    Statistics stats = new Statistics(new int[] { 1, 2, 3, 4, 5});
    Assert.assertEquals(stats.mean(), 15.0/5, 0.1);
    
    stats.set(new int[] { 1, 2, 3, 4, 5, 6 });
    Assert.assertEquals(stats.mean(), 21.0/6, 0.1);
  }
  
  @Test
  public void stddev() throws Exception {
    Statistics stats = new Statistics(new int[] { 1, 2, 3, 4, 5});
    Assert.assertEquals(stats.stddev(), 1.58, 0.1);
    
    stats.set(new int[] { 1, 2, 3, 4, 5, 6 });
    Assert.assertEquals(stats.stddev(), 1.87, 0.1);
  }
  
  @Test
  public void zscore() throws Exception {
    Statistics stats = new Statistics(new int[] { 1, 2, 3, 4, 15});
    int[] a = stats.removeusingzscore(1.0);
    Assert.assertArrayEquals(new int[] { 1, 2, 3, 4 }, a);
  }
}
