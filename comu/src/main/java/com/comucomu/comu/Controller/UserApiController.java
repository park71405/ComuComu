package com.comucomu.comu.Controller;

import com.comucomu.comu.DTO.AddUserRequest;
import com.comucomu.comu.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequest request){
        userService.save(request);

        return "redirect:/login";
    }


}
