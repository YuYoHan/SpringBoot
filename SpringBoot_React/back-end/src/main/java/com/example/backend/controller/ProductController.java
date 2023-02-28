package com.example.backend.controller;

import com.example.backend.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @RequestMapping("/list")
    public List<Map<String, Object>> list() {
        return productMapper.list();
    }
}
