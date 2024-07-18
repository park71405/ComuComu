package com.comucomu.comu.DTO.Login;

import com.comucomu.comu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginUserRequest {

    private String id;
    private String password;

    public User toEntity(){
        return User.builder()
                .id(id)
                .password(password)
                .build();
    }

}
