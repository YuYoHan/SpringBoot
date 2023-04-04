package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "popularSearch")
@Entity(name = "popularSearch")
public class PopularSearchEntity {
    @Id
    private String popularTerm;
    private int popularSearchCount;

}
