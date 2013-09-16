package com.orbious.util;

import java.util.ArrayList;

public class StringArrayUtils {
  
  private StringArrayUtils() { }
  
  public static String[] unpad(final String[] a) {
    ArrayList<String> al = new ArrayList<String>();
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] != null ) al.add(a[i]);
    }

    return al.toArray(new String[al.size()]);
  }
  
  public static boolean allpad(final String[] a) {
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] != null ) return false;
    
    return true;
  }
  
  public static int count(String[] a) {
    int ct = 0;
    for ( int i = 0; i < a.length; i++ ) 
      if ( a[i] != null ) ct++;
    
    return ct;
  }

}
