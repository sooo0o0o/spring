package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "article")
@Entity
@Table(name = "Board_Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")    //FK 설정
    private Article article; // = article_no

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User user;

    @CreationTimestamp
    private LocalDateTime wdate;


}
