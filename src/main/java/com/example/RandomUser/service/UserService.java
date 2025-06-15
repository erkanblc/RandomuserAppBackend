package com.example.RandomUser.service;

import com.example.RandomUser.dto.UserResponse;
import com.example.RandomUser.entity.User;
import com.example.RandomUser.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createNewUser(User user) {
        //System.out.println(user.toString());
        return userRepository.save(user);
    }

    public void deleteUser(Long deleteUserById){
        this.userRepository.deleteById(deleteUserById);
    }

    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public User updateUser(User user){
        return this.userRepository.save(user);
    }

    public Optional<User> searchWithUsurnameAndPassword(String userName , String password){

        return this.userRepository.findByUserNameAndPassword(userName,password);
    }

    public Optional<User> getAllUsersResponse(Long id) {
        return this.userRepository.findById(id);
    }
}
