package com.github.program_in_chinese.工具;

import java.io.File;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import com.github.program_in_chinese.功用.文件功用;
import com.github.program_in_chinese.工具.类型名提取器.类型名;

public class 遍历JDK类型名 {

  private static final ASTParser 语法解析器 = ASTParser.newParser(AST.JLS8);

  // JDK源码内路径
  private static final String 常量_源文件路径 = "java/util";
  private static final String 常量_输出文件路径 = "命名列表/";

  private static final 类型名提取器 提取器 = new 类型名提取器();

  /**
   * 
   * @param 参数 第一个参数为JDK路径。可由JDK目录下的src.zip解压。
   * @throws Exception
   */
  public static void main(String[] 参数) throws Exception {
    if (参数.length != 1) {
      System.out.println("需要JDK源码路径作为唯一参数");
      return;
    }

    文件功用.创建路径(常量_输出文件路径);
    处理Java文件(new File(参数[0] + 常量_源文件路径));

    类型名 名 = 提取器.获取名();

    // 从方法列表中删除所有构造方法
    名.方法名.removeAll(名.类名);

    String 后缀 = "_" + 常量_源文件路径.replaceAll("/", "_");
    文件功用.写行入文件(名.类名, 常量_输出文件路径 + "类" + 后缀 + ".txt");
    文件功用.写行入文件(名.方法名, 常量_输出文件路径 + "方法" + 后缀 + ".txt");
    文件功用.写行入文件(名.参数名, 常量_输出文件路径 + "参数" + 后缀 + ".txt");
    System.out.println("提取完毕: " + 名.类名.size() + "类;" + 名.方法名.size() + "方法;" + 名.参数名.size() + "参数");
  }

  private static void 处理Java文件(File 路径) throws Exception {
    File[] 文件 = 路径.listFiles();
    if (文件 != null) {
      for (File 某文件 : 文件) {
        if (某文件.getName().endsWith(".java")) {
          解析Java文件(某文件);
        }
      }
    }
  }

  private static void 解析Java文件(File 文件) throws Exception {
    语法解析器.setSource(文件功用.取源文件文本(文件).toCharArray());
    语法解析器.createAST(null).accept(提取器);
  }
}
