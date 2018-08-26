package com.github.program_in_chinese.工具;

import java.io.File;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

import com.github.program_in_chinese.功用.FileUtil;
import com.github.program_in_chinese.工具.类型名提取器.类型名;

public class 遍历JDK类型名 {

  private static final ASTParser 语法解析器 = ASTParser.newParser(AST.JLS8);

  // Config file path or directory path.
  private static final String FILE_PATH = "java/util";
  private static final String OUTPUT_DIR = "namelist/";

  private static final 类型名提取器 提取器 = new 类型名提取器();

  /**
   * 
   * @param args 第一个参数为JDK路径。可由JDK目录下的src.zip解压。
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      System.out.println("需要JDK源码路径作为唯一参数");
      return;
    }

    FileUtil.mkdir(OUTPUT_DIR);
    处理Java文件(new File(args[0] + FILE_PATH));

    类型名 名 = 提取器.获取名();
    // Remove constructor methods from method list
    名.方法名.removeAll(名.类名);

    FileUtil.writeLinesToFile(名.类名, OUTPUT_DIR + "classes_utils.txt");
    FileUtil.writeLinesToFile(名.方法名, OUTPUT_DIR + "methods_utils.txt");
    FileUtil.writeLinesToFile(名.参数名, OUTPUT_DIR + "parameters_utils.txt");
    System.out.println("提取完毕: " + 名.类名.size() + "类;" + 名.方法名.size() + "方法;"
        + 名.参数名.size() + "参数");
  }

  private static void 处理Java文件(File 路径) throws Exception {
    /*if (路径.isFile()) {
      if (路径.getName().endsWith(".java")) {
        解析Java文件(路径);
      }
    } else {*/
      File[] 文件 = 路径.listFiles();
      if (文件 != null) {
        for (File 某文件 : 文件) {
          if (某文件.getName().endsWith(".java")) {
            解析Java文件(某文件);
          }
          //处理Java文件(某文件);
        }
      }
    //}
  }

  private static void 解析Java文件(File 文件) throws Exception {
    语法解析器.setSource(FileUtil.getStringFromSourceFile(文件).toCharArray());
    语法解析器.createAST(null).accept(提取器);
  }
}
