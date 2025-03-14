package kr.co.ch06.service;

import kr.co.ch06.dao.User3Mapper;
import kr.co.ch06.dto.User3DTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class User3Service {

    private final User3Mapper user3Mapper;

    public void register(User3DTO user3DTO) {
        user3Mapper.insertUser3(user3DTO);
    }
    public List<User3DTO> findAll() {
        return user3Mapper.selectAllUser3();
    }
    public User3DTO findById(String uid) {
        return user3Mapper.selectUser3(uid);
    }
    public void modify(User3DTO user3DTO) {
        user3Mapper.updateUser3(user3DTO);
    }
    public void delete(String uid) {
        user3Mapper.deleteUser3(uid);
    }

}
