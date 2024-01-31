package com.nobroker.controller;

import com.nobroker.payload.UserDto;
import com.nobroker.service.EmailService;
import com.nobroker.service.EmailVerificationService;
import com.nobroker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/register")
    public Map<String,String> registerUser(@RequestBody UserDto userDto){
        UserDto dto = userService.registerUser(userDto);
       return emailService.sendOtpEmail(userDto.getEmail());

    }

    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestParam String email,@RequestParam String otp){
        return emailVerificationService.verifyOtp(email,otp);
    }

}

