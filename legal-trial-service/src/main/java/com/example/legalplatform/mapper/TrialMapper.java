package com.example.legalplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.legalplatform.entity.Trial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TrialMapper extends BaseMapper<Trial> {
    List<Trial> selectByCaseId(@Param("caseId") Long caseId);
}
