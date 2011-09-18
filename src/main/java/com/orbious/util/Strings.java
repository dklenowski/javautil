package com.orbious.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

public class Strings {

  /**
   * Extract a <code>String</code> from a character buffer.
   *
   * @param buffer     <code>Character</code> buffer.
   *        start      Start index in <code>buffer</code> to begin extraction.
   *        end        End index in <code>buffer</code> to stop extraction.
   *
   * @return    A <code>String</code> extracted from <code>buffer</code>.
   */

  public static String cvtCharArrayToString(final char[] buffer, int start, int end) {
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

  public static String cvtIntArrayToString(final int[] buffer, int start, int end) {
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

  public static String cvtVectorToString(Vector<String> words) {
    StringBuilder sb;

    sb = new StringBuilder();
    for ( int i = 0; i < words.size(); i++ ) {
      sb.append(words.get(i));
      if ( i+1 < words.size() ) {
        sb.append(" ");
      }
    }

    return(sb.toString());
  }

  /**
   *
   */
  public static Vector<String> cvtStringToVector(String str) {
    String [] a;
    str = str.replaceFirst("^[\\s]+", "");
    a = str.split("[\\s]+");
    return new Vector<String>(Arrays.asList(a));
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
