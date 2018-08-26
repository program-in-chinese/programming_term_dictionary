package com.github.program_in_chinese.功用;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class FileUtil {
  public static String getStringFromSourceFile(String fileName) throws Exception {
    return getStringFromSourceFile(new File(fileName));
  }

  public static String getStringFromSourceFile(File file) throws Exception {
    StringBuilder 文本 = new StringBuilder();
    for (String 行 : 取行(file)) {
      文本.append(行 + "\n");
    }
    return 文本.toString();
  }

  public static List<String> 取行(File file) throws Exception {
    List<String> 行 = new ArrayList<>();
    Files.lines(file.toPath()).forEach(new Consumer<String>() {

      @Override
      public void accept(String t) {
        行.add(t);
      }

    });
    return 行;
  }
  public static void writeLinesToFile(Collection<String> lines, String fileName) {
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
      for (String line : lines) {
        out.write(line + "\n");
      }
      out.close();
    } catch (IOException e) {
    }
  }

  public static void mkdir(String path) {
    File outputDir = new File(path);
    if (!outputDir.exists() && !outputDir.isDirectory()) {
      outputDir.mkdir();
    }
  }
}
