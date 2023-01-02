package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repositrory = new MemoryMemberRepository();

    /*
    *   한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수있다.
    *   이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 수 있다.
    *   @afterEach를 사용하면 각 테스트가 종료될 때 마다 이 기능을 사용한다.
    *   여기에 데이터를 삭제하는 코드를 넣으면 삭제해주므로 테스트를 각각 독립적으로 실행한다.
    * */
    @AfterEach
    public void afterEach() {
        repositrory.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repositrory.save(member);
        // Optional에서 값을 꺼낼 때는 get
        Member result = repositrory.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repositrory.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repositrory.save(member2);

        Member result = repositrory.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repositrory.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repositrory.save(member2);

        List<Member> result = repositrory.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
