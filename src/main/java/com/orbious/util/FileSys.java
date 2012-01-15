package com.orbious.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileSys {

  private static final int TEMP_DIR_ATTEMPTS = 10000;
  private static final long FILE_COPY_BUFFER_SIZE = 1024*1024*30;

  /**
   * From package com.google.common.io.Files;
   *
   * @return
   */
  public static File createTempDir() {
    File baseDir = new File(System.getProperty("java.io.tmpdir"));
    String baseName = System.currentTimeMillis() + "-";

    for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
      File tempDir = new File(baseDir, baseName + counter);
      if (tempDir.mkdir()) {
        return tempDir;
      }
    }
    throw new IllegalStateException("Failed to create directory within "
        + TEMP_DIR_ATTEMPTS + " attempts (tried "
        + baseName + "0 to " + baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
  }

  /**
   * From package org.apache.commons.io
   */

  public static void copyFile(File srcFile, File destFile,
      boolean preserveFileDate) throws IOException, FileNotFoundException {
    if (srcFile == null)
      throw new NullPointerException("Source must not be null");
    if (destFile == null)
      throw new NullPointerException("Destination must not be null");
    if (srcFile.exists() == false)
      throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
    if (srcFile.isDirectory())
      throw new IOException("Source '" + srcFile + "' exists but is a directory");
    if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath()))
      throw new IOException("Source '" + srcFile + "' and destination '" + destFile + "' are the same");

    File parentFile = destFile.getParentFile();
    if (parentFile != null) {
      if (!parentFile.mkdirs() && !parentFile.isDirectory())
          throw new IOException("Destination '" + parentFile + "' directory cannot be created");
    }
    if (destFile.exists() && destFile.canWrite() == false)
      throw new IOException("Destination '" + destFile + "' exists but is read-only");

    doCopyFile(srcFile, destFile, preserveFileDate);
  }

  private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
    if (destFile.exists() && destFile.isDirectory())
        throw new IOException("Destination '" + destFile + "' exists but is a directory");
    FileInputStream fis = null;
    FileOutputStream fos = null;
    FileChannel input = null;
    FileChannel output = null;
    try {
      fis = new FileInputStream(srcFile);
      fos = new FileOutputStream(destFile);
      input  = fis.getChannel();
      output = fos.getChannel();
      long size = input.size();
      long pos = 0;
      long count = 0;
      while (pos < size) {
          count = (size - pos) > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : (size - pos);
          pos += output.transferFrom(input, pos, count);
      }
    } finally {
      closeQuietly(output);
      closeQuietly(fos);
      closeQuietly(input);
      closeQuietly(fis);
    }

    if (srcFile.length() != destFile.length()) {
        throw new IOException("Failed to copy full contents from '" +
                srcFile + "' to '" + destFile + "'");
    }

    if ( preserveFileDate )
      destFile.setLastModified(srcFile.lastModified());
  }

  private static void closeQuietly(Closeable closeable) {
    if ( closeable == null ) return;

    try {
      closeable.close();
    } catch ( IOException ignored ) { }
  }





}
