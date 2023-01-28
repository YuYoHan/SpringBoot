package com.example.board.domain;

import lombok.Data;

@Data
public class PageDTO {
    private int StartPage;
    private int endPage;
    private int realEnd;
    private int total;
    private boolean prev, next;
    private int pageNum;

    public PageDTO(int total, int pageNum) {
        this.total = total;
        this.pageNum = pageNum;

        // 페이지가 10이면 10 / 10.0을 해서 1이 나온다.
        // 그런 다음 * 10을 해줘서 10이된다. 10은 마지막 페이지다.
        this.endPage = (int)Math.ceil(pageNum/10.0) * 10;
        // 마지막 페이지에서 10개를 보여준다고 하면 9를 빼주면 된다.
        // 예를들어, 10이 마지막이라면 10 - 9 = 1이 나와서 첫페이지는 1페이지다.
        this.StartPage = this.endPage - 9;
        // 진짜 페이지하고 수치로만 구한 페이지하고 다를 수 가 있다.
        // realEnd는 진짜 마지막 페이지를 구하는 것이다.
        this.realEnd = (int)Math.ceil(total * 1.0 / 10);
        endPage = endPage > realEnd? realEnd : endPage;

        // 10페이지 마지막 페이지 전으로 보내주는 것
        // startPage가 1보다 크다는 말은 11, 21, 31, ... 이런 경우기 때문에
        // 이전으로 돌아갈 페이지가 있는 것이다.
        this.prev = this.StartPage > 1;
        this.next = this.endPage < this.realEnd;


    }
}
