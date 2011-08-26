package com.orbious.util;

import com.orbious.util.Filename;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
public class FilenameTest {

	@Test
	public void type() throws Exception {
		assertThat(Filename.class, notNullValue());
	}

	@Test
	public void suffix_A$File() throws Exception {
		File f;

		f = new File("/tmp/test.txt");
		assertThat(Filename.suffix(f), is(equalTo("txt")));

    f = new File("test.txt");
    assertThat(Filename.suffix(f), is(equalTo("txt")));

    f = new File("/tmp/testtxt");
    assertThat(Filename.suffix(f), is(equalTo("")));

    f = new File("/tmp/test.t");
    assertThat(Filename.suffix(f), is(equalTo("t")));
	}

	@Test
	public void suffix_A$String() throws Exception {
    assertThat(Filename.suffix("/tmp/test.txt"), is(equalTo("txt")));
    assertThat(Filename.suffix("test.txt"), is(equalTo("txt")));
    assertThat(Filename.suffix("/tmp/testtxt"), is(equalTo("")));
    assertThat(Filename.suffix("/tmp/test.t"), is(equalTo("t")));
	}

	@Test
	public void prefix_A$File() throws Exception {
	  File f;

    f = new File("/tmp/test.txt");
    assertThat(Filename.prefix(f), is(equalTo("test")));

    f = new File("test.txt");
    assertThat(Filename.prefix(f), is(equalTo("test")));

    f = new File("/tmp/testtxt");
    assertThat(Filename.prefix(f), is(equalTo("testtxt")));

    f = new File("/tmp/test.t");
    assertThat(Filename.prefix(f), is(equalTo("test")));
	}

	@Test
	public void prefix_A$String() throws Exception {
    assertThat(Filename.prefix("/tmp/test.txt"), is(equalTo("test")));
    assertThat(Filename.prefix("test.txt"), is(equalTo("test")));
    assertThat(Filename.prefix("/tmp/testtxt"), is(equalTo("testtxt")));
    assertThat(Filename.prefix("/tmp/test.t"), is(equalTo("test")));
	}

}
