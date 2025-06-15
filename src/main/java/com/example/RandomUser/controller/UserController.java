package com.example.RandomUser.controller;

import com.example.RandomUser.dto.UserResponse;
import com.example.RandomUser.entity.User;
import com.example.RandomUser.entity.UserProfile;
import com.example.RandomUser.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserResponse> getAllUsers(){
        List<User> userList = this.userService.getAllUsers();
        return userList.stream().map(p -> new UserResponse(p)).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public UserResponse getAllUsersResponse(@PathVariable Long userId){
        Optional<User> optionalUser = this.userService.getAllUsersResponse(userId);
        if (optionalUser.isPresent()){
            return new UserResponse(optionalUser.get());
        }else {
            return null;
        }

    }

    @PostMapping
    public User createNewUser(@RequestBody User user){
        System.out.println(user.toString());
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());

        UserProfile profile = new UserProfile();
        profile.setFirstName(user.getProfile().getFirstName());
        profile.setLastName(user.getProfile().getLastName());
        profile.setGender(user.getProfile().getGender());
        profile.setCountry(user.getProfile().getCountry());
        profile.setCity(user.getProfile().getCity());
        profile.setPhoto(user.getProfile().getPhoto());

        profile.setUser(newUser);        // ilişkiyi profil tarafında kur
        newUser.setProfile(profile);

        return userService.createNewUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userIdx){
        this.userService.deleteUser(userIdx);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {

        Optional<User> optionalUser = userService.searchWithUsurnameAndPassword(updatedUser.getUserName(), updatedUser.getPassword());
        System.out.println(optionalUser.get().toString());

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(updatedUser.getUserName());
            user.setPassword(updatedUser.getPassword());

            if (user.getProfile() != null && updatedUser.getProfile() != null) {
                UserProfile profile = user.getProfile();
                UserProfile updatedProfile = updatedUser.getProfile();

                if (updatedProfile.getCity() != null) profile.setCity(updatedProfile.getCity());
                if (updatedProfile.getCountry() != null) profile.setCountry(updatedProfile.getCountry());
                if (updatedProfile.getFirstName() != null) profile.setFirstName(updatedProfile.getFirstName());
                if (updatedProfile.getLastName() != null) profile.setLastName(updatedProfile.getLastName());
                if (updatedProfile.getGender() != null) profile.setGender(updatedProfile.getGender());
                if (updatedProfile.getPhoto() != null) profile.setPhoto(updatedProfile.getPhoto());

                profile.setUser(user);     // ilişkiyi koru
                user.setProfile(profile);  // güncellenmiş profili kullanıcıya geri bağla
            }

            return userService.updateUser(user); // ← burada user olmalı
        } else {
            System.out.println("Kullanici Bulunamadi " + id);
            return null;
        }
    }
}
