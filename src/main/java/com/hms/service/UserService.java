package com.hms.service;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private AppUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;

    public UserService(AppUserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> createUser(AppUser user) {
        Optional<AppUser> byUsername = userRepository.findByUsername(user.getUsername());
        if(byUsername.isPresent()){
            return new ResponseEntity<>("username exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> byEmail = userRepository.findByEmail(user.getEmail());
        if(byEmail.isPresent()){
            return new ResponseEntity<>("email exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRole("ROLE_Manager");
        AppUser userSaved = userRepository.save(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }


    public ResponseEntity<?> creatUser(AppUser user) {
        Optional<AppUser> byUsername = userRepository.findByUsername(user.getUsername());
        if(byUsername.isPresent()){
            return new ResponseEntity<>("username exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<AppUser> byEmail = userRepository.findByEmail(user.getEmail());
        if(byEmail.isPresent()){
            return new ResponseEntity<>("email exist",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setRole("ROLE_SellsManager");
        AppUser userSaved = userRepository.save(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    public String verifyLogin(LoginDto dto) {
        Optional<AppUser> byUsername = userRepository.findByUsername(dto.getUsername());
        if(byUsername.isPresent()){
            AppUser user = byUsername.get();
            if(BCrypt.checkpw(dto.getPassword(), user.getPassword())){
                String token = jwtService.generateToken(user.getUsername());
                return token;
            }

        }
        return null;
    }

    public Optional<AppUser> findByMobile(String mobile) {
        Optional<AppUser> byMobile = userRepository.findByMobile(mobile);
        return byMobile;
    }

    public String gettoken(String mobile) {
        Optional<AppUser> byMobile = userRepository.findByMobile(mobile);
        if(byMobile.isPresent()){
            AppUser user = byMobile.get();
            String token = jwtService.generateToken(user.getUsername());
            return token;
        }
        return null;
    }
}
