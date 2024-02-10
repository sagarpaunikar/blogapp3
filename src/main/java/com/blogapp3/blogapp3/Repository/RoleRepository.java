package com.blogapp3.blogapp3.Repository;

import com.blogapp3.blogapp3.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles,Long> {
    Optional<Roles>findByName(String name);
}
