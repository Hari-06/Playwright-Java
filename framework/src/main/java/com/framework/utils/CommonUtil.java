package com.framework.utils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
  public static String createResultsFolder() {
    String userDir = System.getProperty("user.dir");
    Path testResultsDir = Paths.get(userDir, "Test Results");
    if (!Files.exists(testResultsDir)) {
      try {
        Files.createDirectory(testResultsDir);
      } catch (FileAlreadyExistsException e) {
        //System.out.println("Directory already exists: " + testResultsDir);
      } catch (IOException e) {
        throw new RuntimeException("Unable to create Directory: " + testResultsDir);
      }
    }

    String timestamp = LocalDateTime.now()
      .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss"));
    Path timestampFolder = Paths.get(testResultsDir.toString(), timestamp);

    try {
      if (!Files.exists(timestampFolder)) {
        Files.createDirectory(timestampFolder);
      } else {
        System.out.println("Folder already exists: " + timestampFolder);
      }
      return timestampFolder.toString();
    } catch (IOException e) {
      System.err.println("Failed to create timestamp folder.");
      e.printStackTrace();
      return null;
    }
  }
}
