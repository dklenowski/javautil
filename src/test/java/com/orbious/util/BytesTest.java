package com.orbious.util;

import com.orbious.util.Bytes;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BytesTest {

	@Test
	public void type() throws Exception {
		assertThat(Bytes.class, notNullValue());
	}

	@Test
	public void arrays() throws Exception {
	  int[] a = new int[] { 1, 2, 3, 4, 5 };
	  byte[] b = Bytes.convert(a, int[].class);

	  int[] expected = new int[] { 1, 2, 3, 4, 5 };
	  int[] actual = (int[])Bytes.convert(b, int[].class);

	  assertThat(actual, is(equalTo(expected)));
	}

  @Test
  public void shorts() throws Exception {
    short expected, actual;
    byte[] b;

    expected = 0;
    b = Bytes.shortToBytes(expected);
    actual = Bytes.bytesToShort(b);
    assertThat(actual, is(equalTo(expected)));

    expected = 61;
    b = Bytes.shortToBytes(expected);
    actual = Bytes.bytesToShort(b);
    assertThat(actual, is(equalTo(expected)));

    expected = Short.MIN_VALUE;
    b = Bytes.shortToBytes(expected);
    actual = Bytes.bytesToShort(b);
    assertThat(actual, is(equalTo(expected)));

    expected = Short.MAX_VALUE;
    b = Bytes.shortToBytes(expected);
    actual = Bytes.bytesToShort(b);
    assertThat(actual, is(equalTo(expected)));
  }

	@Test
	public void ints() throws Exception {
		int expected, actual;
		byte[] b;

		expected = 0;
		b = Bytes.intToBytes(expected);
		actual = Bytes.bytesToInt(b);
		assertThat(actual, is(equalTo(expected)));

    expected = 61;
    b = Bytes.intToBytes(expected);
    actual = Bytes.bytesToInt(b);
    assertThat(actual, is(equalTo(expected)));

    expected = Integer.MIN_VALUE;
    b = Bytes.intToBytes(expected);
    actual = Bytes.bytesToInt(b);
    assertThat(actual, is(equalTo(expected)));

    expected = Integer.MAX_VALUE;
    b = Bytes.intToBytes(expected);
    actual = Bytes.bytesToInt(b);
    assertThat(actual, is(equalTo(expected)));
	}

  @Test
  public void longs() {
    long actual, expected;
    byte[] b;

    expected = Long.MIN_VALUE;
    b = Bytes.longToBytes(expected);
    actual = Bytes.bytesToLong(b);
    assertThat(actual, is(equalTo(expected)));

    expected = Long.MAX_VALUE;
    b = Bytes.longToBytes(expected);
    actual = Bytes.bytesToLong(b);
    assertThat(actual, is(equalTo(expected)));

    expected = 189L;
    b = Bytes.longToBytes(expected);
    actual = Bytes.bytesToLong(b);
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void doubles() {
    double actual, expected;
    byte[] b;

    expected = Double.MIN_VALUE;
    b = Bytes.doubleToBytes(expected);
    actual = Bytes.bytesToDouble(b);
    assertThat(actual, is(equalTo(expected)));

    expected = Double.MAX_VALUE;
    b = Bytes.doubleToBytes(expected);
    actual = Bytes.bytesToDouble(b);
    assertThat(actual, is(equalTo(expected)));

    expected = 189L;
    b = Bytes.doubleToBytes(expected);
    actual = Bytes.bytesToDouble(b);
    assertThat(actual, is(equalTo(expected)));
  }

  @Test
  public void strings() {
    String actual, expected;
    byte[] b;

    expected = "a string";
    b = Bytes.strToBytes(expected);
    actual = Bytes.bytesToStr(b);
    assertThat(actual, is(equalTo(expected)));
  }
}
