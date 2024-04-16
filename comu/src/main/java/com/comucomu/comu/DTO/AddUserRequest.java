package com.comucomu.comu.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String id;
    private String password;
    private String nickname;
    private String email;

}
