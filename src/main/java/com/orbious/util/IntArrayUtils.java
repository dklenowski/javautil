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
  
  public static int[] pad(final int[] a, int len, int padint) { 
    if ( a.length >= len ) 
      return a;
    
    int[] b = new int[len];
    for ( int i = 0; i < b.length; i++ ) {
      if ( i < a.length ) b[i] = a[i];
      else b[i] = padint;
    }
    
    return b;
  }
  
  public static int[] unpad(final int[] a, int padint) {
    TIntArrayList al = new TIntArrayList(a.length);
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] != padint ) al.add(a[i]);
    }

    return al.toArray();
  }
  
  public static boolean allpad(final int[] a, int padint) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] != padint ) return false;
    
    return true;
  }
  
  public static boolean allzeros(final int[] a) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] != 0 ) return false;
    
    return true;
  }
  
  public static int matchcount(final int[] src, final int[] dst) {
    int ct = 0;
    for ( int i = 0; i < src.length; i++ ) {
      for ( int j = 0; j < dst.length; j++ ) {
        if ( src[i] == dst[j] ) {
          ct++;
          break;
        }
      }
    }

    return ct;
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
  
  // counts the number of entries in a that are > cutoffcount
  public static int count(int[] a, int cutoffcount) {
    int ct = 0;
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] > cutoffcount ) ct++;
    
    return ct;
  }
  
  public static boolean contains(int[] a, int idx) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] == idx ) return true;
    
    return false;
  }
  
  //
  // match items in the smaller array to the larger array
  // order not considered
  //
  public static boolean contains(int[] a, int[] b, int min, boolean padded, int padint) {
    int[] ua;
    int[] ub;
    
    if ( !padded ) {
      ua = a;
      ub = b;
    } else {
      ua = unpad(a, padint);
      ub = unpad(b, padint);
    }

    int[] pattern;
    int[] text;
    
    if ( ua.length > ub.length ) {
      pattern = ub;
      text = ua;
    } else {
      pattern = ua;
      text = ub;
    }
    
    if ( min == -1 ) min = pattern.length;
    
    int i, j;
    boolean matched[] = new boolean[pattern.length];
    int ct = 0;
    
    i = 0;
    while ( i < text.length ) {
      j = 0;
      while ( j < pattern.length ) {
        if ( matched[j] ) {
          j++;
          continue;
        } else if ( text[i] == pattern[j] ) {
          matched[j] = true;
          ct++;
          break;
        }
        j++;
      }
      
      if ( ct >= min ) return true;
      i++;
    }
    
    if ( ct >= min ) return true;
    return false;
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
  
  // Returns entries in b that dont exist in a
  //
  public static int[] findmissing(int[] a, int[] b) {
    TIntArrayList al = new TIntArrayList();
    
    boolean fnd;
    for ( int i = 0; i < b.length; i++ ) {
      fnd = false;
      for ( int j = 0; j < a.length; j++ ) {
        if ( b[i] == a[j] ) {
          fnd = true;
          break;
        }
      }
      
      if ( !fnd ) al.add(b[i]);
    }
    

    return al.toArray();
  }
}
