package com.github.program_in_chinese;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class 词典控制器 {

  private static final Logger 笔录 = LoggerFactory.getLogger(词典控制器.class);
  private static final HashMap<String, String> 英中词典 = new HashMap<>();

  static {
    英中词典.put("List", "列表");
  }
  private final AtomicLong 计数器 = new AtomicLong();

  @GetMapping("/")
  @ResponseBody
  public 词条 取词条(@RequestParam(name = "term", required = false, defaultValue = "") String 英文术语) {
    笔录.info("输入: " + 英文术语);
    if (英中词典.containsKey(英文术语)) {
      return new 词条(计数器.incrementAndGet(), 英中词典.get(英文术语));
    }
    return null;
  }
}
