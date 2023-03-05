package com.example.rest_book1.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDTOTest {

    @Test
    @DisplayName("룸북 기능 테스트")
    public void lombokTest() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        // then

        // assertj라는 테스트 검증 라이브러리의 검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받습니다.
        // 메소드 체이닝이 지원되어 isEqualTo와 같은 메소드를 이어서 사용할 수 있습니다.
        // isEqualTo : 동등 비교 메소드
        // assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }

}