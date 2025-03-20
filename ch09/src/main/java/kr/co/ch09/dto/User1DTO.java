package kr.co.ch09.dto;

import jakarta.validation.constraints.*;
import kr.co.ch09.entity.User1;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User1DTO {
    
    @NotBlank // = null, "", "공백문자열" 을 허용하지 X
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "영어 소문자, 숫자로 4자리 ~ 10자리 입력")
    private String uid;

    @NotEmpty // = null, "" 을 허용하지 X
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "한글 이름 4자리 ~ 10자리 입력")
    private String name;

    @NotNull
    private String hp;

    @Min(1) @Max(100)
    private String age;

    @Email
    private String email;

    public User1 toEntity(){
        return User1.builder()
                .uid(uid)
                .name(name)
                .hp(hp)
                .age(age)
                .build();
    }



}
