package com.nobroker.controller;


import com.nobroker.payload.OwnerPlanDto;
import com.nobroker.service.OwnerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/owner-plans")
public class OwnerPlanController {

    //http://localhost:8080/api/owner-plans/subscribe/{userId}/{duration}

    @Autowired
    private OwnerPlanService ownerPlanService;

    @PostMapping("/subscribe/{userId}/{duration}")
    public OwnerPlanDto subscribeOwnerPlan(@PathVariable long userId, @PathVariable int duration){
        return ownerPlanService.subscribeOwnerPlan(userId,duration);
    }

    @GetMapping("/{ownerPlanId}")
    public OwnerPlanDto getOwnerPlan(@PathVariable long ownerPlanId){
        return ownerPlanService.getOwnerPlan(ownerPlanId);
    }
}
















