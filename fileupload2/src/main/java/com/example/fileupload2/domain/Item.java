package com.example.fileupload2.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String itemName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "uploadFileName", column = @Column(name = "attach_upload_file_name")),
            @AttributeOverride(name = "storeFileName", column = @Column(name = "attach_store_file_name"))
    })
    private UploadFile attachFile;


    @ElementCollection
    @CollectionTable(name = "item_image", joinColumns = @JoinColumn(name = "item_id"))
    private List<UploadFile> imageFiles = new ArrayList<>();

    public Item(String itemName, UploadFile attachFile, List<UploadFile> imageFiles) {
        this.itemName = itemName;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }
}