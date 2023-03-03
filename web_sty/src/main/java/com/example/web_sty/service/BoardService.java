package com.example.web_sty.service;

import com.example.web_sty.dto.BoardDTO;
import com.example.web_sty.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// DTO → Entity (Entity Class)
// Entity → DTO (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {

        boardRepository.save();
    }
}
