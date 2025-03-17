package kr.co.ch07.service;

import kr.co.ch07.dto.User2DTO;
import kr.co.ch07.entity.User2;
import kr.co.ch07.repository.User2Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class User2Service {

    private final User2Repository repository;

    public void register(User2DTO user2DTO) {
        User2 user2 = user2DTO.toEntity();

        repository.save(user2);
    }
    public List<User2DTO> findAll(){
        List<User2> user2Entities = repository.findAll();
        List<User2DTO> user2DTOs = user2Entities
                                    .stream()
                                    .map(entity -> entity.toDTO())
                                    .toList();
        return user2DTOs;
    }
    public User2DTO findById(String uid){
        Optional<User2> optUser2 = repository.findById(uid);
        if(optUser2.isPresent()){
            User2 user2 = optUser2.get();
            return user2.toDTO();
        }
        return null;
    }
    public void modify(User2DTO user2DTO) {
        boolean exists = repository.existsById(user2DTO.getUid());

        if(exists){
            User2 user2 = user2DTO.toEntity();
            repository.save(user2);
        }
    }
    public void delete(String uid){
        repository.deleteById(uid);
    }


}
