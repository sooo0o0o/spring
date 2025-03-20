package kr.co.ch09.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import kr.co.ch09.entity.User2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User2DTO {

    @NotBlank // = null, "", "공백문자열" 을 허용하지 X
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "영어 소문자, 숫자로 4자리 ~ 10자리 입력")
    private String uid;

    @NotEmpty // = null, "" 을 허용하지 X
    @Pattern(regexp = "^[가-힣]{2,10}$", message = "한글 이름 4자리 ~ 10자리 입력")
    private String name;

    @NotNull
    private String birth;

    @NotNull
    private String addr;

    public User2 toEntity(){
        return User2.builder()
                .uid(uid)
                .name(name)
                .birth(birth)
                .addr(addr)
                .build();
    }

}
