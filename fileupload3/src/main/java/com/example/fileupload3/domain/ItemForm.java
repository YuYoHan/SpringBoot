package com.example.fileupload3.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
@NoArgsConstructor
public class ItemForm {
    private Long itemId;
    private String itemName;
    private MultipartFile imageFile;
    private MultipartFile attachFile;

    private Long userId;

    @Builder
    public ItemForm(String itemName, MultipartFile imageFile, MultipartFile attachFile, Long userId) {
        this.itemName = itemName;
        this.imageFile = imageFile;
        this.attachFile = attachFile;
        this.userId = userId;
    }
}
