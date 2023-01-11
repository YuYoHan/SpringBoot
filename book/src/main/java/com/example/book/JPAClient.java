package com.example.book;

import com.example.book.domain.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class JPAClient {
    public static void main(String[] args) {
        // Entity Manager 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study01");
        EntityManager em = emf.createEntityManager();
        try{
            Board board = new Board();
            board.setTitle("JPA제목");
            board.setWriter("관리자");
            board.setContent("JPA 글 등록중입니다");
            board.setCreateDate(new Date());
            board.setCnt(0l);

            // 글 등록
            em.persist(board);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
