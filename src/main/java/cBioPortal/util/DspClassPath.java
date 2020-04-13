package cBioPortal.util;

import java.net.URL;
import java.net.URLClassLoader;

public class DspClassPath {

  public static void main(String args[]) {

    ClassLoader cl = ClassLoader.getSystemClassLoader();

    URL[] urls = ((URLClassLoader) cl).getURLs();

    for (URL url : urls) {

      System.out.println(url.getFile());

    }

    System.out.println("classpath=" + System.getProperty("java.class.path"));

  }

}
