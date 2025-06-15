package com.example.RandomUser.repository;

import com.example.RandomUser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserNameAndPassword(String userName, String password);
    User findByUserName(String userName);
}
