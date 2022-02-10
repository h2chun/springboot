package springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity 클래스들이 BaseTimeEntity 상속할 경우 필드들도 칼럼으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class)  //Auditing 기능(생성 또는 수정 시 자동으로 값을 넣어주는 기능) 포함.
public abstract class BaseTimeEntity {
    @CreatedDate    //Entity가 생성되어 저장될 때 시간이 자동 저장되게 함.
    private LocalDateTime createdDate;

    @LastModifiedDate   //Entity가 변경될 때 시간 자동 저장.
    private  LocalDateTime modifiedDate;

}
