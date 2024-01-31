package com.nobroker.repository;

import com.nobroker.entity.OwnerPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OwnerPlanRepository extends JpaRepository<OwnerPlan,Long> {
    List<OwnerPlan> findBySubscriptionActiveTrueAndSubscriptionExpirationDateBefore(LocalDate date);

    List<OwnerPlan>findBySubscriptionExpirationDateBefore(LocalDate date);
}
