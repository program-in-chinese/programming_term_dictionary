package com.github.program_in_chinese.工具;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.program_in_chinese.功用.文件功用;

public class 处理命名 {

  private static final String 常量_输出文件路径 = "命名列表";
  private static final String 后缀 = "_java_util.txt";
  private static final List<String> 命名列表文件 = Arrays.asList("参数", "方法", "类");

  public static void main(String[] args) throws Exception {

    Set<String> 单词表 = new HashSet<>();

    for (String 文件名 : 命名列表文件) {
      List<String> 命名 = 文件功用.取行(new File(常量_输出文件路径 + "/" + 文件名 + 后缀));
      for (String 某命名 : 命名) {
        for (String 单词 : 拆分骆驼命名(某命名)) {
          单词表.add(单词.toLowerCase());
        }
      }
    }

    for (String 某单词 : 单词表) {
      System.out.println(某单词);
    }
  }

  /**
   * @param s 输入一个骆驼法的命名, 如 aCamelString
   * @return 拆分开的单词, 即[a, Camel, String]
   */
  public static String[] 拆分骆驼命名(String s) {
    return s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
  }
}
