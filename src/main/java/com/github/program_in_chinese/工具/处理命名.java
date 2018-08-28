package com.github.program_in_chinese.工具;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.program_in_chinese.功用.文件功用;

public class 处理命名 {

  private static final String 常量_输出文件路径 = "命名列表";
  private static final String 常量_输出后缀 = "";
  private static final String 后缀 = "_java" + 常量_输出后缀 + ".txt";
  private static final List<String> 命名列表文件 = Arrays.asList("参数", "方法", "类");

  public static void main(String[] args) throws Exception {

    Map<String, List<String>> 单词表 = new HashMap<>();

    for (String 文件名 : 命名列表文件) {
      List<String> 命名带来源 = 文件功用.取行(new File(常量_输出文件路径 + "/" + 文件名 + 后缀));
      for (String 某命名带来源 : 命名带来源) {
        String[] 字段 = 某命名带来源.split("#");
        for (String 单词 : 拆分骆驼命名(字段[1])) {
          String 小写单词 = 单词.toLowerCase();
          if (!单词表.containsKey(小写单词)) {
            单词表.put(小写单词, new ArrayList<>());
          }
          单词表.get(小写单词).add(某命名带来源);
        }
      }
    }

    List<String> 所有单词 = new ArrayList<>(单词表.keySet());
    所有单词.sort((o1, o2) -> o1.compareTo(o2));
    List<String> 行 = new ArrayList<>();
    for (String 某单词 : 所有单词) {
      List<String> 来源 = 单词表.get(某单词);
      行.add(某单词 + ",\"" + String.join(",", 来源) + "\"," + 来源.size());
    }
    文件功用.写行入文件(行, 常量_输出文件路径 + "/" + "词汇" + 常量_输出后缀 + ".csv");
  }

  /**
   * @param s 输入一个骆驼法的命名, 如 aCamelString
   * @return 拆分开的单词, 即[a, Camel, String]
   */
  public static String[] 拆分骆驼命名(String s) {
    return s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
  }
}
