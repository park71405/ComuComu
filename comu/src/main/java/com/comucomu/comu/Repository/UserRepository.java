package com.comucomu.comu.Repository;

import com.comucomu.comu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
