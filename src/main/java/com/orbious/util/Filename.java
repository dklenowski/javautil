package com.orbious.util;

import java.io.File;

public class Filename {

  private Filename() { }

  public static String suffix(File f) {
    return suffix(f.getName());
  }

  public static String suffix(String str) {
    File f;
    int ct;

    f = new File(str);
    str = f.getName();

    ct = str.lastIndexOf('.');
    if ( ct < 0 ) {
      return "";
    }

    return str.substring(ct+1);
  }

  public static String prefix(File f) {
    return prefix(f.getName());
  }

  public static String prefix(String str) {
    File f;
    int ct;

    f = new File(str);
    str = f.getName();

    ct = str.lastIndexOf('.');
    if ( ct < 0 ) {
      return str;
    }

    return str.substring(0, ct);
  }

}
