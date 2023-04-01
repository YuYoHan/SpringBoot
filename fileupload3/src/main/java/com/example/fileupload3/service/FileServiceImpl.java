package com.example.fileupload3.service;

import com.example.fileupload3.domain.Item;
import com.example.fileupload3.mapper.FileMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private FileMapper fileMapper;

    @Override
    public void save(Item item) {
        fileMapper.uploadFile(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return fileMapper.findId(id);
    }
}
