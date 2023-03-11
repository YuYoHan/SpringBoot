package com.example.demo3.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class FileDTO {
    // unique한 파일 이름을 만들기 위한 속성
    // 동일한 이름의 파일이 업로드 될 때 이름 충돌을 피하기 위해 uuid를 이용해 준다.
    private String uuid;
    // 실제 파일 이름
    private String fileName;
    private String contentType;
}
