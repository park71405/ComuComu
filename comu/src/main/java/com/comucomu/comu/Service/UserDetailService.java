package com.comucomu.comu.Service;

import com.comucomu.comu.Repository.UserRepository;
import com.comucomu.comu.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 아이디로 사용자 정보 탐색
    @Override
    public User loadUserByUsername(String id){

        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 찾지 못함... " + id));
    }

}
