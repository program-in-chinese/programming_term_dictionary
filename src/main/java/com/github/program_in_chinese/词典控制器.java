package com.github.program_in_chinese;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class 词典控制器 {

  private static final Logger 笔录 = LoggerFactory.getLogger(词典控制器.class);

  @Autowired
  private 词库接口 词库;

  @GetMapping("/")
  @ResponseBody
  public List<词条> 取词条(@RequestParam(name = "term", required = false, defaultValue = "") String 英文术语) {
    笔录.info("输入: " + 英文术语);
    return 词库.findBy英文术语(英文术语);
  }
}
