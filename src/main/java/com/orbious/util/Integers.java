package com.orbious.util;

public class Integers {
  
  private Integers() { }

  public static int[] cvt(String str) {
    String[] sa = str.trim().split("[\\s]+");
    
    int[] ia = new int[sa.length];
    for ( int i = 0; i < sa.length; i++ ) {
      try {
        ia[i] = Integer.parseInt(sa[i]);
      } catch ( NumberFormatException nfe ) {
        ia[i] = -1;
      }
    }

    return ia;
  }

}
