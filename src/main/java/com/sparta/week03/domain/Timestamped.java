package com.sparta.week03.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//Timestamped클래스를 상속한 녀석이 자동으로 생성시간과 수정시간을 컬럼으로 잡도록 도와줌
@MappedSuperclass // Entity가 자동으로 컬럼으로 인식합니다.
// Memo클래스가변화하는 것을 항상 지켜보고 있다-->자동으로  auditing 업데이트
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다.
// abstract는 객체를 못 만듬, 다른데서 상속이 되어야만 그때만 객체를 만들 수 있다
public abstract class Timestamped {
    
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
