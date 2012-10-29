package com.orbious.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

public class Bytes {
  
  private Bytes() { }

  public static Object convert(byte[] b, Class<?> clazz)
      throws UnsupportedEncodingException {
    if ( clazz == String.class )
      return bytesToStr(b);
    else if ( clazz == Short.class )
      return new Short(bytesToShort(b));
    else if ( clazz == Integer.class )
      return new Integer(bytesToInt(b));
    else if ( clazz == Long.class )
      return new Long(bytesToLong(b));
    else if ( clazz == Double.class )
      return new Double(bytesToDouble(b));

    return deserialize(b);
  }

  public static byte[] convert(Object obj, Class<?> clazz) {
    if ( clazz == String.class )
      return strToBytes((String)obj);
    else if ( clazz == Short.class )
      return shortToBytes(((Short)obj).shortValue());
    else if ( clazz == Integer.class )
      return intToBytes(((Integer)obj).intValue());
    else if ( clazz == Long.class )
      return longToBytes(((Long)obj).longValue());
    else if ( clazz == Double.class )
      return doubleToBytes(((Double)obj).doubleValue());

    return serialize(obj);
  }

  // assumes 2 byte shorts
  public static byte[] shortToBytes(int i) {
    byte[] array = new byte[2];
    array[0] = (byte)(0xff & (i >> 8));
    array[1] = (byte)(0xff & i);
    return array;
  }

  // assumes 2 byte shorts
  public static short bytesToShort(byte[] b) {
    return
        (short)(((short)(b[0] & 0xff) << 8) |
                ((short)(b[1] & 0xff)));
  }

  // assumes 4 byte ints
  public static byte[] intToBytes(int i) {
    byte[] array = new byte[4];

    array[0] = (byte)(0xff & (i >> 24));
    array[1] = (byte)(0xff & (i >> 16));
    array[2] = (byte)(0xff & (i >> 8));
    array[3] = (byte)(0xff & i);

    return array;
  }

  // assumes 4 bytes ints
  public static int bytesToInt(byte[] b) {
    return
        ((int)(b[0] & 0xff) << 24) |
        ((int)(b[1] & 0xff) << 16) |
        ((int)(b[2] & 0xff) << 8) |
        ((int)(b[3] & 0xff));
  }

  // assumes 8 bytes long
  public static byte[] longToBytes(long l) {
    byte[] array = new byte[8];

    array[0] = (byte)(0xff & (l >> 56));
    array[1] = (byte)(0xff & (l >> 48));
    array[2] = (byte)(0xff & (l >> 40));
    array[3] = (byte)(0xff & (l >> 32));
    array[4] = (byte)(0xff & (l >> 24));
    array[5] = (byte)(0xff & (l >> 16));
    array[6] = (byte)(0xff & (l >> 8));
    array[7] = (byte)(0xff & l);

    return array;
  }

  // assumes 8 bytes long
  public static long bytesToLong(byte[] b) {
    return
      ((long)(b[0] & 0xff) << 56) |
      ((long)(b[1] & 0xff) << 48) |
      ((long)(b[2] & 0xff) << 40) |
      ((long)(b[3] & 0xff) << 32) |
      ((long)(b[4] & 0xff) << 24) |
      ((long)(b[5] & 0xff) << 16) |
      ((long)(b[6] & 0xff) << 8) |
      ((long)(b[7] & 0xff));
  }

  public static byte[] doubleToBytes(double d) {
    return longToBytes(Double.doubleToRawLongBits(d));
  }

  public static double bytesToDouble(byte[] b) {
    return Double.longBitsToDouble(bytesToLong(b));
  }

  public static byte[] strToBytes(String str) {
    try {
      return str.getBytes("UTF-8");
    } catch ( UnsupportedEncodingException uee ) {
      return str.getBytes();
    }
  }

  public static String bytesToStr(byte[] b) {
    try {
      return new String(b, "UTF-8");
    } catch ( UnsupportedEncodingException uee ) {
      return new String(b);
    }
  }

  /**
   * Taken from TokyoCabinet java api Util
   * Serialize an object.
   * @param obj a serializable object.
   * @return a byte array of the serialized object or null if an error occurs.
   */
  public static byte[] serialize(Object obj){
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(baos);
      oos.writeObject(obj);
      oos.flush();
      baos.flush();
      return baos.toByteArray();
    } catch(IOException e){
      return null;
    } finally {
      try {
        if(oos != null) oos.close();
      } catch(IOException e){}
    }
  }
  /**
   * Redintegrate a serialized object.
   * @param serial a byte array of the serialized object.
   * @return the original object or null if an error occurs.
   */
  public static Object deserialize(byte[] serial){
    ByteArrayInputStream bais = new ByteArrayInputStream(serial);
    ObjectInputStream ois = null;
    try {
      ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch(IOException e){
      return null;
    } catch(ClassNotFoundException e){
      return null;
    } finally {
      try {
        if(ois != null) ois.close();
      } catch(IOException e){}
    }
  }
}
