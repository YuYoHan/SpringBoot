package com.example.demo3.service;

import com.example.demo3.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

//    @Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;


}
