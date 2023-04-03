package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {
    @Id
    private String userEmail;
    private String userPassword;
    private String userNickName;
    private String userPhoneNumber;
    private String userAddress;
    private String userProfile;
}
