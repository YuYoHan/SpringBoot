package com.example.fileupload3.service;

import com.example.fileupload3.domain.Item;

import java.util.Optional;

public interface FileService {
    void save(Item item);

    Optional<Item> findById(Long id);
}
