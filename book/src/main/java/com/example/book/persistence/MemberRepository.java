package com.example.book.persistence;

import com.example.book.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository  extends CrudRepository<Member, String> {

}
