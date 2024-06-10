package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.CreateAccessTokenResponse;
import com.comucomu.comu.DTO.LoginUserRequest;
import com.comucomu.comu.Service.BoardService;
import com.comucomu.comu.Service.UserService;
import com.comucomu.comu.config.TokenProvider;
import com.comucomu.comu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
public class UserController {

    @Autowired
    private TokenProvider tokenProvider;

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/login")
    public ResponseEntity<CreateAccessTokenResponse> postLogin(@RequestBody LoginUserRequest loginUserRequest){

        //전달받은 유저가 실제 DB에 존재하는 유저인지 확인
        User user = userService.findById(loginUserRequest.getId());

        //user의 비번과 전달받은 비번이 같은 경우 로그인 성공
        if(user.getPassword().equals(loginUserRequest.getPassword())){
            //acess token 생성
            tokenProvider.generateToken(user, Duration.ofHours(1));
        }
        //다른 경우 로그인 실패
        else{
            
        }

        return null;
    }

    

}
