package aces.ark_encoded_listener.unsubscribe;

import aces.ark_encoded_listener.error_handler.NotFoundException;
import aces.ark_encoded_listener.subscription.SubscriptionEntity;
import aces.ark_encoded_listener.subscription.SubscriptionRepository;
import aces.ark_encoded_listener.subscription.SubscriptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class UnsubscribeController {

    private final SubscriptionRepository subscriptionRepository;
    private final UnsubscribeRepository unsubscribeRepository;

    @PostMapping("/subscription/{subscriptionId}/unsubscribes")
    public Unsubscribe postUnsubscribe(@PathVariable Long subscriptionId) {
        SubscriptionEntity subscriptionEntity = subscriptionRepository.findOne(subscriptionId);
        if (subscriptionEntity == null) {
            throw new NotFoundException("SubscriptionNotFound", "Subscription not found.");
        }

        UnsubscribeEntity unsubscribeEntity = new UnsubscribeEntity();
        unsubscribeEntity.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        unsubscribeRepository.save(unsubscribeEntity);

        subscriptionEntity.setStatus(SubscriptionStatus.unsubscribed);
        subscriptionRepository.save(subscriptionEntity);

        Unsubscribe unsubscribe = new Unsubscribe();
        unsubscribe.setId(unsubscribeEntity.getId());
        unsubscribe.setCreatedAt(unsubscribeEntity.getCreatedAt().toString());

        return unsubscribe;
    }

}
