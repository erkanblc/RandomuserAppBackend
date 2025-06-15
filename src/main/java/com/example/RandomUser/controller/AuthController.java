package com.example.RandomUser.controller;

import com.example.RandomUser.dto.AuthResponse;
import com.example.RandomUser.entity.User;
import com.example.RandomUser.repository.UserRepository;
import com.example.RandomUser.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        AuthResponse authResponse = new AuthResponse();
        if (userRepository.findByUserName(user.getUserName()) != null) {
            authResponse.setMessage("Kullanıcı zaten var");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getProfile() != null) {
            user.getProfile().setUser(user); // !!! Önemli
        }

        userRepository.save(user);

        authResponse.setMessage("Kayıt başarılı");
        authResponse.setUserId(user.getId());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) {

        User dbUser = userRepository.findByUserName(user.getUserName());
        System.out.println(user);
        System.out.println(dbUser);
        AuthResponse authResponse = new AuthResponse();

        if (dbUser == null || !passwordEncoder.matches(user.getPassword(),dbUser.getPassword())) {
            authResponse.setMessage("Geçersiz kullanıcı adı veya şifre");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }

        String token = jwtTokenProvider.generateToken(user.getUserName());
        authResponse.setMessage("Bearer " + token);
        authResponse.setUserId(dbUser.getId());
        System.out.println(authResponse.toString());
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
