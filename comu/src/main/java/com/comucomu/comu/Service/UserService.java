package com.comucomu.comu.Service;

import com.comucomu.comu.DTO.AddUserRequest;
import com.comucomu.comu.Repository.UserRepository;
import com.comucomu.comu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String save(AddUserRequest addUserRequest){
        return userRepository.save(User.builder()
                .id(addUserRequest.getId())
                .password(bCryptPasswordEncoder.encode(addUserRequest.getPassword()))
                .nickname(addUserRequest.getNickname())
                .email(addUserRequest.getEmail())
                .build()).getId();
    }



}
