package com.orbious.util;

public class RandomUtils {

  private RandomUtils() { }
  
  /**
   * Based on the Fisher-Yates shuffle algorithm.
   * @param size
   * @return
   */
  public static int[] shuffle(int size) {
    int[] a = new int[size];
    for ( int i = 0; i < a.length; i++ ) {
      a[i] = i;
    }
    
    int select, tmp;
    for ( int i = size-1; i > 0; i-- ) {
      select = (int) (Math.random() * (i + 1)); //0 <= select <= i
      
      tmp = a[i];
      a[i] = a[select];
      a[select] = tmp;
    }
    
    return a;
  }
}
