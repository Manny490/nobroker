package com.nobroker.payload;



import com.nobroker.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerPlanDto {

    private long ownerPlanId;

    private long userId;


    private boolean subscriptionActive;


    private LocalDate subscriptionActiveDate;


    private LocalDate subscriptionExpirationDate;

    private int numberOfDays;


}

