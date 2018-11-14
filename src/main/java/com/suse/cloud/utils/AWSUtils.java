package com.suse.cloud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public final class AWSUtils {

  private static final Logger LOGGER = Logger.getLogger(AWSUtils.class);

  private AWSUtils(){
  }

  /**
   * This method logs the text file details.
   * @param input
   * @throws IOException
   */
  public static void displayTextInputStream(final InputStream input) throws IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    String line = null;
    while ((line = reader.readLine()) != null) {
      LOGGER.info(line);
    }
  }
}
