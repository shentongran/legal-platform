package com.example.legaltrialservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.legaltrialservice.entity.Trial;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialMapper extends BaseMapper<Trial> {
}