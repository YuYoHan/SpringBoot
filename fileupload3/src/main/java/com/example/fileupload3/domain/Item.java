package com.example.fileupload3.domain;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private UploadFile imageFile;

    @Builder
    public Item(String itemName, UploadFile attachFile, UploadFile imageFile) {
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFile = imageFile;
    }
}
