package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int amount;
    private String type;
    private String keyword;
    private int startrow;

    public Criteria() {
        // this() : 현재 클래스 생성자
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.startrow =0;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        this.startrow = (this.pageNum -1) * this.amount;
    }


    public String[] getTypeArr() {
        // type이 null이라면 return {}
        // type에 "TC"가 있다면 return {"T", "C"}
        return type == null ? new String[] {} : type.split("");
    }

    public String getListLink() {

        // URI를 보다 쉽게 다룰 수 있도록 도와주는 UriComponentBuilder를 스프링에서 제공
        // UriComponents : URI를 구성하는 Components들을 효과적으로 다룰 수 있도록 하는 클래스
        /*
        *   URI Components :
        *       - Scheme
        *       - UserInfo
        *       - Host
        *       - Port
        *       - Path
        *       - Query
        *       - Fragment
        *
        *   URI에 대한 정보를 각 구성요소별로 분리하여 변수에 저장하여 가지고 있는 URI 구성요소의 집합
        *   모든 구성요소에 대해 getter() 메서드를 가지고 있어 이 클래스를 이용하여 각 구성요소에
        *   손쉽게 접근할 수 있다.
        * */
        // UriComponentsBuilder는 UriComponents를 Build할 수 있도록 도와주는 클래스
        // UriComponents 클래스의 생성자는 모두 package-private or private이기 때문에
        // 개발자가 이를 구현하지 않는 이상 생성자를 통해 직접 생성 x
        // 기본이 되는 문자열 → ? 앞에 오는 uri 문자열
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                // 파라미터 추가
                // ? 뒤에 오는 uri 문자열
                .queryParam("pageNum", pageNum)
                .queryParam("amount", amount)
                .queryParam("keyword", keyword)
                .queryParam("type", type);
        // ?pageNum=3&amount=10&keyword=app&type=TC
        // 빌더가 가지고 있는 설정대로 문자열 만들기
        return builder.toUriString();
    }
}
