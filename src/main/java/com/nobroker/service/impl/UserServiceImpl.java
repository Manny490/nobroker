package com.nobroker.service.impl;

import com.nobroker.entity.User;
import com.nobroker.payload.UserDto;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user=new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setPassword(userDto.getPassword());
        user.setEmailVerified(userDto.isEmailVerified());

        User dto = userRepository.save(user);
        UserDto savedUserDto = new UserDto();
        BeanUtils.copyProperties(dto, savedUserDto);

        return savedUserDto;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    @Override
    public boolean isEmailVerified(String email) {

        User byEmail = userRepository.findByEmail(email);
        return byEmail !=null && byEmail.isEmailVerified();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    User mapToEntity (UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    UserDto mapToDto(User user){
       return modelMapper.map(user,UserDto.class);
    }


}
