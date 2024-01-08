package com.nobroker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="owner_Plans")
public class OwnerPlan {
    @Id
    private long planId;
    @Column(name="plan_Name" , unique = true)
    private String planName;
    @Column(name="price" )
    private double price;
    @Column(name="plan_Validity" )
    private int planValidity;
    @Column(name="relation_Manager" )
    private boolean relationManager;
    @Column(name="rental_Agreement" )
    private boolean rentalAgreement;
    @Column(name="property_Promotion" )
    private boolean propertyPromotion;
    @Column(name="guaranteed_Tenants" )
    private boolean guaranteedTenants;
    @Column(name="showing_Property" )
    private  boolean showingProperty;
    @Column(name="facebook_Marketing" )
    private  boolean facebookMarketing;


}
