package com.example.rest_book1.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우
// 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 합니다.
@MappedSuperclass
// BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    /*
    *   @CreationTimestamp : 생성된 시간
        @Column(updatable = false) : 수정시에는 관여하지 않음
        @UpdateTimestamp : 업데이트가 발생했을 때 시간
    *   @Column(insertable = false) : 입력시에는 관여하지 않음
    * */
    // Entity가 생성되어 저장될 때 시간이 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
