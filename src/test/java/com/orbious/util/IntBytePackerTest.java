package com.orbious.util;
import junit.framework.Assert;

import org.junit.Test;

public class IntBytePackerTest {

  @Test
  public void pack_unpack() throws Exception {
    int in;
    byte[] b;
    int[] is;
    
    in = IntBytePacker.pack((byte)1, (byte)2, (byte)3, (byte)4);
    b =  IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals((i+1), (int)b[i]);

    is = IntBytePacker.unpack2(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals((i+1), is[i]);
    
    in = IntBytePacker.pack(Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE);
    b = IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals(Byte.MAX_VALUE, b[i]);

    is = IntBytePacker.unpack2(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals((int)Byte.MAX_VALUE, is[i]);
    
    in = IntBytePacker.pack(Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE);
    b = IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals(Byte.MIN_VALUE, b[i]);
    
    is = IntBytePacker.unpack2(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals((int)Byte.MIN_VALUE, is[i]);
  }

  @Test
  public void getset() throws Exception {
    int in;
    byte[] b;
    
    in =0;
    for ( int i = 0; i < 4; i++ ) 
      in = IntBytePacker.set(in, i, (byte)(i+1));
    
    b =  IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) {
      Assert.assertEquals((i+1), (int)b[i]);
      Assert.assertEquals((i+1), IntBytePacker.get(in, i));
    }
    
    in = 0;
    for ( int i = 0; i < 4; i++ ) {
      in = IntBytePacker.set(in, i, Byte.MAX_VALUE);
      Assert.assertEquals(Byte.MAX_VALUE, IntBytePacker.get(in, i));
    }
    
    b =  IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals(Byte.MAX_VALUE, (int)b[i]);
    
    in = 0;
    for ( int i = 0; i < 4; i++ ) 
      in = IntBytePacker.set(in, i, Byte.MIN_VALUE);
    
    b =  IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals(Byte.MIN_VALUE, (int)b[i]);
  }
 
  @Test
  public void setnext() throws Exception {
    int in;
    byte[] b;

    in = 0;
    in = IntBytePacker.setnext2(in, 1);
    in = IntBytePacker.setnext2(in, 2);
    in = IntBytePacker.setnext2(in, 3);
    in = IntBytePacker.setnext2(in, 4);
    
    b =  IntBytePacker.unpack(in);
    for ( int i = 0; i < b.length; i++ ) 
      Assert.assertEquals((i+1), (int)b[i]);
  }
  
}
