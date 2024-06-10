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

    // 전달받은 유저 정보로 유저 생성
    public String save(AddUserRequest addUserRequest){
        return userRepository.save(User.builder()
                .id(addUserRequest.getId())
                .password(bCryptPasswordEncoder.encode(addUserRequest.getPassword()))
                .nickname(addUserRequest.getNickname())
                .email(addUserRequest.getEmail())
                .build()).getId();
    }

    // 전달받은 유저 ID로 유저 검색
    public User findById(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }



}
