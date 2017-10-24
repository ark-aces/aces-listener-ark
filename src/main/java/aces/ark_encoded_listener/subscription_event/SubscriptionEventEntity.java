package aces.ark_encoded_listener.subscription_event;

import aces.ark_encoded_listener.event.EventEntity;
import aces.ark_encoded_listener.subscription.SubscriptionEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "subscription_events")
public class SubscriptionEventEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    SubscriptionEntity subscriptionEntity;

    @OneToOne
    EventEntity eventEntity;

    ZonedDateTime createdAt;
}
