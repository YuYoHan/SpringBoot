package com.example.study01.mapper;

import com.example.study01.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int join(UserDTO user);
    // MyBatis는 두개 이상의 데이터를 파라미터로 넘길 때 객체나 Map, List 등 혹은 @Param을 이용한다.
    // 정해진 파라미터는 MyBatis에서 #{param명}으로 사용 가능하다.
    UserDTO login(@Param("userId") String userId, @Param("userPw") String userPw);
}
