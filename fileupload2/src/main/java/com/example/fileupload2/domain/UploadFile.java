package com.example.fileupload2.domain;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {
    // 고객이 업로드한 파일명
    private String uploadFileName;

    // 서버 내부에서 관리하는 파일명
    private String storeFileName;
}
