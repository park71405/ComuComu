package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = {"role"})
    Optional<User> findById(String userId);

}
