package com.github.program_in_chinese;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface 词库接口 extends JpaRepository<词条, Long> {

}
