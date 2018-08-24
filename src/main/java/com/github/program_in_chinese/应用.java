package com.github.program_in_chinese;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class 应用 implements CommandLineRunner {

  private static final Logger 笔录 = LoggerFactory.getLogger(应用.class);

  private final 词库接口 词库;

  @Autowired
  public 应用(词库接口 词库) {
    this.词库 = 词库;
  }

  public static void main(String[] 参数) {
    笔录.info("服务启动开始");
    SpringApplication.run(应用.class, 参数);
    笔录.info("服务已启动");
  }

  @Override
  public void run(String ...参数) throws Exception {
    词条 词条1 = new 词条(1L, "Collection", "集合");
    词条 词条2 = new 词条(2L, "Dictionary", "字典");
    词条 词条3 = new 词条(3L, "List", "列表");
    
    笔录.info("添加H2数据");
    词库.save(词条1);
    词库.save(词条2);
    词库.save(词条3);
    笔录.info("数据条数: {}", 词库.count());
  }
}
