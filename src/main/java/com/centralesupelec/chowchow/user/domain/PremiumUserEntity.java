package com.centralesupelec.chowchow.user.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM_USER")
public class PremiumUserEntity extends UserEntity {

    @Column
    private SubscriptionType subscriptionType;

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
