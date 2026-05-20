package com.example.legaltrialservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.legaltrialservice.entity.Trial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface TrialMapper extends BaseMapper<Trial> {
    List<Trial> selectByCaseId(@Param("caseId") Long caseId);

    @Select("SELECT t.*, c.case_name AS caseName " +
            "FROM trial t " +
            "LEFT JOIN legal_case c ON t.case_id = c.id")
    List<Trial> selectListWithCaseName();
}