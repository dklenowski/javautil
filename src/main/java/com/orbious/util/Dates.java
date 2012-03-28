package com.orbious.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

  private Dates() { }

  public static String nowAsBackupStr() {
    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
    return fmt.format(new Date());
  }
}