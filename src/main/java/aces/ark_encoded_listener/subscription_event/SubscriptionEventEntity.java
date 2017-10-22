package aces.ark_encoded_listener.subscription_event;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class SubscriptionEventEntity {

    @Id
    @GeneratedValue
    private Long id;


}
