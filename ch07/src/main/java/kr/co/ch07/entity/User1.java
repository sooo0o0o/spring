package kr.co.ch07.entity;

import jakarta.persistence.*;
import kr.co.ch07.dto.User1DTO;
import lombok.*;

//@Setter 는 entity 에서 사용하지 않음!
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity                 // entity 정의 어노테이션
@Table(name = "user1")  // 매핑 테이블 어노테이션
public class User1 {

    @Id
    private String uid;

    @Column(name="name") //매핑 컬럼 설정 어노테이션 (속성 명과 컬럼 명이 동일할 경우엔 생략이 가능)
    private String name;

    @Column(name="hp")
    private String hp;

    private int age;

    //DTO 변환 메서드 정의
    public User1DTO toDTO(){
        return User1DTO.builder()
                    .uid(uid)
                    .name(name)
                    .hp(hp)
                    .age(age)
                    .build();
    }

}
