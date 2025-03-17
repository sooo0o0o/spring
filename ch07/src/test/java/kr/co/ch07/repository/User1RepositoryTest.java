package kr.co.ch07.repository;

import kr.co.ch07.entity.Child;
import kr.co.ch07.entity.Parent;
import kr.co.ch07.entity.User1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class User1RepositoryTest {

    @Autowired
    private User1Repository user1Repository;

    @Test
    void findUser1ByUid() {

        //given
        String uid = "A102";
        //when
        User1 user1 = user1Repository.findUser1ByUid(uid);

        //then
        System.out.println(user1);

    }

    @Test
    void findUser1ByName() {

        List<User1> user1List = user1Repository.findUser1ByName("이순신");
        System.out.println(user1List);
    }

    @Test
    void findUser1ByNameNot() {
        List<User1> user1List = user1Repository.findUser1ByNameNot("홍길동");
        System.out.println(user1List);
    }

    @Test
    void findUser1ByUidAndName() {
        String uid = "A102";
        String name = "이순신";

        System.out.println("test");
        User1 user1 = user1Repository.findUser1ByUidAndName(uid, name);

        System.out.println(user1);
    }


    @Test
    void findUser1ByUidOrName() {
        List<User1> user1List = user1Repository.findUser1ByUidOrName("A102", "이순신");
        System.out.println(user1List);
    }

    @Test
    void findUser1ByAgeGreaterThan() {
        List<User1> user1List = user1Repository.findUser1ByAgeGreaterThan(50);
        System.out.println(user1List);
    }

    @Test
    void findUser1ByAgeGreaterThanEqual() {
        List<User1> user1List = user1Repository.findUser1ByAgeGreaterThanEqual(55);
        System.out.println(user1List);
    }

    @Test
    void findUser1ByAgeLessThan() {
        List<User1> user1List = user1Repository.findUser1ByAgeLessThan(50);
        System.out.println(user1List);
    }

    @Test
    void findUser1ByAgeLessThanEqual() {
        List<User1> user1List = user1Repository.findUser1ByAgeLessThanEqual(22);
        System.out.println(user1List);
    }

    @Test
    void findUser1ByAgeBetween() {
        List<User1> user1List = user1Repository.findUser1ByAgeBetween(50, 60);
        System.out.println(user1List);
    }

    @Test
    void findUser1ByNameLike() {
        String name = "%순%";
        List<User1> user1List = user1Repository.findUser1ByNameLike(name);

        for (User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByNameContains() {
        String name = "이";
        List<User1> user1List = user1Repository.findUser1ByNameContains(name);
        for (User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByNameStartsWith() {
        String name = "이";
        List<User1> user1List = user1Repository.findUser1ByNameStartsWith(name);
        for (User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByNameEndsWith() {
        String name = "진";
        List<User1> user1List = user1Repository.findUser1ByNameEndsWith(name);
        for (User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByOrderByName() {
        List<User1> user1List = user1Repository.findUser1ByOrderByName();
        for (User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByNameOrderByUid() {
        String name = "이순신";
        List<User1> user1List = user1Repository.findUser1ByNameOrderByUid(name);

        for(User1 user1 : user1List) {
            System.out.println(user1);
        }

    }

    @Test
    void findUser1ByOrderByAgeAsc() {
        List<User1> user1List = user1Repository.findUser1ByOrderByAgeAsc();
        for(User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByOrderByAgeDesc() {
        List<User1> user1List = user1Repository.findUser1ByOrderByAgeDesc();
        for(User1 user1 : user1List) {
            System.out.println(user1);
        }
    }

    @Test
    void findUser1ByAgeGreaterThanOrderByAgeAsc() {
        // given
        int age = 30;

        // when
        List<User1> users = user1Repository.findUser1ByAgeGreaterThanOrderByAgeAsc(age);

        //then
        for(User1 user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    void countUser1ByName() {
        // given
        String name = "이순신";

        // when
        long count = user1Repository.countUser1ByName(name);

        // then
        System.out.println(count);
    }

    @Test
    void selectUser1UnderAge30() {// given
        int age = 30;

        // when
        List<User1> users = user1Repository.findUser1ByAgeLessThan(age);

        for (User1 user1 : users) {
            System.out.println(user1);
        }


    }

    @Test
    void selectUser1ByName() {
        // given
        String name = "이순신";

        // when
        List<User1> users = user1Repository.findUser1ByName(name);
        for (User1 user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    void selectUser1ByNameParam() {
        // given
        String name = "이순신";

        // when
        List<User1> users = user1Repository.selectUser1ByNameParam(name);
        for (User1 user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    void selectUser1ByUid() {
        // given
        String uid = "A102";

        // when
        User1 user = user1Repository.findUser1ByUid(uid);
        System.out.println(user);
    }

    @Test
    void selectParentWithChild() {
        //given
        String pid = "p101";

        //when
        List<Object[]> list = user1Repository.selectParentWithChild(pid);

        for (Object[] arr : list) {
            Parent parent = (Parent) arr[0];
            Child child = (Child) arr[1];

            System.out.println(parent);
            System.out.println(child);
        }

    }
}