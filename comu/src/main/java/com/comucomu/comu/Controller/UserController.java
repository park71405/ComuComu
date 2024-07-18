package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.Join.AddUserRequest;
import com.comucomu.comu.DTO.Login.LoginUserRequest;
import com.comucomu.comu.DTO.Login.LoginUserResponse;
import com.comucomu.comu.Service.UserService;
import com.comucomu.comu.config.TokenProvider;
import com.comucomu.comu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> postLogin(@RequestBody LoginUserRequest loginUserRequest){

        //전달받은 유저가 실제 DB에 존재하는 유저인지 확인
        User user = userService.findById(loginUserRequest.getId());

        //user의 비번과 전달받은 비번이 같은 경우 로그인 성공
        if(bCryptPasswordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())){

            //acess token 생성
            String token = tokenProvider.generateToken(user, Duration.ofHours(1));

            LoginUserResponse loginUserResponse = new LoginUserResponse(user, token);

            // 토큰 전달
            return ResponseEntity.status(HttpStatus.OK)
                    .body(loginUserResponse);
        }
        //다른 경우 로그인 실패
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }

    }

    // 회원가입
    @PostMapping("/user")
    public String signup(@RequestBody AddUserRequest request){
        userService.save(request);

        return "redirect:/login";
    }
    

}
