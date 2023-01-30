package com.example.demo.domain;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Log4j2
public class MemberRepository {
    // 회원 정보는 static ConcorrentHashMap을 사용해서 메모리에 저장하도록 할 것이다.
    private static Map<Long, MemberDTO> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public MemberDTO save(MemberDTO member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);

        return member;
    }

    public MemberDTO findById(long id) {
        return store.get(id);
    }

    // 여기서 findByLoginId()가 loginId를 받아 회원 저장소에서 회원 인스턴스를 찾는 메소드이다.
    // 저장소에 loginId에 해당하는 회원이 없을수도 있으므로 리턴 타입은 Optional로 감싼다.
    // Java8에서는 Optional<T> 클래스를 사용해 NPE를 방지할 수 있도록 도와준다.
    // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로,
    // 참조하더라도 NPE가 발생하지 않도록 도와준다.
    // Optional 클래스는 아래와 같은 value에 값을 저장하기 때문에 값이 null이더라도 바로 NPE가 발생하지 않으며,
    // 클래스이기 때문에 각종 메소드를 제공해준다.
    public Optional<MemberDTO> findByLoginId(String loginId) {
        return this.findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<MemberDTO> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }


    // 종속성 주입이 완료된 후 실행되어야 하는 메서드에 사용된다. 이 어노테이션은 다른 리소스에서 호출되지 않아도 수행된다.
    // 회원 가입은 화면을 별도로 만들지 않을 것이므로 @PostConstruct를 사용해 테스트용 회원을 만들도록 하였다.
    @PostConstruct
    public void init() {
        MemberDTO member = new MemberDTO();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        save(member);
    }
}
