package com.example.fileupload2.file;

import com.example.fileupload2.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("c:/upload/file/")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile: multipartFiles
             ) {
            if(!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        String originFileName = multipartFile.getOriginalFilename();

        // 서버 내부에서 관리하는 파일명은 유일한 이름을 생성하는 UUID를 사용
        String storeFileName = createSoreFileName(originFileName);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originFileName, storeFileName);
    }

    private String createSoreFileName(String originalFileName) {
        // 확장자를 별도로 추출해서 서버 내부에서 관리하는 파일명에도 붙여준다.
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return  uuid + "."+ ext;
    }
    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos +1);
    }
}
