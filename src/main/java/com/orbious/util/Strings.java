package com.orbious.util;

import gnu.trove.set.hash.TIntHashSet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;
import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

public class Strings {

  private Strings() { }
  
  public static String compress(String str) {
    return str.replaceAll("\\s", "");
  }
  
  public static String compress(String[] a) { 
    StringBuilder sb = new StringBuilder();
    
    for ( int i = 0; i < a.length; i++ ) {
      if ( a[i] == null ) continue;
      sb.append( a[i].replaceAll("\\s", "") );
    }
    
    return sb.toString();
  }
  
  public static String cvtArray(Object a) {
    if ( a instanceof int[] )
      return cvtIntArray( (int[]) a);
    else if ( a instanceof String[] ) 
      return cvtStringArray( (String[])a );
    else 
      return a.toString();
  }
  
  public static String cvtCharArray(final char[] buffer) {
    return cvtCharArray(buffer, 0, buffer.length);
  }

  public static String cvtCharArray(final char[] buffer, int start, int end) {
    if ( start < 0 ) start = 0;
    if ( end > buffer.length ) end = buffer.length;

    StringBuilder sb = new StringBuilder();
    for ( int i = start; i < end; i++ )
      sb.append(buffer[i]);

    return sb.toString();
  }

  public static String cvtIntArray(final int[] buffer) {
    return cvtIntArray(buffer, 0, buffer.length);
  }

  public static String cvtIntArray(final int[] buffer, int start, int end) {
  	return cvtIntArray(buffer, " ", start, end);
  }
  
  public static String cvtIntArray(final int[] buffer, String spacer) {
    return cvtIntArray(buffer, spacer, 0, buffer.length);
  }
  
  public static String cvtIntArray(final int[] buffer, String spacer, int start, int end) {
    if ( start < 0 ) start = 0;
    if ( end > buffer.length ) end = buffer.length;

    StringBuilder sb = new StringBuilder();

    for ( int i = start; i < end; i++ ) {
      if ( (i+1) >= end ) spacer = "";
      sb.append(buffer[i] + spacer);
    }

    return sb.toString();
  }
  
  public static String cvtDoubleArray(final double[] buffer) {
    return cvtDoubleArray(buffer, 0, buffer.length);
  }

  public static String cvtDoubleArray(final double[] buffer, int start, int end) {
    if ( start < 0 ) start = 0;
    if ( end > buffer.length ) end = buffer.length;

    String spacer =  " ";
    StringBuilder sb = new StringBuilder();

    for ( int i = start; i < end; i++ ) {
      if ( (i+1) >= end ) spacer = "";
      sb.append(buffer[i] + spacer);
    }

    return sb.toString();
  }


  /**
   * Convert a <code>Vector</code> of <code>String</code>'s to a single
   * <code>String</code> separated by whitespace.
   *
   * @param buffer    A list of <code>String</code>'s to convert.
   *
   * @return    The <code>Vector</code> with its <code>String</code>
   *            contents appended to a <code>String</code>.
   */

  public static String cvtVector(Vector<String> buffer) {
    String spacer = " ";
    StringBuilder sb = new StringBuilder();

    for ( int i = 0; i < buffer.size(); i++ ) {
      if ( (i+1) >= buffer.size() ) spacer = "";
      sb.append(buffer.get(i) + spacer);
    }

    return sb.toString();
  }

  /**
   * Convert a <code>ArrayList</code> of <code>String</code>'s to a single
   * <code>String</code> separated by whitespace.
   *
   * @param buffer    A list of <code>String</code>'s to convert.
   *
   * @return    The <code>ArrayList</code> with its <code>String</code>
   *            contents appended to a <code>String</code>.
   */

  public static String cvtArrayList(ArrayList<String> buffer) {
    String spacer = " ";
    StringBuilder sb = new StringBuilder();

    for ( int i = 0; i < buffer.size(); i++ ) {
      if ( (i+1) >= buffer.size() ) spacer = "";
      sb.append(buffer.get(i) + spacer);
    }

    return sb.toString();
  }

  public static String cvtTIntHashSet(TIntHashSet buffer) {
    int[] a = buffer.toArray();
    return cvtIntArray(a);
  }

  /**
   *
   */
  public static Vector<String> cvtString(String str) {
    str = str.replaceFirst("^[\\s]+", "");
    String[] a = str.split("[\\s]+");
    return new Vector<String>(Arrays.asList(a));
  }

  public static String cvtStringArray(String[] a) {
    String spacer =  " ";
    StringBuilder sb = new StringBuilder();

    for ( int i = 0; i < a.length; i++ ) {
      if ( (i+1) >= a.length ) spacer = "";

      sb.append(a[i] + spacer);
    }

    return sb.toString();
  }

  public static String read(File f) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(f));

    String line;
    StringBuilder sb = new StringBuilder();
    while ( (line = br.readLine()) != null )
      sb.append(line + "\n");
    
    try {
      br.close();
    } catch ( IOException ignored ) { }

    return sb.toString();
  }
  
  public static String[] readAsArray(File f) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(f));

    String line;
    ArrayList<String> al = new ArrayList<String>();
    while ( (line = br.readLine()) != null ) {
      al.add( line );
    }
    
    try {
      br.close();
    } catch ( IOException ignored ) { }

    String[] a = new String[al.size()];
    al.toArray(a);
    return a;
  }

  public static void write(File f, String buffer) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(f));
    bw.write(buffer);
    bw.close();
  }

  /**
   *
   */
  public static String diff(String expected, String actual) {
    diff_match_patch dmp = new diff_match_patch();
    LinkedList<Diff> d = dmp.diff_main(expected, actual);

    String str = "Expected=|" + expected + "|\n" +
                 "Actual  =|" + actual + "|\n" +
                 "Diff    =" + d;
    return(str);
  }
}
