package com.orbious.util;

import com.orbious.util.Files;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
public class FilesTest {

	@Test
	public void type() throws Exception {
		assertThat(Files.class, notNullValue());
	}

	@Test
	public void suffix_A$File() throws Exception {
		File f;

		f = new File("/tmp/test.txt");
		assertThat(Files.suffix(f), is(equalTo("txt")));

    f = new File("test.txt");
    assertThat(Files.suffix(f), is(equalTo("txt")));

    f = new File("/tmp/testtxt");
    assertThat(Files.suffix(f), is(equalTo("")));

    f = new File("/tmp/test.t");
    assertThat(Files.suffix(f), is(equalTo("t")));
	}

	@Test
	public void suffix_A$String() throws Exception {
    assertThat(Files.suffix("/tmp/test.txt"), is(equalTo("txt")));
    assertThat(Files.suffix("test.txt"), is(equalTo("txt")));
    assertThat(Files.suffix("/tmp/testtxt"), is(equalTo("")));
    assertThat(Files.suffix("/tmp/test.t"), is(equalTo("t")));
	}

	@Test
	public void prefix_A$File() throws Exception {
	  File f;

    f = new File("/tmp/test.txt");
    assertThat(Files.prefix(f), is(equalTo("test")));

    f = new File("test.txt");
    assertThat(Files.prefix(f), is(equalTo("test")));

    f = new File("/tmp/testtxt");
    assertThat(Files.prefix(f), is(equalTo("testtxt")));

    f = new File("/tmp/test.t");
    assertThat(Files.prefix(f), is(equalTo("test")));
	}

	@Test
	public void prefix_A$String() throws Exception {
    assertThat(Files.prefix("/tmp/test.txt"), is(equalTo("test")));
    assertThat(Files.prefix("test.txt"), is(equalTo("test")));
    assertThat(Files.prefix("/tmp/testtxt"), is(equalTo("testtxt")));
    assertThat(Files.prefix("/tmp/test.t"), is(equalTo("test")));
	}

}
