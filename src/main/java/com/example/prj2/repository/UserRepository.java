package com.example.prj2.repository;

import com.example.prj2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByIdAndDeletedAtIsNull(Long id);
    List<User> findAllByDeletedAtIsNull();

    User findByUsername(String username);

    boolean existsByEmail(String email);
}
