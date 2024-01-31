package com.nobroker.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private  long id;
    private String name;
    private String email;
    private String password;
    private String mobile;
    private boolean emailVerified;


}
