package com.example.legalplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.legalplatform.entity.CaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CaseInfoMapper extends BaseMapper<CaseInfo> {

    // 只加这一个方法！
    @Select("SELECT * FROM case_info")
    List<CaseInfo> selectAll();
}