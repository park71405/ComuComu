package com.comucomu.comu.DTO.Login;

import com.comucomu.comu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginUserResponse {

    private String id;
    private String nickname;
    private String email;
    private String roleName;
    private String token;

    public LoginUserResponse(User user, String token){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.roleName = user.getRole().getRoleName();
        this.token = token;
    }

}
