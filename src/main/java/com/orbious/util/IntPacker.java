package com.orbious.util;

public class IntPacker {

  public static final long mask = -1L >>> 32;

  private IntPacker() { }

  public static long pack(int i, int j) {
    long l;

    l = ((long)i << 32) | ((long)j & mask);
    return l;
  }

  public static int i(long l) {
    int i;

    i = (int)(l >> 32);
    return i;
  }

  public static int j(long l) {
    int i;

    i = (int)(l & mask);
    return i;
  }
}