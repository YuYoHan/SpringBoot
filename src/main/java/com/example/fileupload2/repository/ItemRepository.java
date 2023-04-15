package com.example.fileupload2.repository;

import com.example.fileupload2.domain.Item;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ItemRepository extends Repository<Item, Long> {
    void save(Item item);

    Optional<Item> findById(Long id);
}