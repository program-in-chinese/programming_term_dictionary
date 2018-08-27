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

public class 文件功用 {
  public static String 取源文件文本(String 文件名) throws Exception {
    return 取源文件文本(new File(文件名));
  }

  public static String 取源文件文本(File 文件) throws Exception {
    StringBuilder 文本 = new StringBuilder();
    for (String 行 : 取行(文件)) {
      文本.append(行 + "\n");
    }
    return 文本.toString();
  }

  public static List<String> 取行(File 文件) throws Exception {
    List<String> 行 = new ArrayList<>();
    Files.lines(文件.toPath()).forEach(new Consumer<String>() {

      @Override
      public void accept(String 文本) {
        行.add(文本);
      }

    });
    return 行;
  }

  public static void 写行入文件(Collection<String> 行, String 文件名) {
    try {
      BufferedWriter 输出 = new BufferedWriter(new FileWriter(文件名));
      for (String 某行 : 行) {
        输出.write(某行 + "\n");
      }
      输出.close();
    } catch (IOException e) {
    }
  }

  public static void 创建路径(String 文件路径) {
    File 路径 = new File(文件路径);
    if (!路径.exists() && !路径.isDirectory()) {
      路径.mkdir();
    }
  }
}
