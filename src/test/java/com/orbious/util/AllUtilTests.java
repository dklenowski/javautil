package com.orbious.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.orbious.util.config.ConfigTest;

@RunWith(Suite.class)
@SuiteClasses({
  ConfigTest.class,
  BytesTest.class,
  CommandTest.class,
  FilenameTest.class,
  StringsTest.class
})

public class AllUtilTests {
  public AllUtilTests() { }
}

