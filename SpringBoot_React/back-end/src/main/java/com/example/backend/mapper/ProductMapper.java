package com.example.backend.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    List<Map<String, Object>> list();
}
