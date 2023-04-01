package com.example.fileupload3.mapper;

import com.example.fileupload3.domain.Item;

import java.util.Optional;

public interface FileMapper {
    void uploadFile(Item item);

    Optional<Item> findId(Long id);
}
