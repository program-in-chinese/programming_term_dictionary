package com.github.program_in_chinese;

public class 词条 {
  // TODO: 仅为演示用
  private final long id;
  private final String 中文术语;

  public 词条(long id, String 中文术语) {
    this.id = id;
    this.中文术语 = 中文术语;
  }

  public long getId() {
    return id;
  }

  public String get中文术语() {
    return 中文术语;
  }
}
