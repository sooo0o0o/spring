package kr.co.ch07.service;

import kr.co.ch07.dto.User4DTO;
import kr.co.ch07.entity.User4;
import kr.co.ch07.repository.User4Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class User4Service {
    private final User4Repository repository;

    public void register(User4DTO user4DTO){
        User4 user4 = user4DTO.toEntity();

        repository.save(user4);
    }

    public List<User4DTO> findAll(){
        List<User4> user4Entities = repository.findAll();
        List<User4DTO> user4DTOS = user4Entities
                .stream()
                .map(entity -> entity.toDTO())
                .toList();

        return user4DTOS;
    }

    public User4DTO findById(String uid){
        Optional<User4> optUser4 = repository.findById(uid);

        if(optUser4.isPresent()){
            User4 user4 = optUser4.get();
            return user4.toDTO();
        }

        return null;

    }

    public void modify(User4DTO user4DTO){
        boolean exist = repository.existsById(user4DTO.getUid());
        if(exist){
            User4 user4 = repository.findById(user4DTO.getUid()).get();
            repository.save(user4);
        }
    }

    public void delete(String uid){
        repository.deleteById(uid);
    }





}
