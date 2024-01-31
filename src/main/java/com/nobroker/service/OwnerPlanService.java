package com.nobroker.service;

import com.nobroker.entity.OwnerPlan;
import com.nobroker.payload.OwnerPlanDto;
import com.nobroker.repository.OwnerPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


public interface OwnerPlanService {





     OwnerPlanDto subscribeOwnerPlan(long userId, int duration);


     OwnerPlanDto getOwnerPlan(long ownerPlanId);


}
