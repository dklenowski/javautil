package com.orbious.util.matrix;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ShortPackMatrixTest {

	@Test
	public void getset_Valid() throws Exception {
	  ShortPackMatrix matrix = new ShortPackMatrix();

	  matrix.set(0, 0, (short)1);
	  matrix.set(0, 1, (short)1);
	  matrix.set(1, 0, (short)9);
	  matrix.set(1, 1, (short)2);

	  assertThat(matrix.get(0, 0), is(equalTo((short)1)));
    assertThat(matrix.get(0, 1), is(equalTo((short)1)));
    assertThat(matrix.get(1, 0), is(equalTo((short)9)));
    assertThat(matrix.get(1, 1), is(equalTo((short)2)));

    assertThat(matrix.get(2, 2), is(equalTo((short)-1)));
	}

	@Test
	public void getset_Invalid() throws Exception {
	  ShortPackMatrix matrix = new ShortPackMatrix();

	  assertThat(matrix.get(0, 0), is(equalTo((short)-1)));
	  assertThat(matrix.get(0, 1), is(equalTo((short)-1)));
	  assertThat(matrix.get(1, 0), is(equalTo((short)-1)));
	  assertThat(matrix.get(1, 1), is(equalTo((short)-1)));
	  assertThat(matrix.get(2, 2), is(equalTo((short)-1)));
	}
}
