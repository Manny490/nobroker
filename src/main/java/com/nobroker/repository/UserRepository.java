package com.nobroker.repository;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail (String email);

    User getUserById(long userId);
}
