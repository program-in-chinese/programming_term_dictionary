package com.github.program_in_chinese;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class 词条 {

  @Id
  private long id;
  private String 英文术语;
  private String 中文术语;

  public 词条() {
  }
  
  public 词条(long id, String 英文术语, String 中文术语) {
    this.id = id;
    this.英文术语 = 英文术语;
    this.中文术语 = 中文术语;
  }

  public long getId() {
    return id;
  }

  public String get中文术语() {
    return 中文术语;
  }
}
