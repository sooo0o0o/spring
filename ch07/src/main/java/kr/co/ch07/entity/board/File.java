package kr.co.ch07.entity.board;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "article")
@Entity
@Table(name = "Board_File")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ano")    //FK 설정
    private Article article; // = article_no

    private String oName;
    private String sName;
}
