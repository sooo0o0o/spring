package kr.co.ch06.dao;

import kr.co.ch06.dto.User2DTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface User2Mapper {

    public void insertUser2(User2DTO user2DTO);
    public User2DTO selectUser2(String uid);
    public List<User2DTO> selectAllUser2();
    public void updateUser2(User2DTO user2DTO);
    public void deleteUser2(String uid);

}
