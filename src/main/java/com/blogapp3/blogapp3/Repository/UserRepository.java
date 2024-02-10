package com.blogapp3.blogapp3.Repository;

import com.blogapp3.blogapp3.entities.User;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users where username=:usernameOrEmail or email=:usernameOrEmail",nativeQuery = true)
    User findByUsernameOrEmail(String usernameOrEmail);

}
