package kr.co.ch07.repository;

import kr.co.ch07.entity.board.Article;
import kr.co.ch07.entity.board.Comment;
import kr.co.ch07.entity.board.File;
import kr.co.ch07.entity.board.User;
import kr.co.ch07.repository.board.ArticleRepository;
import kr.co.ch07.repository.board.CommentRepository;
import kr.co.ch07.repository.board.FileRepository;
import kr.co.ch07.repository.board.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private FileRepository fileRepository;
    @Autowired private UserRepository userRepository;

    //TEST<1> - Insert User
    @Test
    public void test1(){
        //given
        User user = User.builder()
                .uid("a102")
                .name("김춘추")
                .hp("010-4321-1234")
                .build();

        //when
        User savedUser = userRepository.save(user);

        //then
        System.out.println(savedUser);

    }

    //TEST<2> - Insert Article
    @Test
    public void test2(){

        User user = User.builder()
                .uid("a101")
                .build();

        //given
        Article article = Article.builder()
                .title("TITLE2")
                .content("CONTENT2")
                .user(user)
                .build();

        //when
        Article savedArticle = articleRepository.save(article);

        //then
        System.out.println(savedArticle);

    }

    //TEST<3> - Insert Comment
    @Test
    public void test3(){
        User user = User.builder()
                .uid("a101")
                .build();
        Article article = Article.builder()
                .no(2)
                .build();
        //given
        Comment comment = Comment.builder()
                .article(article)
                .content("COMMENT4")
                .user(user)
                .build();

        //when
        Comment savedComment = commentRepository.save(comment);

        //then
        System.out.println(savedComment);
    }

    //TEST<4> - Insert File
    @Test
    public void test4(){
        Article article = Article.builder()
                .no(1)
                .build();
        //given
        File file = File.builder()
                .article(article)
                .oName("Original2")
                .sName("Saved2")
                .build();

        //when
        File savedFile = fileRepository.save(file);

        //then
        System.out.println(savedFile);

    }

    //TEST<5> - Select All Article
    @Transactional  //no session error 발생시 추가하기
    @Test
    public void test5(){
        //given

        //when
        /*
            엔티티가 관계 설정으로 되어있을 경우,
            해당 엔티티를 조회할 때 관계가 맺어진 다른 엔티티도 함께 조회되기 때문에
            단일 트랜잭션으로 처리해야 됨.
            => 아니면 no session 에러가 발생!
            --> @Transactional 어노테이션 사용
        */
        List<Article> articleList = articleRepository.findAll();

        //then
        /*
            article.toString 에서 comment 를 호출하고,
            comment.toString 에서 article 을 호출
            => 재귀 (recursive) 발생! == ##무한루프##
            => Article 의 @ToString 어노테이션에서 (exclude = {""}) 설정
        */
        //System.out.println(articleList);

        for(Article article : articleList){
            System.out.println(article);

            List<File> fileList = article.getFile();
            for(File file : fileList){
                System.out.println(file);
            }

            List<Comment> commentList = article.getComment();
            for(Comment comment : commentList){
                System.out.println(comment);
            }
        }

    }

    //TEST<6> - Select Article By ID
    @Transactional
    @Test
    public void test6(){
        //given


        //when
        Optional<Article> optional = articleRepository.findById(1L);    //Long = 숫자 + L
        if(optional.isPresent()){
            Article article = optional.get();
            System.out.println(article);

            List<File> fileList = article.getFile();
            for(File file : fileList){
                System.out.println(file);
            }

            List<Comment> commentList = article.getComment();
            for(Comment comment : commentList){
                System.out.println(comment);
            }
        }

        //then

    }

}
