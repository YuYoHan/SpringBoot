package com.example.fileupload3.controller;

import com.example.fileupload3.domain.Item;
import com.example.fileupload3.domain.ItemForm;
import com.example.fileupload3.domain.UploadFile;
import com.example.fileupload3.file.FileStore;
import com.example.fileupload3.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class ItemController {

    private final FileService fileService;
    private final FileStore fileStore;

    @PostMapping("/")
    public ResponseEntity<?> saveItem(@RequestBody ItemForm form) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
        UploadFile storeImageFile = fileStore.storeFile(form.getImageFile());

        Item item = new Item(form.getItemName(), attachFile, storeImageFile);
        fileService.save(item);

        return ResponseEntity.status(HttpStatus.OK).body("파일 업로드 성공");
    }
    @GetMapping("/")
    public ResponseEntity<ItemForm> newItem(@RequestBody ItemForm form) {
        return ResponseEntity.ok(form);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable("id") Long id) {
        Item item = fileService.findById(id).orElseThrow(()
                -> new IllegalArgumentException("NOT FOUND ITEM :" + id));
    }


}
