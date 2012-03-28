package com.orbious.util;

import java.util.Arrays;
import java.util.Vector;
import com.orbious.util.Strings;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class StringsTest {

	@Test
	public void type() throws Exception {
		assertThat(Strings.class, notNullValue());
	}

	@Test
	public void cvtString_A$() {
	  String str;
	  Vector<String> expected;
	  Vector<String> actual;

	  str = "a test string ,   there should be no more than   a single space between words";
	  expected = new Vector<String>(
	      Arrays.asList("a", "test", "string", ",", "there", "should", "be",
	          "no", "more", "than", "a", "single", "space", "between", "words") );
	  actual = Strings.cvtString(str);
	  assertThat(actual, is(equalTo(expected)));

	  str = "   another   test string 12.4 , 4 .";
	  expected = new Vector<String>(
	      Arrays.asList("another", "test", "string", "12.4", ",", "4", ".") );
    actual = Strings.cvtString(str);
    assertThat(actual, is(equalTo(expected)));

    str = "   a   final  test      string    ";
    expected = new Vector<String>(
        Arrays.asList("a", "final", "test", "string") );
    actual = Strings.cvtString(str);
    assertThat(actual, is(equalTo(expected)));
	}

	@Test
	public void cvtIntArrray_A$() {
	  int[] a;
	  String expected;
	  String actual;

	  a = new int[] { 1, 2, 3, 4, 5 };

	  expected = "1 2 3 4 5";
	  actual = Strings.cvtIntArray(a, 0, a.length);
	  assertThat(actual, is(equalTo(expected)));

	  actual = Strings.cvtIntArray(a, -1, 10);
	  assertThat(actual, is(equalTo(expected)));

    expected = "1 2 3";
    actual = Strings.cvtIntArray(a, 0, 3);
    assertThat(actual, is(equalTo(expected)));
	}

  @Test
  public void cvtStringArray_A$() {
    String[] a = new String[] { "one", "two", "three" };
    String expected = "one two three";
    String actual = Strings.cvtStringArray(a);
    assertThat(actual, is(equalTo(expected)));
  }
}
