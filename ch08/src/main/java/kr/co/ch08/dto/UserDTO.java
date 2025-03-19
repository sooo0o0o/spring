package kr.co.ch08.dto;

import kr.co.ch08.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {      //클라이언트가 생성한 데이터 : DTO
    private String uid;
    private String pass;
    private String name;
    private String birth;
    private String role;

    public User toEntity() {
        return User.builder()
                .uid(uid)
                .pass(pass)
                .name(name)
                .birth(birth)
                .role(role)
                .build();
    }

}
