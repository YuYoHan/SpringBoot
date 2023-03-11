package com.example.demo3.controller;

import com.example.demo3.DTO.FileDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @PostMapping("/upload")
    // 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    public String upload(@RequestParam MultipartFile[] uploadFile, Model model) throws IllegalStateException, IOException {
        List<FileDTO> list = new ArrayList<>();

        for (MultipartFile file: uploadFile
             ) {
            if (!file.isEmpty()) {
                // UUID를 이용해 unique한 파일이름을 만들어준다.
                FileDTO dto = new FileDTO(UUID.randomUUID().toString(),
                        file.getOriginalFilename(),
                        file.getContentType());

                list.add(dto);

                File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
                // 전달된 내용을 실제 물리적인 파일로 저장해 준다.
                file.transferTo(newFileName);
            }
        }
        model.addAttribute("files", list);
        return "result";
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

    @GetMapping("/download")
    public ResponseEntity<Response> download(@ModelAttribute FileDTO fileDTO) throws IOException {
        Path path = Paths.get(filePath + "/" + fileDTO.getUuid() + "_" + fileDTO.getFileName());
        String contentType = Files.probeContentType(path);

        // header를 통해서 다운로드 되는 파일의 정보를 설정한다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                        .filename(fileDTO.getFileName(), StandardCharsets.UTF_8)
                .build());

        headers.add(HttpHeaders.CONTENT_TYPE, contentType);

        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
