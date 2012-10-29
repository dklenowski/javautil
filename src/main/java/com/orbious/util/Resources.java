package com.orbious.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

public class Resources {
  
  private Resources() { } 

  public static InputStream getResourceStream(File file) {
    InputStream in;

    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    if ( loader != null ) {
      in = loader.getResourceAsStream(file.toString());
      if ( in != null )
        return(in);
    }

    loader = Resources.class.getClassLoader();
    if ( loader != null ) {
      in = loader.getResourceAsStream(file.toString());
      if ( in != null )
        return(in);
    }

    return ClassLoader.getSystemResourceAsStream(file.toString());
  }

  public static String getClasspathString(ClassLoader loader) {
    StringBuilder sb = new StringBuilder();
    URL[] urls = ((URLClassLoader)loader).getURLs();

    for(int i=0; i< urls.length; i++)
      sb.append(urls[i].getFile() + "\n");

    return sb.toString();
  }


  public static File getResourceFile(File file) throws IOException {
    InputStream in = getResourceStream(file);
    if ( in == null )
      throw new FileNotFoundException("Failed to find file " + file.toString());

    File f = null;
    try {
      f = File.createTempFile(Filename.prefix(file.getName()), "." +
          Filename.suffix(file.getName()));
    } catch ( IOException ioe ) {
      ioe.printStackTrace();
      return null;
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    BufferedWriter bw = new BufferedWriter(new FileWriter(f));

    String line;
    while ( (line = br.readLine()) != null )
      bw.write(line + "\n");

    br.close();
    bw.close();

    return(f);
  }
}
