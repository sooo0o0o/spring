package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Board_Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // = auto_increment
    private int no;
    private String title;
    private String content;

    /*
        @ManyToOne
            - N:1 관계 설정 어노테이션
            - 현재 엔티티인 Article 과 User 엔티티를 N:1 관계 설정

        @OnetoMany
            - 1:N 양방향 관계 설정 어노테이션
            - mappingBy 속성은 양방향 관계설정에서 기준이 되는 엔티티의 속성을 설정
            - FK가 되는 엔티티 속성명을 적용
            - 참조타입 : List<>

        @JoinColumn
            - User 엔티티가 참조되는 테이블 컬럼명
            - 외래키 참조 컬럼(FK)

        @Fetch 전략
            - 엔티티가 조회될 때 관계 설정된 엔티티가 조회되는 타이밍 전략
            - 1:1, N:1은 즉시 로딩(Eager) 전략 사용
            - 성능 문제로 즉시로딩 대신 Lazy 전략 권장

        추가 연구 과제
            - N+1 문제 이해하기
            - 고아객체 문제 이해하기
    */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    @OneToMany(mappedBy = "article")    //@onetomany : (default) fetch = FetchType.LAZY
    private List<Comment> comment;

    @OneToMany(mappedBy = "article")
    private List<File> file;

    @CreationTimestamp
    private LocalDateTime wdate;


}
