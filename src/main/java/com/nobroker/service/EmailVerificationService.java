package com.nobroker.service;

import com.nobroker.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class EmailVerificationService {

    static final Map<String,String> emailOtpMapping=new HashMap<>();
    @Autowired
      private JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private String lastEmail;

    public Map<String,String>verifyOtp(String email,String otp){
        String storedOtp = emailOtpMapping.get(email);

        Map<String,String> response =new HashMap<>();
        if(storedOtp!=null && storedOtp.equals(otp)){

            User user=userService.getUserByEmail(email);
            if ( user!=null){
                userService.verifyEmail(user);
                emailOtpMapping.remove(email);
                response.put("status","success");
                response.put("message","Email verified successfully");
            }else{
                response.put("status","error");
                response.put("message"," User not Found");
            }

        }else {
            response.put("status","error");
            response.put("message"," Invalid OTP");
        }
        return response;
    }

    public Map<String, String> sendOtpForLogin(String email) {
        if(userService.isEmailVerified(email)) {
            String otp = emailService.generateOtp();
            emailOtpMapping.put(email, otp);

            sendEmail(email, "Your Otp is :" +otp);
            lastEmail=email;

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP sent successfully");
            return  response;
        }else{
            Map<String,String> response=new HashMap<>();
            response.put("Status","error");
            response.put("message", "Email is not verified");
            return  response;
        }
    }

    private void sendEmail(String to, String text){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("your.gmail@gmail.com");
        message.setTo(to);
        message.setSubject("OTP for login verification");
        message.setText(text);
        javaMailSender.send(message);
    }

    public Map<String, String> verifyOtpForLogin(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response =new HashMap<>();
        if(storedOtp != null && storedOtp.equals(otp) ){
            emailOtpMapping.remove(email);
            response.put("Status","success");
            response.put("message","OTP is verified successfully");
            return response;
        }else{
            response.put("Status","error");
            response.put("message","Invalid OTP");
            return response;
        }
    }
    @Scheduled(fixedRate = 2*60*1000)
    public void otpExpired(){
        emailOtpMapping.remove(lastEmail);
    }

}

