package com.orbious.util;

public class IntBytePacker {

  public static int pack(byte i, byte j, byte k, byte l) {
    return 
        ((int)(i & 0xff) << 24) |
        ((int)(j & 0xff) << 16) |
        ((int)(k & 0xff) << 8) |
        ((int)(l & 0xff));
  }
  
  public static int set(int i, int idx, byte b) {
    int shift;
    switch ( idx ) {
    case 0:
      shift = 24;
      break;
    case 1:
      shift = 16;
      break;
    case 2:
      shift = 8;
      break;
    default:
      shift = 0;
      break;
    }
    
    return ((int)b & 0xff) << shift | i;
  }
  
  public static int setnext2(int i, int val) {
    int shift;
    
    if ( (byte)(0xff & (i >> 24)) == 0 ) {
      shift = 24;
    } else if ( (byte)(0xff & (i >> 16)) == 0 ) {
      shift = 16;
    } else if ( (byte)(0xff & (i >> 8)) == 0 ) {
      shift = 8;
    } else if ( (byte)(0xff & i) == 0 ) {
      shift = 0;
    } else {
      throw new ArrayIndexOutOfBoundsException("No space left in " + 
          Integer.toHexString(i) + " adding " + val + "\n");
    }
    
    byte b = (byte)val;
    return ((int)b & 0xff) << shift | i;
  }
  
  public static byte[] unpack(int i) {
    byte[] b = new byte[4];

    b[0] = (byte)(0xff & (i >> 24));
    b[1] = (byte)(0xff & (i >> 16));
    b[2] = (byte)(0xff & (i >> 8));
    b[3] = (byte)(0xff & i);

    return b;
  }
  
  public static int[] unpack2(int i) {
    int[] a = new int[4];

    a[0] = (int)(0xff & (i >> 24));
    a[1] = (int)(0xff & (i >> 16));
    a[2] = (int)(0xff & (i >> 8));
    a[3] = (int)(0xff & i);

    return a;
  }
  
  public static byte get(int i, int idx) {
    int shift;
    switch ( idx ) {
    case 0:
      shift = 24;
      break;
    case 1:
      shift = 16;
      break;
    case 2:
      shift = 8;
      break;
    default:
      shift = 0;
      break;
    }
    
    return (byte)(0xff & (i >> shift));
  }
  
  
}
