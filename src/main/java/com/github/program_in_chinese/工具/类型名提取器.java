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
  public boolean visit(MethodDeclaration node) {
    if (isPublicDeclaration(node)) {
      名.方法名.add(node.getName().getFullyQualifiedName());
    }

    for (Object parameter : node.parameters()) {
      VariableDeclaration variableDeclaration = (VariableDeclaration) parameter;
      String parameterName = variableDeclaration.getName().getFullyQualifiedName();

      // Skip parameter name with only single character
      if (parameterName.length() > 1) {
        名.参数名.add(parameterName);
      }
    }
    return super.visit(node);
  }

  @Override
  public boolean visit(TypeDeclaration node) {
    if (isPublicDeclaration(node)) {
      名.类名.add(node.getName().getFullyQualifiedName());
    }
    return super.visit(node);
  }

  public 类型名 获取名() {
    return 名;
  }

  private boolean isPublicDeclaration(BodyDeclaration node) {
    return (node.getModifiers() & Modifier.PUBLIC) != 0;
  }

  public class 类型名 {
    public Set<String> 类名 = new HashSet<>();
    public Set<String> 方法名 = new HashSet<>();
    public Set<String> 参数名 = new HashSet<>();
  }
}
