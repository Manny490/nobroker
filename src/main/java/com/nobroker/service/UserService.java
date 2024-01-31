package com.nobroker.service;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;

public interface UserService {
    public UserDto registerUser(UserDto userDto);
    public User getUserByEmail(String email);
    public void verifyEmail(User user);
    public boolean isEmailVerified(String email);

    User findByEmail(String email);

    void save(User user);
}
