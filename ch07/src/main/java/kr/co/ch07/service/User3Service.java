package kr.co.ch07.service;

import kr.co.ch07.dto.User3DTO;
import kr.co.ch07.entity.User3;
import kr.co.ch07.repository.User3Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class User3Service {

    private final User3Repository repository;

    public void register(User3DTO user3DTO){
        log.info("user3DTO" + user3DTO);

        User3 user3 = user3DTO.toEntity();
        repository.save(user3);
    }

    public List<User3DTO> findAll(){
        List<User3> user3Entities = repository.findAll();

        List<User3DTO> user3DTOS = user3Entities
                                        .stream()
                                        .map(entity -> entity.toDTO())
                                        .toList();

        return user3DTOS;
    }

    public User3DTO findById(String uid){
        Optional<User3> optUser3 = repository.findById(uid);

        if(optUser3.isPresent()){
            User3 user3 = optUser3.get();
            log.info("user3" + user3);
            return user3.toDTO();
        }

        return null;
    }

    public void modify(User3DTO user3DTO){
        boolean exists = repository.existsById(user3DTO.getUid());

        if(exists){
            User3 user3 = user3DTO.toEntity();
            repository.save(user3);
        }

    }

    public void delete(String uid){
        repository.deleteById(uid);
    }


}
