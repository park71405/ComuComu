package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findById(String id); // id을 통해 사용자 정보 탐색
}
