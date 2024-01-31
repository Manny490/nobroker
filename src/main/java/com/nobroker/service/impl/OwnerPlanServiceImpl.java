package com.nobroker.service.impl;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.entity.User;
import com.nobroker.payload.OwnerPlanDto;
import com.nobroker.payload.UserDto;
import com.nobroker.repository.OwnerPlanRepository;
import com.nobroker.repository.UserRepository;
import com.nobroker.service.OwnerPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OwnerPlanServiceImpl  implements OwnerPlanService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OwnerPlanRepository ownerPlanRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public OwnerPlanDto subscribeOwnerPlan(long userId, int duration) {
        // Fetch the user from the UserRepository based on userId
        User user = userRepository.findById(userId).get(); // Assuming userRepository is autowired
        OwnerPlan save=null;
        if (user != null) {
            OwnerPlan  ownerPlan = new OwnerPlan();
            ownerPlan.setUserId(userId);
            ownerPlan.setDuration(duration);
            ownerPlan.setNumberOfDays(duration);
            ownerPlan.setSubscriptionActive(true);
            ownerPlan.setSubscriptionActiveDate(LocalDate.now());
            ownerPlan.setSubscriptionExpirationDate(LocalDate.now().plusDays(duration));
             save = ownerPlanRepository.save(ownerPlan);
            // Handle the case when user is not found
        }
        OwnerPlanDto ownerPlanDto = mapToDto(save);

        return ownerPlanDto;
    }

    @Override
    public OwnerPlanDto getOwnerPlan(long ownerPlanId) {
        OwnerPlan ownerPlan = ownerPlanRepository.findById(ownerPlanId).get();
        OwnerPlanDto ownerPlanDto = mapToDto(ownerPlan);
        return ownerPlanDto;
    }
    OwnerPlan mapToEntity (OwnerPlanDto ownerPlanDto){
        return modelMapper.map(ownerPlanDto,OwnerPlan.class);
    }

    OwnerPlanDto mapToDto(OwnerPlan ownerPlan){
        return modelMapper.map(ownerPlan,OwnerPlanDto.class);
    }
}
