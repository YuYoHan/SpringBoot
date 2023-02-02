package com.example.study01.repository;

import com.example.study01.entity.Memo;
import com.example.study01.repository.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemoRepositoryTest {
    
    @Autowired
    MemoRepository memoRepository;


    @Test
    public void testInsertDummies() {
        // 100개의 새로운 Memo 객체를 생성하고 MemoRepositroy를 이용해서 이를 insert하는 것입니다.
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample... " + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        // 데이터베이스에 존재하는 mno
        Long mno = 100L;

        // findById()의 경우 java.util 패키지의 Optional 타입으로 반환되기 때문에 한번 더 결과가 존재하는지를
        // 체크하는 형태로 작성
        Optional<Memo> result = memoRepository.findById(mno);

        log.info("----------------------------------");
        if(result.isPresent()) {
            Memo memo = result.get();
            log.info(memo);
        }
    }

    @Test
    @Transactional
    public void testSelect02() {
        Long mno = 100L;

        Memo memo = memoRepository.getOne(mno);
    }
}