package com.example.demo3.DTO;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.Criteria;

@ToString
@Getter
public class pageDTO {
    private int endPage;
    private int startPage;
    private boolean prev, next;
    private int total;
    private Criteria cri;

    public pageDTO(int total, Criteria cri) {
        this.total = total;
        this.cri = cri;
    }

}
