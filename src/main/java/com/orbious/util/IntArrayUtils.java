package com.orbious.util;

import java.util.Arrays;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.set.hash.TIntHashSet;

public class IntArrayUtils {

  private IntArrayUtils() { } 
  
  public static int[] cardinality(final int[] a) {
    TIntHashSet hs = new TIntHashSet();
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] == -1 ) continue;
      hs.add(a[i]);
    }

    int b[] = hs.toArray();
    Arrays.sort(b);
    return b;
  }
  
  public static int[] unpad(final int[] a) {
    TIntArrayList al = new TIntArrayList(a.length);
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] != -1 ) al.add(a[i]);
    }

    return al.toArray();
  }
  
  public static boolean allpad(final int[] a) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] != -1 ) return false;
    
    return true;
  }
  
  public static boolean allzeros(final int[] a) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] != 0 ) return false;
    
    return true;
  }
  


  // min is the mininum number of unpaded entries
  // that must exist
  public static boolean minunpad(final int[] a, int min) {
    int ct = 0;
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] != -1 ) {
        ct++;
        if ( ct >= min ) return true;
      }
    }
    
    if ( ct >= min ) return true;
    return false;
  }
  
  public static boolean contains(int[] a, int idx) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] == idx ) return true;
    
    return false;
  }
  
  
  public static boolean contains(int[] a, int[] b) {
    int alen, blen;
    int[] master;
    int masterlen;
    int[] child;
    int childlen;

    alen = a.length-1;
    while ( alen >= 0 && a[alen] == -1 ) {
      alen--;
    }
    alen++;
    
    blen = b.length-1;
    while ( blen >= 0 && b[blen] == -1 ) {
      blen--;
    }
    blen++;
    
    if ( alen < blen ) {
      master = a;
      masterlen = alen;
      child = b;
      childlen = blen;
    } else {
      master = b;
      masterlen = blen;
      child = a;
      childlen = alen;
    }
    
    int i, j;
    boolean matched[] = new boolean[child.length];
    boolean fnd;
    
    i = 0;
    while ( i < masterlen ) {
      j = 0;
      fnd = false;
      while ( j < childlen ) {
        if ( !matched[j] && (master[i] == child[j]) ) {
          matched[j] = fnd = true;
          break;
        }
        
        j++;
      }

      if ( !fnd ) return false;
      i++;
    }
    
    return true;
  }
  
  public static boolean equals(int[] a, int[] b) {
    if ( a == b ) return true;
    if ( a == null || b == null ) return false;
    
    int alen = a.length-1;
    while ( alen >= 0 && a[alen] == -1 ) {
      alen--;
    }
    alen++;
    
    int blen = b.length-1;
    while ( blen >= 0 && b[blen] == -1 ) {
      blen--;
    }
    blen++;
    
    if ( alen != blen ) return false;
    
    for ( int i = 0; i < alen; i++ ) {
      if ( a[i] != b[i] ) return false;
    }
    
    return true;
  }
  
}
