package com.github.program_in_chinese;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface 词库接口 extends JpaRepository<词条, Long> {

  List<词条> findBy英文术语(String 英文术语);
}
