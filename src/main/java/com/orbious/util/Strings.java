package com.orbious.util;

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
   * @param words    A list of <code>String</code>'s to convert.
   *
   * @return    The <code>Vector</code> with its <code>String</code>
   *            contents appended to a <code>String</code>.
   */

  public static String cvtVector(Vector<String> words) {
    String spacer = " ";
    StringBuilder sb = new StringBuilder();

    for ( int i = 0; i < words.size(); i++ ) {
      if ( (i+1) >= words.size() ) spacer = "";
      sb.append(words.get(i) + spacer);
    }

    return sb.toString();
  }

  /**
   * Convert a <code>ArrayList</code> of <code>String</code>'s to a single
   * <code>String</code> separated by whitespace.
   *
   * @param words    A list of <code>String</code>'s to convert.
   *
   * @return    The <code>ArrayList</code> with its <code>String</code>
   *            contents appended to a <code>String</code>.
   */

  public static String cvtArrayList(ArrayList<String> words) {
    String spacer = " ";
    StringBuilder sb = new StringBuilder();

    for ( int i = 0; i < words.size(); i++ ) {
      if ( (i+1) >= words.size() ) spacer = "";
      sb.append(words.get(i) + spacer);
    }

    return sb.toString();
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
