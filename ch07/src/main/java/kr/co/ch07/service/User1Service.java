package kr.co.ch07.service;

import kr.co.ch07.dto.User1DTO;
import kr.co.ch07.entity.User1;
import kr.co.ch07.repository.User1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class User1Service {

    private final User1Repository repository;

    public void register(User1DTO user1DTO){
        log.info("user1DTO" + user1DTO);

        //DTO를 Entity로 변환
        User1 user1 = user1DTO.toEntity();
        log.info("user1" + user1);

        repository.save(user1);
    }

    public List<User1DTO> findAll(){

        //Entity 전체 조회
        List<User1> user1Entities = repository.findAll();

        //DTO 리스트 변환
        /* 외부 반복자를 이용 => 성능 저하 ##비추천##
        List<User1DTO> user1DTOs = new ArrayList<>();
        for(User1 user1 : user1Entities){
            user1DTOs.add(user1.toDTO());
        }
        */
        //내부 반복자를 이용해 변환
        List<User1DTO> user1DTOs = user1Entities
                                    .stream()
                                    .map(entity -> entity.toDTO())
                                    .toList();

        return user1DTOs;
    }

    public User1DTO findById(String uid){
        //아이디 조회
        Optional<User1> optUser1 = repository.findById(uid);
        log.info("optUser1" + optUser1);
        
        /*
        * Optional 클래스
            - null 체크를 강제하기위한 클래스
            - 데이터 조회 결과를 쉽고 안전하게 처리하기위한 Wrapper 클래스
        */
        
        //Optional Entity Test (existence)
        if(optUser1.isPresent()){
            User1 user1 = optUser1.get();   //optional 에서 entity 를 가져오기(get)
            return user1.toDTO();
        }

        return null;
    }

    public void modify(User1DTO user1DTO){

        boolean exist = repository.existsById(user1DTO.getUid());

        //수정할 Entity가 존재하는 경우
        if(exist){
            User1 user1 = user1DTO.toEntity();
            repository.save(user1); //save : 존재하지 않으면 insert, 존재하면 update
        }

    }

    public void delete(String uid){
        repository.deleteById(uid);
    }

}
