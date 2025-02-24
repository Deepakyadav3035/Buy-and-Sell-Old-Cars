package com.hms.controller.Auth;

import com.hms.entity.AppUser;
import com.hms.payload.LoginDto;
import com.hms.payload.TokenDto;
import com.hms.service.OtpService;
import com.hms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hms/user")
public class AuthController {
    private UserService userService;
    private OtpService otpService;

    public AuthController(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/Manager-signup")
    public ResponseEntity<?>addUser(
        @RequestBody AppUser user
){
        ResponseEntity<?> OpUser = userService.createUser(user);
        return OpUser;
    }

    @PostMapping("/Sells_manager-signup")
    public ResponseEntity<?>adUser(
            @RequestBody AppUser user
    ){
        ResponseEntity<?> OpUser = userService.creatUser(user);
        return OpUser;
    }

    @PostMapping("/signin")
    public Object verifyLogin(
            @RequestBody LoginDto dto
            ){
        String token = userService.verifyLogin(dto);
        if(token!=null){
            TokenDto tokenDto = new TokenDto();
            tokenDto.setToken(token);
            tokenDto.setTokentype("JWT");
            return tokenDto;
        }
        return new ResponseEntity<>("Invalid username/password", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login-otp")
    public String generateOtp(
            @RequestParam String mobile){
        Optional<AppUser> byMobile = userService.findByMobile(mobile);
        if(byMobile.isPresent()) {
            String otp = otpService.generateOtp(mobile);
            return otp + "  " + mobile;
        }
        return "User not found";
    }

    @PostMapping("/validate-otp")
    public String validateOtp(
            @RequestParam String mobile,
            @RequestParam String otp
    ){
        boolean status = otpService.validateOtp(mobile, otp);
        if(status){
            String gettoken = userService.gettoken(mobile);
            return gettoken;
        }
        return status? "OTP validated successfully" : "Invalid OTP";
    }
}
