package com.orbious.util.config;

public class Exceptions {

  private Exceptions() { }

  public static String asString(Exception ex) {
    StackTraceElement[] ste;
    StringBuilder sb;

    sb = new StringBuilder();
    ste = ex.getStackTrace();

    for ( int i = 0; i < ste.length; i++ ) {
      sb.append(ste[i].toString());
    }

    return sb.toString();
  }
}
