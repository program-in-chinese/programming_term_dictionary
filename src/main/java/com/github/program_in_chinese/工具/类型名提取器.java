package com.github.program_in_chinese.工具;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;

public class 类型名提取器 extends ASTVisitor {

  private 类型名 名 = new 类型名();

  @Override
  public boolean visit(MethodDeclaration 方法节点) {
    if (为公开声明(方法节点)) {
      名.方法名.add(方法节点.getName().getFullyQualifiedName());
    }

    for (Object 参数 : 方法节点.parameters()) {
      VariableDeclaration 变量声明 = (VariableDeclaration) 参数;
      String 参数名 = 变量声明.getName().getFullyQualifiedName();

      // 忽略所有单字母参数名. TODO: 是否需要研究单字母命名?
      if (参数名.length() > 1) {
        名.参数名.add(参数名);
      }
    }
    return super.visit(方法节点);
  }

  @Override
  public boolean visit(TypeDeclaration 类型节点) {
    if (为公开声明(类型节点)) {
      名.类名.add(类型节点.getName().getFullyQualifiedName());
    }
    return super.visit(类型节点);
  }

  public 类型名 获取名() {
    return 名;
  }

  private boolean 为公开声明(BodyDeclaration 节点) {
    return (节点.getModifiers() & Modifier.PUBLIC) != 0;
  }

  public class 类型名 {
    public Set<String> 类名 = new HashSet<>();
    public Set<String> 方法名 = new HashSet<>();
    public Set<String> 参数名 = new HashSet<>();
  }
}
