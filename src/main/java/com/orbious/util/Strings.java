package com.orbious.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

public class Strings {

  public static String cvtCharArray(final char[] buffer) {
    return cvtCharArray(buffer, 0, buffer.length);
  }

  public static String cvtCharArray(final char[] buffer, int start, int end) {
    StringBuilder sb;

    sb = new StringBuilder();
    if ( start < 0 ) {
      start = 0;
    }

    if ( end > buffer.length ) {
      end = buffer.length;
    }

    for ( int i = start; i < end; i++ ) {
      sb.append(buffer[i]);
    }

    return(sb.toString());
  }

  public static String cvtIntArray(final int[] buffer) {
    return cvtIntArray(buffer, 0, buffer.length);
  }

  public static String cvtIntArray(final int[] buffer, int start, int end) {
    StringBuilder sb;
    String spacer;

    sb = new StringBuilder();
    if ( start < 0 ) {
      start = 0;
    }

    if ( end > buffer.length ) {
      end = buffer.length;
    }

    spacer =  " ";
    for ( int i = start; i < end; i++ ) {
      if ( (i+1) >= end ) {
        spacer = "";
      }

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
    StringBuilder sb;
    String spacer;

    sb = new StringBuilder();
    spacer = " ";

    for ( int i = 0; i < words.size(); i++ ) {
      if ( (i+1) >= words.size() ) {
        spacer = "";
      }
      sb.append(words.get(i) + spacer);
    }

    return(sb.toString());
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
    StringBuilder sb;
    String spacer;

    sb = new StringBuilder();
    spacer = " ";

    for ( int i = 0; i < words.size(); i++ ) {
      if ( (i+1) >= words.size() ) {
        spacer = "";
      }
      sb.append(words.get(i) + spacer);
    }

    return(sb.toString());
  }

  /**
   *
   */
  public static Vector<String> cvtString(String str) {
    String [] a;
    str = str.replaceFirst("^[\\s]+", "");
    a = str.split("[\\s]+");
    return new Vector<String>(Arrays.asList(a));
  }

  public static String cvtStringArray(String[] a) {
    StringBuilder sb;
    String spacer;

    sb = new StringBuilder();
    spacer =  " ";

    for ( int i = 0; i < a.length; i++ ) {
      if ( (i+1) >= a.length ) {
        spacer = "";
      }

      sb.append(a[i] + spacer);
    }

    return sb.toString();

  }

  public static String read(File f) throws IOException {
    System.out.println("Reading from " + f);
    BufferedReader br = new BufferedReader(new FileReader(f));

    StringBuilder sb = new StringBuilder();
    String line;
    while ( (line = br.readLine()) != null )
      sb.append(line + "\n");

    return sb.toString();
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
